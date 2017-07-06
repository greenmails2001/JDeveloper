package com.perp.sasregistviewcontroller.backing;

import com.oracle.adffwkextensions.faces.FacesUtils;

import com.perp.beans.PerpUtils;
import com.perp.beans.SasrSessionLoginObject;

import com.perp.sasregistmodel.common.PerpSasRegistAppModule;
import com.perp.sasregistviewcontroller.utils.SasrFacesUtils;

import oracle.adf.view.rich.event.DialogEvent;

public class ChangePassword {
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;


    public ChangePassword() {
    }

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public void changePasswordActionListener(DialogEvent dialogEvent) {
        // Add event code here...
        if (this.isEmpty(this.oldPassword)) {
            FacesUtils.addError(null, "The old password must be enterd.");
            return;
        }
        if (this.isEmpty(this.newPassword)) {
            FacesUtils.addError(null, "The new password must be enterd.");
            return;
        }
        if (this.isEmpty(this.confirmNewPassword) ||
            !this.newPassword.equals(this.confirmNewPassword)) {
            FacesUtils.addError(null,
                                "The new password and confirmation password do not match.");
            return;
        }
        SasrSessionLoginObject loginObject =
            SasrFacesUtils.getSasrSessionLoginObject();
        String password = loginObject.getPerpSasrAccount().getPassword();
        if (!PerpUtils.EncryptPassword(this.oldPassword).equals(password)) {
            FacesUtils.addError(null, "The old password is incorrect.");
            return;
        }
        PerpSasRegistAppModule module =
            SasrFacesUtils.getPerpSasRegistAppModule();
        oracle.jbo.domain.Number accId =
            loginObject.getPerpSasrAccount().getAccId();
        if (module.changePassword(accId, this.newPassword)) {
            loginObject.getPerpSasrAccount().setPassword(PerpUtils.EncryptPassword(this.newPassword));
            this.oldPassword = null;
            this.newPassword = null;
            this.confirmNewPassword = null;
            FacesUtils.addMessage(null, "Your password has been changed!");
        } else {
            FacesUtils.addError(null,
                                "Sorry, error while changing your password.");
        }
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }
}
