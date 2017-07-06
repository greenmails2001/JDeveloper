package com.vc.web.util;

import com.vc.web.PerpSystemInfo;
import com.vc.web.beans.PerpSessionLogin;
import com.vc.web.beans.SasSessionLogin;

public class ViewUtil {
    public ViewUtil() {
    }   
    
    public static PerpSystemInfo getPerpSystemInfo() {
        PerpSessionLogin psl= ViewUtil.getPerpSessionLogin();
        return psl.getAppUserInfo().getPerpSystemInfo();        
    }
  
    public static PerpSessionLogin getPerpSessionLogin() {      
        PerpSessionLogin perp =
            (PerpSessionLogin)FacesUtil.resolveExpression("#{backing_perpSessionLogin}");
        return perp;
    }

    public static SasSessionLogin getSasSessionLogin() {       
        SasSessionLogin sas =
            (SasSessionLogin)FacesUtil.resolveExpression("#{backing_sasSessionLogin}");
        return sas;
    }

   
}
