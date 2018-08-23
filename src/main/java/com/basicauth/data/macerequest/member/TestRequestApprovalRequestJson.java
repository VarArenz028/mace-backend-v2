package com.basicauth.data.macerequest.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableMap;

import java.io.Serializable;
import java.util.*;

public class TestRequestApprovalRequestJson implements Serializable{

    private String memberCode;
    @JsonIgnore
    private Date consultationDate;
    private String consultationReason;
    private String doctorCode;
    private String hospitalCode;
    private String primaryDiagnosisCode;

    private DiagnosisTestJson[] diagnosisTests;

    private String requestBy;
    private String requestOrigin;
    private String requestDeviceId;

    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(Date consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getConsultationReason() {
        return consultationReason;
    }

    public void setConsultationReason(String consultationReason) {
        this.consultationReason = consultationReason;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getPrimaryDiagnosisCode() {
        return primaryDiagnosisCode;
    }

    public void setPrimaryDiagnosisCode(String primaryDiagnosisCode) {
        this.primaryDiagnosisCode = primaryDiagnosisCode;
    }

    public DiagnosisTestJson[] getDiagnosisTests() {
        return diagnosisTests;
    }

    public void setDiagnosisTests(DiagnosisTestJson[] diagnosisTests) {
        this.diagnosisTests = diagnosisTests;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }

    public String getRequestDeviceId() {
        return requestDeviceId;
    }

    public void setRequestDeviceId(String requestDeviceId) {
        this.requestDeviceId = requestDeviceId;
    }
    //</editor-fold>

    public List<Map<String, String>> validate() {
        List<Map<String, String>> validationErrors = new ArrayList<>();

        if (memberCode == null)
            validationErrors.add(ImmutableMap.of("memberCode", "null"));
        else if (memberCode.isEmpty())
            validationErrors.add(ImmutableMap.of("memberCode", "invalid"));

        if (doctorCode == null)
            validationErrors.add(ImmutableMap.of("doctorCode", "null"));
        else if (doctorCode.isEmpty())
            validationErrors.add(ImmutableMap.of("doctorCode", "invalid"));

        if (hospitalCode == null)
            validationErrors.add(ImmutableMap.of("hospitalCode", "null"));
        else if (hospitalCode.isEmpty())
            validationErrors.add(ImmutableMap.of("hospitalCode", "invalid"));

        if (primaryDiagnosisCode == null)
            validationErrors.add(ImmutableMap.of("primaryDiagnosisCode", "null"));
        else if (hospitalCode.isEmpty())
            validationErrors.add(ImmutableMap.of("primaryDiagnosisCode", "invalid"));

        if (diagnosisTests == null)
            validationErrors.add(ImmutableMap.of("diagnosisTests", "null"));
        else if (diagnosisTests.length == 0)
            validationErrors.add(ImmutableMap.of("diagnosisTests", "empty. at least one is required"));

        if (requestBy == null)
            validationErrors.add(ImmutableMap.of("requestBy", "null"));
        else if (requestBy.isEmpty())
            validationErrors.add(ImmutableMap.of("requestBy", "invalid"));

        if (requestOrigin == null)
            validationErrors.add(ImmutableMap.of("requestOrigin", "null"));
        else if (requestOrigin.isEmpty())
            validationErrors.add(ImmutableMap.of("requestOrigin", "invalid"));

        if (requestDeviceId == null)
            validationErrors.add(ImmutableMap.of("requestDeviceId", "null"));
        else if (requestDeviceId.isEmpty())
            validationErrors.add(ImmutableMap.of("requestDeviceId", "invalid"));

        for (DiagnosisTestJson diagnosisTest : diagnosisTests) {
            validationErrors.addAll(diagnosisTest.validate());
        }

        return validationErrors;
    }

    public static class DiagnosisTestJson {

        private String diagnosisCode;
        private String testCode;

        //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
        public String getDiagnosisCode() {
            return diagnosisCode;
        }

        public void setDiagnosisCode(String diagnosisCode) {
            this.diagnosisCode = diagnosisCode;
        }

        public String getTestCode() {
            return testCode;
        }

        public void setTestCode(String testCode) {
            this.testCode = testCode;
        }
        //</editor-fold>

        public List<Map<String, String>> validate() {
            List<Map<String, String>> validationErrors = new ArrayList<>();

            if (diagnosisCode == null)
                validationErrors.add(ImmutableMap.of("diagnosisCode", "null"));
            else if (diagnosisCode.isEmpty())
                validationErrors.add(ImmutableMap.of("diagnosisCode", "invalid"));

            if (testCode == null)
                validationErrors.add(ImmutableMap.of("testCode", "null"));
            else if (testCode.isEmpty())
                validationErrors.add(ImmutableMap.of("testCode", "invalid"));

            return validationErrors;
        }
    }
}
