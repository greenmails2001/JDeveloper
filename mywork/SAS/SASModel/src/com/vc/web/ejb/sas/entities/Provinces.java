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
  @NamedQuery(name = "Provinces.findAll", query = "select o from Provinces o"),
  @NamedQuery(name = "Provinces.findAll.size", query = "select count(o) from Provinces o")
})
public class Provinces implements Serializable {
    private String name;
    @Id
    @Column(name="PRO_ID", nullable = false)
    private Long proId;
    @OneToMany(mappedBy = "provinces")
    private List<Accounts> accountsList;

    public Provinces() {
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
        accounts.setProvinces(this);
        return accounts;
    }

    public Accounts removeAccounts(Accounts accounts) {
        getAccountsList().remove(accounts);
        accounts.setProvinces(null);
        return accounts;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public Long getProId() {
        return proId;
    }
}
