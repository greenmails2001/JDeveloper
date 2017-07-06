package com.perp.sasregistviewcontroller.backing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.input.RichTextEditor;

public class RichTextBoxEditorToolbar {
    public RichTextBoxEditorToolbar() {
        super();
    }

    private RichTextEditor rte;


    private List<SelectItem> radioGroupItems = new ArrayList<SelectItem>();

    String[] boxdata2 =
    { "font", "history", "color", "formatAll", "justify", "list", "indent",
      "link", "newline", "stretch" };


    String[] toolbarItems;
    Map toolbarItemMap = new HashMap();

    public void setRte(RichTextEditor rte) {
        this.rte = rte;
    }

    public RichTextEditor getRte() {
        return rte;
    }


    public String changeToolbarBack() {
        // Add event code here...
        rte.setToolboxLayout(boxdata2);
        return null;
    }

    public String changeToolbar() {
        // Add event code here...
        rte.setToolboxLayout(boxdata2);
        return null;
    }


    public void setRadioGroupItems(List<SelectItem> radioGroupItems) {
        this.radioGroupItems = radioGroupItems;
    }

    public List<SelectItem> getRadioGroupItems() {

        radioGroupItems.add(new SelectItem("font"));
        radioGroupItems.add(new SelectItem("history"));
        radioGroupItems.add(new SelectItem("color"));
        radioGroupItems.add(new SelectItem("formatAll"));
        radioGroupItems.add(new SelectItem("justify"));
        radioGroupItems.add(new SelectItem("list"));
        radioGroupItems.add(new SelectItem("indent"));
        radioGroupItems.add(new SelectItem("link"));
        radioGroupItems.add(new SelectItem("newline"));
        radioGroupItems.add(new SelectItem("stretch"));

        setToolbarItems(boxdata2);

        return radioGroupItems;

    }


    public void setToolbarItems(String[] toolbarItems) {
        this.toolbarItems = toolbarItems;
        toolbarItemMap.clear();
        for (int i = 0; i < toolbarItems.length; i++) {
            toolbarItemMap.put(toolbarItems[i], Boolean.TRUE);
        }
        rte.setToolboxLayout(toolbarItems);
    }

    public String[] getToolbarItems() {
        return toolbarItems;

    }
}
