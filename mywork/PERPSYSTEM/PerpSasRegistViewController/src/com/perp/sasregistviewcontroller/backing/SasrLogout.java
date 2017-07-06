package com.perp.sasregistviewcontroller.backing;


import com.perp.beans.LoginHistoryBean;
import com.perp.beans.SasrSessionLoginObject;
import com.perp.sasregistmodel.common.PerpSasRegistAppModule;
import com.perp.sasregistviewcontroller.utils.SasrFacesUtils;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.adf.view.rich.render.ClientEvent;


public class SasrLogout {
    public SasrLogout() {
    }

    private void logOut() {
        SasrSessionLoginObject sasrObject =
            SasrFacesUtils.getSasrSessionLoginObject();

        if (sasrObject != null &&
            sasrObject.getPerpSasrAccountChecking() != null) {
            LoginHistoryBean loginHistory =
                sasrObject.getPerpSasrAccountChecking().getLoginHistoy();
            if (loginHistory != null) {
                PerpSasRegistAppModule module =
                    SasrFacesUtils.getPerpSasRegistAppModule();
                module.signOut(loginHistory.getLhiId());
            }
        }
        sasrObject.setPerpSasrAccountChecking(null);
    }

    public void logoutActionListener(ActionEvent actionEvent) {
        System.out.print("Logout here: ActionEvent");
        logOut();
    }

    public void onUnloadHander(ClientEvent clientEvent) {
        System.out.print("Logout here: ClientEvent");
        logOut();
    }
}
