package pages.userprofile;

import com.oracle.adffwkextensions.faces.FacesUtils;

import com.perp.beans.SessionLoginObject;
import com.perp.sasmodel.common.PerpSasAppModule;
import com.perp.sasviewcontroller.util.PerpSasViewUtils;

import oracle.adf.view.rich.event.DialogEvent;

public class ChangePassword {
    private String _oldPassword;
    private String _newPassword;
    private String _confirmNewPassword;

    public ChangePassword() {
    }

    public void setOldPassword(String _oldPassword) {
        this._oldPassword = _oldPassword;
    }

    public String getOldPassword() {
        return _oldPassword;
    }

    public void setNewPassword(String _newPassword) {
        this._newPassword = _newPassword;
    }

    public String getNewPassword() {
        return _newPassword;
    }

    public void setConfirmNewPassword(String _confirmNewPassword) {
        this._confirmNewPassword = _confirmNewPassword;
    }

    public String getConfirmNewPassword() {
        return _confirmNewPassword;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public void changePasswordActionListener(DialogEvent dialogEvent) {
        // Add event code here...
        if (this.isEmpty(this._oldPassword)) {
            FacesUtils.addError(null, "You must enter the old password.");
            return;
        }
        if (this.isEmpty(this._newPassword)) {
            FacesUtils.addError(null, "You must enter a new password.");
            return;
        }
        if (this.isEmpty(this._confirmNewPassword)) {
            FacesUtils.addError(null,
                                "You must enter the new password confirmation.");
            return;
        }
        if (!this._newPassword.equals(this._confirmNewPassword)) {
            FacesUtils.addError(null,
                                "The new password and confirmation password do not match.");
            return;
        }
        SessionLoginObject loginObject =
            PerpSasViewUtils.getSessionLoginObject();

        String oldPassword = loginObject.getPerpAccount().getPassword();
        if (!this._oldPassword.equals(oldPassword)) {
            FacesUtils.addError(null, "The old password is incorrect.");
            return;
        }
        PerpSasAppModule module =
            (PerpSasAppModule)FacesUtils.getApplicationModuleForDataControl("PerpSasAppModuleDataControl");
        if (module.doChangePassword(loginObject.getPerpAccount().getAusId(),
                                    this._newPassword)) {
            loginObject.getPerpAccount().setPassword(this._newPassword);
            FacesUtils.addMessage(null,
                                  "Congratulations, your password has been changed.");
        } else {
            FacesUtils.addError(null,
                                "Sorry, error while changing your password.");
        }
    }
}
