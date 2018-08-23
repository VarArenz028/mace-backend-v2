package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by angulo on 10/25/2016.
 */
public class UtilizationRM implements Serializable{

//    CONTROL_CODE VARCHAR
//    AVAIL_FR DATE
//    DIAG_DESC VARCHAR
//    DX_REM  VARCHAR
//    HOSPITAL_NAME VARCHAR
//    APPROVED NUMERIC
//    DISAPPROVED NUMERIC
//    ADVANCES NUMERIC
//    ERC NUMERIC
//    MEMCODE VARCHAR
//    REMARKS2 VARCHAR
//    HOSP_SOA

    @JsonProperty("controlCode")
    private String controlCode;

    @JsonProperty("availFr")
    private Date availFr;

    @JsonProperty("diagDesc")
    private String diagDesc;

    @JsonProperty("dxRem")
    private String dxRem;

    @JsonProperty("hospitalName")
    private String hospitalName;

    @JsonProperty("approved")
    private BigDecimal approved;

    @JsonProperty("disapproved")
    private BigDecimal disapproved;

    @JsonProperty("advances")
    private BigDecimal advances;

    @JsonProperty("erc")
    private BigDecimal erc;

    @JsonProperty("memCode")
    private String memCode;

    @JsonProperty("remarks2")
    private String remarks2;

    @JsonProperty("hospSoa")
    private BigDecimal hospSoa;

    public UtilizationRM(){}

//    public UtilizationRM(String controlCode, Date availFr, String diagDesc, String dxRem, String hospitalName, BigDecimal approved, BigDecimal disapproved, BigDecimal advances, BigDecimal erc, String memCode, String remarks2, BigDecimal hospSoa) {
//        this.controlCode = controlCode;
//        this.availFr = availFr;
//        this.diagDesc = diagDesc;
//        this.dxRem = dxRem;
//        this.hospitalName = hospitalName;
//        this.approved = approved;
//        this.disapproved = disapproved;
//        this.advances = advances;
//        this.erc = erc;
//        this.memCode = memCode;
//        this.remarks2 = remarks2;
//        this.hospSoa = hospSoa;
//    }

    public String getControlCode() {
        return controlCode;
    }

    public void setControlCode(String controlCode) {
        this.controlCode = controlCode;
    }

    public Date getAvailFr() {
        return availFr;
    }

    public void setAvailFr(Date availFr) {
        this.availFr = availFr;
    }

    public String getDiagDesc() {
        return diagDesc;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
    }

    public String getDxRem() {
        return dxRem;
    }

    public void setDxRem(String dxRem) {
        this.dxRem = dxRem;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public BigDecimal getApproved() {
        return approved;
    }

    public void setApproved(BigDecimal approved) {
        this.approved = approved;
    }

    public BigDecimal getDisapproved() {
        return disapproved;
    }

    public void setDisapproved(BigDecimal disapproved) {
        this.disapproved = disapproved;
    }

    public BigDecimal getAdvances() {
        return advances;
    }

    public void setAdvances(BigDecimal advances) {
        this.advances = advances;
    }

    public BigDecimal getErc() {
        return erc;
    }

    public void setErc(BigDecimal erc) {
        this.erc = erc;
    }

    public String getMemCode() {
        return memCode;
    }

    public void setMemCode(String memCode) {
        this.memCode = memCode;
    }

    public String getRemarks2() {
        return remarks2;
    }

    public void setRemarks2(String remarks2) {
        this.remarks2 = remarks2;
    }

    public BigDecimal getHospSoa() {
        return hospSoa;
    }

    public void setHospSoa(BigDecimal hospSoa) {
        this.hospSoa = hospSoa;
    }
}
