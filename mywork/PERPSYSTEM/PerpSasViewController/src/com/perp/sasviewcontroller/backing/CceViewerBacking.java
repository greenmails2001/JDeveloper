package com.perp.sasviewcontroller.backing;

import oracle.jbo.domain.Number;

public class CceViewerBacking {

    public oracle.jbo.domain.Number ounId;

    public CceViewerBacking() {
        super();
    }

    public void setOunId(Number ounId) {
        System.out.println("Set OunID="+ounId);
        this.ounId = ounId;
    }

    public Number getOunId() {
      System.out.println("Get OunID="+ounId);
        return ounId;
    }
}
