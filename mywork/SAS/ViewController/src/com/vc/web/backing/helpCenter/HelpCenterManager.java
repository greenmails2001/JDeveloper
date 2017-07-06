package com.vc.web.backing.helpCenter;

import com.vc.web.ejb.sas.SASFacadeLocal;
import com.vc.web.ejb.sas.entities.HelpDesks;
import com.vc.web.ejb.sas.entities.NewsCategories;
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


/**
 * @author vha (hatv@pythis.com) 16-12-2008 
 */
public class HelpCenterManager {
    /** Root Help Code se duoc hien thi */
    private String helpCode = null;
    private RichTree helpTree;

    private HelpDesks currentHelp;

    private TreeModel helpTreeModel;

    private RichTextEditor afHelpContent;
    private RichActiveOutputText afHelpTitle;

    public HelpCenterManager() {
    }


    public void setHelpTree(RichTree helpTree) {
        this.helpTree = helpTree;
    }

    public RichTree getHelpTree() {
        return helpTree;
    }

    public void setHelpTreeModel(TreeModel helpTreeModel) {
        this.helpTreeModel = helpTreeModel;
    }

    public TreeModel getHelpTreeModel() {
        if (this.helpTreeModel == null) {
            SASFacadeLocal sasFacade = EJBUtil.lockupSASFacade();
            List<HelpDesks> list = 
                sasFacade.queryHelpDesksFindAllRootByCode(this.helpCode);
            this.helpTreeModel = 
                    new ChildPropertyTreeModel(list, "helpDesksList");

        }
        return helpTreeModel;
    }

    public void setCurrentHelp(HelpDesks currentHelp) {
        this.currentHelp = currentHelp;
    }

    public HelpDesks getCurrentHelp() {
        if (this.currentHelp == null) {
            return new HelpDesks();
        }
        return currentHelp;
    }

    private HelpDesks getSelectedHelpDesk(RichTree tree) {
        if (tree == null) {
            return null;
        }
        RowKeySet keySet = tree.getSelectedRowKeys();
        Iterator ite = keySet.iterator();
        Object key = ite.next();
        if (key == null) {
            return null;
        }
        tree.setRowKey(key);
        return (HelpDesks)tree.getRowData();
    }


    public void treeHelpSelectionListener(SelectionEvent event) {
        RichTree tree = (RichTree)event.getSource();
        HelpDesks help = this.getSelectedHelpDesk(tree);
        this.currentHelp = help;

        FacesUtil.addPartialTarget(this.afHelpTitle);
        FacesUtil.addPartialTarget(this.afHelpContent);
    }

    public void setAfHelpContent(RichTextEditor afHelpContent) {
        this.afHelpContent = afHelpContent;
    }

    public RichTextEditor getAfHelpContent() {
        return afHelpContent;
    }

    public void setAfHelpTitle(RichActiveOutputText afHelpTitle) {
        this.afHelpTitle = afHelpTitle;
    }

    public RichActiveOutputText getAfHelpTitle() {
        return afHelpTitle;
    }

    public void helpTreeRefreshActionListener(ActionEvent actionEvent) {
        this.helpTreeModel=null;
        FacesUtil.addPartialTarget(this.helpTree);
        
    }
}
