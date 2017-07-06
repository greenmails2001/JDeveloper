package com.perp.sasregistviewcontroller.backing;


import com.oracle.adffwkextensions.faces.BundleUtils;

import com.perp.sasregistmodel.common.PerpSasRegistAppModule;
import com.perp.sasregistviewcontroller.utils.SasrFacesUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class SasrCreateAccountUserValidator implements Validator {
  public SasrCreateAccountUserValidator() {
  }

  public void validate(FacesContext facesContext, UIComponent uiComponent,
                       Object object) {
    String userName = (String)object;
    PerpSasRegistAppModule module = SasrFacesUtils.getPerpSasRegistAppModule();
    boolean userExists = module.isUserExistsInAccountsTable(userName);
    if (userExists) {
      FacesMessage message = new FacesMessage();
      String value= BundleUtils.getBundle("User da duoc su dung");
      message.setDetail(value);
      message.setSummary(value);
      message.setSeverity(FacesMessage.SEVERITY_ERROR);
      throw new ValidatorException(message);
    }
  }
}
