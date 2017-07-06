package com.perp.sasviewcontroller.util;

import com.oracle.adffwkextensions.faces.FacesUtils;

import com.perp.beans.SessionLoginObject;


import com.perp.sasmodel.common.PerpSasAppModule;

import com.perp.sasregistmodel.common.PerpSasRegistAppModule;



public class PerpSasViewUtils {
  public PerpSasViewUtils() {
  }

  public static SessionLoginObject getSessionLoginObject() {    
    return (SessionLoginObject)FacesUtils.resolveExpression("#{sessionLoginObject}");
  }
  
  public static PerpSasRegistAppModule getPerpSasRegistAppModule() {
     return (PerpSasRegistAppModule)FacesUtils.getApplicationModuleForDataControl("PerpSasRegistAppModuleDataControl");
  }
  
  public static PerpSasAppModule getPerpSasAppModule() {
     return (PerpSasAppModule)FacesUtils.getApplicationModuleForDataControl("PerpSasAppModuleDataControl");
  }
}
