package com.vc.web.backing;

import com.vc.web.beans.PerpSessionLogin;
import com.vc.web.util.ViewUtil;


public class PerpSessionHandler {
    public PerpSessionHandler() {
    }
    
    
    /** Ham nay duoc su dung trong Flow : adf-config.xml  */
    public String getFlowCaseOfAdfConfig() {
        PerpSessionLogin perp=  ViewUtil.getPerpSessionLogin();
        
        if(!perp.isValid()) {
            return "appView";
        }        
        if(!perp.isValidSetting()){
            return "initialSetting";
        }       
        return "appView";
    }
    
    /** Ham nay duoc su dung trong Flow : app-view-flow.xml  */
    public String getFlowCaseOfAppViewFlow() {
        PerpSessionLogin perp= null;
        try {
            perp= new ViewUtil().getPerpSessionLogin();
        }catch(Exception e) {
            System.out.println("Error :"+e);
            return "login";
        }
        if(!perp.isValid()) {
            return "login";
        }        
        if(!perp.isValidSetting()){
            return "initialSetting";
        }       
        return "appView";
    }
}
