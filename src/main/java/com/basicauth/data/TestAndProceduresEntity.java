package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Jabito on 13/06/2017.
 */
@Table(name = "vw_Diagnosis_TestProcedures", schema = "dbo", catalog = "Mace")
public class TestAndProceduresEntity implements Serializable {

    private String diagCode;
    private String diagDesc;
    private String procCode;
    private String procDesc;
    private String maceServiceType;
    private String approvalType;
    private Integer costCenterId;
    private String costCenter;
    private Double amount;

    @Basic
    @Column(name="DiagnosisCode")
    @JsonIgnore
    public String getDiagCode() {
        return diagCode;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    @Basic
    @Column(name="DiagnosisDesc")
    @JsonIgnore
    public String getDiagDesc() {
        return diagDesc;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
    }

    @Basic
    @Column(name="ProcedureCode")
    @JsonProperty("procedureCode")
    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    @Basic
    @Column(name="ProcedureName")
    @JsonProperty("procedureDesc")
    public String getProcDesc() {
        return procDesc;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    @Basic
    @Column(name="MaceServiceType")
    @JsonProperty("maceServiceType")
    public String getMaceServiceType() {
        return maceServiceType;
    }

    public void setMaceServiceType(String maceServiceType) {
        this.maceServiceType = maceServiceType;
    }

    @Basic
    @Column(name="CostCenterID")
    @JsonIgnore
    public Integer getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(Integer costCenterId) {
        this.costCenterId = costCenterId;
    }

    @Basic
    @Column(name="ApprovalType")
    @JsonIgnore
    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    @Basic
    @Column(name="CostCenter")
    @JsonProperty("costCenter")
    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    @Basic
    @Column(name="Amount")
    @JsonProperty("procedureAmount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
