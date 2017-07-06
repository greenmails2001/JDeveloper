package com.perp.sasregistviewcontroller.backing;


import com.oracle.adffwkextensions.faces.FacesUtils;

import java.util.Random;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUIteratorBinding;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;


public class Testting {
    private String debugText="Default tetxt ...";
    private RichInputText debugTextField;

    private ActionListener refreshDebug = new ActionListener() {
        public void processAction(ActionEvent actionEvent) {
            debugText = "Change " + (new Random().nextLong());
            JUIteratorBinding iteBinding =
                (JUIteratorBinding)FacesUtils.resolveExpression("#{bindings.Sasr002A1ServicePackagesIterator}");
            Row row= iteBinding.getCurrentRow();
            debugText = (String)row.getAttribute("PackageCode");
            FacesUtils.addPartialTarget(debugTextField);
            System.out.println("Text :"+debugText);
            
            FacesContext context = FacesContext.getCurrentInstance();
            String popupId = "p1";
            StringBuilder script = new StringBuilder();
            script.append("var popup = AdfPage.PAGE.findComponent('");
            script.append(popupId).append("'); ");
            script.append("if (!popup.isPopupVisible()) { ");
            script.append("var hints = {}; ");
            //   .append("hints[AdfRichPopup.HINT_ALIGN_ID] = '").append(alignId).append("'; ")
            //   .append("hints[AdfRichPopup.HINT_ALIGN] = AdfRichPopup.ALIGN_AFTER_START; ")
            script.append("popup.show(hints);}");
            ExtendedRenderKitService erks =
                Service.getService(context.getRenderKit(),
                                   ExtendedRenderKitService.class);
            erks.addScript(context, script.toString());
           
            popup.setContentDelivery("lazyUncached");;
            FacesUtils.addPartialTarget(popup);
        }
    };
    private RichPopup popup;
    private RichPopup showDetailsPopup;

    public Testting() {
        
    }

    public void setDebugTextField(RichInputText debugTextField) {
        this.debugTextField = debugTextField;
    }

    public RichInputText getDebugTextField() {
        return debugTextField;
    }



    public String getDebugText() {
        return debugText;
    }

    public void setRefreshDebug(ActionListener refreshDebug) {
        this.refreshDebug = refreshDebug;
    }

    public ActionListener getRefreshDebug() {
        return refreshDebug;
    }

    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }

    public void popupFeatchListener(PopupFetchEvent popupFetchEvent) {
        debugText = "Change " + (new Random().nextLong());
        JUIteratorBinding iteBinding =
            (JUIteratorBinding)FacesUtils.resolveExpression("#{bindings.Sasr002A1ServicePackagesIterator}");
        Row row= iteBinding.getCurrentRow();
        debugText = (String)row.getAttribute("PackageCode");
        FacesUtils.addPartialTarget(debugTextField);
        System.out.println("Text :"+debugText);
        
        FacesContext context = FacesContext.getCurrentInstance();
        String popupId = "p1";
        StringBuilder script = new StringBuilder();
        script.append("var popup = AdfPage.PAGE.findComponent('");
        script.append(popupId).append("'); ");
        script.append("if (!popup.isPopupVisible()) { ");
        script.append("var hints = {}; ");
        //   .append("hints[AdfRichPopup.HINT_ALIGN_ID] = '").append(alignId).append("'; ")
        //   .append("hints[AdfRichPopup.HINT_ALIGN] = AdfRichPopup.ALIGN_AFTER_START; ")
        script.append("popup.show(hints);}");
        ExtendedRenderKitService erks =
            Service.getService(context.getRenderKit(),
                               ExtendedRenderKitService.class);
        erks.addScript(context, script.toString());
        
        popup.setContentDelivery("lazyUncached");;
        FacesUtils.addPartialTarget(popup);
    }

    public void showId(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void testShowClientId(ActionEvent actionEvent) {
        System.out.println("Details Popup Id="+this.showDetailsPopup.getClientId(FacesContext.getCurrentInstance()));
    }

    public void setShowDetailsPopup(RichPopup showDetailsPopup) {
        this.showDetailsPopup = showDetailsPopup;
    }

    public RichPopup getShowDetailsPopup() {
        return showDetailsPopup;
    }
}
