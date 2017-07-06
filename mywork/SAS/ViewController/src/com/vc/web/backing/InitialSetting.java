package com.vc.web.backing;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import org.apache.myfaces.trinidad.component.html.HtmlScript;


public class InitialSetting {
    
    private RichDocument document1;
    private HtmlScript script1;
    private RichRegion initi1;


    public void setDocument1(RichDocument document1) {
        this.document1 = document1;
    }

    public RichDocument getDocument1() {
        return document1;
    }

    public void setScript1(HtmlScript script1) {
        this.script1 = script1;
    }

    public HtmlScript getScript1() {
        return script1;
    }


    public void setIniti1(RichRegion initi1) {
        this.initi1 = initi1;
    }

    public RichRegion getIniti1() {
        return initi1;
    }


}
