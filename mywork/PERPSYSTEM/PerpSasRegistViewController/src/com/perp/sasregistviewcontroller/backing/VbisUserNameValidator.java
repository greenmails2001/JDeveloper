package com.perp.sasregistviewcontroller.backing;

import com.oracle.adffwkextensions.faces.ADFUtils;
import com.oracle.adffwkextensions.faces.BundleUtils;

import com.perp.beans.SasrSessionLoginObject;
import com.perp.sasregistmodel.common.PerpSasRegistAppModule;
import com.perp.sasregistviewcontroller.utils.SasrFacesUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import oracle.jbo.domain.Number;

public class VbisUserNameValidator implements Validator {
    public VbisUserNameValidator() {
        super();
    }

    private void showErrors(String messageText) {
        FacesMessage message = new FacesMessage();
        String value = BundleUtils.getBundle(messageText);
        message.setDetail(value);
        message.setSummary(value);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(message);
    }

    public void validate(FacesContext facesContext, UIComponent uiComponent,
                         Object object) {
        Object oldValue = ((RichInputText)uiComponent).getValue();
        String messageText;
        if (object == null) {
            messageText = "User name bat buoc nhap!";
            this.showErrors(messageText);
        }
        // Value has not changed.
        if (object.equals(oldValue)) {
            return;
        }
        Number accId =
            ((SasrSessionLoginObject)SasrFacesUtils.getSasrSessionLoginObject()).getPerpSasrAccount().getAccId();
        PerpSasRegistAppModule am =
            (PerpSasRegistAppModule)ADFUtils.getDCBindingContainer().getDataControl().getApplicationModule();
        if (am.checkVbisUserByAccId(accId, object.toString())) {
            messageText = "User da duoc su dung";
            this.showErrors(messageText);
        }
    }
}
