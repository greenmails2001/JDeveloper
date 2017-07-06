package com.vc.web.backing.shoppingSas;

import com.vc.web.backing.shoppingSas.beans.SpaOrder;
import com.vc.web.beans.SasSessionLogin;
import com.vc.web.ejb.sas.SASFacadeLocal;

import com.vc.web.ejb.sas.entities.ServicePackages;
import com.vc.web.ejb.sas.entities.Subscriptions;
import com.vc.web.util.EJBUtil;

import com.vc.web.util.FacesUtil;
import com.vc.web.util.ViewUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import javax.naming.NamingException;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.dnd.DnDAction;
import oracle.adf.view.rich.event.DropEvent;

import oracle.ias.container.event.Subscription;

import oracle.jbo.RowSetIterator;

import org.apache.myfaces.trinidad.model.RowKeySet;

public class ShoppingCartHandler {
    private List<SpaOrder> spaOrderList;
    private RichTable table;
    private RichTable shoppingCartTable;

    public ShoppingCartHandler() {
    }

    public void setSpaOrderList(List<SpaOrder> spaOrderList) {
        this.spaOrderList = spaOrderList;
    }

    public List<SpaOrder> getSpaOrderList() {
        if (this.spaOrderList == null) {
            this.spaOrderList = new ArrayList<SpaOrder>();
        }
        return this.spaOrderList;
    }

    /** Them vao gio hang mot Service Package */
    private void addServicePackage(String packageCode) throws Exception {
        if (packageCode == null) {
            return;
        }
        // Kiem tra trong danh sach hien tai da co service Package do chua .
        List<SpaOrder> list = this.getSpaOrderList();
        for (SpaOrder item : list) {
            if (packageCode.equals(item.getPackageCode())) {
                return;
            }
        }
        SASFacadeLocal sasFacade = EJBUtil.lockupSASFacade();
        ServicePackages spa = 
            sasFacade.queryServicePackagesFindByPackageCode(packageCode);
        if (spa == null) {
            return;
        }
        SpaOrder spaOrder = new SpaOrder();
        spaOrder.setPackageCode(spa.getPackageCode());
        spaOrder.setSpaId(spa.getSpaId());
        spaOrder.setPackageName(spa.getPackageName());
        list.add(spaOrder);
    }

    /** Remove Service Package */
    private void removeServicePackage(String packageCode) throws Exception {
        if (packageCode == null) {
            return;
        }
        // Kiem tra trong danh sach hien tai da co service Package do chua .
        List<SpaOrder> list = this.getSpaOrderList();
        for (SpaOrder item : list) {
            if (packageCode.equals(item.getPackageCode())) {
                list.remove(item);
                return;
            }
        }
    }

    /** Su ly tinh huong khi drop lua chon mot ServicePackage .
     */
    public DnDAction shoppingCartDropListener(DropEvent dropEvent) {
        String packageCode = dropEvent.getTransferable().getData(String.class);
        if (packageCode == null) {
            return DnDAction.NONE;
        }
        try {
            this.addServicePackage(packageCode);
        } catch (Exception e) {
            FacesUtil.addError("Error", 
                               "Shopping Cart Error " + e.getMessage());
        }
        return DnDAction.COPY;
    }


    private void clearShoppingCart() {
        this.spaOrderList = null;
    }

    /** Nguoi dung nhan vao nut chap nhan gio hang */
    public String acceptShoppingCartAction() {
        SASFacadeLocal sasFacade = EJBUtil.lockupSASFacade();

        List<SpaOrder> list = this.getSpaOrderList();
        SasSessionLogin sasSession = ViewUtil.getSasSessionLogin();
        for (SpaOrder item : list) {
            Subscriptions sub = 
                sasFacade.createSubscriptions(sasSession.getAccId(), 
                                              item.getSpaId());
            try {
                if (sub != null) {
                    sasFacade.sendEmailCreateSub(sasSession.getAccId(), 
                                                 sub.getSubId());
                }
            } catch (Exception e) {
                System.out.println("Error Send Mail where buy PERP :" + 
                                   e.getMessage());
            }
        }
        this.clearShoppingCart();
        return "save";
    }

    public String clearShoppingCartAction() {
        this.clearShoppingCart();
        return null;
    }

    public void selectProductCheckChangeListener(ValueChangeEvent e) {
      //  DCIteratorBinding ite = 
      //      (DCIteratorBinding)FacesUtil.resolveExpression("#{queryServicePackagesFindToOrderIterator}");
        //RowSetIterator rowIte = ite.getRowSetIterator();

        /*
        Boolean chon = (Boolean)e.getNewValue();
        RowKeySet keySet = this.table.getSelectedRowKeys();
        Iterator ite = keySet.iterator();
        Object key = ite.next();
        if (key == null) {
            return;
        }
        System.out.println("OBJECT2 :"+this.table.getRowData().getClass().getName());
        this.table.setRowKey(key);
        Object obj=this.table.getRowData();
        System.out.println("OBJECT :"+obj+this.table.getRowData());
        ServicePackages spa = (ServicePackages)this.table.getRowData();
        if (spa == null) {
            return;
        }
        String packageCode = spa.getPackageCode();
        try {
            if (chon) {
                this.addServicePackage(packageCode);
            } else {
                this.removeServicePackage(packageCode);
            }
        } catch (Exception e1) {
            FacesUtil.addError("Error", e1.getMessage());
        }
        FacesUtil.addPartialTarget(this.shoppingCartTable);//*/
    }

    public void setTable(RichTable table) {
        this.table = table;
    }

    public RichTable getTable() {
        return table;
    }

    public void setShoppingCartTable(RichTable shoppingCartTable) {
        this.shoppingCartTable = shoppingCartTable;
    }

    public RichTable getShoppingCartTable() {
        return shoppingCartTable;
    }
}
