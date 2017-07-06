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
  @NamedQuery(name = "Industries.findAll", query = "select o from Industries o"),
  @NamedQuery(name = "Industries.findAll.size", query = "select count(o) from Industries o")
})
public class Industries implements Serializable {
    @Id
    @Column(name="IND_ID", nullable = false)
    private Long indId;
    private String name;
    @OneToMany(mappedBy = "industries")
    private List<Accounts> accountsList;

    public Industries() {
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
        accounts.setIndustries(this);
        return accounts;
    }

    public Accounts removeAccounts(Accounts accounts) {
        getAccountsList().remove(accounts);
        accounts.setIndustries(null);
        return accounts;
    }

    public void setIndId(Long indId) {
        this.indId = indId;
    }

    public Long getIndId() {
        return indId;
    }
}
