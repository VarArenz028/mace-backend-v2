package com.basicauth.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Jabito on 09/06/2017.
 */
@Table(name = "vw_Diagnosis_ClinicProcedures", schema = "dbo", catalog = "Mace")
public class DiagnosisClinicProceduresEntity implements Serializable{

    private String diagCode;
    private String diagDesc;
    private String icd10Code;
    private String procID;
    private String procCode;
    private String procedureName;
    private Short approvalId;
    private String approvalType;
    private Double amount;
    private String diagType;
    private String diagTypeDesc;
    public String proClassCode;

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
    @Column(name="ICD1Code")
    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    @Basic
    @Column(name="ProcedureID")
    public String getProcID() {
        return procID;
    }

    public void setProcID(String procID) {
        this.procID = procID;
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
    @Column(name="ApprovalID")
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
    @Column(name="DiagType")
    public String getDiagType() {
        return diagType;
    }

    public void setDiagType(String diagType) {
        this.diagType = diagType;
    }

    @Basic
    @Column(name="Diag_Type_Desc")
    public String getDiagTypeDesc() {
        return diagTypeDesc;
    }

    public void setDiagTypeDesc(String diagTypeDesc) {
        this.diagTypeDesc = diagTypeDesc;
    }

    @Basic
    @Column(name="Proclass_Code")
    public String getProClassCode() {
        return proClassCode;
    }

    public void setProClassCode(String proClassCode) {
        this.proClassCode = proClassCode;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiagnosisClinicProceduresEntity that = (DiagnosisClinicProceduresEntity) o;

        if (diagCode != null ? !diagCode.equals(that.diagCode) : that.diagCode != null) return false;
        if (diagDesc != null ? !diagDesc.equals(that.diagDesc) : that.diagDesc != null) return false;
        if (icd10Code != null ? !icd10Code.equals(that.icd10Code) : that.icd10Code != null) return false;
        if (procID != null ? !procID.equals(that.procID) : that.procID != null) return false;
        if (procCode != null ? !procCode.equals(that.procCode) : that.procCode != null) return false;
        if (procedureName != null ? !procedureName.equals(that.procedureName) : that.procedureName != null)
            return false;
        if (approvalId != null ? !approvalId.equals(that.approvalId) : that.approvalId != null) return false;
        if (approvalType != null ? !approvalType.equals(that.approvalType) : that.approvalType != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (diagType != null ? !diagType.equals(that.diagType) : that.diagType != null) return false;
        return diagTypeDesc != null ? diagTypeDesc.equals(that.diagTypeDesc) : that.diagTypeDesc == null;
    }

    @Override
    public int hashCode() {
        int result = diagCode != null ? diagCode.hashCode() : 0;
        result = 31 * result + (diagDesc != null ? diagDesc.hashCode() : 0);
        result = 31 * result + (icd10Code != null ? icd10Code.hashCode() : 0);
        result = 31 * result + (procID != null ? procID.hashCode() : 0);
        result = 31 * result + (procCode != null ? procCode.hashCode() : 0);
        result = 31 * result + (procedureName != null ? procedureName.hashCode() : 0);
        result = 31 * result + (approvalId != null ? approvalId.hashCode() : 0);
        result = 31 * result + (approvalType != null ? approvalType.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (diagType != null ? diagType.hashCode() : 0);
        result = 31 * result + (diagTypeDesc != null ? diagTypeDesc.hashCode() : 0);
        return result;
    }
}
