package com.vc.web.backing.newsAdmin;

import com.evermind.server.ejb.EJBUtils;

import com.vc.web.ejb.sas.SASFacade;
import com.vc.web.ejb.sas.SASFacadeLocal;
import com.vc.web.ejb.sas.entities.News;
import com.vc.web.ejb.sas.entities.NewsCategories;
import com.vc.web.util.EJBUtil;

import com.vc.web.util.FacesUtil;
import com.vc.web.util.ViewUtil;

import java.sql.Timestamp;

import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.naming.NamingException;

import oracle.adf.controller.internal.binding.TaskFlowRegionModel;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTree;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.TreeModel;

public class NewsAdminManager {

    private TreeModel categoriesTreeModel;
    private NewsCategories currentNewsCategories;

    private RichInputText afCurrentNcaTitle;
    private RichRegion region1;

    private Long editNewsId;

    public NewsAdminManager() {
    }

    public void setCategoriesTreeModel(TreeModel categoriesTreeModel) {
        this.categoriesTreeModel = categoriesTreeModel;
    }

    public TreeModel getCategoriesTreeModel() {
        if (this.categoriesTreeModel != null) {
            return this.categoriesTreeModel;
        }
        SASFacadeLocal facade = EJBUtil.lockupSASFacade();

        List<NewsCategories> list = facade.queryNewsCategoriesFindAllRoot();
        this.categoriesTreeModel = 
                new ChildPropertyTreeModel(list, "newsCategoriesList");
        return categoriesTreeModel;
    }

    private NewsCategories getSelectedNewsCategories(RichTree table) {
        if (table == null) {
            return null;
        }
        RowKeySet keySet = table.getSelectedRowKeys();
        Iterator ite = keySet.iterator();
        Object key = ite.next();
        if (key == null) {
            return null;
        }
        table.setRowKey(key);
        return (NewsCategories)table.getRowData();
    }

    /** Nguoi dung lua chon tren cay News Categories 
     */
    public void newsCategoriesTreeSelectionListener(SelectionEvent event) {
        if (this.categoriesTreeModel == null) {
            return;
        }
        RichTree tree = (RichTree)event.getSource();
        NewsCategories category = this.getSelectedNewsCategories(tree);
        this.currentNewsCategories = category;     
        FacesUtil.addPartialTarget(this.afCurrentNcaTitle);
        FacesUtil.addPartialTarget(this.region1);
    }

    public String getCurrentNewsCategoriesTitle() {
        if (this.currentNewsCategories == null) {
            return null;
        }
        return this.currentNewsCategories.getTitle();
    }

    public Long getCurrentNcaId() {
        if (this.currentNewsCategories == null) {
            return null;
        }
        return this.currentNewsCategories.getNcaId();
    }

    public void setAfCurrentNcaTitle(RichInputText afCurrentNcaTitle) {
        this.afCurrentNcaTitle = afCurrentNcaTitle;
    }

    public RichInputText getAfCurrentNcaTitle() {
        return afCurrentNcaTitle;
    }

    public void setRegion1(RichRegion region1) {
        this.region1 = region1;
    }

    public RichRegion getRegion1() {
        return region1;
    }

    /** Save Create News */
    public String saveNewsCreationAction() {
        try {
            Object obj = 
                FacesUtil.resolveExpression("#{bindings.queryNewsFindAllIterator.currentRow.dataProvider}");
            News news = (News)obj;
            if (news == null) {
                return null;
            }
            if (this.currentNewsCategories == null) {
                FacesUtil.addMessage("Message", 
                                     "Ban phai lua chon nhom tin tuc truoc");
                return null;
            }
            news.setNewsCategories(this.currentNewsCategories);
            news.setCreateDate(new Timestamp(System.currentTimeMillis()));
            news.setCreatedBy("system");
            SASFacadeLocal sasFacade = EJBUtil.lockupSASFacade();
            sasFacade.persistEntity(obj);
        } catch (Exception e) {
            FacesUtil.addError("Error", e.getMessage());
            return null;
        }
        return "save";
    }

    /**
     * Tu trang Index chon Edit de hieu chinh mot bai viet (News) . Method nay lam nhiem vu 
     * @return
     */
    public String goEditNewsAction() {
        News news = 
            (News)FacesUtil.resolveExpression("#{bindings.queryNewsFindByNcaIdIterator.currentRow.dataProvider}");
        if (news == null) {
            return null;
        }
        this.editNewsId = news.getNewsId();
        return "edit";
    }

    public Long getEditNewsId() {
        return editNewsId;
    }
}
