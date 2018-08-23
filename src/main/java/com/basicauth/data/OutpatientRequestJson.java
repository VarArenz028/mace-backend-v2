package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IPC_Server on 5/18/2017.
 */
public class OutpatientRequestJson implements Serializable {

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

    @JsonProperty("admissionType")
    private String admissionType;

    @JsonProperty("admissionDate")
    private Date admissionDate;

    @JsonProperty("isDisclaimerTicked")
    private Boolean isDisclaimerTicked;

    @JsonProperty("requestDevice")
    private String requestDevice;

    @JsonProperty("serviceType")
    private Integer serviceType;

    @JsonIgnore
    private Integer serviceSubtype;

    @JsonProperty("primaryComplaint")
    private String primaryComplaint;

    @JsonProperty("diagnosisProcedures")
    private DiagnosisProcedureJson[] diagnosisProcedures;

    @JsonProperty("doctors")
    private DoctorJson[] doctors;

    @JsonProperty("rooms")
    private RoomJson[] rooms;

    @JsonProperty("otherCharges")
    private OtherChargeJsonOutpatient[] otherCharges;

    @JsonProperty("otherInformation")
    private OtherInformationJson[] otherInformation;

    @JsonProperty("remarks")
    private String remarks;

    @JsonProperty("duplicateTag")
    private String duplicateTag;

    @JsonProperty("coorEmail")
    private String coorEmail;

    @JsonProperty("coorContact")
    private String coorContact;

    /*
    * No parRequestId = new request
    * With parRequestId but not overridden = updated draft
    * With parRequestId but overridden = override request
    * */
    @JsonProperty("isOverriden")
    private Boolean isOverriden;

    @JsonProperty("parRequestId")
    private Integer parRequestId;

    @JsonProperty("submitForApproval")
    private Boolean submitForApproval;
    //<editor-fold desc="OutpatientRequestJson Getters and Setters" defaultstate="collapsed">


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

    public Boolean getDisclaimerTicked() {
        return isDisclaimerTicked;
    }

    public void setDisclaimerTicked(Boolean isDisclaimerTicked) {
        this.isDisclaimerTicked = isDisclaimerTicked;
    }

    public String getRequestDevice() {
        return requestDevice;
    }

    public void setRequestDevice(String requestDevice) {
        this.requestDevice = requestDevice;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getServiceSubtype() {
        return serviceSubtype;
    }

    public void setServiceSubtype(Integer serviceSubtype) {
        this.serviceSubtype = serviceSubtype;
    }

    public String getPrimaryComplaint() {
        return primaryComplaint;
    }

    public void setPrimaryComplaint(String primaryComplaint) {
        this.primaryComplaint = primaryComplaint;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDuplicateTag() {
        return duplicateTag;
    }

    public void setDuplicateTag(String duplicateTag) {
        this.duplicateTag = duplicateTag;
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

    public Boolean getIsOverriden() {
        return isOverriden;
    }

    public void setIsOverriden(Boolean isOverriden) {
        this.isOverriden = isOverriden;
    }

    public Integer getParRequestId() {
        return parRequestId;
    }

    public void setParRequestId(Integer parRequestId) {
        this.parRequestId = parRequestId;
    }

    public Boolean getSubmitForApproval() {
        return submitForApproval;
    }

    public void setSubmitForApproval(Boolean submitForApproval) {
        this.submitForApproval = submitForApproval;
    }

    public DiagnosisProcedureJson[] getDiagnosisProcedures() {
        return diagnosisProcedures;
    }

    public void setDiagnosisProcedures(DiagnosisProcedureJson[] diagnosisProcedures) {
        this.diagnosisProcedures = diagnosisProcedures;
    }
    //</editor-fold>


    public static class DiagnosisProcedureJson {

        /*
        * diagType and costCenter are non-JSON properties and set internally during backend processes
        * */
        @JsonIgnore
        private Integer diagType;

        @JsonIgnore
        private String costCenter;

        @JsonProperty("procedureCode")
        private String procedureCode;

        @JsonProperty("diagnosisCode")
        private String diagnosisCode;

        @JsonProperty("amount")
        private Double amount;

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

        public Integer getDiagType() {
            return diagType;
        }

        public void setDiagType(Integer diagType) {
            this.diagType = diagType;
        }

        public String getCostCenter() {
            return costCenter;
        }

        public void setCostCenter(String costCenter) {
            this.costCenter = costCenter;
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
    }

    public DoctorJson[] getDoctors() {
        return doctors;
    }

    public void setDoctors(DoctorJson[] doctors) {
        this.doctors = doctors;
    }

    public static class DoctorJson {

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

    public RoomJson[] getRooms() {
        return rooms;
    }

    public void setRooms(RoomJson[] rooms) {
        this.rooms = rooms;
    }

    public static class RoomJson {

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
    }

    public OtherChargeJsonOutpatient[] getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(OtherChargeJsonOutpatient[] otherCharges) {
        this.otherCharges = otherCharges;
    }

    public static class OtherChargeJsonOutpatient {

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

    public OtherInformationJson[] getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(OtherInformationJson[] otherInformation) {
        this.otherInformation = otherInformation;
    }

    public static class OtherInformationJson {

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
