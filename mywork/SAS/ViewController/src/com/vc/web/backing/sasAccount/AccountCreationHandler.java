package com.vc.web.backing.sasAccount;

import com.vc.web.beans.SasSessionLogin;
import com.vc.web.ejb.sas.SASFacadeLocal;
import com.vc.web.ejb.sas.entities.Accounts;
import com.vc.web.util.EJBUtil;
import com.vc.web.util.FacesUtil;

import com.vc.web.util.ViewUtil;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.naming.NamingException;

public class AccountCreationHandler {
    public AccountCreationHandler() {
    }

    public String createAccountAction() {
        Object obj = 
            FacesUtil.resolveExpression("#{bindings.queryAccountsFindAllIterator.currentRow.dataProvider}");
        Accounts acc = (Accounts)obj;
        String mail = acc.getEmail();
        String userName = acc.getUserName();
        SASFacadeLocal sasFacade = EJBUtil.lockupSASFacade();
        if (acc.getConfirmPassword() == null || 
            !acc.getConfirmPassword().equals(acc.getPassword())) {
            FacesUtil.addError("Error", 
                               "Mat khau va xac nhan khong khop nhau");
            return null;
        }

        if (acc.getEmail() == null) {
            FacesUtil.addError("Error", "Ban phai nhap email");
            return null;
        }
        Accounts acc1 = sasFacade.queryAccountsFindByEmail(mail);
        if (acc1 != null) {
            FacesUtil.addError("Error", 
                               "Email hien tai duoc dang ky , ban phai su dung mot email khac");
            return null;
        }
        acc1 = sasFacade.queryAccountsFindByUserName(userName);
        if (acc1 != null) {
            FacesUtil.addError("Error", 
                               "Tai khoan '" + userName + "' da duoc dang ky , ban phai su dung mot tai khoan khac");
            return null;
        }
        sasFacade.persistEntity(acc);
        SasSessionLogin sasSession = ViewUtil.getSasSessionLogin();
        sasSession.setNewAccId(acc.getAccId());
        // Send email
        try {
            ExternalContext ec = 
                FacesContext.getCurrentInstance().getExternalContext();
            String url = 
                ec.getRequestContextPath() + "/faces/sas01.jspx"; //TODO

            sasFacade.sendEmailCreatedAccount(mail, acc.getFullName(), 
                                              acc.getUserName(), 
                                              acc.getPassword(), url);
        } catch (Exception e) {
            FacesUtil.addError("Error", "Send email error " + e.getMessage());
        }
        return "info";
    }

    /** Kiem tra xem nguoi dung da tung login chua neu da login roi thi chuyen sang trang cho phep edit 
     * Ham nay duoc su dung de Navigation khi nguoi dung nhan vao Link edit Account .
     * Dong thoi no cung duoc dung de Enabled Link tren hay Disabled .
     */
    public String editAccountAction() {
        SasSessionLogin sasSession = ViewUtil.getSasSessionLogin();
        if (sasSession == null) {
            return null;
        }
        if (!sasSession.isValid()) {
            return null;
        }
        return "edit";
    }

    public boolean isEditLinkEnabled() {
        SasSessionLogin sasSession = ViewUtil.getSasSessionLogin();
        if (sasSession == null) {
            return false;
        }
        return sasSession.isValid();
    }
}
