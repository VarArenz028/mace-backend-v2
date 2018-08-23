package com.basicauth.data;

import com.basicauth.domain.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Jabito on 08/02/2018.
 */
public class InpatientSummaryReturnJson implements Serializable {
    @JsonProperty("memberCode")
    private String memberCode;
    @JsonProperty("memberName")
    private String memberName;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("birthDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date birthDate;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("hospitalName")
    private String hospitalName;
    @JsonProperty("hospitalAddress")
    private String hospitalAddress;

    public void setMember(Member member) {
        this.memberCode = member.getCode();
        this.memberName = member.getFullname();
        this.birthDate = member.getBirthDate();
        this.companyName = member.getCompanyDesc();
        this.age = member.getAGE();
        this.gender = member.getGender() == 0? "FEMALE": "MALE";
    }

    public void setHospital(Hospital hospital) {
        this.hospitalName = hospital.getHospitalName();
        this.hospitalAddress = hospital.getStreetAddress();
    }

    @JsonProperty("contactList")
    private List<ContactList> contactList;

    public static class ContactList implements Serializable{
        @JsonProperty("coorContact")
        private String coorContact;
        @JsonProperty("coorEmail")
        private String coorEmail;
        @JsonProperty("memberContact")
        private String memberContact;
        @JsonProperty("dateTimeAdded")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date dateAdmitted;

    public void setMaceRequest(MaceRequest maceRequest) {
        this.reasonForAdmission = maceRequest.getReasonForConsult();
        this.dateAdmitted = maceRequest.getAdmissionDate();
        this.admissionType = maceRequest.getAdmissionType();
    }

    @JsonProperty("docList")
    public List<DoctorList> docList;

    public static class DoctorList implements Serializable{
        @JsonProperty("doctorName")
        private String doctorName;
        @JsonProperty("doctorSpec")
        private String doctorSpec;
        @JsonProperty("dateTimeAdded")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
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
    private List<DiagnosisList> diagnoses;

    public static class DiagnosisList implements Serializable{
        @JsonProperty("admittingDiagnosis")
        private String admittingDiagnosis;
        @JsonProperty("dateTimeAdded")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
        private Date dateTimeAdded;

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
    private List<ProcList> procList;

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
    private List<RoomList> roomList;

    public static class RoomList implements Serializable{
        @JsonProperty("roomNo")
        private String roomNo;
        @JsonProperty("roomRate")
        private BigDecimal roomRate;
        @JsonProperty("roomCategory")
        private String roomCategory;
        @JsonProperty("dateTimeAdded")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
        private Date dateTimeAdded;
        @JsonProperty("dateFrom")
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT +8")
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
    @JsonProperty("remarksLists")
    private List<RemarksList> remarksLists;

    public static class RemarksList implements Serializable{
        @JsonProperty("remarks")
        private String remarks;
        @JsonProperty("dateTimeAdded")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
        private Date dateTimeAdded;

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public Date getDateTimeAdded() {
            return dateTimeAdded;
        }

        public void setDateTimeAdded(Date dateTimeAdded) {
            this.dateTimeAdded = dateTimeAdded;
        }
    }
    @JsonProperty("admissionType")
    private String admissionType;
    @JsonProperty("attachments")
    private List<MaceRequestAttachment> attachments;
    @JsonProperty("dateTimeAdmitted")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date dateTimeAdmitted;
    @JsonProperty("dateTimeDischarged")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date dateTimeDischarged;

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public List<ContactList> getContactList() {
        return contactList;
    }

    public void setContactList(List<ContactList> contactList) {
        this.contactList = contactList;
    }

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

    public List<RemarksList> getRemarksLists() {
        return remarksLists;
    }

    public void setRemarksLists(List<RemarksList> remarksLists) {
        this.remarksLists = remarksLists;
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
}
