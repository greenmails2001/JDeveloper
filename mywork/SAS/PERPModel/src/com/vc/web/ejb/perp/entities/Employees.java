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

@Entity
@NamedQueries({
  @NamedQuery(name = "Employees.findAll", query = "select o from Employees o"),
  @NamedQuery(name = "Employees.findAll.size", query = "select count(o) from Employees o")
})
public class Employees implements Serializable {
    private String address;
    @Column(name="ALIAS_NAME")
    private String aliasName;
    @Column(name="ALI_ID")
    private Long aliId;
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
    private String bank;
    @Column(name="BEGINNING_TYPE")
    private String beginningType;
    @Column(name="BLOOD_TYPE")
    private String bloodType;
    @Column(name="BROTHER_NUM")
    private Long brotherNum;
    @Column(name="CLASS")
    private String class_;
    @Column(nullable = false)
    private String collector;
    @Column(name="COMPUTER_LEVEL")
    private String computerLevel;
    @Column(name="CREATED_BY", nullable = false)
    private String createdBy;
    @Column(name="CREATE_DATE", nullable = false)
    private Timestamp createDate;
    @Column(name="DATE_OF_BIRTH")
    private Timestamp dateOfBirth;
    @Column(name="DATE_OF_ISSUE")
    private Timestamp dateOfIssue;
    private String disabled;
    private String email;
    @Column(name="EMAIL_ADDRESS")
    private String emailAddress;
    @Id
    @Column(name="EMP_ID", nullable = false)
    private String empId;
    @Column(name="EMP_ID_CHECK")
    private String empIdCheck;
    @Column(name="EMP_NUMBER")
    private String empNumber;
    @Column(name="EMP_POSITION")
    private String empPosition;
    @Column(name="ENTERING_DATE")
    private Timestamp enteringDate;
    @Column(name="EUN_ID")
    private Long eunId;
    private String fax;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="FULL_NAME")
    private String fullName;
    @Column(name="HOME_ADDRESS")
    private String homeAddress;
    @Column(name="ID_CARD")
    private String idCard;
    private String image;
    @Column(name="IMAGE_PATH")
    private String imagePath;
    @Column(name="LABOUR_NUMBER")
    private String labourNumber;
    @Column(name="LABOUR_TYPE")
    private String labourType;
    private String language;
    @Column(name="LANGUAGE_LEVEL")
    private String languageLevel;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name="LEVEL_OF_EDUCATION")
    private String levelOfEducation;
    @Column(name="LEVEL_OF_INSTRUCTION")
    private String levelOfInstruction;
    @Column(name="MAIL_ADDRESS")
    private String mailAddress;
    @Column(name="MARITAL_STATUS")
    private String maritalStatus;
    @Column(name="MEDICAL_CERTIFICATE")
    private String medicalCertificate;
    @Column(name="MEMBER_FAMILY")
    private String memberFamily;
    @Column(name="MIDDLE_NAME")
    private String middleName;
    private String mobile;
    @Column(name="MODIFIED_BY")
    private String modifiedBy;
    @Column(name="MODIFY_DATE")
    private Timestamp modifyDate;
    private String name;
    private String nation;
    private String nationality;
    @Column(name="NOTE_MEDICAL_CERTIFICATE")
    private String noteMedicalCertificate;
    @Column(name="OTHER_MEMBER_DATE")
    private Timestamp otherMemberDate;
    @Column(name="OTHER_MEMBER_PLACE")
    private String otherMemberPlace;
    @Column(name="PARTY_MEMBER_DATE")
    private Timestamp partyMemberDate;
    @Column(name="PARTY_MEMBER_PLACE")
    private String partyMemberPlace;
    @Column(name="PARTY_NUMBER")
    private String partyNumber;
    @Column(name="PARTY_OFFICIAL_DATE")
    private Timestamp partyOfficialDate;
    @Column(name="PARTY_PAY_APE")
    private String partyPayApe;
    @Column(name="PASSPORT_DATE")
    private Timestamp passportDate;
    @Column(name="PASSPORT_NUMBER")
    private String passportNumber;
    @Column(name="PERSONAL_ACCOUNT")
    private String personalAccount;
    @Column(name="PERSONAL_ID")
    private String personalId;
    private String phone;
    @Column(name="PLACE_OF_BIRTH")
    private String placeOfBirth;
    @Column(name="PLACE_OF_ISSUE")
    private String placeOfIssue;
    @Column(name="PLACE_OF_ORIGIN")
    private String placeOfOrigin;
    @Column(name="POLICY_TYPE")
    private String policyType;
    @Column(name="POLITICAL_LEVEL")
    private String politicalLevel;
    private String professional;
    @Column(name="PROFESSIONAL_2")
    private String professional2;
    @Column(name="PROF_ID")
    private Long profId;
    @Column(name="PUBLIC_SECTOR_DATE")
    private Timestamp publicSectorDate;
    private String religion;
    @Column(name="RETIRED_DATE")
    private Timestamp retiredDate;
    @Column(name="RETIRED_REASON")
    private String retiredReason;
    @Column(name="RETURNEE_DATE")
    private Timestamp returneeDate;
    @Column(name="RETURNEE_REASON")
    private String returneeReason;
    @Column(name="REV_MARTYR_FAMILY")
    private String revMartyrFamily;
    private String risidence;
    @Column(name="SALEMAN_FLAG")
    private String salemanFlag;
    private String sex;
    @Column(name="SHIPMAN_FLAG")
    private String shipmanFlag;
    @Column(name="SOLDIER_NUMBER")
    private String soldierNumber;
    @Column(nullable = false)
    private String status;
    private String subject;
    private String suitable;
    @Column(name="TAX_ID")
    private String taxId;
    private String telephone;
    private String title;
    @Column(name="TYPE_OF_INSTRUCTION")
    private String typeOfInstruction;
    @Column(name="TYPE_SCHOOL_FINAL")
    private String typeSchoolFinal;
    @Column(name="UNION_MEMBER_DATE")
    private Timestamp unionMemberDate;
    @Column(name="UNION_MEMBER_PLACE")
    private String unionMemberPlace;
    @Column(name="UNION_NUMBER")
    private String unionNumber;
    @Column(name="UNION_PAY_APE")
    private String unionPayApe;
    @Column(name="YEAR_SCHOOL_FINAL")
    private Timestamp yearSchoolFinal;
    @OneToMany(mappedBy = "employees")
    private List<OrganizationUnits> organizationUnitsList;
    @ManyToOne
    @JoinColumn(name = "EMP_ID_MANAGED_BY")
    private Employees employees;
    @OneToMany(mappedBy = "employees")
    private List<Employees> employeesList;
    @OneToMany(mappedBy = "employees")
    private List<CostCenters> costCentersList;
    @OneToMany(mappedBy = "employees")
    private List<AppUsers> appUsersList;
    @ManyToOne
    @JoinColumn(name = "CCE_ID")
    private CostCenters costCenters;
    @ManyToOne
    @JoinColumn(name = "GRO_ID")
    private Groups groups;

    public Employees() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public Long getAliId() {
        return aliId;
    }

    public void setAliId(Long aliId) {
        this.aliId = aliId;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBeginningType() {
        return beginningType;
    }

    public void setBeginningType(String beginningType) {
        this.beginningType = beginningType;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Long getBrotherNum() {
        return brotherNum;
    }

    public void setBrotherNum(Long brotherNum) {
        this.brotherNum = brotherNum;
    }


    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    public String getComputerLevel() {
        return computerLevel;
    }

    public void setComputerLevel(String computerLevel) {
        this.computerLevel = computerLevel;
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

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Timestamp getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Timestamp dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpIdCheck() {
        return empIdCheck;
    }

    public void setEmpIdCheck(String empIdCheck) {
        this.empIdCheck = empIdCheck;
    }


    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getEmpPosition() {
        return empPosition;
    }

    public void setEmpPosition(String empPosition) {
        this.empPosition = empPosition;
    }

    public Timestamp getEnteringDate() {
        return enteringDate;
    }

    public void setEnteringDate(Timestamp enteringDate) {
        this.enteringDate = enteringDate;
    }

    public Long getEunId() {
        return eunId;
    }

    public void setEunId(Long eunId) {
        this.eunId = eunId;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getLabourNumber() {
        return labourNumber;
    }

    public void setLabourNumber(String labourNumber) {
        this.labourNumber = labourNumber;
    }

    public String getLabourType() {
        return labourType;
    }

    public void setLabourType(String labourType) {
        this.labourType = labourType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(String languageLevel) {
        this.languageLevel = languageLevel;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLevelOfEducation() {
        return levelOfEducation;
    }

    public void setLevelOfEducation(String levelOfEducation) {
        this.levelOfEducation = levelOfEducation;
    }

    public String getLevelOfInstruction() {
        return levelOfInstruction;
    }

    public void setLevelOfInstruction(String levelOfInstruction) {
        this.levelOfInstruction = levelOfInstruction;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMedicalCertificate() {
        return medicalCertificate;
    }

    public void setMedicalCertificate(String medicalCertificate) {
        this.medicalCertificate = medicalCertificate;
    }

    public String getMemberFamily() {
        return memberFamily;
    }

    public void setMemberFamily(String memberFamily) {
        this.memberFamily = memberFamily;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNoteMedicalCertificate() {
        return noteMedicalCertificate;
    }

    public void setNoteMedicalCertificate(String noteMedicalCertificate) {
        this.noteMedicalCertificate = noteMedicalCertificate;
    }

    public Timestamp getOtherMemberDate() {
        return otherMemberDate;
    }

    public void setOtherMemberDate(Timestamp otherMemberDate) {
        this.otherMemberDate = otherMemberDate;
    }

    public String getOtherMemberPlace() {
        return otherMemberPlace;
    }

    public void setOtherMemberPlace(String otherMemberPlace) {
        this.otherMemberPlace = otherMemberPlace;
    }

    public Timestamp getPartyMemberDate() {
        return partyMemberDate;
    }

    public void setPartyMemberDate(Timestamp partyMemberDate) {
        this.partyMemberDate = partyMemberDate;
    }

    public String getPartyMemberPlace() {
        return partyMemberPlace;
    }

    public void setPartyMemberPlace(String partyMemberPlace) {
        this.partyMemberPlace = partyMemberPlace;
    }

    public String getPartyNumber() {
        return partyNumber;
    }

    public void setPartyNumber(String partyNumber) {
        this.partyNumber = partyNumber;
    }

    public Timestamp getPartyOfficialDate() {
        return partyOfficialDate;
    }

    public void setPartyOfficialDate(Timestamp partyOfficialDate) {
        this.partyOfficialDate = partyOfficialDate;
    }

    public String getPartyPayApe() {
        return partyPayApe;
    }

    public void setPartyPayApe(String partyPayApe) {
        this.partyPayApe = partyPayApe;
    }

    public Timestamp getPassportDate() {
        return passportDate;
    }

    public void setPassportDate(Timestamp passportDate) {
        this.passportDate = passportDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(String personalAccount) {
        this.personalAccount = personalAccount;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPlaceOfIssue() {
        return placeOfIssue;
    }

    public void setPlaceOfIssue(String placeOfIssue) {
        this.placeOfIssue = placeOfIssue;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getPoliticalLevel() {
        return politicalLevel;
    }

    public void setPoliticalLevel(String politicalLevel) {
        this.politicalLevel = politicalLevel;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getProfessional2() {
        return professional2;
    }

    public void setProfessional2(String professional2) {
        this.professional2 = professional2;
    }

    public Long getProfId() {
        return profId;
    }

    public void setProfId(Long profId) {
        this.profId = profId;
    }

    public Timestamp getPublicSectorDate() {
        return publicSectorDate;
    }

    public void setPublicSectorDate(Timestamp publicSectorDate) {
        this.publicSectorDate = publicSectorDate;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public Timestamp getRetiredDate() {
        return retiredDate;
    }

    public void setRetiredDate(Timestamp retiredDate) {
        this.retiredDate = retiredDate;
    }

    public String getRetiredReason() {
        return retiredReason;
    }

    public void setRetiredReason(String retiredReason) {
        this.retiredReason = retiredReason;
    }

    public Timestamp getReturneeDate() {
        return returneeDate;
    }

    public void setReturneeDate(Timestamp returneeDate) {
        this.returneeDate = returneeDate;
    }

    public String getReturneeReason() {
        return returneeReason;
    }

    public void setReturneeReason(String returneeReason) {
        this.returneeReason = returneeReason;
    }

    public String getRevMartyrFamily() {
        return revMartyrFamily;
    }

    public void setRevMartyrFamily(String revMartyrFamily) {
        this.revMartyrFamily = revMartyrFamily;
    }

    public String getRisidence() {
        return risidence;
    }

    public void setRisidence(String risidence) {
        this.risidence = risidence;
    }

    public String getSalemanFlag() {
        return salemanFlag;
    }

    public void setSalemanFlag(String salemanFlag) {
        this.salemanFlag = salemanFlag;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getShipmanFlag() {
        return shipmanFlag;
    }

    public void setShipmanFlag(String shipmanFlag) {
        this.shipmanFlag = shipmanFlag;
    }

    public String getSoldierNumber() {
        return soldierNumber;
    }

    public void setSoldierNumber(String soldierNumber) {
        this.soldierNumber = soldierNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSuitable() {
        return suitable;
    }

    public void setSuitable(String suitable) {
        this.suitable = suitable;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeOfInstruction() {
        return typeOfInstruction;
    }

    public void setTypeOfInstruction(String typeOfInstruction) {
        this.typeOfInstruction = typeOfInstruction;
    }

    public String getTypeSchoolFinal() {
        return typeSchoolFinal;
    }

    public void setTypeSchoolFinal(String typeSchoolFinal) {
        this.typeSchoolFinal = typeSchoolFinal;
    }

    public Timestamp getUnionMemberDate() {
        return unionMemberDate;
    }

    public void setUnionMemberDate(Timestamp unionMemberDate) {
        this.unionMemberDate = unionMemberDate;
    }

    public String getUnionMemberPlace() {
        return unionMemberPlace;
    }

    public void setUnionMemberPlace(String unionMemberPlace) {
        this.unionMemberPlace = unionMemberPlace;
    }

    public String getUnionNumber() {
        return unionNumber;
    }

    public void setUnionNumber(String unionNumber) {
        this.unionNumber = unionNumber;
    }

    public String getUnionPayApe() {
        return unionPayApe;
    }

    public void setUnionPayApe(String unionPayApe) {
        this.unionPayApe = unionPayApe;
    }

    public Timestamp getYearSchoolFinal() {
        return yearSchoolFinal;
    }

    public void setYearSchoolFinal(Timestamp yearSchoolFinal) {
        this.yearSchoolFinal = yearSchoolFinal;
    }

    public List<OrganizationUnits> getOrganizationUnitsList() {
        return organizationUnitsList;
    }

    public void setOrganizationUnitsList(List<OrganizationUnits> organizationUnitsList) {
        this.organizationUnitsList = organizationUnitsList;
    }

    public OrganizationUnits addOrganizationUnits(OrganizationUnits organizationUnits) {
        getOrganizationUnitsList().add(organizationUnits);
        organizationUnits.setEmployees(this);
        return organizationUnits;
    }

    public OrganizationUnits removeOrganizationUnits(OrganizationUnits organizationUnits) {
        getOrganizationUnitsList().remove(organizationUnits);
        organizationUnits.setEmployees(null);
        return organizationUnits;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public List<Employees> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employees> employeesList) {
        this.employeesList = employeesList;
    }

    public Employees addEmployees(Employees employees) {
        getEmployeesList().add(employees);
        employees.setEmployees(this);
        return employees;
    }

    public Employees removeEmployees(Employees employees) {
        getEmployeesList().remove(employees);
        employees.setEmployees(null);
        return employees;
    }

    public List<CostCenters> getCostCentersList() {
        return costCentersList;
    }

    public void setCostCentersList(List<CostCenters> costCentersList) {
        this.costCentersList = costCentersList;
    }

    public CostCenters addCostCenters(CostCenters costCenters) {
        getCostCentersList().add(costCenters);
        costCenters.setEmployees(this);
        return costCenters;
    }

    public CostCenters removeCostCenters(CostCenters costCenters) {
        getCostCentersList().remove(costCenters);
        costCenters.setEmployees(null);
        return costCenters;
    }

    public List<AppUsers> getAppUsersList() {
        return appUsersList;
    }

    public void setAppUsersList(List<AppUsers> appUsersList) {
        this.appUsersList = appUsersList;
    }

    public AppUsers addAppUsers(AppUsers appUsers) {
        getAppUsersList().add(appUsers);
        appUsers.setEmployees(this);
        return appUsers;
    }

    public AppUsers removeAppUsers(AppUsers appUsers) {
        getAppUsersList().remove(appUsers);
        appUsers.setEmployees(null);
        return appUsers;
    }

    public CostCenters getCostCenters() {
        return costCenters;
    }

    public void setCostCenters(CostCenters costCenters) {
        this.costCenters = costCenters;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
}
