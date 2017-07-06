package com.vc.web.backing;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;

public class AppView {
    private RichDocument document2;
    private RichRegion appvi1;


    public void setDocument2(RichDocument document2) {
        this.document2 = document2;
    }

    public RichDocument getDocument2() {
        return document2;
    }


    public void setAppvi1(RichRegion appvi1) {
        this.appvi1 = appvi1;
    }

    public RichRegion getAppvi1() {
        return appvi1;
    }
}
