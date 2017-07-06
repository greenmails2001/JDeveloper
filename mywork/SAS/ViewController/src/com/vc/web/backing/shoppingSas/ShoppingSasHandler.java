package com.vc.web.backing.shoppingSas;

import com.vc.web.beans.SasSessionLogin;
import com.vc.web.util.ViewUtil;

public class ShoppingSasHandler {
    public ShoppingSasHandler() {
    }
    
    public String getFlowCaseOfShoppingSasFlow() {
        SasSessionLogin sas=ViewUtil.getSasSessionLogin();
        System.out.println("SAS="+sas);
        if(sas.isValid()){
            return "subscriptionList";
        }
        return "login";
    }
}
