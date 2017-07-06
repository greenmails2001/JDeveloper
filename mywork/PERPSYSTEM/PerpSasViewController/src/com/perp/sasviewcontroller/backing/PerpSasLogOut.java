package com.perp.sasviewcontroller.backing;

import com.perp.beans.LoginHistoryBean;
import com.perp.beans.SasrSessionLoginObject;
import com.perp.beans.SessionLoginObject;
import com.perp.sasmodel.common.PerpSasAppModule;
import com.perp.sasregistmodel.common.PerpSasRegistAppModule;

import com.perp.sasviewcontroller.util.PerpSasViewUtils;

import javax.faces.event.ActionEvent;

public class PerpSasLogOut {
    public PerpSasLogOut() {
        super();
    }


    public void logoutActionListener(ActionEvent actionEvent) {

        SessionLoginObject sasObject =
            PerpSasViewUtils.getSessionLoginObject();

        if (sasObject != null && sasObject.getPerpChecking() != null) {
            LoginHistoryBean loginHistory =
                sasObject.getPerpChecking().getLoginHistoy();
            if (loginHistory != null) {
                PerpSasRegistAppModule module =
                    PerpSasViewUtils.getPerpSasRegistAppModule();
                module.signOut(loginHistory.getLhiId());
            }
        }
        sasObject.setPerpChecking(null);
    }
}
