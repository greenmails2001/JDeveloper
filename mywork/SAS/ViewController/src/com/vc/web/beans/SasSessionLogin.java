package com.vc.web.beans;

public class SasSessionLogin {
    private Long accId;
    private String userName;
    private String password;
    private boolean valid=false;
    
    /** Account ma nguoi dung moi tao */
    private Long newAccId;

    public SasSessionLogin() {
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

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setAccId(Long accId) {
        this.accId = accId;
    }

    public Long getAccId() {
        return accId;
    }

    public void setNewAccId(Long newAccId) {
        this.newAccId = newAccId;
    }

    public Long getNewAccId() {
        return newAccId;
    }
}
