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
  @NamedQuery(name = "ForumMessages.findAll", query = "select o from ForumMessages o"),
  @NamedQuery(name = "ForumMessages.findAllRootByFcaId",
              query = "select o from ForumMessages o where o.forumCategories.fcaId=:fcaId and o.forumMessages is null order by o.createDate desc"),
  @NamedQuery(name = "ForumMessages.findAll.size", query = "select count(o) from ForumMessages o")
})
@Table(name = "FORUM_MESSAGES")
public class ForumMessages implements Serializable {
    @Column(nullable = false)
    private char[] content;
    @Column(name="CREATE_DATE", nullable = false)
    private Timestamp createDate;
    @Id
    @Column(name="MESSAGE_ID", nullable = false)
    private Long messageId;
    @Column(name="MODIFIED_COUNT", nullable = false)
    private Long modifiedCount;
    @Column(name="MODIFY_DATE")
    private Timestamp modifyDate;
    @Column(nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "FCA_PARENT_ID")
    private ForumMessages forumMessages;
    @OneToMany(mappedBy = "forumMessages")
    private List<ForumMessages> forumMessagesList;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private ForumMembers forumMembers;
    @ManyToOne
    @JoinColumn(name = "FCA_ID")
    private ForumCategories forumCategories;

    public ForumMessages() {
    }

    public char[] getContent() {
        return content;
    }

    public void setContent(char[] content) {
        this.content = content;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }


    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getModifiedCount() {
        return modifiedCount;
    }

    public void setModifiedCount(Long modifiedCount) {
        this.modifiedCount = modifiedCount;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ForumMembers getForumMembers() {
        return forumMembers;
    }

    public void setForumMembers(ForumMembers forumMembers) {
        this.forumMembers = forumMembers;
    }

    public ForumCategories getForumCategories() {
        return forumCategories;
    }

    public void setForumCategories(ForumCategories forumCategories) {
        this.forumCategories = forumCategories;
    }
    
    public ForumMessages getForumMessages() {
        return forumMessages;
    }

    public void setForumMessages(ForumMessages forumMessages) {
        this.forumMessages = forumMessages;
    }

    public List<ForumMessages> getForumMessagesList() {
        return forumMessagesList;
    }

    public void setForumMessagesList(List<ForumMessages> forumMessagesList) {
        this.forumMessagesList = forumMessagesList;
    }
}
