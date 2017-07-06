package com.vc.web.util;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import oracle.adf.model.binding.DCBindingContainer;

import org.apache.myfaces.trinidad.context.RequestContext;


public class FacesUtil {
    /** Chi cac static method only */
    private FacesUtil() {
    }
    
    public static void addMessage(String summaryMessage, String detailMessage) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = 
            new FacesMessage(FacesMessage.SEVERITY_INFO, summaryMessage, 
                             detailMessage);
        context.addMessage(null, fm);
    }

    public static void addError(String summaryError, String detailError) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, summaryError, 
                             detailError);
        context.addMessage(null, fm);
    }

    public static Object resolveExpression(String expression) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp = 
            elFactory.createValueExpression(elContext, expression, 
                                            Object.class);
        return valueExp.getValue(elContext);
    }

    /**
     * Return the current page's binding container.
     * @return the current page's binding container
     * oracle.adf.model.binding.DCBindingContainer
     * oracle.binding.BindingContainer
     */
    public static DCBindingContainer getBindingContainer() {
        return (DCBindingContainer)resolveExpression("#{bindings}");
    }
    
    
    public static void addPartialTarget(UIComponent component) {
        RequestContext.getCurrentInstance().addPartialTarget(component);
    }

}
