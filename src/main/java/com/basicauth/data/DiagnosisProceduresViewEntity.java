package com.basicauth.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Jabito on 12/06/2017.
 */
@Table(name = "vw_Diagnosis_Procedures", schema = "dbo", catalog = "Mace")
public class DiagnosisProceduresViewEntity implements Serializable {

    private String diagCode;
    private String diagDesc;
    private String icd10Code;
    private String procCode;
    private String procedureName;
    private Short approvalId;
    private String approvalType;
    private Double amount;
    private Integer costCenterID;
    private String costCenter;
    private Integer procedureGroupId;
    private String procedureGroup;
    public Boolean active;

    @Basic
    @Column(name="DiagnosisCode")
    public String getDiagCode() {
        return diagCode;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    @Basic
    @Column(name="DiagnosisDesc")
    public String getDiagDesc() {
        return diagDesc;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
    }

    @Basic
    @Column(name="ICD10Code")
    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    @Basic
    @Column(name="ProcedureCode")
    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    @Basic
    @Column(name="ProcedureName")
    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    @Basic
    @Column(name="ApprovalTypeID")
    public Short getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Short approvalId) {
        this.approvalId = approvalId;
    }

    @Basic
    @Column(name="ApprovalType")
    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    @Basic
    @Column(name="Amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name="CostCenterID")
    public Integer getCostCenterID() {
        return costCenterID;
    }

    public void setCostCenterID(Integer costCenterID) {
        this.costCenterID = costCenterID;
    }

    @Basic
    @Column(name="CostCenter")
    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    @Basic
    @Column(name="ProcedureGroupId")
    public Integer getProcedureGroupId() {
        return procedureGroupId;
    }

    public void setProcedureGroupId(Integer procedureGroupId) {
        this.procedureGroupId = procedureGroupId;
    }

    @Basic
    @Column(name="ProcedureGroup")
    public String getProcedureGroup() {
        return procedureGroup;
    }

    public void setProcedureGroup(String procedureGroup) {
        this.procedureGroup = procedureGroup;
    }

    @Basic
    @Column(name="active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

