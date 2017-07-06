package com.vc.web.backing.initialSetting.beans;

import com.vc.web.PerpSystemException;
import com.vc.web.PerpSystemInfo;
import com.vc.web.beans.PerpSessionLogin;
import com.vc.web.ejb.perp.DynamicPERPFacadeLocal;
import com.vc.web.perp.OunInfo;
import com.vc.web.perp.OunShortInfo;
import com.vc.web.perp.Spa4Oun;
import com.vc.web.util.EJBUtil;
import com.vc.web.util.FacesUtil;
import com.vc.web.util.ViewUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;

import java.util.Iterator;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import javax.naming.NamingException;

import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichImage;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.TreeModel;
import org.apache.myfaces.trinidad.model.UploadedFile;


public class OunSpaManager {

    private Spa4Oun spa4Oun = new Spa4Oun();

    private List<OunShortInfo> ounShortInfoList;
    private TreeModel treeTableModel = null;
    private RichTreeTable treeTable;

    /** Some Binding inputField */
    private RichInputText coNameTf;

    private RichInputText coAddressTf;
    private RichInputText coTelNoTf;
    private RichInputText coFaxNoTf;
    private RichInputText coTaxFormTf;
    private RichInputText coTaxNoTf;
    private RichInputText coLogoTf;
    private RichImage logoImage;

    private RichCommandLink coLogoLink;
    private RichInputFile upploadLogo;


    public OunSpaManager() {
    }

    public void setSpa4Oun(Spa4Oun spa4Oun) {
        this.spa4Oun = spa4Oun;
    }

    public Spa4Oun getSpa4Oun() {
        return spa4Oun;
    }

    public void setTreeTable(RichTreeTable treeTable) {
        this.treeTable = treeTable;
    }

    public RichTreeTable getTreeTable() {
        return treeTable;
    }


    public void setCoNameTf(RichInputText coNameTf) {
        this.coNameTf = coNameTf;
    }

    public RichInputText getCoNameTf() {
        return coNameTf;
    }

    public void setCoLogoTf(RichInputText coLogoTf) {
        this.coLogoTf = coLogoTf;
    }

    public RichInputText getCoLogoTf() {
        return coLogoTf;
    }

    public void setCoLogoLink(RichCommandLink coLogoLink) {
        this.coLogoLink = coLogoLink;
    }

    public RichCommandLink getCoLogoLink() {
        return coLogoLink;
    }


    public void setCoAddressTf(RichInputText coAddressTf) {
        this.coAddressTf = coAddressTf;
    }

    public RichInputText getCoAddressTf() {
        return coAddressTf;
    }

    public void setCoTelNoTf(RichInputText coTelNoTf) {
        this.coTelNoTf = coTelNoTf;
    }

    public RichInputText getCoTelNoTf() {
        return coTelNoTf;
    }

    public void setCoFaxNoTf(RichInputText coFaxNoTf) {
        this.coFaxNoTf = coFaxNoTf;
    }

    public RichInputText getCoFaxNoTf() {
        return coFaxNoTf;
    }

    public void setCoTaxFormTf(RichInputText coTaxFormTf) {
        this.coTaxFormTf = coTaxFormTf;
    }

    public RichInputText getCoTaxFormTf() {
        return coTaxFormTf;
    }

    public void setCoTaxNoTf(RichInputText coTaxNoTf) {
        this.coTaxNoTf = coTaxNoTf;
    }

    public RichInputText getCoTaxNoTf() {
        return coTaxNoTf;
    }

    public void setUpploadLogo(RichInputFile upploadLogo) {
        this.upploadLogo = upploadLogo;
    }

    public RichInputFile getUpploadLogo() {
        return upploadLogo;
    }


    public void setLogoImage(RichImage logoImage) {
        this.logoImage = logoImage;
    }

    public RichImage getLogoImage() {
        return logoImage;
    }


    public void setOunShortInfoList(List<OunShortInfo> ounShortInfoList) {
        this.ounShortInfoList = ounShortInfoList;
    }

    public List<OunShortInfo> getOunShortInfoList() {
        return ounShortInfoList;
    }

    public void setTreeTableModel(TreeModel treeTableModel) {
        this.treeTableModel = treeTableModel;
    }

    private void close(InputStream in) {
        try {
            in.close();
        } catch (IOException e) {
        }
    }

    private void close(OutputStream out) {
        try {
            out.close();
        } catch (IOException e) {
        }
    }


    private boolean isSelectOun() {
        if (this.spa4Oun == null || this.spa4Oun.getOunId() == null) {
            return false;
        }
        return true;
    }

