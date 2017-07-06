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

public class ChangePasswordValidator implements Validator {
    public ChangePasswordValidator() {
    }

    public void validate(FacesContext facesContext, UIComponent uiComponent,
                         Object object) {
        String password = (String)object;
        boolean isValid = PerpUtils.isValidPwd(password);
        if (!isValid) {
            FacesMessage message = new FacesMessage();
            String value =
                BundleUtils.getBundle("M\u1EADt kh\u1EA9u c\u00F3 \u00EDt nh\u1EA5t 6 k\u00FD t\u1EF1, c\u00F3 c\u1EA3 k\u00FD t\u1EF1 ch\u1EEF v\u00E0 s\u1ED1 v\u00E0 bao g\u1ED3m c\u1EA3 ch\u1EEF hoa v\u00E0 ch\u1EEF th\u01B0\u1EDDng.");
            message.setDetail(value);
            message.setSummary(value);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
