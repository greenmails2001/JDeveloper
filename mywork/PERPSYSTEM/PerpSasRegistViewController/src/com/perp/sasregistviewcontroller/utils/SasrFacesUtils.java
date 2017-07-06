package com.perp.sasregistviewcontroller.utils;


import com.oracle.adffwkextensions.faces.FacesUtils;

import com.perp.beans.SasrSessionLoginObject;
import com.perp.sasregistmodel.common.PerpSasRegistAppModule;
import com.perp.sasutilsmodel.entities.PerpSasUtilsAppModuleImpl;

public class SasrFacesUtils {
  public SasrFacesUtils() {
  }
  
  public static PerpSasRegistAppModule getPerpSasRegistAppModule() {
     return (PerpSasRegistAppModule)FacesUtils.getApplicationModuleForDataControl("PerpSasRegistAppModuleDataControl");
  }
  
  public static PerpSasUtilsAppModuleImpl getPerpSasUtilsAppModule() {
    return (PerpSasUtilsAppModuleImpl)FacesUtils.getApplicationModuleForDataControl("PerpSasUtilsAppModuleImplDataControl");
  }
  
  public static SasrSessionLoginObject getSasrSessionLoginObject() {
      return (SasrSessionLoginObject)FacesUtils.resolveExpression("#{sasrSessionLoginObject}");
  }
}
