package com.basicauth.data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by IPC_Server on 5/9/2017.
 */
@Entity
@Table(name = "MACE_REQ_OP_DIAG")
public class MaceRequestOpDiag implements Serializable {

    @Id
    @Column(name = "REQDIAG_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reqDiagId;

    @Column(name = "TRANSACTION_ID")
    private long transactionId;

    @Column(name = "MACEREQUEST_ID")
    private long maceRequestId;

    @Column(name = "MACE_DIAG_TYPE")
    private int maceDiagType;

    @Column(name = "DIAG_CODE")
    private String diagCode;

    @Column(name = "DIAG_DESC")
    private String diagDesc;

    @Column(name = "DIAG_TYPE")
    private String diagType;

    @Column(name = "DIAG_TYPE_OLD")
    private String diagTypeOld;

    @Column(name = "DIAG_CLASS")
    private String diagClass;

    @Column(name = "DIAG_REMARKS")
    private String diagRemarks;

    @Column(name = "TO_RULE_OUT")
    private Boolean toRuleOut;

    @Column(name = "TO_CONSIDER")
    private Boolean toConsider;

    @Column(name = "VERSUS")
    private Boolean versus;

    @Column(name = "ICD10_CODE")
    private String icd10Code;

    @Column(name = "ICD10_CLASS")
    private String icd10Class;

    @Column(name = "ICD10_4C")
    private String icd104C;

    @Column(name = "TYPE_DESC")
    private String typeDesc;

    @Column(name = "DIAG_CASETYPE")
    private String diagCaseType;

    @Column(name = "GROUP_DESC")
    private String groupDesc;

    @Column(name="TRANS_CODE")
    private String transCode;

    @Column(name = "DISEASE_LIMIT")
    private BigDecimal diseaseLimit;

    @Column(name = "REM_LIMIT")
    private BigDecimal remLimit;

    @Column(name = "IS_PRIMARY")
    private Boolean isPrimary;

    @Column(name = "IS_ADMITTING")
    private Boolean isAdmitting;

    @Column(name = "IS_FINAL")
    private Boolean isFinal;

    //<editor-fold desc="Getters and Setters">
    public int getReqDiagId() {
        return reqDiagId;
    }

    public void setReqDiagId(int reqDiagId) {
        this.reqDiagId = reqDiagId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(long maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public int getMaceDiagType() {
        return maceDiagType;
    }

    public void setMaceDiagType(int maceDiagType) {
        this.maceDiagType = maceDiagType;
    }

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

    public String getDiagType() {
        return diagType;
    }

    public void setDiagType(String diagType) {
        this.diagType = diagType;
    }

    public String getDiagTypeOld() {
        return diagTypeOld;
    }

    public void setDiagTypeOld(String diagTypeOld) {
        this.diagTypeOld = diagTypeOld;
    }

    public String getDiagClass() {
        return diagClass;
    }

    public void setDiagClass(String diagClass) {
        this.diagClass = diagClass;
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

    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getIcd10Class() {
        return icd10Class;
    }

    public void setIcd10Class(String icd10Class) {
        this.icd10Class = icd10Class;
    }

    public String getIcd104C() {
        return icd104C;
    }

    public void setIcd104C(String icd104C) {
        this.icd104C = icd104C;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getDiagCaseType() {
        return diagCaseType;
    }

    public void setDiagCaseType(String diagCaseType) {
        this.diagCaseType = diagCaseType;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
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

    //</editor-fold>

    public void setDiagnosisEntity(DiagnosisEntity diagnosisEntity) {
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
        setDiagCaseType("");
        setGroupDesc(diagnosisEntity.getGroupDesc());
    }
}
