package com.vc.web.ejb.sas.entities;

import java.io.Serializable;

import java.sql.Timestamp;

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

@Entity
@NamedQueries( { @NamedQuery(name = "News.findAll", 
                             query = "select o from News o")
        , 
        @NamedQuery(name = "News.findByNcaId", query = "select o from News o where o.newsCategories.ncaId=:ncaId order by o.newsId desc")
     //   , 
     //   @NamedQuery(name = "News.findTopByCategoryCode", query = "select o from News o where o.newsCategories.categoryCode=:categoryCode order by o.newsId desc")
        , 
        @NamedQuery(name = "News.findByCategoryCodeAndBookmarkCode", query = "select o from News o where o.newsCategories.categoryCode=:categoryCode and o.newsBookmarks.bookmarkCode=:bookmarkCode order by o.newsId desc")
        , 
        @NamedQuery(name = "News.findByNcaIdOrderByPriorityLevel", query = "select o from News o where o.newsCategories.ncaId=:ncaId order by o.priorityLevel desc")
        , 
        @NamedQuery(name = "News.findByNewsId", query = "select o from News o where o.newsId=:newsId order by o.newsId desc")
        , 
        @NamedQuery(name = "News.findAll.size", query = "select count(o) from News o")
        } )

@SequenceGenerator(name = "News_Seq", sequenceName = "News_Seq", 
                   allocationSize = 1)
public class News implements Serializable {
    private String content;


    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;


    @Column(name = "SHORT_CONTENT", nullable = false)
    private String shortContent;


    @Column(name = "CREATE_DATE", nullable = false)
    private Timestamp createDate;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFY_DATE")
    private Timestamp modifyDate;
    @Id
    @GeneratedValue(generator = "News_Seq")
    @Column(name = "NEWS_ID", nullable = false)
    private Long newsId;
    @Column(name = "PRIORITY_LEVEL", nullable = false)
    private Long priorityLevel;
    @Column(nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "NCA_ID")
    private NewsCategories newsCategories;
    @OneToMany(mappedBy = "news")
    private List<NewsRelations> newsRelationsList;
    @OneToMany(mappedBy = "news1")
    private List<NewsRelations> newsRelationsList1;

    @ManyToOne
    @JoinColumn(name = "BOOKMARK_CODE")
    private NewsBookmarks newsBookmarks;

    public News() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }


    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public Long getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Long priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NewsCategories getNewsCategories() {
        return newsCategories;
    }

    public void setNewsCategories(NewsCategories newsCategories) {
        this.newsCategories = newsCategories;
    }

    public List<NewsRelations> getNewsRelationsList() {
        return newsRelationsList;
    }

    public void setNewsRelationsList(List<NewsRelations> newsRelationsList) {
        this.newsRelationsList = newsRelationsList;
    }

    public NewsRelations addNewsRelations(NewsRelations newsRelations) {
        getNewsRelationsList().add(newsRelations);
        newsRelations.setNews(this);
        return newsRelations;
    }

    public NewsRelations removeNewsRelations(NewsRelations newsRelations) {
        getNewsRelationsList().remove(newsRelations);
        newsRelations.setNews(null);
        return newsRelations;
    }

    public List<NewsRelations> getNewsRelationsList1() {
        return newsRelationsList1;
    }

    public void setNewsRelationsList1(List<NewsRelations> newsRelationsList1) {
        this.newsRelationsList1 = newsRelationsList1;
    }

    public NewsRelations addNewsRelations1(NewsRelations newsRelations) {
        getNewsRelationsList1().add(newsRelations);
        newsRelations.setNews1(this);
        return newsRelations;
    }

    public NewsRelations removeNewsRelations1(NewsRelations newsRelations) {
        getNewsRelationsList1().remove(newsRelations);
        newsRelations.setNews1(null);
        return newsRelations;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setNewsBookmarks(NewsBookmarks newsBookmarks) {
        this.newsBookmarks = newsBookmarks;
    }

    public NewsBookmarks getNewsBookmarks() {
        return newsBookmarks;
    }
}
