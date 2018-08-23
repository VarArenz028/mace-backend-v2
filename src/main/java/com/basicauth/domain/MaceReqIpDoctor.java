package com.basicauth.domain;

import com.basicauth.data.Doctor;
import com.basicauth.data.DoctorToHospital;
import com.basicauth.data.Hospital;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by service.incuventure on 5/29/2017.
 */
@Entity
@Table(name = "mace_req_ip_doctor")
public class MaceReqIpDoctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ip_reqdoc_id")
    private Integer ipReqdocId;

    @Column(name = "MACEREQUEST_ID")
    private long maceRequestId;

    @Column(name = "TRANSACTION_ID")
    private Integer transactionId;

    @Column(name = "DOC_HOSP_ID")
    private Long docHospId;

    @Column(name = "DOCTOR_CODE")
    private String doctorCode;

    @Column(name = "DOC_LNAME")
    private String docLname;

    @Column(name = "DOC_FNAME")
    private String docFname;

    @Column(name = "DOC_MNAME")
    private String docMname;

//    @Column(name = "SPECIALIZATION")
//    private String specialization;

    @Column(name = "ISACCREDITED")
    private Boolean isAccredited;

    @Column(name = "HOSPITAL_CODE")
    private String hospitalCode;

    @Column(name = "HOSP_PROF_FEE", precision = 10, scale = 2)
    private BigDecimal hospProfFee;

    @Column(name = "DEF_PROF_FEE", precision = 10, scale = 2)
    private BigDecimal defProfFee;

    @Column(name = "ACTUAL_PROF_FEE", precision = 10, scale = 2)
    private BigDecimal actualProfFee;

    @Column(name = "REF_DIAGPROC_ID")
    private Integer refDiagProcId;

    @Column(name = "MACE_DOCTYPE")
    private String maceDoctype;

    @Column(name = "ADDED_ON")
    private Date addedOn;

    @Column(name = "ADDED_BY")
    private String addedBy;

    @Column(name = "TRANS_CODE")
    private String transCode;

    private String fullName;

    private String specialization;

    public MaceReqIpDoctor() {
    }

    public MaceReqIpDoctor(Doctor doctor, Hospital hospital, DoctorToHospital doctorToHospital,
                           String addedBy, Date addedOn) {
        setDoctorCode(doctor.getDoctorCode());
        setDocFname(doctor.getDocFname());
        setDocLname(doctor.getDocLname());
        setDocMname(doctor.getDocMname());
        setDocHospId(doctorToHospital.getDocHospId());

        setActualProfFee(BigDecimal.ZERO);
        setHospitalCode(hospital.getHospitalCode());

        setAddedBy(addedBy);
        setAddedOn(addedOn);
    }

    //<editor-fold desc="Getters and Setters">

    public Integer getIpReqdocId() {
        return ipReqdocId;
    }

    public void setIpReqdocId(Integer ipReqdocId) {
        this.ipReqdocId = ipReqdocId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Boolean getAccredited() {
        return isAccredited;
    }

    public void setAccredited(Boolean accredited) {
        isAccredited = accredited;
    }

    public long getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(long maceRequestId) {
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

    public String getDocLname() {
        return docLname;
    }

    public void setDocLname(String docLname) {
        this.docLname = docLname;
    }

    public String getDocFname() {
        return docFname;
    }

    public void setDocFname(String docFname) {
        this.docFname = docFname;
    }

    public String getDocMname() {
        return docMname;
    }

    public void setDocMname(String docMname) {
        this.docMname = docMname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    public String getMaceDoctype() {
        return maceDoctype;
    }

    public void setMaceDoctype(String maceDoctype) {
        this.maceDoctype = maceDoctype;
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

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public Integer getRefDiagProcId() {
        return refDiagProcId;
    }

    public void setRefDiagProcId(Integer refDiagProcId) {
        this.refDiagProcId = refDiagProcId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    //</editor-fold>

    //hospitalCode should be Hospital Where Test Will Be Done (so json.getHospitalCode)
    public void setDoctorEntity(DoctorToHospital doctor, String hospitalCode, TestProcObject doctorTpo) {
        setDocHospId(doctor.getDocHospId());
        setDoctorCode(doctor.getDoctorCode());
        setDocLname(doctor.getDocLname());
        setDocFname(doctor.getDocFname());
        setDocMname(doctor.getDocMname());
        setSpecialization(doctor.getSpecDesc());
        setIsAccredited("true".equalsIgnoreCase(doctor.getIsAccredited()));
        setHospitalCode(hospitalCode);
        if (doctorTpo != null) {
            //If doctor has a valid procedure code, the doctor is an Attending Physician (AP)
            setMaceDoctype("AP");
        } else {
            //If no valid procedure code, the doctor is a Resident Doctor (RES)
            setMaceDoctype("RES");
        }
    }

    public String getDoctorName() {
        String name = "";
        name += null != this.docLname ? this.docLname : "";
        name += null != this.docFname ? " , " +this.docFname : "";
        name += null != this.docMname ? " " + this.docMname +".": "";
        return name;
    }
}

