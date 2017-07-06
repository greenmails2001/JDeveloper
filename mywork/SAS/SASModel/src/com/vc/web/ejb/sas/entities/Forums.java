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

@Entity
@NamedQueries({
  @NamedQuery(name = "Forums.findAll", query = "select o from Forums o"),
  @NamedQuery(name = "Forums.findByOwnerCode", query = "select o from Forums o where o.forumOwners.forumOwnerCode=:forumOwnerCode"),
  @NamedQuery(name = "Forums.findAll.size", query = "select count(o) from Forums o")
})
public class Forums implements Serializable {
    @Column(nullable = false)
    private String description;
    @Id
    @Column(name="FORUM_ID", nullable = false)
    private Long forumId;
    @Column(name="START_DATE", nullable = false)
    private Timestamp startDate;
    @Column(nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "FORUM_OWNER_CODE")
    private ForumOwners forumOwners;
    @OneToMany(mappedBy = "forums")
    private List<ForumCategories> forumCategoriesList;

    public Forums() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getForumId() {
        return forumId;
    }

    public void setForumId(Long forumId) {
        this.forumId = forumId;
    }


    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ForumOwners getForumOwners() {
        return forumOwners;
    }

    public void setForumOwners(ForumOwners forumOwners) {
        this.forumOwners = forumOwners;
    }

    public List<ForumCategories> getForumCategoriesList() {
        return forumCategoriesList;
    }

    public void setForumCategoriesList(List<ForumCategories> forumCategoriesList) {
        this.forumCategoriesList = forumCategoriesList;
    }

    public ForumCategories addForumCategories(ForumCategories forumCategories) {
        getForumCategoriesList().add(forumCategories);
        forumCategories.setForums(this);
        return forumCategories;
    }

    public ForumCategories removeForumCategories(ForumCategories forumCategories) {
        getForumCategoriesList().remove(forumCategories);
        forumCategories.setForums(null);
        return forumCategories;
    }
}
