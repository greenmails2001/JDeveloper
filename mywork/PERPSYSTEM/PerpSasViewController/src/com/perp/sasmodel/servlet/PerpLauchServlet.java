package com.perp.sasmodel.servlet;


import com.oracle.adffwkextensions.faces.FacesUtils;

import com.perp.beans.PerpAccount;
import com.perp.beans.PerpSasEnv;
import com.perp.beans.PerpSasSubscriptionInfo;
import com.perp.beans.SessionLoginObject;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.faces.FactoryFinder;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PerpLauchServlet extends HttpServlet {

    public PerpLauchServlet() {
        super();
    }

    /**
     * Link gui sang co dang :
     *
     * http://host:7778/forms/frmservlet?config=PERP01_PERP&otherparams=p_root_app_name=STA+p_aus_id=1+p_gus_id=108+p_initial_module=FIN112
     *
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException,
                                                              IOException {
        FacesContext context =
            FacesUtils.getFacesContextForServlet(request, response);
        SessionLoginObject sessionLoginObj =
            (SessionLoginObject)FacesUtils.resolveExpression(context,
                                                             "#{sessionLoginObject}");

        Long gusId = Long.parseLong(request.getParameter("gusId"));
        String menuName = request.getParameter("menuName");
        Long menId = Long.parseLong(request.getParameter("menId"));

        if (sessionLoginObj != null) {
            PerpSasEnv env = sessionLoginObj.getPerpSasEnv();
            PerpSasSubscriptionInfo subInfo =
                sessionLoginObj.getSubscriptionInfo();
            PerpAccount acc = sessionLoginObj.getPerpAccount();

            Map atts = new HashMap();

            atts.put("config", subInfo.getSubscriptionCode());
            atts.put("otherparams",
                     "p_root_app_name=" + subInfo.getPackageCode() +
                     "+p_aus_id=" + acc.getAusId() + "+p_gus_id=" + gusId +
                     "+p_initial_module=" + menuName+
                     "+p_men_id=" + menId);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("callFormMap",
                                                                                       atts);
            String url = "/perpsas/faces/callforms";
            response.sendRedirect(url);

            //String url = "http://" + env.getAppHost() + ":" + env.getAppPort();
            //url += "/forms/frmservlet?config=" + subInfo.getSubscriptionCode();
            //url += "&otherparams=p_root_app_name=" + subInfo.getPackageCode();
            //url += "+p_aus_id=" + acc.getAusId();
            //url += "+p_gus_id=" + gusId;
            //url += "+p_initial_module=" + menuName;
            //response.sendRedirect(url);
        }
    }


}
