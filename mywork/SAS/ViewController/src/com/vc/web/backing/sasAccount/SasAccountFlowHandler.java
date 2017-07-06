package com.vc.web.backing.sasAccount;

import com.vc.web.beans.SasSessionLogin;
import com.vc.web.util.FacesUtil;
import com.vc.web.util.ViewUtil;

public class SasAccountFlowHandler {
    public SasAccountFlowHandler() {
    }

    public boolean isSasLogin() {
        SasSessionLogin sas = ViewUtil.getSasSessionLogin();
        if (sas.isValid()) {
            return true;
        }
        return false;
    }

    public String sasLogin2shoppingSasFlow() {
        if (this.isSasLogin()) {
            return "shoppingSas";
        }
        FacesUtil.addError("Login", 
                           "Ban phai login vao he thong Sas truoc khi su dung chuc nang nay");
        return null;
    }
}
