package com.basicauth.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IPC_Server on 5/24/2017.
 */
@Table(name = "vw_Diagnosis_Procedures", schema = "dbo", catalog = "Mace")
public class DiagnosticProceduresEntity implements Serializable {

    private String dxCode;
    private String diagDesc;
    private String icd10Code;
    private String procCode;
    private String procedureName;
    private String procedureDesc;
    private Short approvalId;
    private String approvalType;
    private short costcenterId;
    private String costCenter;
    private String procedureGroup;
    private Double amount;
    private boolean active;
    private String diagType;
    private String diagTypeDesc;

    @Basic
    @Column(name = "DiagnosisCode")
    public String getDxCode() {
        return dxCode;
    }

    public void setDxCode(String dxCode) {
        this.dxCode = dxCode;
    }

    @Basic
    @Column(name = "DiagnosisDesc")
    public String getDiagDesc() {
        return diagDesc;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
    }

    @Basic
    @Column(name = "ICD10Code")
    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    @Basic
    @Column(name = "ProcedureCode")
    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    @Basic
    @Column(name = "ProcedureName")
    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    @Basic
    @Column(name = "ProcedureDesc")
    public String getProcedureDesc() {
        return procedureDesc;
    }

    public void setProcedureDesc(String procedureDesc) {
        this.procedureDesc = procedureDesc;
    }

    @Basic
    @Column(name = "ApprovalTypeID")
    public Short getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Short approvalId) {
        this.approvalId = approvalId;
    }

    @Basic
    @Column(name = "ApprovalType")
    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    @Basic
    @Column(name = "CostCenterID")
    public short getCostcenterId() {
        return costcenterId;
    }

    public void setCostcenterId(short costcenterId) {
        this.costcenterId = costcenterId;
    }

    @Basic
    @Column(name = "CostCenter")
    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    @Basic
    @Column(name = "ProcedureGroup")
    public String getProcedureGroup() {
        return procedureGroup;
    }

    public void setProcedureGroup(String procedureGroup) {
        this.procedureGroup = procedureGroup;
    }

    @Basic
    @Column(name = "Amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiagnosticProceduresEntity that = (DiagnosticProceduresEntity) o;

        if (costcenterId != that.costcenterId) return false;
        if (active != that.active) return false;
        if (dxCode != null ? !dxCode.equals(that.dxCode) : that.dxCode != null) return false;
        if (diagDesc != null ? !diagDesc.equals(that.diagDesc) : that.diagDesc != null) return false;
        if (icd10Code != null ? !icd10Code.equals(that.icd10Code) : that.icd10Code != null) return false;
        if (procCode != null ? !procCode.equals(that.procCode) : that.procCode != null) return false;
        if (procedureName != null ? !procedureName.equals(that.procedureName) : that.procedureName != null)
            return false;
        if (procedureDesc != null ? !procedureDesc.equals(that.procedureDesc) : that.procedureDesc != null)
            return false;
        if (approvalId != null ? !approvalId.equals(that.approvalId) : that.approvalId != null) return false;
        if (approvalType != null ? !approvalType.equals(that.approvalType) : that.approvalType != null) return false;
        if (costCenter != null ? !costCenter.equals(that.costCenter) : that.costCenter != null) return false;
        if (procedureGroup != null ? !procedureGroup.equals(that.procedureGroup) : that.procedureGroup != null)
            return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dxCode != null ? dxCode.hashCode() : 0;
        result = 31 * result + (diagDesc != null ? diagDesc.hashCode() : 0);
        result = 31 * result + (icd10Code != null ? icd10Code.hashCode() : 0);
        result = 31 * result + (procCode != null ? procCode.hashCode() : 0);
        result = 31 * result + (procedureName != null ? procedureName.hashCode() : 0);
        result = 31 * result + (procedureDesc != null ? procedureDesc.hashCode() : 0);
        result = 31 * result + (approvalId != null ? approvalId.hashCode() : 0);
        result = 31 * result + (approvalType != null ? approvalType.hashCode() : 0);
        result = 31 * result + (int) costcenterId;
        result = 31 * result + (costCenter != null ? costCenter.hashCode() : 0);
        result = 31 * result + (procedureGroup != null ? procedureGroup.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    public void setMaceRequest(MaceRequest maceRequest) {

    }
}
