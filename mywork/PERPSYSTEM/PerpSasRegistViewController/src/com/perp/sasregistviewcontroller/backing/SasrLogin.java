package com.perp.sasregistviewcontroller.backing;


import com.oracle.adffwkextensions.faces.FacesUtils;

import com.oracle.adffwkextensions.utils.DateUtils;

import com.perp.beans.LoginHistoryBean;
import com.perp.beans.PerpSasrAccount;
import com.perp.beans.PerpSasrAccountChecking;
import com.perp.beans.SasrSessionLoginObject;
import com.perp.sasregistmodel.common.PerpSasRegistAppModule;

import com.perp.sasregistviewcontroller.utils.SasrFacesUtils;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

import oracle.jbo.JboException;

import oracle.jbo.domain.Number;

public class SasrLogin {

    private Number lhiId;

    public SasrLogin() {
    }

    private String getOS(String userAgent) {
        String os = "";
        if (userAgent.toLowerCase().indexOf("windows") >= 0) {
            os = "Windows";
        } else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
            os = "Mac";
        } else if (userAgent.toLowerCase().indexOf("X11") >= 0) {
            os = "Unix";
        }
        return os;
    }

    public String perpSasrLoginAction() {

        PerpSasrAccountChecking checking =
            (PerpSasrAccountChecking)FacesUtils.resolveExpression("#{bindings.doPerpSasrAccountChecking.result}");
        String userName =
            (String)FacesUtils.resolveExpression("#{bindings.doPerpSasrAccountChecking_userName}");
        if (checking == null || !checking.isUserFound()) {
            FacesUtils.addError("Login", "User not found");
            return null;
        } else if (!checking.isPasswordValid()) {
            FacesUtils.addError("Login", "Invalid password");
            return null;
        }
        PerpSasrAccount account = checking.getPerpSasrAccount();
        //L?u v?t ??ng nh?p
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request =
                (HttpServletRequest)context.getExternalContext().getRequest();
            String agent = request.getHeader("User-Agent");
            String useros = getOS(agent);

            LoginHistoryBean bean = new LoginHistoryBean();

            bean.setAccId(account != null ? account.getAccId() : null);
            bean.setUserName(userName);
            bean.setLoginAt(DateUtils.getOracleSysdate());
            bean.setIpAddress(request.getRemoteAddr());
            bean.setModule("sasr");
            bean.setPlatform(useros);
            bean.setUserAgent(agent);
            bean.setCountry("Viet Nam");
            bean.setStatus("Y");
            //bean.setAttribute1("");
            //bean.setAttribute2("");
            //bean.setAttribute3("");
            //bean.setAttribute4("");
            //bean.setAttribute5("");
            PerpSasRegistAppModule module =
                SasrFacesUtils.getPerpSasRegistAppModule();
            this.lhiId = module.writeLoginHistory(bean);
            checking.setLoginHistoy(bean);
        } catch (JboException ex) {
            System.out.print(ex.getMessage());
        }

        SasrSessionLoginObject sasrObject =
            SasrFacesUtils.getSasrSessionLoginObject();
        sasrObject.setPerpSasrAccountChecking(checking);

        if (!checking.isAccountActivated()) {
            // Chuyen sang trang active
            return "active";
        }
        // Dang nhap thanh cong (Chuyen den trang mua hang)
        return "success";
    }

    public void setLhiId(Number lhiId) {
        this.lhiId = lhiId;
    }

    public Number getLhiId() {
        return lhiId;
    }
}
