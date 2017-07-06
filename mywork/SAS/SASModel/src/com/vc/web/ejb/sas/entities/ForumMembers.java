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
  @NamedQuery(name = "ForumMembers.findAll", query = "select o from ForumMembers o"),
  @NamedQuery(name = "ForumMembers.findAll.size", query = "select count(o) from ForumMembers o")
})
@Table(name = "FORUM_MEMBERS")
public class ForumMembers implements Serializable {
    @Column(nullable = false)
    private String email;
    private byte[] image;
    private String introduction;
    @Column(name="JOIN_DATE", nullable = false)
    private Timestamp joinDate;
    @Column(nullable = false)
    private String locked;
    @Id
    @Column(name="MEMBER_ID", nullable = false)
    private Long memberId;
    @Column(nullable = false)
    private String password;
    @Column(name="USER_NAME", nullable = false)
    private String userName;
    @OneToMany(mappedBy = "forumMembers")
    private List<ForumMessages> forumMessagesList;

    public ForumMembers() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ForumMessages> getForumMessagesList() {
        return forumMessagesList;
    }

    public void setForumMessagesList(List<ForumMessages> forumMessagesList) {
        this.forumMessagesList = forumMessagesList;
    }

    public ForumMessages addForumMessages(ForumMessages forumMessages) {
        getForumMessagesList().add(forumMessages);
        forumMessages.setForumMembers(this);
        return forumMessages;
    }

    public ForumMessages removeForumMessages(ForumMessages forumMessages) {
        getForumMessagesList().remove(forumMessages);
        forumMessages.setForumMembers(null);
        return forumMessages;
    }
}
