package com.perp.sasregistviewcontroller.backing;

import com.oracle.adffwkextensions.faces.FacesUtils;

import com.perp.beans.PerpSasrAccount;
import com.perp.beans.SasrSessionLoginObject;

import com.perp.sasregistmodel.common.PerpSasRegistAppModule;
import com.perp.sasregistviewcontroller.utils.SasrFacesUtils;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;

public class Sasr002FlowManager {

    private static final String VIEW_FIRST_PREVIEW = "firstPreview";
    private static final String VIEW_PODM = "podm";
    private static final String VIEW_ACCOUNT_INFO = "accountInfo";
    private static final String VIEW_OTHER_SERVICES = "otherServices";
    private static final String VIEW_VBIS_USER = "vbisuser";
    private static final String VIEW_SASR_ADMIN = "sasrAdmin";

    private String caseFlow;
    private RichRegion region;

    public Sasr002FlowManager() {
        super();
    }

    public void setCaseFlow(String caseFlow) {
        this.caseFlow = caseFlow;
        this.region.getRegionModel().refresh(FacesContext.getCurrentInstance());
        FacesUtils.addPartialTarget(this.region);
    }

    public String getCaseFlow() {
        if (this.caseFlow == null) {
            SasrSessionLoginObject sessionObject =
                SasrFacesUtils.getSasrSessionLoginObject();
            boolean actived =
                sessionObject.getPerpSasrAccountChecking().isAccountActivated();
            if (!actived) {
                this.caseFlow = VIEW_FIRST_PREVIEW;
            } else {
                this.caseFlow = VIEW_PODM;
            }
        }
        return caseFlow;
    }

    public void setCaseFlow2FirstPreview(ActionEvent actionEvent) {
        this.setCaseFlow(VIEW_FIRST_PREVIEW);

    }

    public void setCaseFlow2Podm(ActionEvent actionEvent) {
        this.setCaseFlow(VIEW_PODM);

    }

    public void setCaseFlow2AccountInfo(ActionEvent actionEvent) {
        this.setCaseFlow(VIEW_ACCOUNT_INFO);

    }

    public void setCaseFlow2VBISUser(ActionEvent actionEvent) {
        SasrSessionLoginObject sessionObject =
            SasrFacesUtils.getSasrSessionLoginObject();
        if (sessionObject != null) {
            if (!sessionObject.getPerpSasrAccount().isActivated()) {
                FacesUtils.addMessage(null,
                                      "Tai khoan cua ban chua duoc kich hoat. Vui long kich hoat tai khoan truoc khi thuc hien chuc nang nay!");
                return;
            }
            PerpSasRegistAppModule sasr =
                SasrFacesUtils.getPerpSasRegistAppModule();
            if (sasr.checkSubExistedByAccId(sessionObject.getPerpSasrAccount().getAccId())) {
                this.setCaseFlow(VIEW_VBIS_USER);
            } else {
                FacesUtils.addMessage(null,
                                      "Ban phai dang ky mot san pham cua VBIS truoc khi thuc hien chuc nang nay!");
                return;
            }
        }

    }

    public void setCaseFlow2AccountLog(ActionEvent actionEvent) {
        SasrSessionLoginObject sessionObject =
            SasrFacesUtils.getSasrSessionLoginObject();
        if (sessionObject != null) {
            PerpSasrAccount account = sessionObject.getPerpSasrAccount();
            if (account != null) {
                PerpSasRegistAppModule sasr =
                    SasrFacesUtils.getPerpSasRegistAppModule();
                if (sasr.isAdminUser(account.getAccId())) {
                    this.setCaseFlow(VIEW_SASR_ADMIN);
                } else {
                    FacesUtils.addMessage(null,
                                          "Ban khong phai la nguoi dung Quan tri he thong, khong the vao chuc nang nay!");
                    return;
                }
            } else {
                FacesUtils.addMessage(null,
                                      "Phien dang nhap da ket thuc. Ban khong the vao chuc nang nay!");

                return;
            }
        } else {
            FacesUtils.addMessage(null,
                                  "Phien dang nhap da ket thuc. Ban khong the vao chuc nang nay!");
            return;
        }
    }

    public void setRegion(RichRegion region) {
        this.region = region;
    }

    public RichRegion getRegion() {
        return region;
    }


}
