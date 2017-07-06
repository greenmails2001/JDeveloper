package com.vc.web.ejb.perp.entities;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "OrganizationUnits.findAll", query = "select o from OrganizationUnits o"),

  @NamedQuery(name = "OrganizationUnits.findAll.size", query = "select count(o) from OrganizationUnits o")
})
@Table(name = "ORGANIZATION_UNITS")
public class OrganizationUnits implements Serializable {
    private String address;
    private String attribute1;
    private String attribute10;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String attribute6;
    private String attribute7;
    private String attribute8;
    private String attribute9;
    @Column(nullable = false)
    private String country;
    @Column(name="CREATED_BY", nullable = false)
    private String createdBy;
    @Column(name="CREATE_DATE", nullable = false)
    private Timestamp createDate;
    private String description;
    @Column(name="EFFECTIVE_DATE_FROM", nullable = false)
    private Timestamp effectiveDateFrom;
    @Column(name="EFFECTIVE_DATE_TO")
    private Timestamp effectiveDateTo;
    @Column(name="EXTERNAL_FLAG", nullable = false)
    private String externalFlag;
    @Column(name="HN_BRANCH")
    private String hnBranch;
    @Column(name="HQ_FLAG", nullable = false)
    private String hqFlag;
    @Column(name="MODIFIED_BY")
    private String modifiedBy;
    @Column(name="MODIFY_DATE")
    private Timestamp modifyDate;
    @Column(nullable = false)
    private String name;
    @Column(name="OPS_FLAG", nullable = false)
    private String opsFlag;
    @Id
    @Column(name="OUN_ID", nullable = false)
    private Long ounId;
    @Column(name="OUN_NUMBER", nullable = false)
    private String ounNumber;
    @Column(name="OUN_NUMBER_2")
    private String ounNumber2;
    @Column(name="OUN_TYPE", nullable = false)
    private String ounType;
    private String province;
    private String telephone;
    @OneToMany(mappedBy = "organizationUnits")
    private List<SystemParas> systemParasList;
    @OneToMany(mappedBy = "organizationUnits")
    private List<CostCenters> costCentersList;
    @ManyToOne
    @JoinColumn(name = "EMP_ID")
    private Employees employees;
    @ManyToOne
    @JoinColumn(name = "OUN_ID_CHILD_OF")
    private OrganizationUnits organizationUnits;
    @OneToMany(mappedBy = "organizationUnits")
    private List<OrganizationUnits> organizationUnitsList;
    @ManyToOne
    @JoinColumn(name = "CCE_ID")
    private CostCenters costCenters;

    public OrganizationUnits() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getEffectiveDateFrom() {
        return effectiveDateFrom;
    }

    public void setEffectiveDateFrom(Timestamp effectiveDateFrom) {
        this.effectiveDateFrom = effectiveDateFrom;
    }

    public Timestamp getEffectiveDateTo() {
        return effectiveDateTo;
    }

    public void setEffectiveDateTo(Timestamp effectiveDateTo) {
        this.effectiveDateTo = effectiveDateTo;
    }


    public String getExternalFlag() {
        return externalFlag;
    }

    public void setExternalFlag(String externalFlag) {
        this.externalFlag = externalFlag;
    }

    public String getHnBranch() {
        return hnBranch;
    }

    public void setHnBranch(String hnBranch) {
        this.hnBranch = hnBranch;
    }

    public String getHqFlag() {
        return hqFlag;
    }

    public void setHqFlag(String hqFlag) {
        this.hqFlag = hqFlag;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpsFlag() {
        return opsFlag;
    }

    public void setOpsFlag(String opsFlag) {
        this.opsFlag = opsFlag;
    }

    public Long getOunId() {
        return ounId;
    }

    public void setOunId(Long ounId) {
        this.ounId = ounId;
    }


    public String getOunNumber() {
        return ounNumber;
    }

    public void setOunNumber(String ounNumber) {
        this.ounNumber = ounNumber;
    }

    public String getOunNumber2() {
        return ounNumber2;
    }

    public void setOunNumber2(String ounNumber2) {
        this.ounNumber2 = ounNumber2;
    }

    public String getOunType() {
        return ounType;
    }

    public void setOunType(String ounType) {
        this.ounType = ounType;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<SystemParas> getSystemParasList() {
        return systemParasList;
    }

    public void setSystemParasList(List<SystemParas> systemParasList) {
        this.systemParasList = systemParasList;
    }

    public SystemParas addSystemParas(SystemParas systemParas) {
        getSystemParasList().add(systemParas);
        systemParas.setOrganizationUnits(this);
        return systemParas;
    }

    public SystemParas removeSystemParas(SystemParas systemParas) {
        getSystemParasList().remove(systemParas);
        systemParas.setOrganizationUnits(null);
        return systemParas;
    }

    public List<CostCenters> getCostCentersList() {
        return costCentersList;
    }

    public void setCostCentersList(List<CostCenters> costCentersList) {
        this.costCentersList = costCentersList;
    }

    public CostCenters addCostCenters(CostCenters costCenters) {
        getCostCentersList().add(costCenters);
        costCenters.setOrganizationUnits(this);
        return costCenters;
    }

    public CostCenters removeCostCenters(CostCenters costCenters) {
        getCostCentersList().remove(costCenters);
        costCenters.setOrganizationUnits(null);
        return costCenters;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public OrganizationUnits getOrganizationUnits() {
        return organizationUnits;
    }

    public void setOrganizationUnits(OrganizationUnits organizationUnits) {
        this.organizationUnits = organizationUnits;
    }

    public List<OrganizationUnits> getOrganizationUnitsList() {
        return organizationUnitsList;
    }

    public void setOrganizationUnitsList(List<OrganizationUnits> organizationUnitsList) {
        this.organizationUnitsList = organizationUnitsList;
    }

    public OrganizationUnits addOrganizationUnits(OrganizationUnits organizationUnits) {
        getOrganizationUnitsList().add(organizationUnits);
        organizationUnits.setOrganizationUnits(this);
        return organizationUnits;
    }

    public OrganizationUnits removeOrganizationUnits(OrganizationUnits organizationUnits) {
        getOrganizationUnitsList().remove(organizationUnits);
        organizationUnits.setOrganizationUnits(null);
        return organizationUnits;
    }

    public CostCenters getCostCenters() {
        return costCenters;
    }

    public void setCostCenters(CostCenters costCenters) {
        this.costCenters = costCenters;
    }
    
    
    public String toString() {
        return "OrgainizationUnits ["+this.ounId+","+this.ounNumber+","+this.name+"]";
    }
    
}
