package com.vc.web.beans;


public class PerpSessionLogin {
    private String userName;
    private String password;
    private boolean valid = false;
    private AppUserInfo appUserInfo;

    public PerpSessionLogin() {
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

    public boolean isValid() {
        return valid;
    }

    public void setAppUserInfo(AppUserInfo appUserInfo) {
        this.appUserInfo = appUserInfo;
    }

    public AppUserInfo getAppUserInfo() {
        if (this.appUserInfo == null) {
            this.appUserInfo = new AppUserInfo();
        }
        return appUserInfo;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    public boolean isValidSetting() {
        return true;
    }
}
