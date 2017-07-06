package pages.sasr.sasr002;


import com.oracle.adffwkextensions.faces.FacesUtils;

import com.perp.beans.SasrSessionLoginObject;
import com.perp.sasregistmodel.common.PerpSasRegistAppModule;
import com.perp.sasregistmodel.views.sasr002.common.Sasr002A1ServicePackagesRow;
import com.perp.sasregistmodel.views.sasr002.common.Sasr002A2SubscriptionsRow;
import com.perp.sasregistviewcontroller.utils.SasrFacesUtils;

import javax.faces.component.ContextCallback;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUIteratorBinding;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;


/**
 * backing bean class .
 */
public class Sasr002Podm {

    private RichPopup podmPopup01;
    private RichPopup podmPopup02;

    private RichDialog podmDialog2;
    private RichDialog podmDialog1;

    private String packageName;
    private RichPanelBox podmRegistedPanelBox;

    /** ServicePackage da dat hang truoc do */
    private Sasr002A2SubscriptionsRow currentSubscription;

    /** ServicePackage dang duoc chon de mua */
    private Sasr002A1ServicePackagesRow servicePackageToBuy;

    private ActionListener buyServicePackageActionListener =
        new ActionListener() {
        public void processAction(ActionEvent actionEvent) {
            preparePopupToShow();
        }
    };
    private RichOutputText packageNameField;


    public Sasr002Podm() {

    }

    private void preparePopupToShow() {
        JUIteratorBinding iteBinding =
            (JUIteratorBinding)FacesUtils.resolveExpression("#{bindings.Sasr002A1ServicePackagesIterator}");

        servicePackageToBuy =
                (Sasr002A1ServicePackagesRow)iteBinding.getCurrentRow();

        // Tim kiem Subscriptions (Thue bao) da duoc su dung truoc do .
        SasrSessionLoginObject sessionObject =
            SasrFacesUtils.getSasrSessionLoginObject();
        Number accId = sessionObject.getPerpSasrAccount().getAccId();

        PerpSasRegistAppModule module =
            SasrFacesUtils.getPerpSasRegistAppModule();

        this.currentSubscription =
                module.getCurrentRowSasr002A2Subscriptions(accId);

        if (this.currentSubscription == null) {
            module.createSasr002ASubscriptions(new Number(accId),
                                               this.servicePackageToBuy.getSpaId());
            String message = "Ban da dang ky thanh cong goi dich vu nay.";
            FacesUtils.addMessage(null, message);
            // Refresh Buy Box
            FacesUtils.addPartialTarget(this.podmRegistedPanelBox);
        } else {
            // Kiem tra xem 2 ServicePackage da mua va ServicePackage vua chon co giong nhau khong
            Long spaId1 = this.currentSubscription.getSpaId().longValue();
            Long spaId2 = this.servicePackageToBuy.getSpaId().longValue();
            if (spaId1 == spaId2) {
                String message = "Ban da dang ky thanh cong goi dich vu nay.";
                FacesUtils.addMessage(null, message);
                return;
            } else {
                String message =
                    "De chuyen doi sang goi dich vu khac. Vui long lien he voi nhan vien Vinh Cuu de duoc ho tro.";
                FacesUtils.addMessage(null, message);
                return;
                /*
                  FacesContext context = FacesContext.getCurrentInstance();
                  String popupId1 =
                      Sasr002Podm.this.podmPopup01.getClientId(context);
                  String popupId2 =
                      Sasr002Podm.this.podmPopup02.getClientId(context);
                  String popupId = popupId2;
                  Sasr002Podm.this.showPopup(popupId);

                  if (popupId.equals(popupId1)) {
                      this.podmPopup01.setContentDelivery("lazyUncached");
                      FacesUtils.addPartialTarget(this.podmPopup01);
                      FacesUtils.addPartialTarget(this.podmDialog1);
                  } else {
                      this.podmPopup02.setContentDelivery("lazyUncached");
                      FacesUtils.addPartialTarget(this.podmPopup02);
                      FacesUtils.addPartialTarget(this.podmDialog2);
                  }
                */
            }
        }
    }


    private void showPopup(String popupId) {
        if (popupId != null) {
            StringBuilder script = new StringBuilder();
            script.append("var popup = AdfPage.PAGE.findComponent('");
            script.append(popupId).append("'); ");
            script.append("if (!popup.isPopupVisible()) { ");
            script.append("var hints = {}; ");
            //   .append("hints[AdfRichPopup.HINT_ALIGN_ID] = '").append(alignId).append("'; ")
            //   .append("hints[AdfRichPopup.HINT_ALIGN] = AdfRichPopup.ALIGN_AFTER_START; ")
            script.append("popup.show(hints);}");

            FacesContext context = FacesContext.getCurrentInstance();
            ExtendedRenderKitService erks =
                Service.getService(context.getRenderKit(),
                                   ExtendedRenderKitService.class);
            erks.addScript(context, script.toString());
        }
    }

    public void setPodmPopup01(RichPopup podmPopup01) {
        this.podmPopup01 = podmPopup01;
    }

    public RichPopup getPodmPopup01() {
        return podmPopup01;
    }


    public void setPodmDialog2(RichDialog podmDialog2) {
        this.podmDialog2 = podmDialog2;
    }

    public RichDialog getPodmDialog2() {
        return podmDialog2;
    }

    public void setPodmDialog1(RichDialog podmDialog1) {
        this.podmDialog1 = podmDialog1;
    }

    public RichDialog getPodmDialog1() {
        return podmDialog1;
    }

    public void setPodmPopup02(RichPopup podmPopup02) {
        this.podmPopup02 = podmPopup02;
    }

    public RichPopup getPodmPopup02() {
        return podmPopup02;
    }

    public ActionListener getBuyServicePackageActionListener() {
        return buyServicePackageActionListener;
    }


    public Sasr002A2SubscriptionsRow getCurrentSubscription() {
        return currentSubscription;
    }

    public Sasr002A1ServicePackagesRow getServicePackageToBuy() {
        return servicePackageToBuy;
    }


    public void popupFeatchListener(PopupFetchEvent popupFetchEvent) {
        preparePopupToShow();
    }

    public void podmDialog2DialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.yes)) {
            // Thay doi ....
            JUIteratorBinding iteBinding =
                (JUIteratorBinding)FacesUtils.resolveExpression("#{bindings.Sasr002A1ServicePackagesIterator}");

            servicePackageToBuy =
                    (Sasr002A1ServicePackagesRow)iteBinding.getCurrentRow();

            SasrSessionLoginObject sessionObject =
                SasrFacesUtils.getSasrSessionLoginObject();
            Number accId = sessionObject.getPerpSasrAccount().getAccId();

            PerpSasRegistAppModule module =
                SasrFacesUtils.getPerpSasRegistAppModule();
            currentSubscription =
                    module.getCurrentRowSasr002A2Subscriptions(accId);
            currentSubscription.setSpaId(servicePackageToBuy.getSpaId());
            module.getTransaction().commit();
            // Refresh Buy Box
            FacesUtils.addPartialTarget(this.podmRegistedPanelBox);
        }
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageNameField(RichOutputText packageNameField) {
        this.packageNameField = packageNameField;
    }

    public RichOutputText getPackageNameField() {
        return packageNameField;
    }

    public void setPodmRegistedPanelBox(RichPanelBox podmRegistedPanelBox) {
        this.podmRegistedPanelBox = podmRegistedPanelBox;
    }

    public RichPanelBox getPodmRegistedPanelBox() {
        return podmRegistedPanelBox;
    }


}
