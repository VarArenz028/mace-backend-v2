package com.basicauth.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 */
@Entity
public class MaceReqErOtherCharges implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ER_OTHERCHARGE_ID")
    private Integer erOtherChargeId;

    @Column(name = "MACEREQUEST_ID")
    private Integer maceRequestId;

    @Column(name = "CHARGE_TYPE")
    private String chargeType;

    @Column(name = "DIAG_CODE")
    private String diagCode;

    @Column(name = "DETAILS")
    private String details;

    @Column(name = "CHARGE_AMOUNT", precision=18, scale=2)
    private BigDecimal chargeAmount;

    @Column(name = "DIAG_DESC")
    private String diagDesc;

    public Integer getErOtherChargeId() {
        return erOtherChargeId;
    }

    public void setErOtherChargeId(Integer erOtherChargeId) {
        this.erOtherChargeId = erOtherChargeId;
    }

    public Integer getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(Integer maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getDiagCode() {
        return diagCode;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getDiagDesc() {
        return diagDesc;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
    }

}
