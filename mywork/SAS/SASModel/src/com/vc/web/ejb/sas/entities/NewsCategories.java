package com.vc.web.ejb.sas.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "NewsCategories.findAll", 
                             query = "select o from NewsCategories o")
        , 
        @NamedQuery(name = "NewsCategories.findAllRoot", query = "select o from NewsCategories o where o.newsCategories is null")
        , 
        @NamedQuery(name = "NewsCategories.findByNcaIdParent", query = "select o from NewsCategories o where o.newsCategories.ncaId=:ncaIdParent")
        , 
        @NamedQuery(name = "NewsCategories.findByCategoryCodeParent", query = "select o from NewsCategories o where o.newsCategories.categoryCode=:categoryCode")
        , 
        @NamedQuery(name = "NewsCategories.findAll.size", query = "select count(o) from NewsCategories o")
        } )
@Table(name = "NEWS_CATEGORIES")

@SequenceGenerator(name = "Nca_Seq", sequenceName = "Nca_Seq", 
                   allocationSize = 1)
public class NewsCategories implements Serializable {
    private String description;
    @Id
    @GeneratedValue(generator = "Nca_Seq")
    @Column(name = "NCA_ID", nullable = false)
    private Long ncaId;

    @Column(name = "CATEGORY_CODE", nullable = false, unique = true)
    private String categoryCode;

    @Column(nullable = false)
    private String title;
    @OneToMany(mappedBy = "newsCategories")
    private List<News> newsList;
    @ManyToOne
    @JoinColumn(name = "NCA_ID_PARENT")
    private NewsCategories newsCategories;
    @OneToMany(mappedBy = "newsCategories")
    private List<NewsCategories> newsCategoriesList;

    public NewsCategories() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNcaId() {
        return ncaId;
    }

    public void setNcaId(Long ncaId) {
        this.ncaId = ncaId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public News addNews(News news) {
        getNewsList().add(news);
        news.setNewsCategories(this);
        return news;
    }

    public News removeNews(News news) {
        getNewsList().remove(news);
        news.setNewsCategories(null);
        return news;
    }

    public NewsCategories getNewsCategories() {
        return newsCategories;
    }

    public void setNewsCategories(NewsCategories newsCategories) {
        this.newsCategories = newsCategories;
    }

    public List<NewsCategories> getNewsCategoriesList() {
        return newsCategoriesList;
    }

    public void setNewsCategoriesList(List<NewsCategories> newsCategoriesList) {
        this.newsCategoriesList = newsCategoriesList;
    }

    public NewsCategories addNewsCategories(NewsCategories newsCategories) {
        getNewsCategoriesList().add(newsCategories);
        newsCategories.setNewsCategories(this);
        return newsCategories;
    }

    public NewsCategories removeNewsCategories(NewsCategories newsCategories) {
        getNewsCategoriesList().remove(newsCategories);
        newsCategories.setNewsCategories(null);
        return newsCategories;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }
}
