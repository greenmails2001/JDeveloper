package com.vc.web.ejb.perp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "SystemParas.findAll", 
                             query = "select o from SystemParas o")
        , 
        @NamedQuery(name = "SystemParas.findByUserPrivilege", query = "select o from SystemParas o where o.userPrivilege=:userPrivilege")
        ,
        @NamedQuery(name = "SystemParas.findByUserPrivilegeChangable", 
                    query = "select o from SystemParas o where o.userPrivilege in ('U','I')")                 
        , 
        @NamedQuery(name = "SystemParas.findAll.size", query = "select count(o) from SystemParas o")
        } )
@Table(name = "SYSTEM_PARAS")
  @SequenceGenerator(name="Seq_Gen",sequenceName="Spa_Seq",allocationSize=1)

public class SystemParas implements Serializable {
    @Column(nullable = false)
    private String code;
    private String description;
    @Id
    @Column(name = "SPA_ID", nullable = false)
    @GeneratedValue(generator="Seq_Gen")
    private Long spaId;
    @Column(nullable = false)
    private String value;
    @Column(name = "USER_PRIVILEGE")
    private String userPrivilege;

    @ManyToOne
    @JoinColumn(name = "OUN_ID")
    private OrganizationUnits organizationUnits;

    public SystemParas() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Long getSpaId() {
        return spaId;
    }

    public void setSpaId(Long spaId) {
        this.spaId = spaId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public OrganizationUnits getOrganizationUnits() {
        return organizationUnits;
    }

    public void setOrganizationUnits(OrganizationUnits organizationUnits) {
        this.organizationUnits = organizationUnits;
    }

    public void setUserPrivilege(String userPrivilege) {
        this.userPrivilege = userPrivilege;
    }

    public String getUserPrivilege() {
        return userPrivilege;
    }
    
    public String toString() {
        return "spaId="+this.spaId+",code="+this.code+",value="+this.value;
    }
}
