package com.vc.web.perp;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class OunShortInfo implements Serializable {

    protected Long ounId;
    protected Long parentId;
    protected String ounNumber;
    protected String ounName;
    private List<OunShortInfo> childList = new ArrayList<OunShortInfo>();


    public OunShortInfo(Long ounId, Long parentId, String ounNumber, 
                        String ounName) {
        this.ounId = ounId;
        this.parentId = parentId;
        this.ounName = ounName;
        this.ounNumber = ounNumber;
    }

    public void setOunId(Long ounId) {
        this.ounId = ounId;
    }

    public Long getOunId() {
        return ounId;
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

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }
    
    public void addChild(OunShortInfo child) {
        this.childList.add(child);
    }


    public void setChildList(List<OunShortInfo> childList) {
        this.childList = childList;
    }

    public List<OunShortInfo> getChildList() {
        return childList;
    }
}