    public TreeModel getTreeTableModel() {
        if (this.treeTableModel == null) {
            PerpSessionLogin perpSession = ViewUtil.getPerpSessionLogin();
            PerpSystemInfo psi = 
                perpSession.getAppUserInfo().getPerpSystemInfo();
            DynamicPERPFacadeLocal perpFacade = null;
            try {
                perpFacade = EJBUtil.lockupDynamicPERPFacade();
                this.ounShortInfoList = perpFacade.getOunShortInfoList(psi);

                OunShortInfo root = this.ounShortInfoList.get(0);

                this.treeTableModel = 
                        new ChildPropertyTreeModel(this.ounShortInfoList, 
                                                   "childList");
            } catch (PerpSystemException e) {
                FacesUtil.addError("Error", e.getMessage());
            }
        }
        return this.treeTableModel;
    }

    /** TreeTable hien tai la Single Selection */
    public OunShortInfo getSelectedOunShortInfo() {
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
        return (OunShortInfo)this.treeTable.getRowData();
    }

    public void treeTableSelectionListener(SelectionEvent event) {
        RichTreeTable table = (RichTreeTable)event.getSource();
        RowKeySet set = table.getSelectedRowKeys();

        PerpSystemInfo psi = 
            ViewUtil.getPerpSessionLogin().getAppUserInfo().getPerpSystemInfo();

        DynamicPERPFacadeLocal perpFacade = EJBUtil.lockupDynamicPERPFacade();

        OunShortInfo oun = this.getSelectedOunShortInfo();
        if (oun != null) {
            Long ounId = oun.getOunId();
            Long parentOunId = oun.getParentId();
            // Load cac gia tri System Para tuong ung voi OUN nay .        
            try {
                this.spa4Oun = 
                        perpFacade.getSpa4Oun(psi, ounId, parentOunId == null);
            } catch (PerpSystemException e) {
                FacesUtil.addError("Error", e.getMessage());
            }
        }
        // Reload ...
        this.refreshSomeControl();
    }

    private void refreshSomeControl() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.addPartialTarget(this.coNameTf);
        context.addPartialTarget(this.coLogoLink);
        context.addPartialTarget(this.coAddressTf);
        context.addPartialTarget(this.coTelNoTf);
        context.addPartialTarget(this.coFaxNoTf);
        context.addPartialTarget(this.coTaxFormTf);
        context.addPartialTarget(this.coTaxNoTf);
        context.addPartialTarget(this.coLogoTf);
    }


    public String refreshTreeTableAction() {
        this.treeTableModel = null;
        RequestContext.getCurrentInstance().addPartialTarget(this.treeTable);
        return null;
    }


    public String saveSpa4OunAction() {
        if (!this.isSelectOun()) {
            return null;
        }
        PerpSessionLogin perpSession = ViewUtil.getPerpSessionLogin();
        PerpSystemInfo psi = perpSession.getAppUserInfo().getPerpSystemInfo();
        DynamicPERPFacadeLocal perpFacade = EJBUtil.lockupDynamicPERPFacade();
        try {
            perpFacade.saveSpa4Oun(psi, this.spa4Oun);
        } catch (PerpSystemException e) {
            FacesUtil.addError("Error", e.getMessage());
            return null;
        }
        return null;
    }

    public String displayLogoPopupAction() {
        if (!this.isSelectOun()) {
            return null;
        }
        RequestContext.getCurrentInstance().addPartialTarget(this.upploadLogo);
        return null;
    }

    public void saveUploadFileListener(ValueChangeEvent event) {

        UploadedFile file = (UploadedFile)event.getNewValue();
        if (file == null) {
            return;
        }

        PerpSessionLogin perpSess = ViewUtil.getPerpSessionLogin();
        String activeCode = perpSess.getAppUserInfo().getActivatedCode();
        InputStream in = null;
        OutputStream out = null;
        String fileName = 
            "@C0_LOGO_" + activeCode + "_" + this.spa4Oun.getOunId() + ".gif";

        try {
            in = file.getInputStream();
            out = new FileOutputStream("C:/" + fileName);
            byte[] bytes = new byte[1000];
            int i = 0;
            while ((i = in.read(bytes)) > 0) {
                out.write(bytes, 0, i);
            }

            in.close();
            out.close();
        } catch (Exception e) {
            FacesUtil.addError("Error", e.getMessage());
            return;
        } finally {
            this.close(in);
            this.close(out);
        }
        this.spa4Oun.setCoLogo(fileName);
        this.saveSpa4OunAction();
        // Refresh hien thi tren giao dien
        this.refreshSomeControl();
    }
}
