package com.vc.web.ejb.sas.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
@NamedQueries( { @NamedQuery(name = "Accounts.findAll", 
                             query = "select o from Accounts o")
        , 
        @NamedQuery(name = "Accounts.findByUserName", query = "select o from Accounts o where o.userName=:userName")
        , 
        @NamedQuery(name = "Accounts.findByEmail", query = "select o from Accounts o where o.email=:email")
        , 
        @NamedQuery(name = "Accounts.findByUserNamePassowrd", query = "select o from Accounts o where o.userName=:userName and o.password=:password")
        , 
        @NamedQuery(name = "Accounts.findAll.size", query = "select count(o) from Accounts o")
        } )
@SequenceGenerator(name = "Acc_Seq", sequenceName = "Acc_Seq", 
                   allocationSize = 1)
public class Accounts implements Serializable {
    @Id
    @Column(name = "ACC_ID", nullable = false)

    @GeneratedValue(generator = "Acc_Seq")
    private Long accId;
    @Column(name = "ACTIVED_CODE")
    private String activedCode;
    private String address;
    @Column(name = "CONTACT_TELEPHONE")
    private String contactTelephone;
    @Column(name = "CUSTOMER_APPROACH")
    private String customerApproach;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "NUMBER_OF_EMPLOYEE")
    private Long numberOfEmployee;
    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;
    private String password;
    @Transient
    private String confirmPassword;
    private String status;
    @Column(name = "TAX_CODE")
    private String taxCode;
    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String userName;
    @ManyToOne
    @JoinColumn(name = "ROL_ID")
    private Roles roles;
    @ManyToOne
    @JoinColumn(name = "PRO_ID")
    private Provinces provinces;
    @ManyToOne
    @JoinColumn(name = "IND_ID")
    private Industries industries;
    @ManyToOne
    @JoinColumn(name = "COU_ID")
    private Countries countries;
    @OneToMany(mappedBy = "accounts")
    private List<Subscriptions> subscriptionsList;   
    
    @Column(name = "SECURITY_ANSWER")    
    private String securityAnswer ;
    @ManyToOne
    @JoinColumn(name = "SQU_ID")
    private SecurityQuestions securityQuestions;

    public Accounts() {
    }


    public String getActivedCode() {
        return activedCode;
    }

    public void setActivedCode(String activedCode) {
        this.activedCode = activedCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactTelephone() {
        return contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }


    public String getCustomerApproach() {
        return customerApproach;
    }

    public void setCustomerApproach(String customerApproach) {
        this.customerApproach = customerApproach;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public Long getNumberOfEmployee() {
        return numberOfEmployee;
    }

    public void setNumberOfEmployee(Long numberOfEmployee) {
        this.numberOfEmployee = numberOfEmployee;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Provinces getProvinces() {
        return provinces;
    }

    public void setProvinces(Provinces provinces) {
        this.provinces = provinces;
    }

    public Industries getIndustries() {
        return industries;
    }

    public void setIndustries(Industries industries) {
        this.industries = industries;
    }

    public Countries getCountries() {
        return countries;
    }

    public void setCountries(Countries countries) {
        this.countries = countries;
    }

    public List<Subscriptions> getSubscriptionsList() {
        return subscriptionsList;
    }

    public void setSubscriptionsList(List<Subscriptions> subscriptionsList) {
        this.subscriptionsList = subscriptionsList;
    }

    public Subscriptions addSubscriptions(Subscriptions subscriptions) {
        getSubscriptionsList().add(subscriptions);
        subscriptions.setAccounts(this);
        return subscriptions;
    }

    public Subscriptions removeSubscriptions(Subscriptions subscriptions) {
        getSubscriptionsList().remove(subscriptions);
        subscriptions.setAccounts(null);
        return subscriptions;
    }

    public void setAccId(Long accId) {
        this.accId = accId;
    }

    public Long getAccId() {
        return accId;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityQuestions(SecurityQuestions securityQuestions) {
        this.securityQuestions = securityQuestions;
    }

    public SecurityQuestions getSecurityQuestions() {
        return securityQuestions;
    }
}
