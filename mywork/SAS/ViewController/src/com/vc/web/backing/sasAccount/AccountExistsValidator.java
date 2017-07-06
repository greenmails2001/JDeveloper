package com.vc.web.backing.sasAccount;


import com.vc.web.ejb.sas.SASFacadeLocal;
import com.vc.web.ejb.sas.entities.Accounts;
import com.vc.web.util.EJBUtil;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import javax.naming.NamingException;

public class AccountExistsValidator implements Validator {
    public AccountExistsValidator() {
    }

    public void validate(FacesContext facesContext, UIComponent uiComponent, 
                         Object object) {
        String userName = (String)object;
        SASFacadeLocal sasFacade = null;
        FacesMessage message = null;
        sasFacade = EJBUtil.lockupSASFacade();
        Accounts acc = sasFacade.queryAccountsFindByUserName(userName);
        if (acc != null) {
            message = new FacesMessage();
            message.setDetail("Tai khoan da duoc su dung");
            message.setSummary("Tai khoan da duoc su dung");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
