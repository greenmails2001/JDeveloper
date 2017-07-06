package com.vc.web.backing.shoppingSas.beans;



/**
 * Mo ta mot Service-Package ma nguoi dung mua hang ...
 * @author vha 1-11-2008 .
 */
public class SpaOrder  {
    private Long spaId;
    private String packageCode;
    private String packageName;

    public void setSpaId(Long spaId) {
        this.spaId = spaId;
    }

    public Long getSpaId() {
        return spaId;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }



}
