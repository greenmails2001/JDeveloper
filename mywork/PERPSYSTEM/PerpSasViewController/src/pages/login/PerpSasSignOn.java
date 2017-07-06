package pages.login;

import com.oracle.adffwkextensions.faces.FacesUtils;

import com.oracle.adffwkextensions.utils.DateUtils;

import com.perp.beans.LoginHistoryBean;
import com.perp.beans.PerpAccountChecking;
import com.perp.beans.PerpEnvChecking;
import com.perp.beans.SessionLoginObject;
import com.perp.beans.VbisUserLoginHistoryBean;
import com.perp.sasmodel.PerpSasAppModuleImpl;

import com.perp.sasmodel.common.PerpSasAppModule;
import com.perp.sasmodel.dynamicjdbc.PerpSasLoginHelper;
import com.perp.sasregistmodel.common.PerpSasRegistAppModule;

import com.perp.sasviewcontroller.util.PerpSasViewUtils;

import java.io.UnsupportedEncodingException;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;

import oracle.jbo.domain.Date;

public class PerpSasSignOn {
    private String userName;
    private String password;
    private String captchaText;
    private String subscriptionCode;

    private String outCome;

    public PerpSasSignOn() {
    }

    private boolean isValidated() {
        if (this.userName == null || this.userName.equals("")) {
            this.outCome = null;
            FacesUtils.addError(null,
                                "Username must be entered. Please check and try again.");
            return false;
        }
        if (this.password == null || this.password.equals("")) {
            this.outCome = null;
            FacesUtils.addError(null,
                                "Password must be entered. Please check and try again.");
            return false;
        }
        if (this.subscriptionCode == null ||
            this.subscriptionCode.equals("")) {
            this.outCome = null;
            FacesUtils.addError(null,
                                "Subscription Code must be entered. Please check and try again.");
            return false;
        }
        if (this.captchaText == null || this.captchaText.equals("")) {
            this.outCome = null;
            FacesUtils.addError(null,
                                "Captcha Code must be entered. Please check and try again.");
            return false;
        }
        return true;
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

    public void perpSasSignOnActionListener(ActionEvent actionEvent) {
        if (!this.isValidated()) {
            this.outCome = null;
            return;
        }
        // Add event code here...
        SessionLoginObject loginObject =
            PerpSasViewUtils.getSessionLoginObject();
        //Kiem tra ma thue bao truoc
        PerpSasRegistAppModule perpSasRegist =
            (PerpSasRegistAppModule)FacesUtils.getApplicationModuleForDataControl("PerpSasRegistAppModuleDataControl");
        PerpEnvChecking envChecking =
            perpSasRegist.doPerpEnvChecking(subscriptionCode);

        oracle.jbo.domain.Number accId = null;
        if (envChecking.isEnvFound()) {
            accId = envChecking.getAccId();
            //Khoi tao session
            HttpSession session = FacesUtils.getHttpSession();
            session.setAttribute(PerpSasLoginHelper.SESSION_ATTRIBUTE_SUBSCRIPTION_CHECKING,
                                 envChecking);
            loginObject.setEnvChecking(envChecking);
        } else {
            this.outCome = null;
            FacesUtils.addError("Checking Perp Environment",
                                "Environment not found. Please check and try again.");
            return;
        }
        //Kiem tra thong tin user va captcha
        PerpSasAppModule perpsas =
            (PerpSasAppModule)FacesUtils.getApplicationModuleForDataControl("PerpSasAppModuleDataControl");
        PerpAccountChecking accChecking =
            perpsas.doPerpAccountChecking(userName, password, captchaText);
        loginObject.setPerpChecking(accChecking);

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request =
            (HttpServletRequest)context.getExternalContext().getRequest();

        String browser = null;
        Date loginAt = DateUtils.getOracleSysdate();
        String ipAddress = null;
        if (loginObject.isPerpAccountValid()) {
            try {
                //Lay thong tin
                String userName = loginObject.getPerpAccount().getUserName();
                String envId =
                    loginObject.getPerpSasEnv().getEnvId().toString();

                browser = request.getHeader("User-Agent");
                ipAddress = request.getRemoteAddr();
                //String useros = getOS(browser);
                perpsas.createAppLogin(userName, envId, browser, loginAt,
                                       ipAddress, "Viet Nam");

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Login History Insert Ok");
            try {
                LoginHistoryBean bean = new LoginHistoryBean();

                bean.setAccId(envChecking.getAccId());
                bean.setUserName(userName);
                bean.setLoginAt(loginAt);
                bean.setIpAddress(ipAddress);
                bean.setModule("perpsas");
                bean.setPlatform("Windows");
                bean.setUserAgent(browser);
                bean.setCountry("Viet Nam");
                bean.setStatus("Y");
                
                accChecking.setLoginHistoy(bean);
                
                perpSasRegist.writeLoginHistory(bean);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Login PerpSas History Insert Ok");
        } else {
            FacesUtils.addError("Checking Perp Account",
                                "Invalid username or password. Please check and try again.");

            this.outCome = null;
            return;
        }
        //Kiem tr captcha
        Map<String, Object> sessionMap =
            context.getExternalContext().getSessionMap();
        Captcha captcha = (Captcha)sessionMap.get(Captcha.NAME);
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            //bad luck - but ignore
            System.out.println("UTF not supported !");
        }
        String answer = accChecking.getCaptchaText();
        if (answer != null && captcha.isCorrect(answer.toLowerCase())) {
            //FacesUtils.addMessage(null, "Captcha is OK!");
        } else {
            FacesUtils.addError(null,
                                "Captcha is incorrect.Please check and try again!");
            this.outCome = null;
            return;
        }

        this.outCome = "loginSuccess";
    }


    public String perpLoginAction() {
        return "loginSuccess";
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

    public void setCaptchaText(String captchaText) {
        this.captchaText = captchaText;
    }

    public String getCaptchaText() {
        return captchaText;
    }

    public void setSubscriptionCode(String subscriptionCode) {
        this.subscriptionCode = subscriptionCode;
    }

    public String getSubscriptionCode() {
        return subscriptionCode;
    }

    public void setOutCome(String outCome) {
        this.outCome = outCome;
    }

    public String getOutCome() {
        return outCome;
    }
}
