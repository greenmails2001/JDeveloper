package com.vc.web.ejb.perp.entities;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "AppUsers.findAll", 
                             query = "select o from AppUsers o")
        , 
        @NamedQuery(name = "AppUsers.findByUserNameEncrytPassword", query = "select o from AppUsers o where o.userName= :userName and o.password=:password")
        , 
        @NamedQuery(name = "AppUsers.findAll.size", query = "select count(o) from AppUsers o")
        } )
@Table(name = "APP_USERS")
public class AppUsers implements Serializable {
    @Column(name = "APP_NAME")
    private String appName;
    private String attribute1;
    private String attribute10;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String attribute6;
    private String attribute7;
    private String attribute8;
    private String attribute9;
    @Id
    @Column(name = "AUS_ID", nullable = false)
    private Long ausId;
    @Column(name = "CHART_ADMIN")
    private String chartAdmin;
    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;
    @Column(name = "CREATE_DATE", nullable = false)
    private Timestamp createDate;
    @Column(name = "FORUM_ADMIN")
    private String forumAdmin;
    @Column(name = "KI_ADMIN")
    private String kiAdmin;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFY_DATE")
    private Timestamp modifyDate;
    @Column(nullable = false)
    private String password;
    @Column(name = "PRO_ADMIN")
    private String proAdmin;
    private String status;
    @Column(name = "TIMECHECK_ADMIN")
    private String timecheckAdmin;
    @Column(name = "USER_NAME", nullable = false)
    private String userName;
    @ManyToOne
    @JoinColumn(name = "EMP_ID")
    private Employees employees;

    public AppUsers() {
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    public Long getAusId() {
        return ausId;
    }

    public void setAusId(Long ausId) {
        this.ausId = ausId;
    }

    public String getChartAdmin() {
        return chartAdmin;
    }

    public void setChartAdmin(String chartAdmin) {
        this.chartAdmin = chartAdmin;
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


    public String getForumAdmin() {
        return forumAdmin;
    }

    public void setForumAdmin(String forumAdmin) {
        this.forumAdmin = forumAdmin;
    }

    public String getKiAdmin() {
        return kiAdmin;
    }

    public void setKiAdmin(String kiAdmin) {
        this.kiAdmin = kiAdmin;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProAdmin() {
        return proAdmin;
    }

    public void setProAdmin(String proAdmin) {
        this.proAdmin = proAdmin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimecheckAdmin() {
        return timecheckAdmin;
    }

    public void setTimecheckAdmin(String timecheckAdmin) {
        this.timecheckAdmin = timecheckAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }
}
