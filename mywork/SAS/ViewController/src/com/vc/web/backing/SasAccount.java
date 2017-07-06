package com.vc.web.backing;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;

public class SasAccount {
    private RichDocument document1;
    private RichRegion sasac1;
    private RichRegion shopp1;

    public void setDocument1(RichDocument document1) {
        this.document1 = document1;
    }

    public RichDocument getDocument1() {
        return document1;
    }


    public void setSasac1(RichRegion sasac1) {
        this.sasac1 = sasac1;
    }

    public RichRegion getSasac1() {
        return sasac1;
    }

    public void setShopp1(RichRegion shopp1) {
        this.shopp1 = shopp1;
    }

    public RichRegion getShopp1() {
        return shopp1;
    }
}
