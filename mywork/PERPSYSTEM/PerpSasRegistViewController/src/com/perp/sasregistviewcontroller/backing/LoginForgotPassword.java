package com.perp.sasregistviewcontroller.backing;


import com.oracle.adffwkextensions.faces.FacesUtils;

import com.perp.sasregistmodel.common.PerpSasRegistAppModule;
import com.perp.sasregistviewcontroller.utils.SasrFacesUtils;

import javax.faces.event.ActionEvent;

public class LoginForgotPassword {
    private String email;

    public LoginForgotPassword() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void sendEmailActionListener(ActionEvent actionEvent) {
        PerpSasRegistAppModule module =
            SasrFacesUtils.getPerpSasRegistAppModule();
        Object accId = module.isEmailExistsInAccountsTable(this.email);
        if (accId == null) {
            FacesUtils.addError(null,
                                "Email does not exists in system. Please check and try again.");
            return;
        }
        module.sendMailNewPassword(this.email, accId);
        FacesUtils.addMessage("Forgot Password",
                              "A new password has been sent to your email.");
        //Send email

    }
}
