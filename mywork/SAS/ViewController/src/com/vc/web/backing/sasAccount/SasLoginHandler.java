package com.vc.web.backing.sasAccount;

import com.vc.web.beans.SasSessionLogin;
import com.vc.web.ejb.sas.SASFacadeLocal;
import com.vc.web.ejb.sas.entities.Accounts;
import com.vc.web.util.EJBUtil;
import com.vc.web.util.FacesUtil;
import com.vc.web.util.ViewUtil;

public class SasLoginHandler {

    private static String SAS_INVALID_USERNAME_OR_PASSWORD =
        "Invalid User name password";
    private String userName;
    private String password;

    public SasLoginHandler() {
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

    public String loginAction() {      
        SASFacadeLocal sasFacade;
        Accounts account = null;
        try {
            sasFacade = EJBUtil.lockupSASFacade();
            account =
                    sasFacade.queryAccountsFindByUserNamePassowrd(this.userName,
                                                                  this.password);
        } catch (Exception e) {
            FacesUtil.addError("Login Error", e.getMessage());
            return null;
        }
        if (account == null) {
            FacesUtil.addError("Login Error",
                               SAS_INVALID_USERNAME_OR_PASSWORD);
            return null;
        }   
        SasSessionLogin sas= ViewUtil.getSasSessionLogin();
        sas.setAccId(account.getAccId());
        sas.setUserName(this.userName);
        sas.setPassword(this.password);
        sas.setValid(true);
        return "shoppingSas";
    }
}
