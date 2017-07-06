package com.vc.web.backing.initialSetting;

import com.vc.web.PerpSystemException;
import com.vc.web.PerpSystemInfo;
import com.vc.web.beans.PerpSessionLogin;
import com.vc.web.ejb.perp.DynamicPERPFacade;
import com.vc.web.ejb.perp.DynamicPERPFacadeLocal;
import com.vc.web.perp.OunInfo;
import com.vc.web.perp.OunShortInfo;
import com.vc.web.perp.Spa4Oun;

import com.vc.web.util.EJBUtil;
import com.vc.web.util.FacesUtil;
import com.vc.web.util.ViewUtil;

import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;

import javax.naming.NamingException;

import oracle.adf.view.rich.component.rich.data.RichTreeTable;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.event.PopupFetchEvent;

import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.LaunchEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.TreeModel;

/**
 * @author vha 16-11-2008 .
 */
public class OunStructureManager {

    private List<OunInfo> ounInfoList;
    private TreeModel treeTableModel = null;
    private RichTreeTable treeTable;
    private RichInputDate afEffectiveDateFrom;
    private RichInputDate afEffectiveDateTo;
    private RichInputText afOunDescription;
    private RichInputText afOunAddress;
    private RichInputText afOunName;
    private RichInputText afOunNumber;
    private RichInputText afCountry;

    /** OunInfo hien thi tren Popup */
    private OunInfo ounInfoPopup = new OunInfo();

    public OunStructureManager() {
    }


    public void setTreeTableModel(TreeModel treeTableModel) {
        this.treeTableModel = treeTableModel;
    }

    public TreeModel getTreeTableModel() {
        if (this.treeTableModel == null) {
            PerpSessionLogin perpSession = ViewUtil.getPerpSessionLogin();
            PerpSystemInfo psi = 
                perpSession.getAppUserInfo().getPerpSystemInfo();
            DynamicPERPFacadeLocal perpFacade = null;
            try {
                perpFacade = EJBUtil.lockupDynamicPERPFacade();
                this.ounInfoList = perpFacade.getOunInfoList(psi);
                OunInfo root = this.ounInfoList.get(0);

                this.treeTableModel = 
                        new ChildPropertyTreeModel(this.ounInfoList, 
                                                   "childList");

            } catch (PerpSystemException e) {
                FacesUtil.addError("Error", e.getMessage());
            }
        }
        return this.treeTableModel;
    }

    public void setTreeTable(RichTreeTable treeTable) {
        this.treeTable = treeTable;
    }

    public RichTreeTable getTreeTable() {
        return treeTable;
    }

    public void setOunInfoList(List<OunInfo> ounInfoList) {
        this.ounInfoList = ounInfoList;
    }

    public List<OunInfo> getOunInfoList() {
        return ounInfoList;
    }

    public void treeTableSelectionListener(SelectionEvent event) {
        OunInfo ounInfo = this.getSelectedOunInfo();
        if (ounInfo == null) {
            this.ounInfoPopup = new OunInfo();
        } else {
            try {
                this.ounInfoPopup = (OunInfo)ounInfo.clone();
            } catch (CloneNotSupportedException e) {
                FacesUtil.addError("Error", e.getMessage());
            }
        }
        FacesUtil.addPartialTarget(this.afEffectiveDateFrom);
        FacesUtil.addPartialTarget(this.afEffectiveDateTo);
        FacesUtil.addPartialTarget(this.afOunAddress);
        FacesUtil.addPartialTarget(this.afOunDescription);
        FacesUtil.addPartialTarget(this.afOunName);
        FacesUtil.addPartialTarget(this.afOunNumber);
        //FacesUtil.addPartialTarget(this.afCountry);       
    }

    /** TreeTable hien tai la Single Selection */
    public OunInfo getSelectedOunInfo() {
        if (this.treeTable == null) {
            return null;
        }
        RowKeySet set = this.treeTable.getSelectedRowKeys();
        Iterator i = set.iterator();
        Object obj = i.next();
        if (obj == null) {
            return null;
        }
        this.treeTable.setRowKey(obj);
        return (OunInfo)this.treeTable.getRowData();
    }

