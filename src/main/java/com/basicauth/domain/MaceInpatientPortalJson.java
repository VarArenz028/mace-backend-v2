package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jabito on 28/12/2017.
 */
public class MaceInpatientPortalJson implements Serializable {

    @JsonProperty("memberCode")
    private String memberCode;

    @JsonProperty("doctorCode")
    private String doctorCode;

    @JsonProperty("requestingHospCode")
    private String requestingHospCode;

    @JsonProperty("hospitalCode")
    private String hospitalCode;

    @JsonProperty("primaryDiagnosisCode")
    private String primaryDiagnosisCode;

    @JsonProperty("appUsername")
    private String appUsername;

    @JsonProperty("disposition")
    private String disposition;

    @JsonProperty("requestOrigin")
    private String requestOrigin;

    @JsonProperty("requestByCode")
    private String requestByCode;

    @JsonProperty("requestBy")
    private String requestBy;

    @JsonProperty("requestDate")
    private Date requestDate;

    @JsonProperty("totalUtilization")
    private String totalUtilization;

    @JsonProperty("reasonForAdmission")
    private String reasonForAdmission;

    @JsonProperty("admissionType")
    private String admissionType;

    @JsonProperty("admissionDate")
    private Date admissionDate;

    @JsonProperty("dischargeDate")
    private Date dischargeDate;

    @JsonProperty("otherNotes")
    private String otherNotes;

    //true if at least one doctor is not accredited or not in the doctors library?
    @JsonProperty("sendToPrd")
    private Boolean sendToPrd;

    @JsonProperty("submitForApproval")
    private Boolean submitForApproval;

    //List of Diagnoses
    @JsonProperty("diagnoses")
    private IPDiagnosis[] diagnoses;

    //List of Procedures
    @JsonProperty("procedures")
    private IPProcedure[] procedures;

    //List of Doctors
    @JsonProperty("doctors")
    private IPDoctor[] doctors;

    //List of Rooms
    @JsonProperty("rooms")
    private IPRoom[] rooms;

    @JsonProperty("otherCharges")
    private IPOtherCharges[] otherCharges;

    @JsonProperty("otherInformation")
    private IPOtherInformation[] otherInformation;

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

    public String getRequestingHospCode() {
        return requestingHospCode;
    }

