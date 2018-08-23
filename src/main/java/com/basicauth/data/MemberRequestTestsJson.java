package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jabito on 01/06/2017.
 */
public class MemberRequestTestsJson implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("memberCode")
    private String memberCode;

    @JsonProperty("doctorCode")
    private String doctorCode;

    @JsonProperty("username")
    private String username;

    @JsonProperty("deviceID")
    private String deviceId;

    @JsonProperty("hospitalCode")
    private String hospitalCode;

    @JsonProperty("primaryDiagnosis")
    private String primaryDiagnosis;

    @JsonProperty("otherDiagnosisCode")
    private String [] otherDiagnosisCode;

    @JsonProperty("otherDiagnosisDesc")
    private String [] getOtherDiagnosisDesc;

    @JsonProperty("reasonForConsult")
    private String reasonForConsult;

    @JsonProperty("primaryDiagnosisDesc")
    private String primaryDiagnosisDesc;

    @JsonProperty("searchType")
    private String searchType;

    @JsonProperty("diagnosisProcedures")
    private DiagnosisProcedureJsonOld[] diagnosisProcedures;

    public static class DiagnosisProcedureJsonOld {

        @JsonProperty(value = "serviceType", required = true)
        private Integer serviceType;

        @JsonProperty("procedureCode")
        private String procedureCode;

        @JsonProperty("diagnosisCode")
        private String diagnosisCode;

        @JsonProperty("amount")
        private Double amount;

        //<editor-fold desc="DiagnosisProcedureJson Getters and Setters" defaultstate="collapsed">
        public Integer getServiceType() {
            return serviceType;
        }

        public void setServiceType(Integer serviceType) {
            this.serviceType = serviceType;
        }

        public String getProcedureCode() {
            return procedureCode;
        }

        public void setProcedureCode(String procedureCode) {
            this.procedureCode = procedureCode;
        }

        public String getDiagnosisCode() {
            return diagnosisCode;
        }

        public void setDiagnosisCode(String diagnosisCode) {
            this.diagnosisCode = diagnosisCode;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
        //</editor-fold>
    }

    public MemberRequestTestsJson() {
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getPrimaryDiagnosis() {
        return primaryDiagnosis;
    }

    public void setPrimaryDiagnosis(String primaryDiagnosis) {
        this.primaryDiagnosis = primaryDiagnosis;
    }

    public String[] getOtherDiagnosisCode() {
        return otherDiagnosisCode;
    }

    public void setOtherDiagnosisCode(String[] otherDiagnosisCode) {
        this.otherDiagnosisCode = otherDiagnosisCode;
    }

    public String[] getGetOtherDiagnosisDesc() {
        return getOtherDiagnosisDesc;
    }

    public void setGetOtherDiagnosisDesc(String[] getOtherDiagnosisDesc) {
        this.getOtherDiagnosisDesc = getOtherDiagnosisDesc;
    }

    public String getReasonForConsult() {
        return reasonForConsult;
    }

    public void setReasonForConsult(String reasonForConsult) {
        this.reasonForConsult = reasonForConsult;
    }

    public String getPrimaryDiagnosisDesc() {
        return primaryDiagnosisDesc;
    }

    public void setPrimaryDiagnosisDesc(String primaryDiagnosisDesc) {
        this.primaryDiagnosisDesc = primaryDiagnosisDesc;
    }

    public DiagnosisProcedureJsonOld[] getDiagnosisProcedures() {
        return diagnosisProcedures;
    }

    public void setDiagnosisProcedures(DiagnosisProcedureJsonOld[] diagnosisProcedures) {
        this.diagnosisProcedures = diagnosisProcedures;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
