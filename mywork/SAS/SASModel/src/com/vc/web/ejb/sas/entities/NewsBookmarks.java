package com.vc.web.ejb.sas.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "NewsBookmarks.findAll", query = "select o from NewsBookmarks o"),
  @NamedQuery(name = "NewsBookmarks.findAll.size", query = "select count(o) from NewsBookmarks o")
})
@Table(name = "NEWS_BOOKMARKS")
public class NewsBookmarks implements Serializable {
    @Id
    @Column(name="BOOKMARK_CODE", nullable = false)
    private String bookmarkCode;
    private String description;
    @OneToMany(mappedBy = "newsBookmarks")
    private List<News> newsList;
    
    public NewsBookmarks() {
    }

    public String getBookmarkCode() {
        return bookmarkCode;
    }

    public void setBookmarkCode(String bookmarkCode) {
        this.bookmarkCode = bookmarkCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public List<News> getNewsList() {
        return newsList;
    }
}
