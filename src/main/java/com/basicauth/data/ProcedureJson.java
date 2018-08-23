package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by angulo on 11/23/2016.
 */
public class ProcedureJson implements Serializable {

    private double id;

    private String serviceClassCode;

    @JsonProperty("procedureCode")
    private String procedureCode;

    @JsonProperty("procedureDesc")
    private String procedureDesc;

    @JsonProperty("procedureAmount")
    private BigDecimal procedureAmount;

    @JsonProperty("status")
    private String status;

    public ProcedureJson() {
    }

    public ProcedureJson(DiagnosisProceduresViewEntity tape) {
        this.procedureCode = tape.getProcCode();
        this.procedureDesc = tape.getProcedureName();
        this.procedureAmount = BigDecimal.valueOf(tape.getAmount());
    }

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

    public BigDecimal getProcedureAmount() {
        return procedureAmount;
    }

    public void setProcedureAmount(BigDecimal procedureAmount) {
        this.procedureAmount = procedureAmount;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getServiceClassCode() {
        return serviceClassCode;
    }

    public void setServiceClassCode(String serviceClassCode) {
        this.serviceClassCode = serviceClassCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
