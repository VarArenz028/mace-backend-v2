package com.basicauth.domain;

import com.basicauth.data.Hospital;
import com.basicauth.data.InpatientSummaryReturnJson;
import com.basicauth.data.MaceRequest;
import com.basicauth.data.MaceRequestAttachment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by macbookpro on 2/23/18.
 */
public class MaceRequestErReturned implements Serializable  {







    @JsonProperty("contactList")
    private List<MaceRequestErReturned.ContactList> contactList;

    public static class ContactList implements Serializable {
        @JsonProperty("coorContact")
        private String coorContact;
        @JsonProperty("coorEmail")
        private String coorEmail;
        @JsonProperty("memberContact")
        private String memberContact;
        @JsonProperty("dateTimeAdded")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        private Date dateTimeAdded;

        public String getCoorContact() {
            return coorContact;
        }

        public void setCoorContact(String coorContact) {
            this.coorContact = coorContact;
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

        public Date getDateTimeAdded() {
            return dateTimeAdded;
        }

        public void setDateTimeAdded(Date dateTimeAdded) {
            this.dateTimeAdded = dateTimeAdded;
        }
    }

    @JsonProperty("reasonForAdmission")
    private String reasonForAdmission;
    @JsonProperty("dateAdmitted")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date dateAdmitted;

    public void setMaceRequest(MaceRequest maceRequest) {
        this.reasonForAdmission = maceRequest.getReasonForConsult();
        this.dateAdmitted = maceRequest.getAdmissionDate();
        this.admissionType = maceRequest.getAdmissionType();
    }

    @JsonProperty("docList")
    public List<MaceRequestErReturned.DoctorList> docList;

    public static class DoctorList implements Serializable{
        @JsonProperty("doctorName")
        private String doctorName;
        @JsonProperty("doctorSpec")
        private String doctorSpec;
        @JsonProperty("dateTimeAdded")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        private Date dateTimeAdded;

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

        public Date getDateTimeAdded() {
            return dateTimeAdded;
        }

        public void setDateTimeAdded(Date dateTimeAdded) {
            this.dateTimeAdded = dateTimeAdded;
        }
    }
    @JsonProperty("diagnoses")
    private List<MaceRequestErReturned.DiagnosisList> diagnoses;

    public static class DiagnosisList implements Serializable{
        @JsonProperty("admittingDiagnosis")
        private String admittingDiagnosis;
        @JsonProperty("diagDesc")
        private String diagDesc;
        @JsonProperty("icd10Desc")
        private String icd10Desc;
        @JsonProperty("dateTimeAdded")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        private Date dateTimeAdded;

        public String getDiagDesc() {
            return diagDesc;
        }

        public void setDiagDesc(String diagDesc) {
            this.diagDesc = diagDesc;
        }

        public String getIcd10Desc() {
            return icd10Desc;
        }

        public void setIcd10Desc(String icd10Desc) {
            this.icd10Desc = icd10Desc;
        }

        public String getAdmittingDiagnosis() {
            return admittingDiagnosis;
        }

        public void setAdmittingDiagnosis(String admittingDiagnosis) {
            this.admittingDiagnosis = admittingDiagnosis;
        }

        public Date getDateTimeAdded() {
            return dateTimeAdded;
        }

        public void setDateTimeAdded(Date dateTimeAdded) {
            this.dateTimeAdded = dateTimeAdded;
        }
    }
    @JsonProperty("procList")
    private List<MaceRequestErReturned.ProcList> procList;

    public static class ProcList implements Serializable{
        @JsonProperty("procDesc")
        private String procDesc;
        @JsonProperty("procAmount")
        private BigDecimal procAmount;
        @JsonProperty("dateTimeAdded")
        private Date dateTimeAdded;

        public String getProcDesc() {
            return procDesc;
        }

        public void setProcDesc(String procDesc) {
            this.procDesc = procDesc;
        }

        public BigDecimal getProcAmount() {
            return procAmount;
        }

        public void setProcAmount(BigDecimal procAmount) {
            this.procAmount = procAmount;
        }

        public Date getDateTimeAdded() {
            return dateTimeAdded;
        }

        public void setDateTimeAdded(Date dateTimeAdded) {
            this.dateTimeAdded = dateTimeAdded;
        }
    }
    @JsonProperty("roomList")
    private List<MaceRequestErReturned.RoomList> roomList;

    public static class RoomList implements Serializable{
        @JsonProperty("roomNo")
        private String roomNo;
        @JsonProperty("roomRate")
        private BigDecimal roomRate;
        @JsonProperty("roomCategory")
        private String roomCategory;
        @JsonProperty("dateTimeAdded")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        private Date dateTimeAdded;
        @JsonProperty("dateFrom")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date dateFrom;
        @JsonProperty("lengthOfStay")
        private String lengthOfStay;

        public String getRoomNo() {
            return roomNo;
        }

        public void setRoomNo(String roomNo) {
            this.roomNo = roomNo;
        }

        public BigDecimal getRoomRate() {
            return roomRate;
        }

        public void setRoomRate(BigDecimal roomRate) {
            this.roomRate = roomRate;
        }

        public String getRoomCategory() {
            return roomCategory;
        }

        public void setRoomCategory(String roomCategory) {
            this.roomCategory = roomCategory;
        }

        public Date getDateTimeAdded() {
            return dateTimeAdded;
        }

        public void setDateTimeAdded(Date dateTimeAdded) {
            this.dateTimeAdded = dateTimeAdded;
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
    }

    @JsonProperty("admissionType")
    private String admissionType;
    @JsonProperty("attachments")
    private List<MaceRequestAttachment> attachments;
    @JsonProperty("dateTimeAdmitted")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date dateTimeAdmitted;
    @JsonProperty("dateTimeDischarged")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date dateTimeDischarged;




    public String getReasonForAdmission() {
        return reasonForAdmission;
    }

    public void setReasonForAdmission(String reasonForAdmission) {
        this.reasonForAdmission = reasonForAdmission;
    }

    public Date getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(Date dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
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

    public Date getDateTimeAdmitted() {
        return dateTimeAdmitted;
    }

    public void setDateTimeAdmitted(Date dateTimeAdmitted) {
        this.dateTimeAdmitted = dateTimeAdmitted;
    }

    public Date getDateTimeDischarged() {
        return dateTimeDischarged;
    }

    public void setDateTimeDischarged(Date dateTimeDischarged) {
        this.dateTimeDischarged = dateTimeDischarged;
    }

    public List<ContactList> getContactList() {
        return contactList;
    }

    public void setContactList(List<ContactList> contactList) {
        this.contactList = contactList;
    }

    public List<DoctorList> getDocList() {
        return docList;
    }

    public void setDocList(List<DoctorList> docList) {
        this.docList = docList;
    }

    public List<DiagnosisList> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<DiagnosisList> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public List<ProcList> getProcList() {
        return procList;
    }

    public void setProcList(List<ProcList> procList) {
        this.procList = procList;
    }

    public List<RoomList> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<RoomList> roomList) {
        this.roomList = roomList;
    }
}
