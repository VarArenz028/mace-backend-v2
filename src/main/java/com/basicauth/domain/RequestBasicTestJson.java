package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Jabito on 10/08/2017.
 */
public class RequestBasicTestJson implements Serializable{

    @JsonProperty("memberCode")
    private String memberCode;
    @JsonProperty("hospitalCode")
    private String hospitalCode;
    @JsonProperty("appUsername")
    private String appUsername;
    @JsonProperty("requestDevice")
    private String requestDevice;
    @JsonProperty("duplicateTag")
    private String duplicateTag;
    @JsonProperty("coorEmail")
    private String coorEmail;
    @JsonProperty("coorContact")
    private String coorContact;
    @JsonProperty("tests")
    private ArrayList<TestObj> tests;

    public static class TestObj{

        @JsonProperty("procedureCode")
        private String procCode;

        @JsonProperty("diagnosisCode")
        private String diagCode;

        @JsonProperty("serviceType")
        private String serviceType;

        @JsonProperty("amount")
        private BigDecimal amount;

        @JsonProperty("costCenter")
        private String costCenter;

        public String getDiagCode() {
            return diagCode;
        }

        public void setDiagCode(String diagCode) {
            this.diagCode = diagCode;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getCostCenter() {
            return costCenter;
        }

        public void setCostCenter(String costCenter) {
            this.costCenter = costCenter;
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
    }

    public String getCoorEmail() {
        return coorEmail;
    }

    public void setCoorEmail(String coorEmail) {
        this.coorEmail = coorEmail;
    }

    public String getCoorContact() {
        return coorContact;
    }

    public void setCoorContact(String coorContact) {
        this.coorContact = coorContact;
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

    public String getAppUsername() {
        return appUsername;
    }

    public void setAppUsername(String appUsername) {
        this.appUsername = appUsername;
    }

    public ArrayList<TestObj> getTests() {
        return tests;
    }

    public void setTests(ArrayList<TestObj> tests) {
        this.tests = tests;
    }

    public String getRequestDevice() {
        return requestDevice;
    }

    public void setRequestDevice(String requestDevice) {
        this.requestDevice = requestDevice;
    }

    public String getDuplicateTag() {
        return duplicateTag;
    }

    public void setDuplicateTag(String duplicateTag) {
        this.duplicateTag = duplicateTag;
    }
}
