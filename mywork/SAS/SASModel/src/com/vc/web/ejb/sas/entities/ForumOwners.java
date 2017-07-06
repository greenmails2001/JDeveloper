package com.vc.web.ejb.sas.entities;

import java.io.Serializable;

import java.sql.Timestamp;

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
  @NamedQuery(name = "ForumOwners.findAll", query = "select o from ForumOwners o"),
  @NamedQuery(name = "ForumOwners.findAll.size", query = "select count(o) from ForumOwners o")
})
@Table(name = "FORUM_OWNERS")
public class ForumOwners implements Serializable {
    @Column(nullable = false)
    private String description;
    @Id
    @Column(name="FORUM_OWNER_CODE", nullable = false)
    private String forumOwnerCode;
    @Column(nullable = false)
    private String name;
    @Column(name="START_DATE", nullable = false)
    private Timestamp startDate;
    @OneToMany(mappedBy = "forumOwners")
    private List<Forums> forumsList;

    public ForumOwners() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getForumOwnerCode() {
        return forumOwnerCode;
    }

    public void setForumOwnerCode(String forumOwnerCode) {
        this.forumOwnerCode = forumOwnerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public List<Forums> getForumsList() {
        return forumsList;
    }

    public void setForumsList(List<Forums> forumsList) {
        this.forumsList = forumsList;
    }

    public Forums addForums(Forums forums) {
        getForumsList().add(forums);
        forums.setForumOwners(this);
        return forums;
    }

    public Forums removeForums(Forums forums) {
        getForumsList().remove(forums);
        forums.setForumOwners(null);
        return forums;
    }
}
