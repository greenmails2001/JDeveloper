package com.vc.web.beans;

import com.vc.web.PerpSystemInfo;

import java.sql.Timestamp;

public class AppUserInfo {


    private Long ausId;
    private String userName;
    private String password;
    private String encrytPassword;
    private Long subId;

    private String activatedCode;
    private String packageCode;
    private Timestamp startDate;
    private Timestamp endDate;
    private Timestamp subscribeDate;

    private PerpSystemInfo perpSystemInfo;

    private boolean login = false;

    public AppUserInfo() {
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEncrytPassword(String encrytPassword) {
        this.encrytPassword = encrytPassword;
    }

    public String getEncrytPassword() {
        return encrytPassword;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public boolean isLogin() {
        return login;
    }

    public void setAusId(Long ausId) {
        this.ausId = ausId;
    }

    public Long getAusId() {
        return ausId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Long getSubId() {
        return subId;
    }


    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setSubscribeDate(Timestamp subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public Timestamp getSubscribeDate() {
        return subscribeDate;
    }

    public void setActivatedCode(String activatedCode) {
        this.activatedCode = activatedCode;
    }

    public String getActivatedCode() {
        return activatedCode;
    }

    public void setPerpSystemInfo(PerpSystemInfo perpSystemInfo) {
        this.perpSystemInfo = perpSystemInfo;
    }

    public PerpSystemInfo getPerpSystemInfo() {
        return perpSystemInfo;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getPackageCode() {
        return packageCode;
    }
    

}
