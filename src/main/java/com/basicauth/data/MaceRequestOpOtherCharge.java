package com.basicauth.data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IPC_Server on 5/9/2017.
 */
@Entity
@Table(name = "MACE_REQ_OP_OTHERCHARGES")
public class MaceRequestOpOtherCharge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OP_OTHERCHARGE_ID")
    private int opOtherChargeId;

    @Column(name = "MACEREQUEST_ID")
    private long maceRequestId;

    @Column(name = "CHARGE_TYPE")
    private String chargeType;

    @Column(name = "DIAG_CODE")
    private String diagCode;

    @Column(name = "DIAG_DESC")
    private String diagDesc;

    @Column(name = "DETAILS")
    private String details;

    @Column(name = "CHARGE_AMOUNT")
    private BigDecimal chargeAmount;

    //<editor-fold desc="Getters and Setters">


    public int getOpOtherChargeId() {
        return opOtherChargeId;
    }

    public void setOpOtherChargeId(int opOtherChargeId) {
        this.opOtherChargeId = opOtherChargeId;
    }

    public long getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(long maceRequestId) {
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

    public String getDiagDesc() {
        return diagDesc;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
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


    //</editor-fold>
}
