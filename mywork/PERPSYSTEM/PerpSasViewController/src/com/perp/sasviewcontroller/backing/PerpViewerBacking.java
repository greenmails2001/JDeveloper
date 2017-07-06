package com.perp.sasviewcontroller.backing;

import com.commit_consulting.oraformsfaces.component.component.Form;

import com.oracle.adffwkextensions.faces.FacesUtils;

import com.perp.beans.PerpAccount;
import com.perp.beans.PerpSasEnv;
import com.perp.beans.PerpSasSubscriptionInfo;
import com.perp.beans.SessionLoginObject;
import com.perp.sasmodel.common.PerpSasAppModule;

import com.perp.sasviewcontroller.util.PerpSasViewUtils;

import java.io.IOException;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletResponse;

import oracle.adf.view.rich.component.rich.RichPopup;

public class PerpViewerBacking {

    private String findIn;
    private String searchText;

    private String currentMenuName;
    private RichPopup oraformsfacesPopup;
    private Form oraformsfaces;

    public void setCurrentMenuName(String currentMenuName) {
        this.currentMenuName = currentMenuName;
    }

    public String getCurrentMenuName() {
        return currentMenuName;
    }

    public void setOraformsfacesPopup(RichPopup oraformsfacesPopup) {
        this.oraformsfacesPopup = oraformsfacesPopup;
    }

    public RichPopup getOraformsfacesPopup() {
        return oraformsfacesPopup;
    }

    public void setOraformsfaces(Form oraformsfaces) {
        this.oraformsfaces = oraformsfaces;
    }

    public Form getOraformsfaces() {
        return oraformsfaces;
    }

    public void openOraFormsFacesActionListener(ActionEvent e) {

        UIComponent linkObj = (UIComponent)e.getSource(); // RichCommandLink

        Map<String, Object> atts = linkObj.getAttributes();
        String menuName = (String)atts.get("menuName");

        this.currentMenuName = menuName;
        this.oraformsfaces.setFormModuleName(currentMenuName);
        FacesUtils.addPartialTarget(this.oraformsfacesPopup);
        FacesUtils.showPopup(this.oraformsfacesPopup);
    }

    private void showParentViewPopup(String popupId) {
        RichPopup popup =
            (RichPopup)FacesContext.getCurrentInstance().getViewRoot().findComponent(popupId);

        FacesUtils.showPopup(popup);
    }

    public void showSubcriptionPopup() {
        this.callParentViewPopupSubcriptionPopup(null);
    }

    public void callParentViewPopupSubcriptionPopup(ActionEvent actionEvent) {
        String popupId = "aboutSubscriptionPopup";
        this.showParentViewPopup(popupId);
    }

    public void callParentViewPopupFindForm(ActionEvent actionEvent) {
        String popupId = "findPopup2";
        SessionLoginObject loginObject =
            PerpSasViewUtils.getSessionLoginObject();
        PerpSasAppModule perpsas =
            (PerpSasAppModule)FacesUtils.getApplicationModuleForDataControl("PerpSasAppModuleDataControl");

        perpsas.prepareToShowSasMenusSearch(loginObject.getSubscriptionInfo().getPackageCode(),
                                            loginObject.getPerpAccount().getAusId(),
                                            searchText, findIn);
        this.showParentViewPopup(popupId);
    }


    public void setFindIn(String findIn) {
        this.findIn = findIn;
    }

    public String getFindIn() {
        return findIn;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

    public void openFormPerpActionListener(ActionEvent actionEvent) {
        UIComponent linkObj = (UIComponent)actionEvent.getSource();

        Map<String, Object> atts = linkObj.getAttributes();
        //String menuName = (String)atts.get("menuName");
        oracle.jbo.domain.Number gusId =
            (oracle.jbo.domain.Number)atts.get("gusId");
        SessionLoginObject loginObject =
            PerpSasViewUtils.getSessionLoginObject();
        if (loginObject != null) {
            PerpSasEnv env = loginObject.getPerpSasEnv();
            PerpSasSubscriptionInfo subInfo =
                loginObject.getSubscriptionInfo();
            PerpAccount acc = loginObject.getPerpAccount();

            String url = "http://" + env.getAppHost() + ":" + env.getAppPort();
            url += "/forms/frmservlet?config=" + subInfo.getSubscriptionCode();
            url += "&otherparams=p_root_app_name=" + subInfo.getPackageCode();
            url += "+p_aus_id=" + acc.getAusId();
            url += "+p_gus_id=" + gusId;
            url += "+p_initial_module=" + searchText;

            url =
"/faces/perpLauchServlet?gusId=" + gusId + "&amp;menuName=" + searchText;
            HttpServletResponse response = FacesUtils.getHttpServletResponse();
            // HttpServletRequest  request = FacesUtils.getHttpServletRequest();
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
    }
}
