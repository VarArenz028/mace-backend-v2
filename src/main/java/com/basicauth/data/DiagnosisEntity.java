package com.basicauth.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by IPC_Server on 5/24/2017.
 */
@Table(name = "vw_Diagnosis", schema = "dbo", catalog = "Mace")
public class DiagnosisEntity implements Serializable{
    private String classDesc;
    private String icd10Desc;
    private String typeDesc;
    private String groupDesc;
    private String diagCode;
    private String icd10Code;
    private String diagDesc;
    private String diagTypeOld;
    private Boolean status;
    private String diagType;
    private String classCode;
    private String diagRemarks;
    private String icd104c;


    @Basic
    @Column(name = "CLASS_CODE")
    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Basic
    @Column(name = "CLASS_DESC")
    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    @Basic
    @Column(name = "ICD10_DESC")
    public String getIcd10Desc() {
        return icd10Desc;
    }

    public void setIcd10Desc(String icd10Desc) {
        this.icd10Desc = icd10Desc;
    }

    @Basic
    @Column(name = "TYPE_DESC")
    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    @Basic
    @Column(name = "GROUP_DESC")
    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    @Basic
    @Column(name = "DIAG_CODE")
    public String getDiagCode() {
        return diagCode;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    @Basic
    @Column(name = "ICD10_CODE")
    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    @Basic
    @Column(name = "DIAG_DESC")
    public String getDiagDesc() {
        return diagDesc;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
    }

    @Basic
    @Column(name = "DIAG_TYPE_OLD")
    public String getDiagTypeOld() {
        return diagTypeOld;
    }

    public void setDiagTypeOld(String diagTypeOld) {
        this.diagTypeOld = diagTypeOld;
    }

    @Basic
    @Column(name = "STATUS")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Basic
    @Column(name = "DIAG_TYPE")
    public String getDiagType() {
        return diagType;
    }

    public void setDiagType(String diagType) {
        this.diagType = diagType;
    }

    @Basic
    @Column(name = "DIAG_REMARKS")
    public String getDiagRemarks() {
        return diagRemarks;
    }

    public void setDiagRemarks(String diagRemarks) {
        this.diagRemarks = diagRemarks;
    }

    @Basic
    @Column(name = "ICD10_4C")
    public String getIcd104c() {
        return icd104c;
    }

    public void setIcd104c(String icd104c) {
        this.icd104c = icd104c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiagnosisEntity that = (DiagnosisEntity) o;

        if (classDesc != null ? !classDesc.equals(that.classDesc) : that.classDesc != null) return false;
        if (icd10Desc != null ? !icd10Desc.equals(that.icd10Desc) : that.icd10Desc != null) return false;
        if (typeDesc != null ? !typeDesc.equals(that.typeDesc) : that.typeDesc != null) return false;
        if (groupDesc != null ? !groupDesc.equals(that.groupDesc) : that.groupDesc != null) return false;
        if (diagCode != null ? !diagCode.equals(that.diagCode) : that.diagCode != null) return false;
        if (icd10Code != null ? !icd10Code.equals(that.icd10Code) : that.icd10Code != null) return false;
        if (diagDesc != null ? !diagDesc.equals(that.diagDesc) : that.diagDesc != null) return false;
        if (diagTypeOld != null ? !diagTypeOld.equals(that.diagTypeOld) : that.diagTypeOld != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (diagType != null ? !diagType.equals(that.diagType) : that.diagType != null) return false;
        if (diagRemarks != null ? !diagRemarks.equals(that.diagRemarks) : that.diagRemarks != null) return false;
        if (icd104c != null ? !icd104c.equals(that.icd104c) : that.icd104c != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classDesc != null ? classDesc.hashCode() : 0;
        result = 31 * result + (icd10Desc != null ? icd10Desc.hashCode() : 0);
        result = 31 * result + (typeDesc != null ? typeDesc.hashCode() : 0);
        result = 31 * result + (groupDesc != null ? groupDesc.hashCode() : 0);
        result = 31 * result + (diagCode != null ? diagCode.hashCode() : 0);
        result = 31 * result + (icd10Code != null ? icd10Code.hashCode() : 0);
        result = 31 * result + (diagDesc != null ? diagDesc.hashCode() : 0);
        result = 31 * result + (diagTypeOld != null ? diagTypeOld.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (diagType != null ? diagType.hashCode() : 0);
        result = 31 * result + (diagRemarks != null ? diagRemarks.hashCode() : 0);
        result = 31 * result + (icd104c != null ? icd104c.hashCode() : 0);
        return result;
    }
}
