package com.vc.web.ejb.sas.entities;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;


@Entity
@NamedQueries( { @NamedQuery(name = "Subscriptions.findAll", 
                             query = "select o from Subscriptions o")
                , 
        @NamedQuery(name = "Subscriptions.findBySubId", query = "select o from Subscriptions o where o.subId=:subId")         
        , 
        @NamedQuery(name = "Subscriptions.findByAccId", query = "select o from Subscriptions o where o.accounts.accId=:accId")
        , 
        @NamedQuery(name = "Subscriptions.findByActivatedCode", query = "select o from Subscriptions o where o.activatedCode=:activatedCode")
        , 
        @NamedQuery(name = "Subscriptions.findAll.size", query = "select count(o) from Subscriptions o")
        } )
@SequenceGenerator(name = "Sub_Seq", sequenceName = "Sub_Seq", 
                   allocationSize = 1)
public class Subscriptions implements Serializable {
    @Column(name = "APP_URL")
    private String appUrl;
    @Column(name = "END_DATE")
    private Timestamp endDate;
    private Double quantity;
    @Column(name = "START_DATE")
    private Timestamp startDate;
    private String status;
    @Column(name = "SUBSCRIBE_DATE")
    private Timestamp subscribeDate;
    @Id
    @GeneratedValue(generator = "Sub_Seq")
    @Column(name = "SUB_ID", nullable = false)
    private Long subId;

    @ManyToOne
    @JoinColumn(name = "ACC_ID")
    private Accounts accounts;

    @GeneratedValue(generator = "Sub_Seq")
    @Column(name = "ACTIVATED_CODE")
    private String activatedCode;
    
    @ManyToOne
    @JoinColumn(name = "SPA_ID")
    private ServicePackages servicePackages;

    public Subscriptions() {
    }


    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }


    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(Timestamp subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public void setActivatedCode(String activatedCode) {
        this.activatedCode = activatedCode;
    }

    public String getActivatedCode() {
        return activatedCode;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Long getSubId() {
        return subId;
    }
    
    public ServicePackages getServicePackages() {
        return servicePackages;
    }

    public void setServicePackages(ServicePackages servicePackages) {
        this.servicePackages = servicePackages;
    }

}
