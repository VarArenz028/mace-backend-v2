package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MaceDentalProcedure{

    private int id;
    private int dentalReqId;
    private int procedureId;
    private int tooth;
    private int surfaceNo;
    private BigDecimal amount;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT+8")
    private Date createdOn;

    public MaceDentalProcedure(){}

    public MaceDentalProcedure(RequestDentalJson.DentalProcedures dentalProcedure) {
        this.procedureId = dentalProcedure.getProcedureId();
        this.tooth = dentalProcedure.getTooth();
        this.surfaceNo = dentalProcedure.getSurfaceNo();
        this.amount = dentalProcedure.getTotalAmount();
        this.createdOn = new Date();
    }

    //<editor-fold desc="Getters and Setters" defaultState="collapsed">

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDentalReqId() {
        return dentalReqId;
    }

    public void setDentalReqId(int dentalReqId) {
        this.dentalReqId = dentalReqId;
    }

    public int getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(int procedureId) {
        this.procedureId = procedureId;
    }

    public int getTooth() {
        return tooth;
    }

    public void setTooth(int tooth) {
        this.tooth = tooth;
    }

    public int getSurfaceNo() {
        return surfaceNo;
    }

    public void setSurfaceNo(int surfaceNo) {
        this.surfaceNo = surfaceNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

//</editor-fold>
}
