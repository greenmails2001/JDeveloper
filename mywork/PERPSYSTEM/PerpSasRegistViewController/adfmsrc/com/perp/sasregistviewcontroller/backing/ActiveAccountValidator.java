package pages.sasr.sasr001;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;


public class ActiveAccountValidator implements Validator {
   

    public void validate(FacesContext facesContext, UIComponent uiComponent,
                         Object object) {
        /*
        FacesMessage fm = null;
        RichInputText richText = (RichInputText)uiComponent;
        oracle.jbo.domain.Number subId2 =
          (oracle.jbo.domain.Number)richText.getValue();
        if (subId2 == null) {
          fm = new FacesMessage("Error", "Subscription required");
          throw new ValidatorException(fm);
        }
        PerpSasEnvAppModule module = SaseaFacesUtils.getPerpSasEnvAppModule();
        ViewObject view =module.getSasem001Environments();
        Row row =view.getCurrentRow();
        DBSequence seq = (DBSequence)row.getAttribute("EnvId");
        Long envId = seq.getValue();
        Long subId = subId2.longValue();
        int count =module.getCountSubscriptionExceptEnvId(envId,subId);
        if(count>0) {
          fm = new FacesMessage("Error", "Sub_Id "+subId+" not unique");
          throw new ValidatorException(fm);
        } //*/   
    }
}
