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
  @NamedQuery(name = "CostCenters.findAll", query = "select o from CostCenters o"),
  @NamedQuery(name = "CostCenters.findAll.size", query = "select count(o) from CostCenters o")
})
@Table(name = "COST_CENTERS")
public class CostCenters implements Serializable {
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
    @Column(name="CCE_DESCRIPTION")
    private String cceDescription;
    @Id
    @Column(name="CCE_ID", nullable = false)
    private String cceId;
    @Column(name="CCE_NAME", nullable = false)
    private String cceName;
    @Column(name="CCE_NUMBER")
    private String cceNumber;
    @Column(name="CCE_NUMBER_KT")
    private String cceNumberKt;
    @Column(name="CCE_TYPE")
    private String cceType;
    @Column(name="CREATED_BY", nullable = false)
    private String createdBy;
    @Column(name="CREATE_DATE", nullable = false)
    private Timestamp createDate;
    @Column(name="EFFECTIVE_DATE_FROM")
    private Timestamp effectiveDateFrom;
    @Column(name="EFFECTIVE_DATE_TO")
    private Timestamp effectiveDateTo;
    @Column(name="END_DATE")
    private Timestamp endDate;
    @Column(name="ENTERPRISE_TYPE")
    private Long enterpriseType;
    @Column(name="FOUNDATION_DATE")
    private Timestamp foundationDate;
    @Column(name="MODIFIED_BY")
    private String modifiedBy;
    @Column(name="MODIFY_DATE")
    private Timestamp modifyDate;
    @Column(name="PARTY_BRANCH")
    private String partyBranch;
    @Column(nullable = false)
    private String status;
    @ManyToOne
    @JoinColumn(name = "OUN_ID")
    private OrganizationUnits organizationUnits;
    @ManyToOne
    @JoinColumn(name = "EMP_ID")
    private Employees employees;
    @OneToMany(mappedBy = "costCenters")
    private List<OrganizationUnits> organizationUnitsList;
    @OneToMany(mappedBy = "costCenters")
    private List<Employees> employeesList;

    public CostCenters() {
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

    public String getCceDescription() {
        return cceDescription;
    }

    public void setCceDescription(String cceDescription) {
        this.cceDescription = cceDescription;
    }

    public String getCceId() {
        return cceId;
    }

    public void setCceId(String cceId) {
        this.cceId = cceId;
    }

    public String getCceName() {
        return cceName;
    }

    public void setCceName(String cceName) {
        this.cceName = cceName;
    }

    public String getCceNumber() {
        return cceNumber;
    }

    public void setCceNumber(String cceNumber) {
        this.cceNumber = cceNumber;
    }

    public String getCceNumberKt() {
        return cceNumberKt;
    }

    public void setCceNumberKt(String cceNumberKt) {
        this.cceNumberKt = cceNumberKt;
    }

    public String getCceType() {
        return cceType;
    }

    public void setCceType(String cceType) {
        this.cceType = cceType;
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


    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Long getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(Long enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public Timestamp getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Timestamp foundationDate) {
        this.foundationDate = foundationDate;
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


    public String getPartyBranch() {
        return partyBranch;
    }

    public void setPartyBranch(String partyBranch) {
        this.partyBranch = partyBranch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrganizationUnits getOrganizationUnits() {
        return organizationUnits;
    }

    public void setOrganizationUnits(OrganizationUnits organizationUnits) {
        this.organizationUnits = organizationUnits;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public List<OrganizationUnits> getOrganizationUnitsList() {
        return organizationUnitsList;
    }

    public void setOrganizationUnitsList(List<OrganizationUnits> organizationUnitsList) {
        this.organizationUnitsList = organizationUnitsList;
    }

    public OrganizationUnits addOrganizationUnits(OrganizationUnits organizationUnits) {
        getOrganizationUnitsList().add(organizationUnits);
        organizationUnits.setCostCenters(this);
        return organizationUnits;
    }

    public OrganizationUnits removeOrganizationUnits(OrganizationUnits organizationUnits) {
        getOrganizationUnitsList().remove(organizationUnits);
        organizationUnits.setCostCenters(null);
        return organizationUnits;
    }

    public List<Employees> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employees> employeesList) {
        this.employeesList = employeesList;
    }

    public Employees addEmployees(Employees employees) {
        getEmployeesList().add(employees);
        employees.setCostCenters(this);
        return employees;
    }

    public Employees removeEmployees(Employees employees) {
        getEmployeesList().remove(employees);
        employees.setCostCenters(null);
        return employees;
    }
}
