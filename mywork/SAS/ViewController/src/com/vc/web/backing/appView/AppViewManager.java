package com.vc.web.backing.appView;

import com.vc.web.PerpSystemException;
import com.vc.web.PerpSystemInfo;
import com.vc.web.beans.PerpSessionLogin;
import com.vc.web.ejb.perp.DynamicPERPFacadeLocal;
import com.vc.web.perp.GusInfo;

import com.vc.web.perp.ModuleInfo;

import com.vc.web.util.EJBUtil;
import com.vc.web.util.FacesUtil;
import com.vc.web.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import java.util.TreeMap;

import javax.naming.NamingException;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;

import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.TreeModel;

public class AppViewManager {

    private RichPanelTabbed tabPane;
    private RichShowDetailItem tabItemForms;
    private RichShowDetailItem tabItemSearchResult;
    private RichShowDetailItem tabItemFavorites;

    public AppViewManager() {
    }
    private List<GusInfo> gusInfoList;

    private TreeModel moduleDataModel;

    private TreeMap<Long, List<ModuleInfo>> map = 
        new TreeMap<Long, List<ModuleInfo>>();
    private RichTreeTable moduleTreeTable;

    private Long gusId;
    private RichTable searchTable;

    /** String dung de search Module , gia tri nay da duoc trim & toLowerCase . */
    private String searchText = null;
    private List<ModuleInfo> searchResult = null;


    public void setModuleTreeTable(RichTreeTable moduleTreeTable) {
        this.moduleTreeTable = moduleTreeTable;
    }

    public RichTreeTable getModuleTreeTable() {
        return moduleTreeTable;
    }


    public void setModuleDataModel(TreeModel moduleDataModel) {
        this.moduleDataModel = moduleDataModel;
    }

    public TreeModel getModuleDataModel() {
        if (moduleDataModel == null) {
            if (this.gusId != null) {
                List<ModuleInfo> moduleInfoList = this.map.get(this.gusId);
                if (moduleInfoList != null) {
                    moduleDataModel = 
                            new ChildPropertyTreeModel(moduleInfoList, 
                                                       "childModules");
                } else {
                    PerpSessionLogin sessionInfo = 
                        ViewUtil.getPerpSessionLogin();
                    PerpSystemInfo psi = 
                        sessionInfo.getAppUserInfo().getPerpSystemInfo();
                    Long ausId = sessionInfo.getAppUserInfo().getAusId();
                    String packageCode = 
                        sessionInfo.getAppUserInfo().getPackageCode();
                    DynamicPERPFacadeLocal bean;
                    try {
                        bean = EJBUtil.lockupDynamicPERPFacade();
                        moduleInfoList = 
                                bean.getModuleInfoList(psi, packageCode, gusId, 
                                                       ausId);
                        this.map.put(this.gusId, moduleInfoList);
                        moduleDataModel = 
                                new ChildPropertyTreeModel(moduleInfoList, 
                                                           "childModules");

                    } catch (PerpSystemException e) {
                        moduleInfoList = new ArrayList<ModuleInfo>();
                        FacesUtil.addError("Error", 
                                           "Error " + e.getErrorCode());
                    }
                }
            }
        }
        return moduleDataModel;
    }


    public void gusSelectionListener(SelectionEvent selectionEvent) {
        Object obj = selectionEvent.getSource();
        RichTable table = (RichTable)obj;
        GusInfo gusInfo = (GusInfo)table.getSelectedRowData();
        this.gusId = gusInfo.getGusId();
        // refresh .(Gan Module Data Module null de chuong trinh refresh lai du lieu TreeTable) .    
        this.moduleDataModel = null;

        RequestContext adfContext = RequestContext.getCurrentInstance();
        adfContext.addPartialTarget(this.moduleTreeTable); //*/
    }

    public void setGusId(Long gusId) {
        this.gusId = gusId;
    }

    public Long getGusId() {
        return gusId;
    }

    public void setGusInfoList(List<GusInfo> gusInfoList) {
        this.gusInfoList = gusInfoList;
    }

    public List<GusInfo> getGusInfoList() {
        PerpSessionLogin sessionInfo = ViewUtil.getPerpSessionLogin();
        Long ausId = sessionInfo.getAppUserInfo().getAusId();

        PerpSystemInfo psi = sessionInfo.getAppUserInfo().getPerpSystemInfo();

        if (this.gusInfoList == null) {
            DynamicPERPFacadeLocal bean;
            try {
                bean = EJBUtil.lockupDynamicPERPFacade();
                this.gusInfoList = bean.getGusInfoList(psi, ausId);
            } catch (PerpSystemException e) {
                this.gusInfoList = new ArrayList<GusInfo>();
                FacesUtil.addError("Error", "Error " + e.getErrorCode());
            }
        }
        return gusInfoList;
    }

    public void setSearchResult(List<ModuleInfo> searchResult) {
        this.searchResult = searchResult;
    }

    public List<ModuleInfo> getSearchResult() {
        if (this.searchResult == null && this.searchText != null) {
            PerpSessionLogin sessionInfo = ViewUtil.getPerpSessionLogin();
            Long ausId = sessionInfo.getAppUserInfo().getAusId();
            PerpSystemInfo psi = 
                sessionInfo.getAppUserInfo().getPerpSystemInfo();
            DynamicPERPFacadeLocal bean = null;
            try {
                bean = EJBUtil.lockupDynamicPERPFacade();
                this.searchResult = 
                        bean.getModuleInfoListSearchResult(psi, ausId, 
                                                           this.gusId, 
                                                           this.searchText);           
            } catch (PerpSystemException e) {
                FacesUtil.addError("Error", "Error " + e.getMessage());
                return null;
            }
        }
        return searchResult;
    }

    public String searchMuduleAction() {
        this.searchResult = null;
        RequestContext adfContext = RequestContext.getCurrentInstance();
        adfContext.addPartialTarget(this.searchTable);
        return null;
    }

    public void setSearchText(String searchText) {
        this.searchText = 
                searchText == null ? null : searchText.trim().toLowerCase();
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchTable(RichTable searchTable) {
        this.searchTable = searchTable;
    }

    public RichTable getSearchTable() {
        return searchTable;
    }

    public void setTabPane(RichPanelTabbed tabPane) {
        this.tabPane = tabPane;
    }

    public RichPanelTabbed getTabPane() {
        return tabPane;
    }

    public void setTabItemForms(RichShowDetailItem tabItemForms) {
        this.tabItemForms = tabItemForms;
    }

    public RichShowDetailItem getTabItemForms() {
        return tabItemForms;
    }

    public void setTabItemSearchResult(RichShowDetailItem tabItemSearchResult) {
        this.tabItemSearchResult = tabItemSearchResult;
    }

    public RichShowDetailItem getTabItemSearchResult() {
        return tabItemSearchResult;
    }

    public void setTabItemFavorites(RichShowDetailItem tabItemFavorites) {
        this.tabItemFavorites = tabItemFavorites;
    }

    public RichShowDetailItem getTabItemFavorites() {
        return tabItemFavorites;
    }
}
