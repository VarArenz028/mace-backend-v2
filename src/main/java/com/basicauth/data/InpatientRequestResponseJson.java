package com.basicauth.data;

import com.basicauth.domain.MaceReqIpDoctor;
import com.basicauth.domain.MaceReqIpOtherservices;
import com.basicauth.domain.MaceReqIpRoom;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by service.incuventure on 5/29/2017.
 */
public class InpatientRequestResponseJson implements Serializable {

    @JsonProperty("hospitalName")
    private String hospitalName;
    @JsonProperty("reasonForAdmission")
    private String reasonForAdmission;
    @JsonProperty("dateSubmitted")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date dateSubmitted;
    @JsonProperty("physicianList")
    private List<AttendingPhysician> physicianList;

    public static class AttendingPhysician implements Serializable{
        @JsonProperty("doctorCode")
        private String doctorCode;
        @JsonProperty("doctorName")
        private String doctorName;
        @JsonProperty("doctorSpec")
        private String doctorSpec;

        public String getDoctorCode() {
            return doctorCode;
        }

        public void setDoctorCode(String doctorCode) {
            this.doctorCode = doctorCode;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getDoctorSpec() {
            return doctorSpec;
        }

        public void setDoctorSpec(String doctorSpec) {
            this.doctorSpec = doctorSpec;
        }
    }

    @JsonProperty("diagnoses")
    private List<DiagnosisList> diagnoses;

    public static class DiagnosisList {
        @JsonProperty("diagCode")
        private String diagCode;

        @JsonProperty("diagDesc")
        private String diagDesc;

        public String getDiagCode() {
            return diagCode;
        }

        public void setDiagCode(String diagCode) {
            this.diagCode = diagCode;
        }

        public String getDiagDesc() {
            return diagDesc;
        }

        public void setDiagDesc(String diagDesc) {
            this.diagDesc = diagDesc;
        }
    }

    @JsonProperty("procedureList")
    private List<ProcedureList> procedureList;

    public static class ProcedureList implements Serializable{
        @JsonProperty("procCode")
        private String procCode;
        @JsonProperty("procedureName")
        private String procedureName;
        @JsonProperty("procedureAmount")
        private BigDecimal procedureAmount;

        public String getProcCode() {
            return procCode;
        }

        public void setProcCode(String procCode) {
            this.procCode = procCode;
        }

        public String getProcedureName() {
            return procedureName;
        }

        public void setProcedureName(String procedureName) {
            this.procedureName = procedureName;
        }

        public BigDecimal getProcedureAmount() {
            return procedureAmount;
        }

        public void setProcedureAmount(BigDecimal procedureAmount) {
            this.procedureAmount = procedureAmount;
        }
    }

    @JsonProperty("roomNo")
    private String roomNo;
    @JsonProperty("roomPrice")
    private BigDecimal roomPrice;
    @JsonProperty("roomCategory")
    private String roomCategory;
    @JsonProperty("dateFrom")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date dateFrom;
    @JsonProperty("lengthOfStay")
    private String lengthOfStay;
    @JsonProperty("remarks")
    private String remarks;
    @JsonProperty("admissionType")
    private String admissionType;
    @JsonProperty("attachments")
    private List<MaceRequestAttachment> attachments;
    @JsonProperty("dateAdmitted")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date dateAdmitted;
    @JsonProperty("coorNumber")
    private String coorNumber;
    @JsonProperty("coorEmail")
    private String coorEmail;
    @JsonProperty("memberContact")
    private String memberContact;

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getReasonForAdmission() {
        return reasonForAdmission;
    }

    public void setReasonForAdmission(String reasonForAdmission) {
        this.reasonForAdmission = reasonForAdmission;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public List<AttendingPhysician> getPhysicianList() {
        return physicianList;
    }

    public void setPhysicianList(List<AttendingPhysician> physicianList) {
        this.physicianList = physicianList;
    }

    public List<ProcedureList> getProcedureList() {
        return procedureList;
    }

    public void setProcedureList(List<ProcedureList> procedureList) {
        this.procedureList = procedureList;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getLengthOfStay() {
        return lengthOfStay;
    }

    public void setLengthOfStay(String lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public List<MaceRequestAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<MaceRequestAttachment> attachments) {
        this.attachments = attachments;
    }

    public Date getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(Date dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }

    public String getCoorNumber() {
        return coorNumber;
    }

    public void setCoorNumber(String coorNumber) {
        this.coorNumber = coorNumber;
    }

    public String getCoorEmail() {
        return coorEmail;
    }

    public void setCoorEmail(String coorEmail) {
        this.coorEmail = coorEmail;
    }

    public String getMemberContact() {
        return memberContact;
    }

    public void setMemberContact(String memberContact) {
        this.memberContact = memberContact;
    }

    public List<DiagnosisList> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<DiagnosisList> diagnoses) {
        this.diagnoses = diagnoses;
    }
}