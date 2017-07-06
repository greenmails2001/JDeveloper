package com.vc.web.backing;

import com.vc.web.beans.SasSessionLogin;
import com.vc.web.util.FacesUtil;
import com.vc.web.util.ViewUtil;

public class SasSessionHandler {
    public SasSessionHandler() {
    }
    
    public boolean isSasLogin(){
      SasSessionLogin 
        sas= ViewUtil.getSasSessionLogin();     
      if(sas.isValid()){
          return true;
      }
      return false;
    }
    
 
}