    public String refreshTreeTableAction() {
        this.treeTableModel = null;
        FacesUtil.addPartialTarget(this.treeTable);
        return null;
    }

    /** Create mac dinh mot OrganizationUnits */
    public String createOrganizationUnitAction() {
        OunInfo ounInfo = this.getSelectedOunInfo();
        if (ounInfo == null || ounInfo.getOunId() == null) {
            FacesUtil.addError("Error", 
                               "Ban phai lua chon cong ty / chi nhanh cha truoc khi tao chi nhanh con");
            return null;
        }
        Long ounId = ounInfo.getOunId();
        DynamicPERPFacadeLocal perpFacade = null;
        PerpSessionLogin info = ViewUtil.getPerpSessionLogin();
        String userName = info.getAppUserInfo().getUserName();
        PerpSystemInfo psi = ViewUtil.getPerpSystemInfo();

        perpFacade = EJBUtil.lockupDynamicPERPFacade();

        try {
            perpFacade.createDefaultChildOranizationUnit(psi, ounId, userName);
            // Refresh table 
            this.treeTableModel = null;
            FacesUtil.addPartialTarget(this.treeTable);
        } catch (Exception e) {
            FacesUtil.addError("Error", e.getMessage());
            return null;
        }
        return null;
    }

    public String removeOrganizationUnitAction() {
        OunInfo ounInfo = this.getSelectedOunInfo();
        if (ounInfo != null) {
            List<OunInfo> childs = ounInfo.getChildList();
            if (childs != null && childs.size() > 0) {
                FacesUtil.addError("Error", 
                                   "Ban phai xoa cac cty/chi nhanh con truoc");
                return null;
            }
            PerpSystemInfo psi = ViewUtil.getPerpSystemInfo();
            DynamicPERPFacadeLocal perpFacade;
            try {
                perpFacade = EJBUtil.lockupDynamicPERPFacade();
                perpFacade.removeOrganizationUnit(psi, ounInfo);
            } catch (PerpSystemException e) {
                FacesUtil.addError("Error", e.getMessage());
                return null;
            }
        }
        this.treeTableModel = null;
        FacesUtil.addPartialTarget(this.treeTable);
        return null;
    }


    public void setAfOunName(RichInputText afOunName) {
        this.afOunName = afOunName;
    }

    public RichInputText getAfOunName() {
        return afOunName;
    }

    public void setAfOunNumber(RichInputText afOunNumber) {
        this.afOunNumber = afOunNumber;
    }

    public RichInputText getAfOunNumber() {
        return afOunNumber;
    }

    public void setAfCountry(RichInputText afCountry) {
        this.afCountry = afCountry;
    }

    public RichInputText getAfCountry() {
        return afCountry;
    }

    public void setAfEffectiveDateFrom(RichInputDate afEffectiveDateFrom) {
        this.afEffectiveDateFrom = afEffectiveDateFrom;
    }

    public RichInputDate getAfEffectiveDateFrom() {
        return afEffectiveDateFrom;
    }

    public void setAfEffectiveDateTo(RichInputDate afEffectiveDateTo) {
        this.afEffectiveDateTo = afEffectiveDateTo;
    }

    public RichInputDate getAfEffectiveDateTo() {
        return afEffectiveDateTo;
    }

    public void setAfOunDescription(RichInputText afOunDescription) {
        this.afOunDescription = afOunDescription;
    }

    public RichInputText getAfOunDescription() {
        return afOunDescription;
    }

    public void setAfOunAddress(RichInputText afOunAddress) {
        this.afOunAddress = afOunAddress;
    }

    public RichInputText getAfOunAddress() {
        return afOunAddress;
    }

    public void setOunInfoPopup(OunInfo ounInfoPopup) {
        this.ounInfoPopup = ounInfoPopup;
    }

    public OunInfo getOunInfoPopup() {
        return ounInfoPopup;
    }


}
