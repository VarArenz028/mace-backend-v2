package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by service.incuventure on 4/18/2017.
 */
@Entity
@Table(name = "Record_Consult")
public class RecordConsult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "id")
    private long id;

    @JsonProperty("referenceCode")
    @Column(name = "reference_code")
    private String referenceCode;

    @JsonProperty("memCode")
    @Column(name = "mem_code")
    private String memCode;

    @JsonProperty("memName")
    @Column(name = "mem_name")
    private String memName;

    @JsonProperty("memAge")
    @Column(name = "mem_age")
    private Integer memAge;

    @JsonProperty("memGender")
    @Column(name = "mem_gender")
    private String memGender;

    @JsonProperty("companyName")
    @Column(name = "company_name")
    private String companyName;

    @JsonProperty("dateApproved")
    @Column(name = "date_approved")
    private Date dateApproved;

    @JsonProperty("hospitalCode")
    @Column(name = "hospital_code")
    private String hospitalCode;

    @JsonProperty("hospitalName")
    @Column(name = "hospital_name")
    private String hospitalName;

    @JsonProperty("doctorCode")
    @Column(name= "doctor_code")
    private String doctorCode;

    @JsonProperty("doctorName")
    @Column(name = "doctor_name")
    private String doctorName;

    @JsonProperty("doctorField")
    @Column(name = "doctor_field")
    private String doctorField;

    @JsonProperty("reasonForConsult")
    @Column(name="reason_for_consult")
    private String reasonForConsult;

    @JsonProperty("primaryDiagnosis")
    @Column(name = "primary_diagnosis")
    private String primaryDiagnosis;

    @JsonProperty("otherDiagnosis")
    @Column(name = "other_diagnosis")
    private String otherDiagnosis;

    @JsonProperty("notes")
    @Column(name = "notes")
    private String notes;

    @JsonProperty("loaMaceRequestId")
    @Column(name="LOA_MACE_REQUEST_ID")
    private String loaMaceRequestId;

    @JsonProperty("dateSubmitted")
    @Column(name="date_submitted")
    private Date dateSubmitted;

    @JsonProperty("dateExpired")
    @Column(name = "date_expired")
    private Date dateExpired;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getReasonForConsult() {
        return reasonForConsult;
    }

    public void setReasonForConsult(String reasonForConsult) {
        this.reasonForConsult = reasonForConsult;
    }

    public String getLoaMaceRequestId() {
        return loaMaceRequestId;
    }

    public void setLoaMaceRequestId(String loaMaceRequestId) {
        this.loaMaceRequestId = loaMaceRequestId;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getMemCode() {
        return memCode;
    }

    public void setMemCode(String memCode) {
        this.memCode = memCode;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public Integer getMemAge() {
        return memAge;
    }

    public void setMemAge(Integer memAge) {
        this.memAge = memAge;
    }

    public String getMemGender() {
        return memGender;
    }

    public void setMemGender(String memGender) {
        this.memGender = memGender;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorField() {
        return doctorField;
    }

    public void setDoctorField(String doctorField) {
        this.doctorField = doctorField;
    }

    public String getPrimaryDiagnosis() {
        return primaryDiagnosis;
    }

    public void setPrimaryDiagnosis(String primaryDiagnosis) {
        this.primaryDiagnosis = primaryDiagnosis;
    }

    public String getOtherDiagnosis() {
        return otherDiagnosis;
    }

    public void setOtherDiagnosis(String otherDiagnosis) {
        this.otherDiagnosis = otherDiagnosis;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(Date dateExpired) {
        this.dateExpired = dateExpired;
    }
}
