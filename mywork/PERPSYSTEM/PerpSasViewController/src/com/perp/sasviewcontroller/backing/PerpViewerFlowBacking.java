package com.perp.sasviewcontroller.backing;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

public class PerpViewerFlowBacking {

    private String outcome;

    public PerpViewerFlowBacking() {
        super();
    }

    public void setOutcome(String outcome) {        
        this.outcome = outcome;
    }

    public String getOutcome() {      
        return outcome;
    }

    public void setOutcomeActionListener(ActionEvent actionEvent) {
        UIComponent component = (UIComponent)actionEvent.getSource();
        Map<String,Object> map=component.getAttributes();
        this.outcome = (String)map.get("outcome");
    }
}
