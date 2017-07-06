package com.vc.web.perp;

public class GusInfo {
    private Long gusId;
    private String gusName;
    private Long ausId;

    public GusInfo() {
    }


    public void setGusId(Long gusId) {
        this.gusId = gusId;
    }

    public Long getGusId() {
        return gusId;
    }

    public void setGusName(String gusName) {
        this.gusName = gusName;
    }

    public String getGusName() {
        return gusName;
    }

    public void setAusId(Long ausId) {
        this.ausId = ausId;
    }

    public Long getAusId() {
        return ausId;
    }
}
