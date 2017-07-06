package com.vc.web.backing;

import com.vc.web.ejb.sas.SASFacadeLocal;
import com.vc.web.ejb.sas.entities.Countries;
import com.vc.web.ejb.sas.entities.HelpDesks;
import com.vc.web.util.EJBUtil;
import com.vc.web.util.FacesUtil;
import com.vc.web.util.ViewUtil;

import java.util.List;

import javax.naming.NamingException;

import oracle.adf.view.rich.component.rich.data.RichTree;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlActionBinding;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.TreeModel;


public class Test {
    Object rowKey;
    private RichTree tree;
    private TreeModel treeMode;

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
        SASFacadeLocal local = EJBUtil.lockupSASFacade();
        List<HelpDesks> list = local.queryHelpDesksFindByCode2("SAS");
        this.treeMode = new ChildPropertyTreeModel(list, "helpDesksList");
        return treeMode;
    }

    public void setRowKey(Object rowKey) {
        this.rowKey = rowKey;
    }

    public Object getRowKey() {
        return rowKey;
    }

    public Long getCountries() {
        return null;
    }

    public void setCountries(Long countries) {

    }

    public String a() {
        Object obj = 
            FacesUtil.resolveExpression("#{bindings.industries.inputValue}");
        System.out.println("OBJECT :" + obj);
        return null;
    }
}
