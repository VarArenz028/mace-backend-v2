package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by service.incuventure on 2/28/2017.
 */
public class Procedure implements Serializable {

    @JsonProperty("PROCEDURE_ID")
    @Column(name="PROCEDURE_ID")
    private String id;
    @JsonProperty("PROCEDURE_DESC")
    @Column(name="PROCEDURE_DESC")
    private String procedureDesc;
    @JsonProperty("PROCEDURE_CODE")
    @Column(name="PROCEDURE_CODE")
    private String procedureCode;
    @JsonProperty("PROCEDURE_RATE")
    @Column(name="PROCEDURE_RATE")
    private BigDecimal procedureRate;
    @JsonProperty("GROUP_REM")
    @Column(name="GROUP_REM")
    private String groupRem;
    @JsonProperty("REMARKS")
    @Column(name="REMARKS")
    private String remarks;
    @JsonProperty("STATUS")
    @Column(name="STATUS")
    private Boolean status;
    @JsonProperty("ORGSYS_CODE")
    @Column(name="ORGSYS_CODE")
    private String orgsysCode;
    @JsonProperty("ORGAN_CODE")
    @Column(name="ORGAN_CODE")
    private String organCode;
    @JsonProperty("PROCLASS_CODE")
    @Column(name="PROCLASS_CODE")
    private String proClassCode;
    @JsonProperty("UPDATED_BY")
    @Column(name="UPDATED_BY")
    private String updatedBy;
    @JsonProperty("UPDATED_DATE")
    @Column(name="UPDATED_DATE")
    private String updatedDate;
    @JsonProperty("EDIT_BY")
    @Column(name="EDIT_BY")
    private String editBy;
    @JsonProperty("EDIT_DATE")
    @Column(name="EDIT_DATE")
    private Date editDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcedureDesc() {
        return procedureDesc;
    }

    public void setProcedureDesc(String procedureDesc) {
        this.procedureDesc = procedureDesc;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public BigDecimal getProcedureRate() {
        return procedureRate;
    }

    public void setProcedureRate(BigDecimal procedureRate) {
        this.procedureRate = procedureRate;
    }

    public String getGroupRem() {
        return groupRem;
    }

    public void setGroupRem(String groupRem) {
        this.groupRem = groupRem;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getOrgsysCode() {
        return orgsysCode;
    }

    public void setOrgsysCode(String orgsysCode) {
        this.orgsysCode = orgsysCode;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getProClassCode() {
        return proClassCode;
    }

    public void setProClassCode(String proClassCode) {
        this.proClassCode = proClassCode;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }
}
