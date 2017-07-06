package pages.sasr.sasr001.sasr001frag;


import com.oracle.adffwkextensions.faces.FacesUtils;

import com.perp.beans.AccountBean;
import com.perp.beans.PerpSasrAccount;
import com.perp.beans.PerpSasrAccountChecking;
import com.perp.beans.PerpUtils;
import com.perp.beans.SasrSessionLoginObject;
import com.perp.sasregistmodel.common.PerpSasRegistAppModule;
import com.perp.sasregistmodel.views.common.AccountsRow;
import com.perp.sasregistviewcontroller.utils.SasrFacesUtils;

import javax.faces.event.ActionEvent;

import oracle.jbo.domain.Number;


public class SasrCreateAccount {

    private AccountBean accountBean = new AccountBean();
    private boolean requiredError = false;
    private boolean noMatch = false;
    private String outCome;

    public SasrCreateAccount() {
    }

    public void setFullName(String fullName) {
        this.accountBean.setFullName(fullName);
    }

    public String getFullName() {
        return this.accountBean.getFullName();
    }

    public void setContactTelephone(String contactTelephone) {
        this.accountBean.setContactTelephone(contactTelephone);
    }

    public String getContactTelephone() {
        return this.accountBean.getContactTelephone();
    }

    public void setEmail(String email) {
        this.accountBean.setEmail(email);
    }

    public String getEmail() {
        return this.accountBean.getEmail();
    }

    public void setOrganizationName(String organizationName) {
        this.accountBean.setOrganizationName(organizationName);
    }

    public String getOrganizationName() {
        return this.accountBean.getOrganizationName();
    }


    public void setAddress(String address) {
        this.accountBean.setAddress(address);
    }

    public String getAddress() {
        return this.accountBean.getAddress();
    }

    public void setTaxCode(String taxCode) {
        this.accountBean.setTaxCode(taxCode);
    }

    public String getTaxCode() {
        return this.accountBean.getTaxCode();
    }


    public void setNumberOfEmployee(Number numberOfEmployee) {
        this.accountBean.setNumberOfEmployee(numberOfEmployee);
    }

    public Number getNumberOfEmployee() {
        return this.accountBean.getNumberOfEmployee();
    }

    public void setCustomerApproach(String customerApproach) {
        this.accountBean.setCustomerApproach(customerApproach);
    }

    public String getCustomerApproach() {
        return this.accountBean.getCustomerApproach();
    }

    public void setUserName(String userName) {
        this.accountBean.setUserName(userName);
    }

    public String getUserName() {
        return this.accountBean.getUserName();
    }

    public void setPassword(String password) {
        this.accountBean.setPassword(password);
    }

    public String getPassword() {
        return this.accountBean.getPassword();

    }

    public void setConfirmPassword(String confirmPassword) {
        this.accountBean.setConfirmPassword(confirmPassword);
    }

    public String getConfirmPassword() {
        return this.accountBean.getConfirmPassword();
    }

    public void setActivedCode(String activedCode) {
        this.accountBean.setActivedCode(activedCode);

    }

    public String getActivedCode() {
        return this.accountBean.getActivedCode();
    }


    public void setStatus(String status) {
        this.accountBean.setStatus(status);
    }

    public String getStatus() {
        return this.accountBean.getStatus();
    }


    public void setSecurityAnswer(String securityAnswer) {
        this.accountBean.setSecurityAnswer(securityAnswer);
    }

    public String getSecurityAnswer() {
        return this.accountBean.getSecurityAnswer();
    }


    public void setActivatedFlag(String activatedFlag) {
        this.accountBean.setActivatedFlag(activatedFlag);
    }

    public String getActivatedFlag() {
        return this.accountBean.getActivatedFlag();
    }

    public void setRequiredError(boolean requiredError) {
        this.requiredError = requiredError;
    }

    public boolean isRequiredError() {
        return requiredError;
    }

    public void setNoMatch(boolean noMatch) {
        this.noMatch = noMatch;
    }

    public boolean isNoMatch() {
        return noMatch;
    }

    public void setOutCome(String outCome) {
        this.outCome = outCome;
    }

    public String getOutCome() {
        return outCome;
    }

    public void createAccountActionListener(ActionEvent actionEvent) {
        Number proId =
            (Number)FacesUtils.resolveExpression("#{bindings.proId}");
        Number rolId =
            (Number)FacesUtils.resolveExpression("#{bindings.rolId}");
        Number couId =
            (Number)FacesUtils.resolveExpression("#{bindings.couId}");
        Number indId =
            (Number)FacesUtils.resolveExpression("#{bindings.indId}");
        Number squId =
            (Number)FacesUtils.resolveExpression("#{bindings.squId}");
        Number mmeId =
            (Number)FacesUtils.resolveExpression("#{bindings.mmeId}");
        Number labId =
            (Number)FacesUtils.resolveExpression("#{bindings.labId}");

        if (rolId == null || couId == null || indId == null || mmeId == null ||
            labId == null) {
            requiredError = true;
            return;
        }
        if (!this.accountBean.getPassword().equals(this.accountBean.getConfirmPassword())) {
            this.noMatch = true;
            return;
        }
        //Ma hoa mat khau truoc khi luu
        String password =
            PerpUtils.EncryptPassword(this.accountBean.getPassword());

        this.accountBean.setPassword(password);
        this.accountBean.setRolId(rolId);
        this.accountBean.setCouId(couId);
        this.accountBean.setIndId(indId);
        this.accountBean.setSquId(squId);
        this.accountBean.setMmeId(mmeId);
        this.accountBean.setLabId(labId);
        this.accountBean.setProId(proId);

        PerpSasRegistAppModule module =
            SasrFacesUtils.getPerpSasRegistAppModule();
        AccountsRow row = module.createAccount(this.accountBean);

        // Login luon vao trong User nay ... thay the Account dang login
        SasrSessionLoginObject sasrObject =
            SasrFacesUtils.getSasrSessionLoginObject();

        PerpSasrAccountChecking checking = new PerpSasrAccountChecking();
        sasrObject.setPerpSasrAccountChecking(checking);
        checking.setToAccountNotActivated(); //(1)

        //(1) da dam bao gia tri perpSasrAccount luon khac null
        PerpSasrAccount perpSasrAccount = checking.getPerpSasrAccount();
        perpSasrAccount.setAccId(row.getAccId());
        perpSasrAccount.setUserName(row.getUserName());
        perpSasrAccount.setPassword(row.getPassword());
        perpSasrAccount.setActivated(false);
        perpSasrAccount.setFullName(row.getFullName());
        perpSasrAccount.setEmail(row.getEmail());
        // Gio coi nhu da login ok .
        this.outCome = "preview";
    }


}
