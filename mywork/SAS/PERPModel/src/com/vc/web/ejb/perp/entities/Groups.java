package com.vc.web.ejb.perp.entities;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
  @NamedQuery(name = "Groups.findAll", query = "select o from Groups o"),
  @NamedQuery(name = "Groups.findAll.size", query = "select count(o) from Groups o")
})
public class Groups implements Serializable {
    @Column(name="ACC_ID_TL")
    private String accIdTl;
    @Column(name="AMOUNT_DAY")
    private Long amountDay;
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
    @Column(name="CCE_ID", nullable = false)
    private String cceId;
    @Column(name="CREATED_BY", nullable = false)
    private String createdBy;
    @Column(name="CREATE_DATE", nullable = false)
    private Timestamp createDate;
    private String description;
    @Column(name="FOUNDATION_DATE")
    private Timestamp foundationDate;
    @Column(name="GROUP_NAME", nullable = false)
    private String groupName;
    @Column(name="GROUP_NUMBER", nullable = false)
    private String groupNumber;
    @Id
    @Column(name="GRO_ID", nullable = false)
    private Long groId;
    @Column(name="MODIFIED_BY")
    private String modifiedBy;
    @Column(name="MODIFY_DATE")
    private Timestamp modifyDate;
    @OneToMany(mappedBy = "groups")
    private List<Employees> employeesList;

    public Groups() {
    }

    public String getAccIdTl() {
        return accIdTl;
    }

    public void setAccIdTl(String accIdTl) {
        this.accIdTl = accIdTl;
    }

    public Long getAmountDay() {
        return amountDay;
    }

    public void setAmountDay(Long amountDay) {
        this.amountDay = amountDay;
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

    public String getCceId() {
        return cceId;
    }

    public void setCceId(String cceId) {
        this.cceId = cceId;
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

    public Timestamp getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Timestamp foundationDate) {
        this.foundationDate = foundationDate;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Long getGroId() {
        return groId;
    }

    public void setGroId(Long groId) {
        this.groId = groId;
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

    public List<Employees> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employees> employeesList) {
        this.employeesList = employeesList;
    }

    public Employees addEmployees(Employees employees) {
        getEmployeesList().add(employees);
        employees.setGroups(this);
        return employees;
    }

    public Employees removeEmployees(Employees employees) {
        getEmployeesList().remove(employees);
        employees.setGroups(null);
        return employees;
    }
}
