package com.vc.web.backing.initialSetting;

import com.vc.web.beans.PerpSessionLogin;
import com.vc.web.util.FacesUtil;
import com.vc.web.util.ViewUtil;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.nav.RichCommandNavigationItem;
import oracle.adf.view.rich.component.rich.nav.RichNavigationPane;

public class InitialSettingHandler {

    private RichCommandNavigationItem afNavHome;
    private RichCommandNavigationItem afNavOunStructure;
    private RichCommandNavigationItem afNavSpa4Oun;
    private RichCommandNavigationItem afNavSystemPara;
    private RichCommandNavigationItem afNavBankAccount;
    private RichCommandNavigationItem afNavAccPeriod;
    private RichCommandNavigationItem afNavEndSetting;
    private RichNavigationPane afNavInititalSetting;

    public InitialSettingHandler() {
    }
    
    public String getCaseFlowOfInitialSettingFlow() {
        PerpSessionLogin perpSession=ViewUtil.getPerpSessionLogin();        
        if(perpSession==null || !perpSession.isValid()) {
            return "login" ;
        }
        return "home";
    }

    public void setAfNavHome(RichCommandNavigationItem afNavHome) {
        this.afNavHome = afNavHome;
    }

    public RichCommandNavigationItem getAfNavHome() {
        return afNavHome;
    }

    public void setAfNavOunStructure(RichCommandNavigationItem afNavOunStructure) {
        this.afNavOunStructure = afNavOunStructure;
    }

    public RichCommandNavigationItem getAfNavOunStructure() {
        return afNavOunStructure;
    }

    public void setAfNavSpa4Oun(RichCommandNavigationItem afNavSpa4Oun) {
        this.afNavSpa4Oun = afNavSpa4Oun;
    }

    public RichCommandNavigationItem getAfNavSpa4Oun() {
        return afNavSpa4Oun;
    }

    public void setAfNavSystemPara(RichCommandNavigationItem afNavSystemPara) {
        this.afNavSystemPara = afNavSystemPara;
    }

    public RichCommandNavigationItem getAfNavSystemPara() {
        return afNavSystemPara;
    }

    public void setAfNavBankAccount(RichCommandNavigationItem afNavBankAccount) {
        this.afNavBankAccount = afNavBankAccount;
    }

    public RichCommandNavigationItem getAfNavBankAccount() {
        return afNavBankAccount;
    }

    public void setAfNavAccPeriod(RichCommandNavigationItem afNavAccPeriod) {
        this.afNavAccPeriod = afNavAccPeriod;
    }

    public RichCommandNavigationItem getAfNavAccPeriod() {
        return afNavAccPeriod;
    }

    public void setAfNavEndSetting(RichCommandNavigationItem afNavEndSetting) {
        this.afNavEndSetting = afNavEndSetting;
    }

    public RichCommandNavigationItem getAfNavEndSetting() {
        return afNavEndSetting;
    }

    public void setAfNavInititalSetting(RichNavigationPane afNavInititalSetting) {
        this.afNavInititalSetting = afNavInititalSetting;
    }

    public RichNavigationPane getAfNavInititalSetting() {
        return afNavInititalSetting;
    }

    public void navigationActionListener(ActionEvent e) {
        Object obj= e.getSource();     
        RichCommandNavigationItem newNav = (RichCommandNavigationItem)obj;
        
        List<UIComponent> childs=this.afNavInititalSetting.getChildren();
        for(UIComponent child:childs) {
            if(child instanceof RichCommandNavigationItem){
                RichCommandNavigationItem child2 = (RichCommandNavigationItem)child;
                child2.setSelected(false);
            }
        }      
        newNav.setSelected(true);
        FacesUtil.addPartialTarget(this.afNavInititalSetting);
    }
}
