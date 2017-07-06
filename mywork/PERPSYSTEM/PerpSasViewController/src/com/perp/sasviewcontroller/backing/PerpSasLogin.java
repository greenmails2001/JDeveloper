package com.perp.sasviewcontroller.backing;


import com.perp.sasmodel.dynamicjdbc.PerpSasLoginHelper;

import com.oracle.adffwkextensions.faces.FacesUtils;

import com.oracle.adffwkextensions.utils.DateUtils;

import com.perp.beans.PerpAccountChecking;
import com.perp.beans.PerpEnvChecking;
import com.perp.beans.SessionLoginObject;
import com.perp.sasviewcontroller.util.PerpSasViewUtils;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.view.rich.component.rich.RichPopup;

import com.perp.sasmodel.PerpSasAppModuleImpl;

import java.io.UnsupportedEncodingException;

import javax.faces.component.UIComponent;

import nl.captcha.Captcha;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.model.binding.DCDataControl;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.domain.Date;

public class PerpSasLogin {
    private RichPopup aboutSubscriptionPopup;

    public PerpSasLogin() {
    }

    /**
     * Kiem tra Subscriptions .
     * @return
     */
    public String doPerpEnvCheckingAction() {
        Object obj =
            FacesUtils.resolveExpression("#{bindings.doPerpEnvChecking.result}");
        PerpEnvChecking envChecking = (PerpEnvChecking)obj;
        SessionLoginObject loginObject =
            PerpSasViewUtils.getSessionLoginObject();

        if (envChecking.isEnvFound()) {
            System.out.println(envChecking.toString());
            HttpSession session = FacesUtils.getHttpSession();
            session.setAttribute(PerpSasLoginHelper.SESSION_ATTRIBUTE_SUBSCRIPTION_CHECKING,
                                 envChecking);
            loginObject.setEnvChecking(envChecking);
            return "login";
        } else {
            FacesUtils.addError("Checking Perp Environment",
                                "Environment not found. Please check and try again.");
            return null;
        }
    }

    /**
     * Sau khi Subscriptions ok . Kiem tra login .
     * @return
     */
    public String doPerpAccountCheckingAction() {
        Object obj =
            FacesUtils.resolveExpression("#{bindings.doPerpAccountChecking.result}");
        PerpAccountChecking checkingInfo = (PerpAccountChecking)obj;
        SessionLoginObject loginObject =
            PerpSasViewUtils.getSessionLoginObject();
        loginObject.setPerpChecking(checkingInfo);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request =
            (HttpServletRequest)context.getExternalContext().getRequest();
        Map<String, Object> sessionMap =
            context.getExternalContext().getSessionMap();
        Captcha captcha = (Captcha)sessionMap.get(Captcha.NAME);
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            //bad luck - but ignore
            System.out.println("UTF not supported !");
        }
        String answer = (String)checkingInfo.getCaptchaText();
        if (answer != null && captcha.isCorrect(answer)) {
            //FacesUtils.addMessage(null, "Captcha is OK!");
        } else {
            FacesUtils.addError(null,
                                "Captcha is incorrect.Please check and try again!");
            return null;
        }
        if (loginObject.isPerpAccountValid()) {
            try {
                //HaoNv them de luu lai qua trinh dang nhap. Chi nhung lan dang nhap thanh cong
                //Lay thong tin
                String userName = loginObject.getPerpAccount().getUserName();
                String envId =
                    loginObject.getPerpSasEnv().getEnvId().toString();
                String browser = request.getHeader("User-Agent");
                Date loginAt = DateUtils.getOracleSysdate();
                String ipAddress = request.getRemoteAddr();

                PerpSasAppModuleImpl perpsas =
                    (PerpSasAppModuleImpl)FacesUtils.getApplicationModuleForDataControl("PerpSasAppModuleDataControl");

                perpsas.createAppLogin(userName, envId, browser, loginAt,
                                       ipAddress, "Viet Nam");
                //End HaoNv
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("ok");
            return "success";
        } else {
            FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Checking Perp Account",
                                 "Invalid username or password. Please check and try again.");
            context.addMessage(null, msg);
            return null;
        }

    }

    public String perpLoginAction() {
        return "loginSuccess";
    }

    public void setAboutSubscriptionPopup(RichPopup aboutSubscriptionPopup) {
        this.aboutSubscriptionPopup = aboutSubscriptionPopup;
    }

    public RichPopup getAboutSubscriptionPopup() {
        return aboutSubscriptionPopup;
    }

    public void showSubscriptionAboutPopupActionListener(ActionEvent actionEvent) {
        FacesUtils.showPopup(this.aboutSubscriptionPopup);
    }
}
