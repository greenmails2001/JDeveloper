package com.vc.web.perp;

/**
 * @author vha 15-11-2008 .
 */
public class Spa4Oun {

    public static final String CO_NAME = "CO_NAME";
    public static final String CO_LOGO = "CO_LOGO";
    public static final String CO_ADDRESS = "CO_ADDRESS";
    public static final String TAX_NO = "TAX_NO";
    public static final String TAX_FORM = "TAX_FORM";
    public static final String TEL_NO = "TEL_NO";
    public static final String FAX_NO = "FAX_NO";

    private Long ounId;
    private boolean root;
    private String coName;
    private String coLogo;
    private String coAddress;
    private String coTelNo;
    private String coFaxNo;
    private String coTaxForm;
    private String coTaxNo;

    public Spa4Oun() {
    }

    public void setOunId(Long ounId) {
        this.ounId = ounId;
    }

    public Long getOunId() {
        return ounId;
    }

    public void setCoLogo(String coLogo) {
        this.coLogo = coLogo;
    }

    public String getCoLogo() {
        return coLogo;
    }

    public void setCoName(String coName) {
        coName = coName == null ? null : coName.trim();
        this.coName = coName == null || coName.equals("") ? null : coName;
    }

    public String getCoName() {
        return coName;
    }


    public void setCoAddress(String coAddress) {
        coAddress = coAddress == null ? null : coAddress.trim();
        this.coAddress = 
                coAddress == null || coAddress.equals("") ? null : coAddress;
    }

    public String getCoAddress() {
        return coAddress;
    }

    public void setCoTelNo(String coTelNo) {
        coTelNo = coTelNo == null ? null : coTelNo.trim();
        this.coTelNo = coTelNo == null || coTelNo.equals("") ? null : coTelNo;
    }

    public String getCoTelNo() {
        return coTelNo;
    }

    public void setCoFaxNo(String coFaxNo) {
        coFaxNo = coFaxNo == null ? null : coFaxNo.trim();
        this.coFaxNo = coFaxNo == null || coFaxNo.equals("") ? null : coFaxNo;
    }

    public String getCoFaxNo() {
        return coFaxNo;
    }

    public void setCoTaxForm(String coTaxForm) {
        coTaxForm = coTaxForm == null ? null : coTaxForm.trim();
        this.coTaxForm = 
                coTaxForm == null || coTaxForm.equals("") ? null : coTaxForm;
    }

    public String getCoTaxForm() {
        return coTaxForm;
    }

    public void setCoTaxNo(String coTaxNo) {
        coTaxNo = coTaxNo == null ? null : coTaxNo.trim();
        this.coTaxNo = coTaxNo == null || coTaxNo.equals("") ? null : coTaxNo;
    }

    public String getCoTaxNo() {
        return coTaxNo;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public boolean isRoot() {
        return root;
    }
}
