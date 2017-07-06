package com.vc.web;

public enum PerpSystemErrorCode {
    
    
    CLASS_NOT_FOUND("Connection Driver Class not found"),

    DS_NOT_FOUND("Connection datasource not found"),
    NAMING_CONTEXT("Naming context error"),
    NOT_GET_DS_CONNECTION("Not get Datasource Connection") ,
    /** Khong tim thay thong tin config cua Subscription */
    PERP_SYSTEM_NOT_FOUND("Perp Connection information not found"),
    PERP_SYSTEM_CONFIG_FAILURE("Perp Connection config failure"),

    /** Khong tim thay Subscription */
    SUBSCRIPTION_NOT_FOUND("Subscription not found"),
    CONNECTION_FAILURE("Connection failure"),
    SQL_EXCEPTION("SQL Exception"),
    UNKNOWN("");
    
    private String errorCode;
    PerpSystemErrorCode(String errorCode) {
        this.errorCode=errorCode;
    }

}
