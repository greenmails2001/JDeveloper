package com.vc.web.backing;

import com.vc.web.ejb.sas.SASFacade;
import com.vc.web.ejb.sas.SASFacadeLocal;
import com.vc.web.ejb.sas.entities.News;
import com.vc.web.util.EJBUtil;
import com.vc.web.util.FacesUtil;

import com.vc.web.util.ViewUtil;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.input.RichTextEditor;
import oracle.adf.view.rich.component.rich.nav.RichCommandNavigationItem;
import oracle.adf.view.rich.component.rich.nav.RichNavigationPane;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

public class NewsViewerHandler {

    private RichOutputLabel afNewsTitle;
    private RichTextEditor afNewsContent;
    private News news;

    public NewsViewerHandler() {
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

    public void setNews(News news) {
        this.news = news;
    }

    public News getNews() {
        if (this.news == null) {
            this.news = new News();
        }
        return news;
    }
}
