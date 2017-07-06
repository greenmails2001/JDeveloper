package com.vc.web.perp;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class OunInfo implements Serializable, Cloneable {
    private Long ounId;
    private Long parentId;
    private String ounNumber;
    private String ounName;

    private String countryCode;
    private String countryName;
    private String provinceCode;
    private String provinceName;
    private Timestamp effectiveDateFrom;
    private Timestamp effectiveDateTo;

    private String ounTypeCode;
    private String ounTypeName;
    private String address;
    private String description;

    private List<OunInfo> childList = null;
    
    public OunInfo() {
        
    }

    public OunInfo(Long ounId, Long parentId, String ounNumber, String ounName, 
                   String countryCode, String countryName, String provinceCode, 
                   String provinceName, String ounTypeCode, String ounTypeName, 
                   String address, Timestamp effectiveDateFrom, 
                   Timestamp effectiveDateTo, String description) {
        this.ounId = ounId;
        this.parentId = parentId;
        this.ounNumber = ounNumber;
        this.ounName = ounName;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
        this.ounTypeCode = ounTypeCode;
        this.ounTypeName = ounTypeName;
        this.address = address;
        this.effectiveDateFrom = effectiveDateFrom;
        this.effectiveDateTo = effectiveDateTo;
        this.description = description;
    }

 

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setEffectiveDateFrom(Timestamp effectiveDateFrom) {
        this.effectiveDateFrom = effectiveDateFrom;
    }

    public Timestamp getEffectiveDateFrom() {
        return effectiveDateFrom;
    }

    public void setEffectiveDateTo(Timestamp effectiveDateTo) {
        this.effectiveDateTo = effectiveDateTo;
    }

    public Timestamp getEffectiveDateTo() {
        return effectiveDateTo;
    }

    public void setOunTypeCode(String ounTypeCode) {
        this.ounTypeCode = ounTypeCode;
    }

    public String getOunTypeCode() {
        return ounTypeCode;
    }

    public void setOunTypeName(String ounTypeName) {
        this.ounTypeName = ounTypeName;
    }

    public String getOunTypeName() {
        return ounTypeName;
    }

    public void setOunId(Long ounId) {
        this.ounId = ounId;
    }

    public Long getOunId() {
        return ounId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setOunNumber(String ounNumber) {
        this.ounNumber = ounNumber;
    }

    public String getOunNumber() {
        return ounNumber;
    }

    public void setOunName(String ounName) {
        this.ounName = ounName;
    }

    public String getOunName() {
        return ounName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setChildList(List<OunInfo> childList) {
        this.childList = childList;
    }

    public List<OunInfo> getChildList() {
        return childList;
    }

    public void addChild(OunInfo child) {
        if (this.childList == null) {
            this.childList = new ArrayList<OunInfo>();
        }
        this.childList.add(child);
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
                        
}
