package com.vc.web.perp;

import java.io.Serializable;

import java.util.List;
import java.util.TreeMap;

public class AppInfo implements Serializable {
    private String appCode;
    private String appName;
  

    public AppInfo() {
    }

    public AppInfo(String appCode, String appName) {
        this.appCode = appCode;
        this.appName = appName;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }
}
