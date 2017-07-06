package com.vc.web.ejb.sas.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "NewsRelations.findAll", query = "select o from NewsRelations o"),
  @NamedQuery(name = "NewsRelations.findAll.size", query = "select count(o) from NewsRelations o")
})
@Table(name = "NEWS_RELATIONS")
public class NewsRelations implements Serializable {
    private String description;
    @Id
    @Column(name="NRE_ID", nullable = false)
    private Long nreId;
    @ManyToOne
    @JoinColumn(name = "NEWS_ID_RELATION")
    private News news;
    @ManyToOne
    @JoinColumn(name = "NEWS_ID")
    private News news1;

    public NewsRelations() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Long getNreId() {
        return nreId;
    }

    public void setNreId(Long nreId) {
        this.nreId = nreId;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public News getNews1() {
        return news1;
    }

    public void setNews1(News news1) {
        this.news1 = news1;
    }
}
