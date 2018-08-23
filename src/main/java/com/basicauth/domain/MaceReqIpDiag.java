package com.basicauth.domain;

import com.basicauth.data.DiagnosisEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * A MaceReqIpDiag.
 */
@Entity
@Table(name = "mace_req_ip_diag")
public class MaceReqIpDiag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ip_reqdiag_id")
    private Integer ipReqdiagId;

    @JsonProperty("maceRequestId")
    @Column(name = "macerequest_id")
    private Integer maceRequestId;

    @JsonProperty("transactionId")
    @Column(name = "transaction_id")
    private Integer transactionId;

    @JsonProperty("diagCode")
    @Column(name = "diag_code")
    private String diagCode;

    @JsonProperty("icd10Code")
    @Column(name = "icd_10_code")
    private String icd10Code;

    @JsonProperty("icd10Class")
    @Column(name = "icd_10_class")
    private String icd10Class;

    @JsonProperty("icd104C")
    @Column(name = "icd_104_c")
    private String icd104C;

    @JsonProperty("diagDesc")
    @Column(name = "diag_desc")
    private String diagDesc;

    @JsonProperty("diagTypeOld")
    @Column(name = "diag_type_old")
    private String diagTypeOld;

    @JsonProperty("diagType")
    @Column(name = "diag_type")
    private String diagType;

    @JsonProperty("diagCaseType")
    @Column(name = "diag_casetype")
    private String diagCaseType;

    @JsonProperty("typeDesc")
    @Column(name = "type_desc")
    private String typeDesc;

    @JsonProperty("procGroupDesc")
    @Column(name = "proc_group_desc")
    private String procGroupDesc;

    @JsonProperty("diagClass")
    @Column(name = "diag_class")
    private String diagClass;

    @JsonProperty("diagRemarks")
    @Column(name = "diag_remarks")
    private String diagRemarks;

    @JsonProperty("maceDiagType")
    @Column(name = "mace_diag_type")
    private Integer maceDiagType;

    @JsonProperty("transCode")
    private String transCode;

    @JsonProperty("toRuleOut")
    @Column(name = "to_rule_out")
    private Boolean toRuleOut;

    @JsonProperty("toConsider")
    @Column(name = "to_consider")
    private Boolean toConsider;

    @JsonProperty("versus")
    @Column(name = "versus")
    private Boolean versus;

    @JsonProperty("diseaseLimit")
    @Column(name = "disease_limit")
    private BigDecimal diseaseLimit;

    @JsonProperty("remLimit")
    @Column(name = "rem_limit")
    private BigDecimal remLimit;

    @JsonProperty("isPrimary")
    @Column(name = "is_primary")
    private Boolean isPrimary;

    @JsonProperty("isAdmitting")
    @Column(name = "is_admitting")
    private Boolean isAdmitting;

    @JsonProperty("isFinal")
    @Column(name = "is_final")
    private Boolean isFinal;

    @JsonProperty("dateAdded")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date dateAdded;

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public Boolean getAdmitting() {
        return isAdmitting;
    }

    public void setAdmitting(Boolean admitting) {
        isAdmitting = admitting;
    }

    public Boolean getFinal() {
        return isFinal;
    }

    public void setFinal(Boolean aFinal) {
        isFinal = aFinal;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public MaceReqIpDiag() { }

    public Integer getIpReqdiagId() {
        return ipReqdiagId;
    }

    public MaceReqIpDiag ipReqdiagId(Integer ipReqdiagId) {
        this.ipReqdiagId = ipReqdiagId;
        return this;
    }

    public void setIpReqdiagId(Integer ipReqdiagId) {
        this.ipReqdiagId = ipReqdiagId;
    }

    public Integer getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(Integer maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getDiagCode() {
        return diagCode;
    }

    public MaceReqIpDiag diagCode(String diagCode) {
        this.diagCode = diagCode;
        return this;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    public String getIcd10Code() {
        return icd10Code;
    }

    public MaceReqIpDiag icd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
        return this;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getIcd10Class() {
        return icd10Class;
    }

    public MaceReqIpDiag icd10Class(String icd10Class) {
        this.icd10Class = icd10Class;
        return this;
    }

    public void setIcd10Class(String icd10Class) {
        this.icd10Class = icd10Class;
    }

    public String getIcd104C() {
        return icd104C;
    }

    public MaceReqIpDiag icd104C(String icd104C) {
        this.icd104C = icd104C;
        return this;
    }

    public void setIcd104C(String icd104C) {
        this.icd104C = icd104C;
    }

    public String getDiagDesc() {
        return diagDesc;
    }

    public MaceReqIpDiag diagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
        return this;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
    }

    public String getDiagTypeOld() {
        return diagTypeOld;
    }

    public MaceReqIpDiag diagTypeOld(String diagTypeOld) {
        this.diagTypeOld = diagTypeOld;
        return this;
    }

    public void setDiagTypeOld(String diagTypeOld) {
        this.diagTypeOld = diagTypeOld;
    }

    public String getDiagType() {
        return diagType;
    }

    public MaceReqIpDiag diagType(String diagType) {
        this.diagType = diagType;
        return this;
    }

    public void setDiagType(String diagType) {
        this.diagType = diagType;
    }

    public String getDiagCaseType() {
        return diagCaseType;
    }

    public void setDiagCaseType(String diagCaseType) {
        this.diagCaseType = diagCaseType;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public MaceReqIpDiag typeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
        return this;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getProcGroupDesc() {
        return procGroupDesc;
    }

    public MaceReqIpDiag procGroupDesc(String procGroupDesc) {
        this.procGroupDesc = procGroupDesc;
        return this;
    }

    public void setProcGroupDesc(String procGroupDesc) {
        this.procGroupDesc = procGroupDesc;
    }

    public String getDiagClass() {
        return diagClass;
    }

    public MaceReqIpDiag diagClass(String diagClass) {
        this.diagClass = diagClass;
        return this;
    }

    public void setDiagClass(String diagClass) {
        this.diagClass = diagClass;
    }

    public String getDiagRemarks() {
        return diagRemarks;
    }

    public MaceReqIpDiag diagRemarks(String diagRemarks) {
        this.diagRemarks = diagRemarks;
        return this;
    }

    public void setDiagRemarks(String diagRemarks) {
        this.diagRemarks = diagRemarks;
    }

    public Integer getMaceDiagType() {
        return maceDiagType;
    }

    public MaceReqIpDiag maceDiagType(Integer maceDiagType) {
        this.maceDiagType = maceDiagType;
        return this;
    }

    public void setMaceDiagType(Integer maceDiagType) {
        this.maceDiagType = maceDiagType;
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

    public BigDecimal getDiseaseLimit() {
        return diseaseLimit;
    }

    public void setDiseaseLimit(BigDecimal diseaseLimit) {
        this.diseaseLimit = diseaseLimit;
    }

    public BigDecimal getRemLimit() {
        return remLimit;
    }

    public void setRemLimit(BigDecimal remLimit) {
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

    public void setDiagnosisEntity(DiagnosisEntity diagnosisEntity, Integer maceDiagType) {
        setDiagCode(diagnosisEntity.getDiagCode());
        setDiagDesc(diagnosisEntity.getDiagDesc());
        setDiagType(diagnosisEntity.getDiagType());
        setDiagTypeOld(diagnosisEntity.getDiagTypeOld());
        setDiagClass("");
        setDiagRemarks(diagnosisEntity.getDiagRemarks());
        setIcd10Code(diagnosisEntity.getIcd10Code());
        setIcd10Class(diagnosisEntity.getClassCode());
        setIcd104C(diagnosisEntity.getIcd104c());
        setTypeDesc(diagnosisEntity.getTypeDesc());
        setMaceDiagType(maceDiagType);
        setProcGroupDesc(diagnosisEntity.getGroupDesc());
    }
}
