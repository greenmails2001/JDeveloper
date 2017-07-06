package com.perp.sasregistmodel.views.sasr002;

import com.perp.sasregistmodel.views.sasr002.common.Sasr002A2ServicePackagesRow;

import oracle.jbo.RowIterator;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Nov 06 10:05:35 ICT 2009
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class Sasr002A2ServicePackagesRowImpl extends ViewRowImpl implements Sasr002A2ServicePackagesRow {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        Description {
            public Object get(Sasr002A2ServicePackagesRowImpl obj) {
                return obj.getDescription();
            }

            public void put(Sasr002A2ServicePackagesRowImpl obj,
                            Object value) {
                obj.setDescription((String)value);
            }
        }
        ,
        Enabled {
            public Object get(Sasr002A2ServicePackagesRowImpl obj) {
                return obj.getEnabled();
            }

            public void put(Sasr002A2ServicePackagesRowImpl obj,
                            Object value) {
                obj.setEnabled((String)value);
            }
        }
        ,
        NumOfServers {
            public Object get(Sasr002A2ServicePackagesRowImpl obj) {
                return obj.getNumOfServers();
            }

            public void put(Sasr002A2ServicePackagesRowImpl obj,
                            Object value) {
                obj.setNumOfServers((Number)value);
            }
        }
        ,
        PackageCode {
            public Object get(Sasr002A2ServicePackagesRowImpl obj) {
                return obj.getPackageCode();
            }

            public void put(Sasr002A2ServicePackagesRowImpl obj,
                            Object value) {
                obj.setPackageCode((String)value);
            }
        }
        ,
        PackageName {
            public Object get(Sasr002A2ServicePackagesRowImpl obj) {
                return obj.getPackageName();
            }

            public void put(Sasr002A2ServicePackagesRowImpl obj,
                            Object value) {
                obj.setPackageName((String)value);
            }
        }
        ,
        SeriveTime {
            public Object get(Sasr002A2ServicePackagesRowImpl obj) {
                return obj.getSeriveTime();
            }

            public void put(Sasr002A2ServicePackagesRowImpl obj,
                            Object value) {
                obj.setSeriveTime((Number)value);
            }
        }
        ,
        ServiceType {
            public Object get(Sasr002A2ServicePackagesRowImpl obj) {
                return obj.getServiceType();
            }

            public void put(Sasr002A2ServicePackagesRowImpl obj,
                            Object value) {
                obj.setServiceType((String)value);
            }
        }
        ,
        SpaId {
            public Object get(Sasr002A2ServicePackagesRowImpl obj) {
                return obj.getSpaId();
            }

            public void put(Sasr002A2ServicePackagesRowImpl obj,
                            Object value) {
                obj.setSpaId((Number)value);
            }
        }
        ,
        UnitPrice {
            public Object get(Sasr002A2ServicePackagesRowImpl obj) {
                return obj.getUnitPrice();
            }

            public void put(Sasr002A2ServicePackagesRowImpl obj,
                            Object value) {
                obj.setUnitPrice((Number)value);
            }
        }
        ,
        Sasr002A2Subscriptions {
            public Object get(Sasr002A2ServicePackagesRowImpl obj) {
                return obj.getSasr002A2Subscriptions();
            }

            public void put(Sasr002A2ServicePackagesRowImpl obj,
                            Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(Sasr002A2ServicePackagesRowImpl object);

        public abstract void put(Sasr002A2ServicePackagesRowImpl object,
                                 Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int DESCRIPTION = AttributesEnum.Description.index();
    public static final int ENABLED = AttributesEnum.Enabled.index();
    public static final int NUMOFSERVERS = AttributesEnum.NumOfServers.index();
    public static final int PACKAGECODE = AttributesEnum.PackageCode.index();
    public static final int PACKAGENAME = AttributesEnum.PackageName.index();
    public static final int SERIVETIME = AttributesEnum.SeriveTime.index();
    public static final int SERVICETYPE = AttributesEnum.ServiceType.index();
    public static final int SPAID = AttributesEnum.SpaId.index();
    public static final int UNITPRICE = AttributesEnum.UnitPrice.index();
    public static final int SASR002A2SUBSCRIPTIONS = AttributesEnum.Sasr002A2Subscriptions.index();

    /**
     * This is the default constructor (do not remove).
     */
    public Sasr002A2ServicePackagesRowImpl() {
    }

    /**
     * Gets ServicePackages entity object.
     * @return the ServicePackages
     */
    public EntityImpl getServicePackages() {
        return (EntityImpl)getEntity(0);
    }

    /**
     * Gets the attribute value for DESCRIPTION using the alias name Description.
     * @return the DESCRIPTION
     */
    public String getDescription() {
        return (String) getAttributeInternal(DESCRIPTION);
    }

    /**
     * Sets <code>value</code> as attribute value for DESCRIPTION using the alias name Description.
     * @param value value to set the DESCRIPTION
     */
    public void setDescription(String value) {
        setAttributeInternal(DESCRIPTION, value);
    }

    /**
     * Gets the attribute value for ENABLED using the alias name Enabled.
     * @return the ENABLED
     */
    public String getEnabled() {
        return (String) getAttributeInternal(ENABLED);
    }

    /**
     * Sets <code>value</code> as attribute value for ENABLED using the alias name Enabled.
     * @param value value to set the ENABLED
     */
    public void setEnabled(String value) {
        setAttributeInternal(ENABLED, value);
    }

    /**
     * Gets the attribute value for NUM_OF_SERVERS using the alias name NumOfServers.
     * @return the NUM_OF_SERVERS
     */
    public Number getNumOfServers() {
        return (Number) getAttributeInternal(NUMOFSERVERS);
    }

    /**
     * Sets <code>value</code> as attribute value for NUM_OF_SERVERS using the alias name NumOfServers.
     * @param value value to set the NUM_OF_SERVERS
     */
    public void setNumOfServers(Number value) {
        setAttributeInternal(NUMOFSERVERS, value);
    }

    /**
     * Gets the attribute value for PACKAGE_CODE using the alias name PackageCode.
     * @return the PACKAGE_CODE
     */
    public String getPackageCode() {
        return (String) getAttributeInternal(PACKAGECODE);
    }

    /**
     * Sets <code>value</code> as attribute value for PACKAGE_CODE using the alias name PackageCode.
     * @param value value to set the PACKAGE_CODE
     */
    public void setPackageCode(String value) {
        setAttributeInternal(PACKAGECODE, value);
    }

    /**
     * Gets the attribute value for PACKAGE_NAME using the alias name PackageName.
     * @return the PACKAGE_NAME
     */
    public String getPackageName() {
        return (String) getAttributeInternal(PACKAGENAME);
    }

    /**
     * Sets <code>value</code> as attribute value for PACKAGE_NAME using the alias name PackageName.
     * @param value value to set the PACKAGE_NAME
     */
    public void setPackageName(String value) {
        setAttributeInternal(PACKAGENAME, value);
    }

    /**
     * Gets the attribute value for SERIVE_TIME using the alias name SeriveTime.
     * @return the SERIVE_TIME
     */
    public Number getSeriveTime() {
        return (Number) getAttributeInternal(SERIVETIME);
    }

    /**
     * Sets <code>value</code> as attribute value for SERIVE_TIME using the alias name SeriveTime.
     * @param value value to set the SERIVE_TIME
     */
    public void setSeriveTime(Number value) {
        setAttributeInternal(SERIVETIME, value);
    }

    /**
     * Gets the attribute value for SERVICE_TYPE using the alias name ServiceType.
     * @return the SERVICE_TYPE
     */
    public String getServiceType() {
        return (String) getAttributeInternal(SERVICETYPE);
    }

    /**
     * Sets <code>value</code> as attribute value for SERVICE_TYPE using the alias name ServiceType.
     * @param value value to set the SERVICE_TYPE
     */
    public void setServiceType(String value) {
        setAttributeInternal(SERVICETYPE, value);
    }

    /**
     * Gets the attribute value for SPA_ID using the alias name SpaId.
     * @return the SPA_ID
     */
    public Number getSpaId() {
        return (Number) getAttributeInternal(SPAID);
    }

    /**
     * Sets <code>value</code> as attribute value for SPA_ID using the alias name SpaId.
     * @param value value to set the SPA_ID
     */
    public void setSpaId(Number value) {
        setAttributeInternal(SPAID, value);
    }

    /**
     * Gets the attribute value for UNIT_PRICE using the alias name UnitPrice.
     * @return the UNIT_PRICE
     */
    public Number getUnitPrice() {
        return (Number) getAttributeInternal(UNITPRICE);
    }

    /**
     * Sets <code>value</code> as attribute value for UNIT_PRICE using the alias name UnitPrice.
     * @param value value to set the UNIT_PRICE
     */
    public void setUnitPrice(Number value) {
        setAttributeInternal(UNITPRICE, value);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link Sasr002A2Subscriptions.
     */
    public RowIterator getSasr002A2Subscriptions() {
        return (RowIterator)getAttributeInternal(SASR002A2SUBSCRIPTIONS);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index,
                                           AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].get(this);
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    /**
     * setAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param value the value to assign to the attribute
     * @param attrDef the attribute

     * @throws Exception
     */
    protected void setAttrInvokeAccessor(int index, Object value,
                                         AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }
}
