package com.vc.web.ejb.sas.entities;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "ForumCategories.findAll", query = "select o from ForumCategories o"),
  @NamedQuery(name = "ForumCategories.findAll.size", query = "select count(o) from ForumCategories o")
})
@Table(name = "FORUM_CATEGORIES")
public class ForumCategories implements Serializable {
    @Column(name="CREATED_BY", nullable = false)
    private String createdBy;
    @Column(name="CREATE_DATE", nullable = false)
    private Timestamp createDate;
    @Column(nullable = false)
    private String description;
    @Id
    @Column(name="FCA_ID", nullable = false)
    private Long fcaId;
    @Column(nullable = false)
    private String title;
    @OneToMany(mappedBy = "forumCategories")
    private List<ForumMessages> forumMessagesList;
    @ManyToOne
    @JoinColumn(name = "FOR_ID")
    private Forums forums;

    public ForumCategories() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFcaId() {
        return fcaId;
    }

    public void setFcaId(Long fcaId) {
        this.fcaId = fcaId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ForumMessages> getForumMessagesList() {
        return forumMessagesList;
    }

    public void setForumMessagesList(List<ForumMessages> forumMessagesList) {
        this.forumMessagesList = forumMessagesList;
    }

    public ForumMessages addForumMessages(ForumMessages forumMessages) {
        getForumMessagesList().add(forumMessages);
        forumMessages.setForumCategories(this);
        return forumMessages;
    }

    public ForumMessages removeForumMessages(ForumMessages forumMessages) {
        getForumMessagesList().remove(forumMessages);
        forumMessages.setForumCategories(null);
        return forumMessages;
    }

    public Forums getForums() {
        return forums;
    }

    public void setForums(Forums forums) {
        this.forums = forums;
    }
}
