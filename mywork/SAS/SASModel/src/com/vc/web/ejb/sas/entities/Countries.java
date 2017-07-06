package com.vc.web.ejb.sas.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
  @NamedQuery(name = "Countries.findAll", query = "select o from Countries o"),
  @NamedQuery(name = "Countries.findAll.size", query = "select count(o) from Countries o")
})
public class Countries implements Serializable {
    @Column(name="COUNTRY_CODE")
    private String countryCode;
    @Id
    @Column(name="COU_ID", nullable = false)
    private Long couId;
    private String name;
    @OneToMany(mappedBy = "countries")
    private List<Accounts> accountsList;

    public Countries() {
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Accounts> getAccountsList() {
        return accountsList;
    }

    public void setAccountsList(List<Accounts> accountsList) {
        this.accountsList = accountsList;
    }

    public Accounts addAccounts(Accounts accounts) {
        getAccountsList().add(accounts);
        accounts.setCountries(this);
        return accounts;
    }

    public Accounts removeAccounts(Accounts accounts) {
        getAccountsList().remove(accounts);
        accounts.setCountries(null);
        return accounts;
    }

    public void setCouId(Long couId) {
        this.couId = couId;
    }

    public Long getCouId() {
        return couId;
    }
    
    public String toString() {
        return this.name;
    }
}
