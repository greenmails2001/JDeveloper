package pages.sasr.sasr002;


import javax.faces.component.ContextCallback;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.context.AdfFacesContext;


public class Sasr002 {
    private RichCommandButton testButton;
   // private RichShowDetailItem podmShowDetailItem;
    private RichPanelBox podmRegistedPanelBox;

    public Sasr002() {
        super();
    }

    public void setTestButton(RichCommandButton testButton) {
        this.testButton = testButton;
    }

    public RichCommandButton getTestButton() {
        return testButton;
    }


    public void testRefreshParent(ActionEvent actionEvent) {
        // Add event code here...
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        // -- Get view root for current view or page
        UIViewRoot root = facesCtx.getViewRoot();
        String viewId = root.getViewId();
        System.out.println("View ID=" + viewId);


        ContextCallback executeCommandButtonAction = new ContextCallback() {
            public void invokeContextCallback(FacesContext ctx,
                                              UIComponent c) {                
                podmRegistedPanelBox.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(testButton);
                AdfFacesContext.getCurrentInstance().addPartialTarget(podmRegistedPanelBox);
                System.out.println("invokeContextCallback");
                podmRegistedPanelBox.setDisclosed(false);
            }
        };
        
        boolean found =
            root.invokeOnComponent(facesCtx, testButton.getClientId(FacesContext.getCurrentInstance()),
                                   executeCommandButtonAction);
        found =
            root.invokeOnComponent(facesCtx, podmRegistedPanelBox.getClientId(FacesContext.getCurrentInstance()),
                                   executeCommandButtonAction);
    }

    public String getRandomValue() {
        return "Value" + Math.random();
    }

 

    public void setPodmRegistedPanelBox(RichPanelBox podmRegistedPanelBox) {
        this.podmRegistedPanelBox = podmRegistedPanelBox;
    }

    public RichPanelBox getPodmRegistedPanelBox() {
        return podmRegistedPanelBox;
    }
}
