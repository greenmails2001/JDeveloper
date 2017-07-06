package com.vc.web.ejb.sas.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "ServicePackages.findAll", 
                             query = "select o from ServicePackages o")
        , 
        @NamedQuery(name = "ServicePackages.findByPackageCode", query = "select spa from ServicePackages spa where spa.packageCode=:packageCode")
        , 
        @NamedQuery(name = "ServicePackages.findBySpaId", query = "select spa from ServicePackages spa where spa.spaId=:spaId")
        , 
        @NamedQuery(name = "ServicePackages.findToOrder", query = "select spa from ServicePackages spa")
        , 
        @NamedQuery(name = "ServicePackages.findAll.size", query = "select count(o) from ServicePackages o")
        } )
@Table(name = "SERVICE_PACKAGES")
public class ServicePackages implements Serializable {
    private String description;
    private String enabled;
    @Column(name = "NUM_OF_SERVERS")
    private Long numOfServers;
    @Column(name = "PACKAGE_CODE")
    private String packageCode;
    @Column(name = "PACKAGE_NAME")
    private String packageName;
    @Column(name = "SERIVE_TIME")
    private Long seriveTime;
    @Column(name = "SERVICE_TYPE")
    private String serviceType;
    @Id
    @Column(name = "SPA_ID", nullable = false)
    private Long spaId;
    @Column(name = "UNIT_PRICE")
    private Double unitPrice;


    public ServicePackages() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public Long getNumOfServers() {
        return numOfServers;
    }

    public void setNumOfServers(Long numOfServers) {
        this.numOfServers = numOfServers;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Long getSeriveTime() {
        return seriveTime;
    }

    public void setSeriveTime(Long seriveTime) {
        this.seriveTime = seriveTime;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }


    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setSpaId(Long spaId) {
        this.spaId = spaId;
    }

    public Long getSpaId() {
        return spaId;
    }
}
