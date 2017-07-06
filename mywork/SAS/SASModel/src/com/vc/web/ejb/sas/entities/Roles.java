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
  @NamedQuery(name = "Roles.findAll", query = "select o from Roles o"),
  @NamedQuery(name = "Roles.findAll.size", query = "select count(o) from Roles o")
})
public class Roles implements Serializable {
    private String name;
    @Id
    @Column(name="ROL_ID", nullable = false)
    private Long rolId;
    @OneToMany(mappedBy = "roles")
    private List<Accounts> accountsList;

    public Roles() {
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
        accounts.setRoles(this);
        return accounts;
    }

    public Accounts removeAccounts(Accounts accounts) {
        getAccountsList().remove(accounts);
        accounts.setRoles(null);
        return accounts;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public Long getRolId() {
        return rolId;
    }
}
