package com.perp.sasregistviewcontroller.backing;

import com.oracle.adffwkextensions.faces.FacesUtils;

import com.perp.beans.SasrSessionLoginObject;
import com.perp.sasregistviewcontroller.utils.SasrFacesUtils;

import java.io.IOException;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import oracle.adf.controller.v2.lifecycle.Lifecycle;

import oracle.adf.controller.v2.lifecycle.PagePhaseEvent;
import oracle.adf.controller.v2.lifecycle.PagePhaseListener;

public class PageListenerBean implements PagePhaseListener {
    public PageListenerBean() {
        super();
    }

    public void afterPhase(PagePhaseEvent pagePhaseEvent) {
    }

    public void beforePhase(PagePhaseEvent pagePhaseEvent) {
        if (pagePhaseEvent.getPhaseId() == Lifecycle.PREPARE_MODEL_ID) {
            if (!isPostback()) {
                System.out.println("This Will Execute AT Page Load");
                SasrSessionLoginObject sasr =
                    SasrFacesUtils.getSasrSessionLoginObject();
                if (sasr == null || sasr.getPerpSasrAccount() == null) {
                    ExternalContext ectx =
                        FacesContext.getCurrentInstance().getExternalContext();
                    HttpServletResponse resp =
                        (HttpServletResponse)ectx.getResponse();                 
                    try {
                        resp.sendRedirect("login");
                    } catch (IOException e) {
                    }
                }
            }
        }

        if (pagePhaseEvent.getPhaseId() == Lifecycle.METADATA_COMMIT_ID) {
            if (!isPostback()) {
                System.out.println("This Will Execute When Leave The Page");
            }
        }

    }

    private boolean isPostback() {
        return Boolean.TRUE.equals(FacesUtils.resolveExpression("#{adfFacesContext.postback}"));
    }

}
