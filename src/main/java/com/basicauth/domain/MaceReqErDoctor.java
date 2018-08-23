package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * A MaceReqErDoctor.
 */
@Entity
@Table(name = "mace_req_er_doctor")
public class MaceReqErDoctor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ER_REQDOC_ID")
    private Integer erReqDocId;

    @Column(name = "MACEREQUEST_ID")
    private Integer maceRequestId;

    @Column(name = "TRANSACTION_ID")
    private Integer transactionId;

    @Column(name = "DOC_HOSP_ID")
    private Long docHospId;

    @Column(name = "DOCTOR_CODE")
    private String doctorCode;

    @Column(name = "DOC_LNAME")
    private String docLName;

    @Column(name = "DOC_FNAME")
    private String docFName;

    @Column(name = "DOC_MNAME")
    private String docMName;

    @Column(name = "ISACCREDITED")
    private Boolean isAccredited;

    @Column(name = "HOSPITAL_CODE")
    private String hospitalCode;

    @Column(name = "HOSP_PROF_FEE", precision=18, scale=0)
    private BigDecimal hospProfFee;

    @Column(name = "DEF_PROF_FEE", precision=18, scale=0)
    private BigDecimal defProfFee;

    @Column(name = "ACTUAL_PROF_FEE", precision=18, scale=0)
    private BigDecimal actualProfFee;

    @Column(name = "MACE_DOCTYPE")
    private String maceDocType;

    @Column(name = "ADDED_ON")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date addedOn;

    @Column(name = "ADDED_BY")
    private String addedBy;

    @Column(name = "PROC_CODE")
    private Long procCode;

    @Column(name = "PROC_DESC")
    private String procDesc;

    @Column(name = "SPECIALIZATION")
    private String specialization;

    @Column(name = "TRANS_CODE")
    private String transCode;

    private String fullName;

    public Integer getErReqDocId() {
        return erReqDocId;
    }

    public void setErReqDocId(Integer erReqDocId) {
        this.erReqDocId = erReqDocId;
    }

    public Integer getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(Integer maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public Long getDocHospId() {
        return docHospId;
    }

    public void setDocHospId(Long docHospId) {
        this.docHospId = docHospId;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getDocLName() {
        return docLName;
    }

    public void setDocLName(String docLName) {
        this.docLName = docLName;
    }

    public String getDocFName() {
        return docFName;
    }

    public void setDocFName(String docFName) {
        this.docFName = docFName;
    }

    public String getDocMName() {
        return docMName;
    }

    public void setDocMName(String docMName) {
        this.docMName = docMName;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Boolean getIsAccredited() {
        return isAccredited;
    }

    public void setIsAccredited(Boolean isAccredited) {
        this.isAccredited = isAccredited;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public BigDecimal getHospProfFee() {
        return hospProfFee;
    }

    public void setHospProfFee(BigDecimal hospProfFee) {
        this.hospProfFee = hospProfFee;
    }

    public BigDecimal getDefProfFee() {
        return defProfFee;
    }

    public void setDefProfFee(BigDecimal defProfFee) {
        this.defProfFee = defProfFee;
    }

    public BigDecimal getActualProfFee() {
        return actualProfFee;
    }

    public void setActualProfFee(BigDecimal actualProfFee) {
        this.actualProfFee = actualProfFee;
    }

    public String getMaceDocType() {
        return maceDocType;
    }

    public void setMaceDocType(String maceDocType) {
        this.maceDocType = maceDocType;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Long getProcCode() {
        return procCode;
    }

    public void setProcCode(Long procCode) {
        this.procCode = procCode;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }
}
