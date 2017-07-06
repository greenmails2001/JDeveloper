package com.vc.web;

import com.vc.web.ejb.perp.entities.OrganizationUnits;

import java.io.Serializable;

/**
 * Trong bang System_Paras cot Oun_Id null tuong uong voi viec set dat 
 * Voi class nay Long ounId mo ta Spa.Oun_Id gia tri nay co the null . 
 * @author vha .
 */
public class SpaOun implements Serializable {
    public static final String KEY_CO_NAME = "CO_NAME";
    public static final String KEY_CO_LOGO = "CO_LOGO";
    public static final String KEY_CO_ADDRESS = "CO_ADDRESS";
    public static final String KEY_CO_TAX_NO = "TAX_NO";
    public static final String KEY_CO_TAX_FORM = "TAX_FORM";
    public static final String KEY_CO_TEL_NO = "TEL_NO";
    public static final String KEY_CO_FAX_NO = "FAX_NO";

    /** Gia tri Oun_Id cua bang System_Paras */

    private Long ounId;
    // Nhom Co_Name 
    private Long spaIdCoName;
    private String coName;
    private String coNameDesc;
    // Nhom Co_Logo
    private Long spaIdCoLogo;
    private String coLogo;
    private String coLogoDesc;
    // Nhom Co Address
    private Long spaIdCoAddress;
    private String coAddress;
    private String coAddressDesc;
    // Nhom Tax No
    private Long spaIdTaxNo;
    private String coTaxNo;
    private String coTaxNoDesc;
    // Nhom Tax Form 
    private Long spaIdTaxForm;
    private String coTaxForm;
    private String coTaxFormDesc;
    // Nhom Tel No 
    private Long spaIdTelNo;
    private String coTelNo;
    private String coTelNoDesc;
    // Nhom Fax No 
    private Long spaIdFaxNo;
    private String coFaxNo;
    private String coFaxNoDesc;

    private Long ounOunId;
    private String ounOunNumber;
    private String ounOunName;
    
    private boolean rootOun;

    public SpaOun() {
    }

    public void setOunId(Long ounId) {
        this.ounId = ounId;
    }

    public Long getOunId() {
        return ounId;
    }

    public void setCoName(String coName) {
        this.coName = coName;
    }


    public String getCoName() {
        return coName;
    }

    public void setCoLogo(String coLogo) {
        this.coLogo = coLogo;
    }

    public String getCoLogo() {
        return coLogo;
    }

    public void setCoAddress(String coAddress) {
        this.coAddress = coAddress;
    }

    public String getCoAddress() {
        return coAddress;
    }

    public void setCoTaxNo(String coTaxNo) {
        this.coTaxNo = coTaxNo;
    }

    public String getCoTaxNo() {
        return coTaxNo;
    }

    public void setCoTaxForm(String coTaxForm) {
        this.coTaxForm = coTaxForm;
    }

    public String getCoTaxForm() {
        return coTaxForm;
    }

    public void setCoTelNo(String coTelNo) {
        this.coTelNo = coTelNo;
    }

    public String getCoTelNo() {
        return coTelNo;
    }

    public void setCoFaxNo(String coFaxNo) {
        this.coFaxNo = coFaxNo;
    }

    public String getCoFaxNo() {
        return coFaxNo;
    }

    public void setCoNameDesc(String coNameDesc) {
        this.coNameDesc = coNameDesc;
    }

    public String getCoNameDesc() {
        return coNameDesc;
    }

    public void setCoLogoDesc(String coLogoDesc) {
        this.coLogoDesc = coLogoDesc;
    }

    public String getCoLogoDesc() {
        return coLogoDesc;
    }

    public void setCoAddressDesc(String coAddressDesc) {
        this.coAddressDesc = coAddressDesc;
    }

    public String getCoAddressDesc() {
        return coAddressDesc;
    }

    public void setCoTaxNoDesc(String coTaxNoDesc) {
        this.coTaxNoDesc = coTaxNoDesc;
    }

    public String getCoTaxNoDesc() {
        return coTaxNoDesc;
    }

    public void setCoTaxFormDesc(String coTaxFormDesc) {
        this.coTaxFormDesc = coTaxFormDesc;
    }

    public String getCoTaxFormDesc() {
        return coTaxFormDesc;
    }

    public void setCoTelNoDesc(String coTelNoDesc) {
        this.coTelNoDesc = coTelNoDesc;
    }

    public String getCoTelNoDesc() {
        return coTelNoDesc;
    }

    public void setCoFaxNoDesc(String coFaxNoDesc) {
        this.coFaxNoDesc = coFaxNoDesc;
    }

    public String getCoFaxNoDesc() {
        return coFaxNoDesc;
    }


    public void setSpaIdCoName(Long spaIdCoName) {
        this.spaIdCoName = spaIdCoName;
    }

    public Long getSpaIdCoName() {
        return spaIdCoName;
    }

    public void setSpaIdCoLogo(Long spaIdCoLogo) {
        this.spaIdCoLogo = spaIdCoLogo;
    }

    public Long getSpaIdCoLogo() {
        return spaIdCoLogo;
    }

    public void setSpaIdCoAddress(Long spaIdCoAddress) {
        this.spaIdCoAddress = spaIdCoAddress;
    }

    public Long getSpaIdCoAddress() {
        return spaIdCoAddress;
    }

    public void setSpaIdTaxNo(Long spaIdTaxNo) {
        this.spaIdTaxNo = spaIdTaxNo;
    }

    public Long getSpaIdTaxNo() {
        return spaIdTaxNo;
    }

    public void setSpaIdTaxForm(Long spaIdTaxForm) {
        this.spaIdTaxForm = spaIdTaxForm;
    }

    public Long getSpaIdTaxForm() {
        return spaIdTaxForm;
    }

    public void setSpaIdTelNo(Long spaIdTelNo) {
        this.spaIdTelNo = spaIdTelNo;
    }

    public Long getSpaIdTelNo() {
        return spaIdTelNo;
    }

    public void setSpaIdFaxNo(Long spaIdFaxNo) {
        this.spaIdFaxNo = spaIdFaxNo;
    }

    public Long getSpaIdFaxNo() {
        return spaIdFaxNo;
    }

    public void setOunOunId(Long ounOunId) {
        this.ounOunId = ounOunId;
    }

    public Long getOunOunId() {
        return ounOunId;
    }

 

    public void setOunOunName(String ounOunName) {
        this.ounOunName = ounOunName;
    }

    public String getOunOunName() {
        return ounOunName;
    }

    public void setOunOunNumber(String ounOunNumber) {
        this.ounOunNumber = ounOunNumber;
    }

    public String getOunOunNumber() {
        return ounOunNumber;
    }

    public void setRootOun(boolean rootOun) {
        this.rootOun = rootOun;
    }

    public boolean isRootOun() {
        return rootOun;
    }
}
