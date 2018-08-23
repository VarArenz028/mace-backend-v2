package com.basicauth.data;

import com.basicauth.domain.MaceReqIpOtherservices;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by service.incuventure on 5/29/2017.
 */
public class OtherServiceJson implements Serializable {

    @JsonProperty("maceReqIpOtherservices")
    private MaceReqIpOtherservices maceReqIpOtherservices;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("serviceType")
    private String serviceType;

    @JsonProperty("remarks")
    private String remarks;

    @JsonProperty("status")
    private Integer status; // ???

    //<editor-fold desc="OtherServiceJson Getters and Setters" defaultstate="collapsed">
    public MaceReqIpOtherservices getMaceReqIpOtherservices() {
        return maceReqIpOtherservices;
    }

    public void setMaceReqIpOtherservices(MaceReqIpOtherservices maceReqIpOtherservices) {
        this.maceReqIpOtherservices = maceReqIpOtherservices;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    //</editor-fold>
}