    public void setRequestingHospCode(String requestingHospCode) {
        this.requestingHospCode = requestingHospCode;
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

    public String getAppUsername() {
        return appUsername;
    }

    public void setAppUsername(String appUsername) {
        this.appUsername = appUsername;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }

    public String getRequestByCode() {
        return requestByCode;
    }

    public void setRequestByCode(String requestByCode) {
        this.requestByCode = requestByCode;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getTotalUtilization() {
        return totalUtilization;
    }

    public void setTotalUtilization(String totalUtilization) {
        this.totalUtilization = totalUtilization;
    }

    public String getReasonForAdmission() {
        return reasonForAdmission;
    }

    public void setReasonForAdmission(String reasonForAdmission) {
        this.reasonForAdmission = reasonForAdmission;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getOtherNotes() {
        return otherNotes;
    }

    public void setOtherNotes(String otherNotes) {
        this.otherNotes = otherNotes;
    }

    public Boolean getSendToPrd() {
        return sendToPrd;
    }

    public void setSendToPrd(Boolean sendToPrd) {
        this.sendToPrd = sendToPrd;
    }

    public Boolean getSubmitForApproval() {
        return submitForApproval;
    }

    public void setSubmitForApproval(Boolean submitForApproval) {
        this.submitForApproval = submitForApproval;
    }

    public IPDiagnosis[] getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(IPDiagnosis[] diagnoses) {
        this.diagnoses = diagnoses;
    }

    public IPProcedure[] getProcedures() {
        return procedures;
    }

    public void setProcedures(IPProcedure[] procedures) {
        this.procedures = procedures;
    }

    public IPDoctor[] getDoctors() {
        return doctors;
    }

    public void setDoctors(IPDoctor[] doctors) {
        this.doctors = doctors;
    }

    public IPRoom[] getRooms() {
        return rooms;
    }

    public void setRooms(IPRoom[] rooms) {
        this.rooms = rooms;
    }

    public IPOtherCharges[] getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(IPOtherCharges[] otherCharges) {
        this.otherCharges = otherCharges;
    }

    public IPOtherInformation[] getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(IPOtherInformation[] otherInformation) {
        this.otherInformation = otherInformation;
    }

    public static class IPDiagnosis{

        @JsonProperty("diagnosisCode")
        private String diagnosisCode;

        @JsonProperty("diagCaseType")
        private String diagCaseType;

        @JsonProperty("diagRemarks")
        private String diagRemarks;

        @JsonProperty("toRuleOut")
        private Boolean toRuleOut;

        @JsonProperty("toConsider")
        private Boolean toConsider;

        @JsonProperty("versus")
        private Boolean versus;

        @JsonProperty("diseaseLimit")
        private Double diseaseLimit;

        @JsonProperty("remLimit")
        private Double remLimit;

        @JsonProperty("isPrimary")
        private Boolean isPrimary;

        @JsonProperty("isAdmitting")
        private Boolean isAdmitting;

        @JsonProperty("isFinal")
        private Boolean isFinal;
        //<editor-fold default="collapsed" desc="Getters and Setters for IPDiagnosis">

        public String getDiagnosisCode() {
            return diagnosisCode;
        }

        public void setDiagnosisCode(String diagnosisCode) {
            this.diagnosisCode = diagnosisCode;
        }

        public String getDiagCaseType() {
            return diagCaseType;
        }

        public void setDiagCaseType(String diagCaseType) {
            this.diagCaseType = diagCaseType;
        }

        public String getDiagRemarks() {
            return diagRemarks;
        }

        public void setDiagRemarks(String diagRemarks) {
            this.diagRemarks = diagRemarks;
        }

        public Boolean getToRuleOut() {
            return toRuleOut;
        }

        public void setToRuleOut(Boolean toRuleOut) {
            this.toRuleOut = toRuleOut;
        }

        public Boolean getToConsider() {
            return toConsider;
        }

        public void setToConsider(Boolean toConsider) {
            this.toConsider = toConsider;
        }

        public Boolean getVersus() {
            return versus;
        }

        public void setVersus(Boolean versus) {
            this.versus = versus;
        }

        public Double getDiseaseLimit() {
            return diseaseLimit;
        }

        public void setDiseaseLimit(Double diseaseLimit) {
            this.diseaseLimit = diseaseLimit;
        }

        public Double getRemLimit() {
            return remLimit;
        }

        public void setRemLimit(Double remLimit) {
            this.remLimit = remLimit;
        }

        public Boolean getIsPrimary() {
            return isPrimary;
        }

        public void setIsPrimary(Boolean isPrimary) {
            this.isPrimary = isPrimary;
        }

        public Boolean getIsAdmitting() {
            return isAdmitting;
        }

        public void setIsAdmitting(Boolean isAdmitting) {
            this.isAdmitting = isAdmitting;
        }

        public Boolean getIsFinal() {
            return isFinal;
        }

        public void setIsFinal(Boolean isFinal) {
            this.isFinal = isFinal;
        }


        //</editor-fold>
    }

    public static class IPProcedure {

        @JsonProperty("procedureCode")
        private String procedureCode;

        @JsonProperty("diagnosisCode")
        private String diagnosisCode;

        @JsonProperty("amount")
        private Double amount;

        //<editor-fold default="collapsed" desc="Getters and Setters for IPProcedure">

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

    public static class IPDoctor{

        @JsonProperty("doctorCode")
        private String doctorCode;

        @JsonProperty("procedureCode")
        private String procedureCode;

        public String getDoctorCode() {
            return doctorCode;
        }

        public void setDoctorCode(String doctorCode) {
            this.doctorCode = doctorCode;
        }

        public String getProcedureCode() {
            return procedureCode;
        }

        public void setProcedureCode(String procedureCode) {
            this.procedureCode = procedureCode;
        }
    }

    public static class IPRoom{

        @JsonProperty("roomType")
        private String roomType;

        @JsonProperty("roomNumber")
        private String roomNumber;

        @JsonProperty("roomRate")
        private Double roomRate;

        @JsonProperty("dateFrom")
        private Date dateFrom;

        @JsonProperty("dateTo")
        private Date dateTo;

        @JsonProperty("isRoomSharing")
        private Boolean isRoomSharing;

        public String getRoomType() {
            return roomType;
        }

        public void setRoomType(String roomType) {
            this.roomType = roomType;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public Double getRoomRate() {
            return roomRate;
        }

        public void setRoomRate(Double roomRate) {
            this.roomRate = roomRate;
        }

        public Date getDateFrom() {
            return dateFrom;
        }

        public void setDateFrom(Date dateFrom) {
            this.dateFrom = dateFrom;
        }

        public Date getDateTo() {
            return dateTo;
        }

        public void setDateTo(Date dateTo) {
            this.dateTo = dateTo;
        }

        public Boolean getIsRoomSharing() {
            return isRoomSharing;
        }

        public void setIsRoomSharing(Boolean isRoomSharing) {
            this.isRoomSharing = isRoomSharing;
        }
        //</editor-fold>
    }

    public static class IPOtherCharges {

        @JsonProperty("otherChargeType")
        private String otherChargeType;

        @JsonProperty("diagnosisCode")
        private String diagnosisCode;

        @JsonProperty("otherChargeDetails")
        private String otherChargeDetails;

        @JsonProperty("amount")
        private Double amount;

        public String getOtherChargeType() {
            return otherChargeType;
        }

        public void setOtherChargeType(String otherChargeType) {
            this.otherChargeType = otherChargeType;
        }

        public String getDiagnosisCode() {
            return diagnosisCode;
        }

        public void setDiagnosisCode(String diagnosisCode) {
            this.diagnosisCode = diagnosisCode;
        }

        public String getOtherChargeDetails() {
            return otherChargeDetails;
        }

        public void setOtherChargeDetails(String otherChargeDetails) {
            this.otherChargeDetails = otherChargeDetails;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
    }

    public static class IPOtherInformation {

        @JsonProperty("otherInformationType")
        private String otherInformationType;

        @JsonProperty("otherInformationDetails")
        private String otherInformationDetails;

        public String getOtherInformationType() {
            return otherInformationType;
        }

        public void setOtherInformationType(String otherInformationType) {
            this.otherInformationType = otherInformationType;
        }

        public String getOtherInformationDetails() {
            return otherInformationDetails;
        }

        public void setOtherInformationDetails(String otherInformationDetails) {
            this.otherInformationDetails = otherInformationDetails;
        }
    }
}
