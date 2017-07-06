package com.perp.sasregistviewcontroller.backing;


import com.oracle.adffwkextensions.faces.BundleUtils;

import com.perp.beans.PerpUtils;
import com.perp.sasregistmodel.common.PerpSasRegistAppModule;
import com.perp.sasregistviewcontroller.utils.SasrFacesUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class SasrCreateAccountEmailValidator implements Validator {
    public SasrCreateAccountEmailValidator() {
    }

    public void validate(FacesContext facesContext, UIComponent uiComponent,
                         Object object) {
        String email = (String)object;
        PerpSasRegistAppModule module =
            SasrFacesUtils.getPerpSasRegistAppModule();
        Object accId = module.isEmailExistsInAccountsTable(email);
        if (accId != null) {
            FacesMessage message = new FacesMessage();
            String value = BundleUtils.getBundle("Email da duoc su dung");
            message.setDetail(value);
            message.setSummary(value);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        if (!PerpUtils.isEmailAddress(email)) {
            FacesMessage message = new FacesMessage();
            String value =
                BundleUtils.getBundle("Email kh\u00F4ng h\u1EE3p l\u1EC7. Vui l\u00F2ng ki\u1EC3m tra v\u00E0 th\u1EED l\u1EA1i.");
            message.setDetail(value);
            message.setSummary(value);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);

        }
    }
}
