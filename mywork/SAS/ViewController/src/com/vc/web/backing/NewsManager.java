package com.vc.web.backing;

import com.vc.web.backing.news.beans.NcaAndLastestNews;
import com.vc.web.ejb.sas.SASFacadeLocal;
import com.vc.web.ejb.sas.entities.News;
import com.vc.web.ejb.sas.entities.NewsCategories;
import com.vc.web.util.EJBUtil;
import com.vc.web.util.FacesUtil;
import com.vc.web.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.internal.binding.TaskFlowRegionModel;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandNavigationItem;

public class NewsManager {

    private String caseFlow;
    private RichRegion newsRegion;
    private RichPanelGroupLayout afNewsPanelGroup;

    public NewsManager() {
    }

    public String getCaseFlow() {
        // System.out.println("Flow Case :"+this.caseFlow);
        return caseFlow;
    }


    public void selectNewsCategoriesActionListener(ActionEvent event) {
        Object obj = event.getSource();
        RichCommandNavigationItem navItem = (RichCommandNavigationItem)obj;
        String itemId = navItem.getId();
        if (itemId.equals("ncaId_1")) {
            this.caseFlow = "home";
        } else {
            this.caseFlow = "news";
        }
        TaskFlowRegionModel model = 
            (TaskFlowRegionModel)FacesUtil.resolveExpression("#{bindings.newsflow1.regionModel}");
        model.refresh(FacesContext.getCurrentInstance());
        if (this.afNewsPanelGroup != null) {
            FacesUtil.addPartialTarget(this.afNewsPanelGroup);
        }

    }

    public void setNewsRegion(RichRegion newsRegion) {
        this.newsRegion = newsRegion;
    }

    public RichRegion getNewsRegion() {
        return newsRegion;
    }

    public void setAfNewsPanelGroup(RichPanelGroupLayout afNewsPanelGroup) {
        this.afNewsPanelGroup = afNewsPanelGroup;
    }

    public RichPanelGroupLayout getAfNewsPanelGroup() {
        return afNewsPanelGroup;
    }

    public void selectNewsActionListener(ActionEvent actionEvent) {
        this.caseFlow = "news 222";

    }

    /**
     * Lay het News Categories va mot so tin tuc moi nhat 
     * cua Nhom tin con cua Nhom 'Su Kien & Tin Tuc'.
     * @return
     */
    public List<NcaAndLastestNews> getNcaAndLastestNewsChildOfSKTN() {
        SASFacadeLocal facade = EJBUtil.lockupSASFacade();
        // TODO HardCode !
        List<NewsCategories> list=facade.queryNewsCategoriesFindByNcaIdParent(new Long(12));
        List<NcaAndLastestNews> retList= new ArrayList<NcaAndLastestNews>();
        for(NewsCategories nca:list) {
            NcaAndLastestNews ncaNews= new NcaAndLastestNews(nca);
            retList.add(ncaNews);
        }
        return retList;
    }
    
    /** Hien thi mot vai bai viet ben phai .
     *  Nhung bai Tin tuc quan tam , moi bat .
     */
    public List<News> getNewsForRightPage() {
        SASFacadeLocal facade= EJBUtil.lockupSASFacade();
        return facade.queryNewsFindByCategoryCodeAndBookmarkCode("ERP","RIGHT");       
    }
    
   
}
