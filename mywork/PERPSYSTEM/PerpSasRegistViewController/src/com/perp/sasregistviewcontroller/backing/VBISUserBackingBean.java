package com.perp.sasregistviewcontroller.backing;

import com.oracle.adffwkextensions.faces.ADFUtils;
import com.oracle.adffwkextensions.faces.BundleUtils;
import com.oracle.adffwkextensions.faces.FacesUtils;

import com.perp.beans.SasrSessionLoginObject;
import com.perp.sasregistmodel.common.PerpSasRegistAppModule;

import com.perp.sasregistviewcontroller.utils.SasrFacesUtils;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.validator.ValidatorException;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;


import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Key;
import oracle.jbo.RowSetIterator;

public class VBISUserBackingBean {

    private String action;

    public VBISUserBackingBean() {
        super();
    }

    private void setCurrentRowWithKey(Key key) {
        DCIteratorBinding iteratorBinding =
            (DCIteratorBinding)getBindings().get("VbisUsersView1Iterator");

        if (iteratorBinding != null) {
            RowSetIterator rowSet = iteratorBinding.getRowSetIterator();
            // iteratorBinding.setCurrentRowWithKey(key.toStringFormat(true));
            Row row = rowSet.findByKey(key, 1)[0];
            if (row != null) {
                rowSet.setCurrentRow(row);
            }
        }
    }

    public void createUserActionListener(ActionEvent actionEvent) {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("CreateInsert");
        operationBinding.execute();
    }

    public void editUserActionListener(ActionEvent actionEvent) {
        UIComponent component = (UIComponent)actionEvent.getSource();
        Map<String, Object> map = component.getAttributes();
        //FacesUtils.addMessage(null, (map.get("rowKey")).toString());
        Key key = (Key)map.get("rowKey");
        this.setCurrentRowWithKey(key);

    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }


    public void deleteVbisUserActionListener(ActionEvent actionEvent) {
        UIComponent component = (UIComponent)actionEvent.getSource();
        Map<String, Object> map = component.getAttributes();
        //FacesUtils.addMessage(null, (map.get("rowKey")).toString());
        Key key = (Key)map.get("rowKey");
        this.setCurrentRowWithKey(key);
    }

    public void saveAndCreateActionListener(ActionEvent actionEvent) {
        ApplicationModule am =
            ADFUtils.getDCBindingContainer().getDataControl().getApplicationModule();
        if (am.getTransaction().isDirty()) {
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding =
                bindings.getOperationBinding("Commit");
            operationBinding.execute();
        }
    }

    public void saveVbisUserActionListener(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void vbisUserNameValidator(FacesContext facesContext,
                                      UIComponent uIComponent, Object object) {
        String oldValue = (String)((UIInput)uIComponent).getValue();
        String userName = object.toString();
        if (oldValue != null ? oldValue.equals(oldValue) : oldValue == null) {
            // Value has not changed.
            return;
        }
        Number accId =
            ((SasrSessionLoginObject)SasrFacesUtils.getSasrSessionLoginObject()).getPerpSasrAccount().getAccId();
        PerpSasRegistAppModule am =
            (PerpSasRegistAppModule)ADFUtils.getDCBindingContainer().getDataControl().getApplicationModule();
        if (am.checkVbisUserByAccId(accId, userName)) {
            FacesMessage message = new FacesMessage();
            String value = BundleUtils.getBundle("User da duoc su dung");
            message.setDetail(value);
            message.setSummary(value);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
