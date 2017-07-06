package com.vc.web.backing.news.beans;

import com.vc.web.ejb.sas.SASFacade;
import com.vc.web.ejb.sas.SASFacadeLocal;
import com.vc.web.ejb.sas.entities.News;
import com.vc.web.ejb.sas.entities.NewsCategories;

import com.vc.web.util.EJBUtil;

import java.util.List;


/**
 * Class nay la Luu tru mot cap NewsCategories va Lastest list of News .
 * Duoc su dung trong trang home.jsff cua 'Tin Tuc su kien' .
 * @author vha 21-12-2008 .
 */
public class NcaAndLastestNews { //latest
    private NewsCategories newsCategories;

    public NcaAndLastestNews(NewsCategories newsCategories) {
        this.newsCategories = newsCategories;
    }

    public NewsCategories getNewsCategories() {
        return newsCategories;
    }


    public List<News> getLastestNewsList() {
        SASFacadeLocal facade = EJBUtil.lockupSASFacade();
        if (this.newsCategories == null) {
            return null;
        }
        //TODO HardCode .!
        return facade.queryNewsFindByNcaId(this.newsCategories.getNcaId(), 3);
    }
}
