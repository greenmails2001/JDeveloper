package com.vc.web.backing;

import com.vc.web.ejb.sas.SASFacadeLocal;
import com.vc.web.ejb.sas.entities.News;
import com.vc.web.ejb.sas.entities.NewsCategories;
import com.vc.web.util.EJBUtil;
import com.vc.web.util.FacesUtil;

import com.vc.web.util.ViewUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.input.RichTextEditor;
import oracle.adf.view.rich.component.rich.nav.RichCommandNavigationItem;
import oracle.adf.view.rich.component.rich.nav.RichNavigationPane;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

/**
 * Dieu khien hien thi du lieu voi cac Tin tuc thong thuong trong cac chuyen muc Gioi Thieu , 
 * San pham , Dich vu , Doi tac , Tu van hoi dap .
 * @author vha (hatv@pythis.com) .
 */
public class CommonNewsHandler {

    private RichOutputLabel afNewsTitle;
    private RichTextEditor afNewsContent;
    private News news;
    private RichNavigationPane afLeftNavPane;
    private Long ncaId;

    public CommonNewsHandler() {
    }

    /** Nguoi dung lua chon cac Link (Gioi thieu , San pham , Dich vu , Doi tac , Tu van hoi dap)
     * tren top Navigation Panel thi se hien thi cac children News truc tiep cua no de 
     * hien thi nhu NavigationPanel phia goc trai man hinh .
     */
    public void viewNewsCategoriesActionListener(ActionEvent event) {
        RichCommandNavigationItem navItem = 
            (RichCommandNavigationItem)event.getSource();
        RichNavigationPane navPanel = (RichNavigationPane)navItem.getParent();
        for (UIComponent component : navPanel.getChildren()) {
            RichCommandNavigationItem nav = 
                (RichCommandNavigationItem)component;
            nav.setSelected(false);
        }
        String id = navItem.getId();
        if (id == null || !id.startsWith("ncaId_")) {
            FacesUtil.addError("Developer Note", 
                               "Id phai co dinh dang ncaId_... ");
            return;
        }
        Long ncaId = Long.parseLong(id.substring("ncaId_".length()));
        this.ncaId = ncaId;
        this.news = null;
        navItem.setSelected(true);
        FacesUtil.addPartialTarget(navPanel);
    }

    public List<News> getLeftNewsList() {
        SASFacadeLocal sasFacade = EJBUtil.lockupSASFacade();
        NewsCategories nca = sasFacade.queryNewsCategoriesFindByNcaId(ncaId);

        if (nca == null) {
            FacesUtil.addError("Error", 
                               "NewsCategories voi Id " + ncaId + " khong tim thay");
            return new ArrayList<News>();
        }
        List<News> list = 
            sasFacade.queryNewsFindByNcaIdOrderByPriorityLevel(ncaId);

        return list;
    }


    public void viewNewsActionListener(ActionEvent event) {
        Object obj = event.getSource();
        RichCommandNavigationItem navItem = (RichCommandNavigationItem)obj;
        RichNavigationPane navPanel = (RichNavigationPane)navItem.getParent();
        for (UIComponent component : navPanel.getChildren()) {
            RichCommandNavigationItem nav = 
                (RichCommandNavigationItem)component;
            nav.setSelected(false);
        }
        String id = navItem.getId();
        if (id == null || !id.startsWith("newsId_")) {
            FacesUtil.addError("Developer Note", 
                               "Id phai co dinh dang newsId_... ");
            return;
        }
        Long newsId = Long.parseLong(id.substring("newsId_".length()));
        SASFacadeLocal sasFacade = EJBUtil.lockupSASFacade();
        News news1 = sasFacade.queryNewsFindByNewsId(newsId);
        if (news1 == null) {
            FacesUtil.addError("Error", 
                               "Bai viet voi ma so " + newsId + " khong tim thay");
            return;
        }
        this.news = news1;

        navItem.setSelected(true);
        FacesUtil.addPartialTarget(navPanel);
        FacesUtil.addPartialTarget(this.afNewsTitle);
        FacesUtil.addPartialTarget(this.afNewsContent);
    }


    public void setAfNewsTitle(RichOutputLabel afNewsTitle) {
        this.afNewsTitle = afNewsTitle;
    }

    public RichOutputLabel getAfNewsTitle() {
        return afNewsTitle;
    }

    public void setAfNewsContent(RichTextEditor afNewsContent) {
        this.afNewsContent = afNewsContent;
    }

    public RichTextEditor getAfNewsContent() {
        return afNewsContent;
    }
    
    public String getNcaTitle() {
        if(this.ncaId==null) {
            return null;
        }
        SASFacadeLocal sasFacade = EJBUtil.lockupSASFacade();
        NewsCategories nca=sasFacade.queryNewsCategoriesFindByNcaId(this.ncaId);
        if(nca==null) {
            return null;
        }
        return nca.getTitle();
        
    }

    public News getNews() {
        if (this.news == null) {           
            SASFacadeLocal facade = EJBUtil.lockupSASFacade();
            News news1 = 
                facade.queryNewsFindFirstByNcaIdOrderByPriorityLevel(this.ncaId);
            if (news1 != null) {
                this.news = news1;
            } else {
                this.news = new News();
            }
        }
        return news;
    }

    public void setAfLeftNavPane(RichNavigationPane afLeftNavPane) {
        this.afLeftNavPane = afLeftNavPane;
    }

    public RichNavigationPane getAfLeftNavPane() {
        return afLeftNavPane;
    }
}
