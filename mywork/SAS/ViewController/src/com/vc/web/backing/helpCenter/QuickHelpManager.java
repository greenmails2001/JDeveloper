package com.vc.web.backing.helpCenter;

import com.vc.web.ejb.sas.SASFacadeLocal;
import com.vc.web.ejb.sas.entities.HelpDesks;

import com.vc.web.util.EJBUtil;

import com.vc.web.util.FacesUtil;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.naming.NamingException;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;


import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.TreeModel;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;


public class QuickHelpManager {

    private RichDialog quickHelpDialog;
    private RichPopup quickHelpPopup;
    private RichPanelCollection treePanelCollection;

    private TreeModel treeModel;

    private RichTree afTreeHelp;
    //  private RichPanelGroupLayout afContentGroup;

    private HelpDesks help = new HelpDesks();


    public void setQuickHelpDialog(RichDialog quickHelpDialog) {
        this.quickHelpDialog = quickHelpDialog;
    }

    public RichDialog getQuickHelpDialog() {
        return quickHelpDialog;
    }

    public void setQuickHelpPopup(RichPopup quickHelpPopup) {
        this.quickHelpPopup = quickHelpPopup;
    }

    public RichPopup getQuickHelpPopup() {
        return quickHelpPopup;
    }

    public String showHelp() {
        return null;
    }

    private void prepareShowHelpInfo() {
        SASFacadeLocal facade = EJBUtil.lockupSASFacade();
        HelpDesks help = facade.queryHelpDesksFindByCode("SAS");
        if (help == null) {
            FacesUtil.addError("Error", "Khong tim thay thong tin help");
            return;
        }
        this.help = help;
        //FacesUtil.addPartialTarget(this.afContentGroup);
    }

    public void showQuickHelpActionListener(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        String popupId = this.quickHelpPopup.getClientId(context);
        //-------------------------------------------------------
        this.prepareShowHelpInfo();
        //------------------------------------------------------
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
    }

    public void setTreePanelCollection(RichPanelCollection treePanelCollection) {
        this.treePanelCollection = treePanelCollection;
    }

    public RichPanelCollection getTreePanelCollection() {
        return treePanelCollection;
    }

    public void setAfTreeHelp(RichTree afTreeHelp) {
        this.afTreeHelp = afTreeHelp;
    }

    public RichTree getAfTreeHelp() {
        return afTreeHelp;
    }


    public void setTreeModel(TreeModel treeModel) {
        this.treeModel = treeModel;
    }


    public void setHelp(HelpDesks help) {
        this.help = help;
    }

    public HelpDesks getHelp() {
        return help;
    }


    public String saveHelpActionListener() {
        SASFacadeLocal facade = EJBUtil.lockupSASFacade();
        facade.mergeEntity(this.help);
        return null;
    }
}
