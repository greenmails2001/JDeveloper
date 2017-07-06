package com.perp.sasregistviewcontroller.backing;

import com.oracle.adffwkextensions.faces.FacesUtils;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;

public class SasrAdminCaseFlowManager {

    private static final String VIEW_ACCOUNTS = "sasr-accounts";
    private static final String VIEW_SERVICE_PACKAGES = "service-packages";

    private String caseFlow;
    private RichRegion region;

    public SasrAdminCaseFlowManager() {
        super();
    }

    public void setCaseFlowActionListener(ActionEvent actionEvent) {
        UIComponent component = (UIComponent)actionEvent.getSource();
        Map<String, Object> map = component.getAttributes();
        this.caseFlow = (String)map.get("caseFlow");
        this.setCaseFlow(this.caseFlow);
    }

    public void setCaseFlow(String caseFlow) {
        this.caseFlow = caseFlow;
        this.region.getRegionModel().refresh(FacesContext.getCurrentInstance());
        FacesUtils.addPartialTarget(this.region);
    }

    public String getCaseFlow() {
        return caseFlow;
    }

    public void setRegion(RichRegion region) {
        this.region = region;
    }

    public RichRegion getRegion() {
        return region;
    }


}
