package com.vc.web.backing.appView;

import com.vc.web.PerpSystemInfo;
import com.vc.web.beans.AppUserInfo;
import com.vc.web.beans.PerpSessionLogin;
import com.vc.web.ejb.perp.DynamicPERPFacadeLocal;
import com.vc.web.ejb.perp.entities.AppUsers;
import com.vc.web.ejb.sas.SASFacadeLocal;
import com.vc.web.ejb.sas.entities.ServicePackages;
import com.vc.web.ejb.sas.entities.Subscriptions;
import com.vc.web.util.EJBUtil;
import com.vc.web.util.FacesUtil;
import com.vc.web.util.ViewUtil;

import javax.faces.event.ActionEvent;

public class PerpLoginHandler {

    private String userName;
    private String password;
    private String activatedCode;
    private boolean valid;

    public PerpLoginHandler() {
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String perpLoginAction() {
        if (this.valid) {
            return "appView";
        }
        return null;
    }

    public void perpLoginListener(ActionEvent actionEvent) {      
        if (this.userName == null || this.password == null ||
            this.activatedCode == null) {
            this.valid = false;         
            return;
        }
      
        SASFacadeLocal sasFacade;
        try {
            sasFacade = EJBUtil.lockupSASFacade();
            Subscriptions sub =
                sasFacade.querySubscriptionsFindByActivatedCode(this.activatedCode);
            if (sub == null) {
                FacesUtil.addError("Login Error", "Activated Code not found");
                this.valid = false;
                return;
            }
            PerpSystemInfo info = sasFacade.getPerpSystemInfo(sub.getSubId());
            if (info == null) {
                FacesUtil.addError("Login Error", "No environments perp");
                this.valid = false;
                return;
            }
            
            DynamicPERPFacadeLocal perpFacade =
                EJBUtil.lockupDynamicPERPFacade();
            AppUsers aus =
                perpFacade.queryAppUsersFindByUserNamePassword(info, this.userName,
                                                               this.password);
            if (aus == null) {
                FacesUtil.addError("Login Error", "User not found");
                this.valid = false;
                return;
            }
            PerpSystemInfo psi = sasFacade.getPerpSystemInfo(sub.getSubId());
            ServicePackages spa =
                sasFacade.queryServicePackagesFindBySpaId(sub.getServicePackages().getSpaId());
            PerpSessionLogin sessionLogin = ViewUtil.getPerpSessionLogin();

            AppUserInfo userInfo = sessionLogin.getAppUserInfo();
            userInfo.setPerpSystemInfo(psi);
            userInfo.setAusId(aus.getAusId());
            userInfo.setPassword(this.password);
            userInfo.setEncrytPassword(aus.getPassword());
            userInfo.setUserName(aus.getUserName());
            userInfo.setLogin(true);
            userInfo.setSubId(sub.getSubId());
            userInfo.setStartDate(sub.getStartDate());
            userInfo.setSubscribeDate(sub.getSubscribeDate());
            userInfo.setEndDate(sub.getEndDate());
            userInfo.setActivatedCode(sub.getActivatedCode());
            userInfo.setPackageCode(spa.getPackageCode());
            sessionLogin.setAppUserInfo(userInfo);
            sessionLogin.setValid(true);
            this.valid = true;          
        } catch (Exception e) {
            FacesUtil.addError("Login Error", e.getMessage());
            this.valid = false;
        }
    }

    public void setActivatedCode(String activatedCode) {
        this.activatedCode = activatedCode;
    }

    public String getActivatedCode() {
        return activatedCode;
    }
}
