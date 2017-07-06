package com.perp.sasraviewcontroller.backing;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.nav.RichCommandNavigationItem;

public class Sasra002aTaskFlowBacking {
  private String inputValue;
  private RichRegion regionSasra002a;

  public Sasra002aTaskFlowBacking() {
  }

  public void setInputValue(String inputValue) {
    this.inputValue = inputValue;
  }

  public String getInputValue() {
    return inputValue;
  }

  public void navItemActionListener(ActionEvent actionEvent) {
    // Add event code here...
    Object obj = actionEvent.getSource();    
    RichCommandNavigationItem navItem = (RichCommandNavigationItem)obj;
    String id =navItem.getId();
    this.inputValue=id;  
    this.regionSasra002a.getRegionModel().refresh(FacesContext.getCurrentInstance());
  }

  public void setRegionSasra002a(RichRegion regionSasra002a) {
    this.regionSasra002a = regionSasra002a;
  }

  public RichRegion getRegionSasra002a() {
    return regionSasra002a;
  }
}
