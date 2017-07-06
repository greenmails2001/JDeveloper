package com.vc.web.backing.helpAdmin;

import com.vc.web.ejb.sas.SASFacadeLocal;
import com.vc.web.ejb.sas.entities.HelpDesks;
import com.vc.web.util.EJBUtil;
import com.vc.web.util.FacesUtil;

import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichTextEditor;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.TreeModel;


public class HelpAdminManager {
    private RichTree tree;
    private TreeModel treeMode;

    private Object focusRowKey;

    private HelpDesks editHelp = new HelpDesks();
    private RichInputText afHelpCode1;
    private RichInputText afHelpTitle1;
    private RichTextEditor afHelpContent1;
    private RichInputText afHelpCode2;
    private RichActiveOutputText afHelpTitle2;
    private RichTextEditor afHelpContent2;


    public HelpAdminManager() {
    }


    public void setTree(RichTree tree) {
        this.tree = tree;
    }

    public RichTree getTree() {
        return tree;
    }   

    public void setTreeMode(TreeModel treeMode) {        
        this.treeMode = treeMode;
    }

    public TreeModel getTreeMode() {
        if (this.treeMode == null) {           
            SASFacadeLocal facade = EJBUtil.lockupSASFacade();       
            List<HelpDesks> list2 = facade.queryHelpDesksFindAllRoot();            
            this.treeMode = new ChildPropertyTreeModel(list2, "helpDesksList");
        }        
        return treeMode;
    }

    public void setFocusRowKey(Object focusRowKey) {
        this.focusRowKey = focusRowKey;
    }

    public Object getFocusRowKey() {
        return focusRowKey;
    }

    private HelpDesks getSelectedRowData() {
        RowKeySet set = this.tree.getSelectedRowKeys();
        Iterator<?> ite = set.iterator();
        Object rowKey = ite.next();
        if (rowKey == null) {
            return null;
        }
        this.tree.setRowKey(rowKey);
        return (HelpDesks)tree.getRowData();
    }

    public void treeHelpSelectionListener(SelectionEvent selectionEvent) {
        HelpDesks helpDesk = this.getSelectedRowData();
        if (helpDesk == null) {
            FacesUtil.addMessage("Message", "No selection node found");
            this.editHelp = new HelpDesks();
            return;
        }
        this.editHelp = helpDesk;
        FacesUtil.addPartialTarget(this.afHelpCode1);
        FacesUtil.addPartialTarget(this.afHelpTitle1);
        FacesUtil.addPartialTarget(this.afHelpContent1);
        //------------------------------------------------
        FacesUtil.addPartialTarget(this.afHelpCode2);
        FacesUtil.addPartialTarget(this.afHelpTitle2);
        FacesUtil.addPartialTarget(this.afHelpContent2);
    }

    public void setEditHelp(HelpDesks editHelp) {
        this.editHelp = editHelp;
    }

    public HelpDesks getEditHelp() {
        return editHelp;
    }


    public void setAfHelpCode1(RichInputText afHelpCode1) {
        this.afHelpCode1 = afHelpCode1;
    }

    public RichInputText getAfHelpCode1() {
        return afHelpCode1;
    }

    public void setAfHelpTitle1(RichInputText afHelpTitle1) {
        this.afHelpTitle1 = afHelpTitle1;
    }

    public RichInputText getAfHelpTitle1() {
        return afHelpTitle1;
    }

    public void setAfHelpContent1(RichTextEditor afHelpContent1) {
        this.afHelpContent1 = afHelpContent1;
    }

    public RichTextEditor getAfHelpContent1() {
        return afHelpContent1;
    }

    public String saveHelpDeskAction() {
        if (this.editHelp == null || this.editHelp.getHelpId() == null) {
            FacesUtil.addMessage("Message", "No current HelpDesk to Save");
            return null;
        }
        SASFacadeLocal facade = EJBUtil.lockupSASFacade();
        facade.mergeEntity(this.editHelp);
        return null;
    }

    public void setAfHelpCode2(RichInputText afHelpCode2) {
        this.afHelpCode2 = afHelpCode2;
    }

    public RichInputText getAfHelpCode2() {
        return afHelpCode2;
    }

    public void setAfHelpTitle2(RichActiveOutputText afHelpTitle2) {
        this.afHelpTitle2 = afHelpTitle2;
    }

    public RichActiveOutputText getAfHelpTitle2() {
        return afHelpTitle2;
    }

    public void setAfHelpContent2(RichTextEditor afHelpContent2) {
        this.afHelpContent2 = afHelpContent2;
    }

    public RichTextEditor getAfHelpContent2() {
        return afHelpContent2;
    }

    public void refreshTreeHelpActionListener(ActionEvent actionEvent) {
        this.treeMode = null;
        FacesUtil.addPartialTarget(this.tree);
    }

    /**
     * Create Help Desk As child 
     * @return
     */
    public void createHelpDeskActionListener(ActionEvent actionEvent) {
        HelpDesks selHelp = this.getSelectedRowData();
        if (selHelp == null) {
            FacesUtil.addError("Error", 
                               "Ban phai lua chon mot help de tao help con");
            return;
        }
        SASFacadeLocal facade = EJBUtil.lockupSASFacade();
        HelpDesks newHelp = facade.createDefaultChildHelpDesks(selHelp);
//        selHelp.addHelpDesks(newHelp); //TODO Viec add nay da co trong createDefaultChildHelpDesks . Sao no khong tac dung .
        //System.out.println("-------------------------------------------------");
        //System.out.println("createHelpDeskActionListener 1 " + this.treeMode);
        this.treeMode = null;
        //System.out.println("createHelpDeskActionListener 2 " + this.treeMode);
        //this.treeMode = this.getTreeMode();
        //System.out.println("createHelpDeskActionListener 3 " + this.treeMode);
        FacesUtil.addPartialTarget(this.tree);
    }
}
