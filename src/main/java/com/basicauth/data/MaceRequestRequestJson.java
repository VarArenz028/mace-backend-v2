package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by IPC_Server on 5/9/2017.
 */
public class MaceRequestRequestJson implements Serializable {

    @JsonProperty("maceRequest")
    private MaceRequestJson maceRequest;

    @JsonProperty("attachments")
    private AttachmentJson[] attachments;

    @JsonProperty("procedures")
    private MaceRequestProcedureJson[] procedures;

    @JsonProperty("deviceID")
    private String deviceID;

    @JsonProperty("appUsername")
    private String appUsername;

    @JsonProperty("appType")
    private String appType;

    public MaceRequestJson getMaceRequest() {
        return maceRequest;
    }

    public void setMaceRequest(MaceRequestJson maceRequest) {
        this.maceRequest = maceRequest;
    }

    public AttachmentJson[] getAttachments() {
        return attachments;
    }

    public void setAttachments(AttachmentJson[] attachments) {
        this.attachments = attachments;
    }

    public MaceRequestProcedureJson[] getProcedures() {
        return procedures;
    }

    public void setProcedures(MaceRequestProcedureJson[] procedures) {
        this.procedures = procedures;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getAppUsername() {
        return appUsername;
    }

    public void setAppUsername(String appUsername) {
        this.appUsername = appUsername;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public static class AttachmentJson { }

    public static class MaceRequestJson {

        @JsonProperty("memberCode")
        private String memberCode;

        @JsonProperty("referenceNo")
        private String referenceNo;

        @JsonProperty("hospitalCode")
        private String hospitalCode;

        @JsonProperty("doctorCode")
        private String doctorCode;

        @JsonProperty("docHospId")
        private String docHospId;

        @JsonProperty("serviceType")
        private Integer serviceType;

        @JsonProperty("primaryComplaint")
        private String primaryComplaint;

        @JsonProperty("searchType")
        private String searchType;

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

        public String getReferenceNo() {
            return referenceNo;
        }

        public void setReferenceNo(String referenceNo) {
            this.referenceNo = referenceNo;
        }

        public String getDoctorCode() {
            return doctorCode;
        }

        public void setDoctorCode(String doctorCode) {
            this.doctorCode = doctorCode;
        }

        public String getDocHospId() {
            return docHospId;
        }

        public void setDocHospId(String docHospId) {
            this.docHospId = docHospId;
        }

        public Integer getServiceType() {
            return serviceType;
        }

        public void setServiceType(Integer serviceType) {
            this.serviceType = serviceType;
        }

        public String getPrimaryComplaint() {
            return primaryComplaint;
        }

        public void setPrimaryComplaint(String primaryComplaint) {
            this.primaryComplaint = primaryComplaint;
        }

        public String getSearchType() {
            return searchType;
        }

        public void setSearchType(String searchType) {
            this.searchType = searchType;
        }
    }
}
