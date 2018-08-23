package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jabito on 31/07/2017.
 */
public class ConsultDetails implements Serializable{

    @JsonProperty("referenceNo")
    private String referenceNo;
    @JsonProperty("memberCode")
    private String memberCode;
    @JsonProperty("hospitalCode")
    private String hospitalCode;
    @JsonProperty("historyOfPresentIllness")
    private String historyOfPresentIllness;
    @JsonProperty("pastOrFamilyHistory")
    private String pastOrFamilyHistory;
    @JsonProperty("vitalsBp")
    private String vitalsBp;
    @JsonProperty("vitalsHr")
    private String vitalsHr;
    @JsonProperty("vitalsRr")
    private String vitalsRr;
    @JsonProperty("vitalsTemp")
    private String vitalsTemp;
    @JsonProperty("reviewOfSystems")
    private String reviewOfSystems;
    @JsonProperty("physicalExamination")
    private String physicalExamination;
    @JsonProperty("dxRemarks")
    private String dxRemarks;
    @JsonProperty("planOfManagement")
    private String planOfManagement;
    @JsonProperty("isCongenital")
    private Boolean isCongenital;
    @JsonProperty("isMaternity")
    private Boolean isMaternity;
    @JsonProperty("isMedicolegal")
    private Boolean isMedicolegal;
    @JsonProperty("prescribedTests")
    private List<PrescribedTestJson> prescribedTests;

    public static class PrescribedTestJson{

        @JsonProperty("diagCode")
        private String diagCode;
        @JsonProperty("diagType")
        private Integer diagType;//1-Primary 2-OtherContributory 3-OtherNonContributory
        @JsonProperty("procCode")
        private String procCode;
        @JsonProperty("serviceSubType")
        private Integer serviceSubType;
        @JsonProperty("amount")
        private BigDecimal amount;
        @JsonProperty("diagRemarks")
        private String diagRemarks;

        public String getDiagCode() {
            return diagCode;
        }

        public void setDiagCode(String diagCode) {
            this.diagCode = diagCode;
        }

        public Integer getDiagType() {
            return diagType;
        }

        public void setDiagType(Integer diagType) {
            this.diagType = diagType;
        }

        public String getProcCode() {
            return procCode;
        }

        public void setProcCode(String procCode) {
            this.procCode = procCode;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public Integer getServiceSubType() {
            return serviceSubType;
        }

        public void setServiceSubType(Integer serviceSubType) {
            this.serviceSubType = serviceSubType;
        }

        public String getDiagRemarks() {
            return diagRemarks;
        }

        public void setDiagRemarks(String diagRemarks) {
            this.diagRemarks = diagRemarks;
        }
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getHistoryOfPresentIllness() {
        return historyOfPresentIllness;
    }

    public void setHistoryOfPresentIllness(String historyOfPresentIllness) {
        this.historyOfPresentIllness = historyOfPresentIllness;
    }

    public String getPastOrFamilyHistory() {
        return pastOrFamilyHistory;
    }

    public void setPastOrFamilyHistory(String pastOrFamilyHistory) {
        this.pastOrFamilyHistory = pastOrFamilyHistory;
    }

    public String getVitalsBp() {
        return vitalsBp;
    }

    public void setVitalsBp(String vitalsBp) {
        this.vitalsBp = vitalsBp;
    }

    public String getVitalsHr() {
        return vitalsHr;
    }

    public void setVitalsHr(String vitalsHr) {
        this.vitalsHr = vitalsHr;
    }

    public String getVitalsRr() {
        return vitalsRr;
    }

    public void setVitalsRr(String vitalsRr) {
        this.vitalsRr = vitalsRr;
    }

    public String getVitalsTemp() {
        return vitalsTemp;
    }

    public void setVitalsTemp(String vitalsTemp) {
        this.vitalsTemp = vitalsTemp;
    }

    public String getReviewOfSystems() {
        return reviewOfSystems;
    }

    public void setReviewOfSystems(String reviewOfSystems) {
        this.reviewOfSystems = reviewOfSystems;
    }

    public String getPhysicalExamination() {
        return physicalExamination;
    }

    public void setPhysicalExamination(String physicalExamination) {
        this.physicalExamination = physicalExamination;
    }

    public String getDxRemarks() {
        return dxRemarks;
    }

    public void setDxRemarks(String dxRemarks) {
        this.dxRemarks = dxRemarks;
    }

    public String getPlanOfManagement() {
        return planOfManagement;
    }

    public void setPlanOfManagement(String planOfManagement) {
        this.planOfManagement = planOfManagement;
    }

    public Boolean getIsCongenital() {
        return isCongenital;
    }

    public void setIsCongenital(Boolean isCongenital) {
        this.isCongenital = isCongenital;
    }

    public Boolean getIsMaternity() {
        return isMaternity;
    }

    public void setIsMaternity(Boolean isMaternity) {
        this.isMaternity = isMaternity;
    }

    public Boolean getIsMedicolegal() {
        return isMedicolegal;
    }

    public void setIsMedicolegal(Boolean isMedicolegal) {
        this.isMedicolegal = isMedicolegal;
    }

    public List<PrescribedTestJson> getPrescribedTests() {
        return prescribedTests;
    }

    public void setPrescribedTests(List<PrescribedTestJson> prescribedTests) {
        this.prescribedTests = prescribedTests;
    }
}
