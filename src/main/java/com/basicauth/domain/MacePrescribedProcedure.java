package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by Jabito on 28/06/2017.
 */
public class MacePrescribedProcedure {

    @JsonProperty("procedureCode")
    private String procedureCode;
    @JsonProperty("procedureDesc")
    private String procedureDesc;
    @JsonProperty("amount")
    private BigDecimal Amount;
    @JsonProperty("approvalType")
    private String approvalType;
    @JsonProperty("proclassCode")
    private String proclassCode;

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getProcedureDesc() {
        return procedureDesc;
    }

    public void setProcedureDesc(String procedureDesc) {
        this.procedureDesc = procedureDesc;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }

    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public String getProclassCode() {
        return proclassCode;
    }

    public void setProclassCode(String proclassCode) {
        this.proclassCode = proclassCode;
    }
}
