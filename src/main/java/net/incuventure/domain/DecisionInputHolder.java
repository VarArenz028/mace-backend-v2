package net.incuventure.domain;

import com.basicauth.domain.UtilizationRM;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by angulo on 10/27/2016.
 */
public class DecisionInputHolder {

    /***
     * Fields used for Status Check
     */
    private String memberStatus;
    private Date memberStatusDate;

    /**
     * Fields used for Validity Check
     */
    private Date validityDate;
    private Date dateNow;


    /**
     * Fields used for Level 1 Limit Checking
     */
    private BigDecimal pecAmount;
    private BigDecimal ddAmount;

    private Boolean philhealtCovered;
    private BigDecimal totalUtilization; //Utilization version 0 moss level

    private CopyOnWriteArrayList<UtilizationRM> utilizationList;
    private Date utiliationListUpdateDate;

    /**
     * Fields used for Level 2 Limit Checking
     */




    /**
     NOTES:
     Disease/Diagnosis PEC/DD/Exclusions Matrix,  -> Ask for copy of table
     Utilization Function 1 (DD/PEC), -> Planned

     Diagnosis-Procedure Matrix,  -> Planned | Being done by doctors
     Utilization Function 2 (Per Diagnosis), -> Planned

     Disease/Diagnosis Inner Limit
     Utilization Function 3 (Per Diagnosis with Inner Limit), -> Planned

     Maximum Benefit Limit, - FOR BA/SA |ATM| not structurally in database|in remarks field -DR JEREEN
     Annual Benefit Limit, - FOR BA/SA |ATM| not structurally in database|in remarks field -DR JEREEN
     Shared Limit, -FOR BA/SA |ATM| not structurally in database|in remarks field| eg Family Plan -DR JEREEN
     Utilization Function A (MBL|ABL|SL)

     Test/Procedure Quick Per Hospital Approval Limit, ATM Not present | Medicard
     Availments, Matrix - ATM| no per diagnosis group tagging of availments | not in database
     Hospital Limit Matrix with Values, - ATM| not structurally in database
     Complications,  -> Group unrelated Diagnosis | With Manual Web Application
     Utilization Function 4 (Per Diagnosis), -> Planned
     */



    public DecisionInputHolder() {
    }


    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public Date getMemberStatusDate() {
        return memberStatusDate;
    }

    public void setMemberStatusDate(Date memberStatusDate) {
        this.memberStatusDate = memberStatusDate;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public Date getDateNow() {
        return dateNow;
    }

    public void setDateNow(Date dateNow) {
        this.dateNow = dateNow;
    }

    public BigDecimal getPecAmount() {
        return pecAmount;
    }

    public void setPecAmount(BigDecimal pecAmount) {
        this.pecAmount = pecAmount;
    }

    public BigDecimal getDdAmount() {
        return ddAmount;
    }

    public void setDdAmount(BigDecimal ddAmount) {
        this.ddAmount = ddAmount;
    }

    public Boolean getPhilhealtCovered() {
        return philhealtCovered;
    }

    public void setPhilhealtCovered(Boolean philhealtCovered) {
        this.philhealtCovered = philhealtCovered;
    }

    public BigDecimal getTotalUtilization() {
        return totalUtilization;
    }

    public void setTotalUtilization(BigDecimal totalUtilization) {
        this.totalUtilization = totalUtilization;
    }

    public CopyOnWriteArrayList<UtilizationRM> getUtilizationList() {
        return utilizationList;
    }

    public void setUtilizationList(CopyOnWriteArrayList<UtilizationRM> utilizationList) {
        this.utilizationList = utilizationList;
    }

    public Date getUtiliationListUpdateDate() {
        return utiliationListUpdateDate;
    }

    public void setUtiliationListUpdateDate(Date utiliationListUpdateDate) {
        this.utiliationListUpdateDate = utiliationListUpdateDate;
    }
}
