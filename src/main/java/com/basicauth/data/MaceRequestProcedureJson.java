package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by service.incuventure on 5/12/2017.
 */
public class MaceRequestProcedureJson implements Serializable {

    @JsonProperty("serviceType")
    private int serviceType;

    @JsonProperty("diagCode")
    private String diagCode;

    @JsonProperty("procCode")
    private String procCode;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("status")
    private String status;

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public String getDiagCode() {
        return diagCode;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
