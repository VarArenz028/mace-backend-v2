package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by service.incuventure on 4/19/2017.
 */
@Table(name = "vw_Diagnosis_Procedures", schema = "dbo", catalog = "Mace")
public class MaceDiagnosisProcedure implements Serializable {

    @JsonProperty("diagCode")
    @Column( name="DiagnosisCode")
    private String diagCode;

    @JsonProperty("diagDesc")
    @Column(name="DiagnosisDesc")
    private String diagDesc;

    @JsonProperty("icd10Code")
    @Column(name="ICD1Code")
    private String icd10Code;

    @JsonProperty("procedureId")
    @Column(name="procedureId")
    private String procedureId;

    @JsonProperty("procCode")
    @Column(name="ProcedureCode")
    private String procCode;

    @JsonProperty("procName")
    @Column(name="ProcedureName")
    private String procName;

    @JsonProperty("amount")
    @Column(name="Amount")
    private Double amount;

    @JsonProperty("approvalId")
    @Column(name="ApprovalID")
    private String approvalId;

    @JsonProperty("approvalType")
    @Column(name="ApprovalType")
    private String approvalType;

    @JsonProperty("diagType")
    @Column(name="DiagType")
    private String diagType;

    @JsonProperty("diagTypeDesc")
    @Column(name="Diag_Type_Desc")
    private String diagTypeDesc;

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

    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public String getDiagType() {
        return diagType;
    }

    public void setDiagType(String diagType) {
        this.diagType = diagType;
    }

    public String getDiagTypeDesc() {
        return diagTypeDesc;
    }

    public void setDiagTypeDesc(String diagTypeDesc) {
        this.diagTypeDesc = diagTypeDesc;
    }
}
