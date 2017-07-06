package pages.sasr.sasr001.sasr001frag;


import com.oracle.adffwkextensions.faces.FacesUtils;

import com.perp.beans.SasrSessionLoginObject;
import com.perp.sasregistmodel.common.PerpSasRegistAppModule;
import com.perp.sasregistviewcontroller.backing.Sasr002FlowManager;
import com.perp.sasregistviewcontroller.utils.SasrFacesUtils;

import java.io.IOException;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.domain.Number;


public class SasrFirstPreview {
    private String outcome;
    private String activeCode;
    private RichInputText activeCodeInputText;

    private RichPopup sendMailPopupInfo;

    public SasrFirstPreview() {
    }

    public void activeAccountActionListener(ActionEvent e) {
        PerpSasRegistAppModule module =
            SasrFacesUtils.getPerpSasRegistAppModule();
        SasrSessionLoginObject sessionObject =
            SasrFacesUtils.getSasrSessionLoginObject();
        Number accId = sessionObject.getPerpSasrAccount().getAccId();
        boolean rs = module.activeAccount(accId, activeCode);
        if (!rs) {
            FacesUtils.addError(this.activeCodeInputText.getClientId(FacesContext.getCurrentInstance()),
                                "", "Active Code is not valid.");
            this.outcome = null;

        } else {
            sessionObject.getPerpSasrAccount().setActivated(true);
            sessionObject.getPerpSasrAccountChecking().setToAccountActivated();
            //FacesUtils.addMessage("Active Account","Congratulations, your account has been activated.");

            RichRegion region =
                (RichRegion)FacesUtils.getUIComponent("pt1:r1");
            if (region != null) {
                region.getRegionModel().refresh(FacesContext.getCurrentInstance());
                FacesUtils.addPartialTarget(region);
                FacesUtils.refreshPage();
            }
            this.outcome = "activesuccess";
        }
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCodeInputText(RichInputText activeCodeInputText) {
        this.activeCodeInputText = activeCodeInputText;
    }

    public RichInputText getActiveCodeInputText() {
        return activeCodeInputText;
    }

    // Goi gui lai Email de kich hoat tai khoan .

    public void reSendEmailActionListener(ActionEvent actionEvent) {
        PerpSasRegistAppModule module =
            SasrFacesUtils.getPerpSasRegistAppModule();
        SasrSessionLoginObject sessionObject =
            SasrFacesUtils.getSasrSessionLoginObject();
        Number accId = sessionObject.getPerpSasrAccount().getAccId();

        module.reSendEmailActiveAccount(accId);
        FacesUtils.showPopup(this.sendMailPopupInfo);
    }

    public void setSendMailPopupInfo(RichPopup sendMailPopupInfo) {
        this.sendMailPopupInfo = sendMailPopupInfo;
    }

    public RichPopup getSendMailPopupInfo() {
        return sendMailPopupInfo;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getOutcome() {
        return outcome;
    }

}
