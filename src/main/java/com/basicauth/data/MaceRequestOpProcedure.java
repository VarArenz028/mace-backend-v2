package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Bryan on 5/6/2017.
 */
@Entity
@Table(name = "MACE_REQ_OP_PROCEDURE")
public class MaceRequestOpProcedure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DIAGPROC_ID")
    @JsonProperty("diagProcId")
    private long diagProcId;

    @Column(name = "REQDIAG_ID")
    @JsonProperty("reqDiagId")
    private int reqDiagId;

    @Column(name = "MACEREQUEST_ID")
    @JsonProperty("maceRequestId")
    private long maceRequestId;

    @Column(name = "PRESCRIBREDTEST_ID")
    @JsonProperty("prescribedTestId")
    private int prescribedTestId;

    @Column(name = "TRANSACTION_ID")
    @JsonProperty("transactionId")
    private long transactionId;

    @Column(name = "REF_DIAGPROC_ID")
    @JsonProperty("refDiagProcId")
    private int refDiagProcId;

    @Column(name = "DIAG_CODE")
    @JsonProperty("diagCode")
    private String diagCode;

    @Column(name = "PROC_CODE")
    @JsonProperty("procCode")
    private String procCode;

    @Column(name = "PROC_DESC")
    @JsonProperty("procDesc")
    private String procDesc;

    @Column(name = "RUV")
    @JsonProperty("ruv")
    private String ruv;

    @Column(name = "AREA")
    @JsonProperty("area")
    private String area;

    @Column(name = "PROC_HOSPAMOUNT")
    @JsonProperty("procHospAmount")
    private BigDecimal procHospAmount;

    @Column(name = "PROC_DEFAMOUNT")
    @JsonProperty("procDefAmount")
    private BigDecimal procDefAmount;

    @Column(name = "PROC_ACTUALAMOUNT")
    @JsonProperty("procActualAmount")
    private BigDecimal procActualAmount;

    @Column(name = "GROUP")
    @JsonProperty("group")
    private String group;

    @Column(name = "PROC_TYPE")
    @JsonProperty("procType")
    private String procType;

    @Column(name = "PROC_TYPEDESC")
    @JsonProperty("procTypeDesc")
    private String procTypeDesc;

    @Column(name = "PROC_CLASS")
    @JsonProperty("procClass")
    private String procClass;

    @Column(name = "PROC_CLASSDESC")
    @JsonProperty("procClassDesc")
    private String procClassDesc;

    @Column(name = "REMARKS")
    @JsonProperty("remarks")
    private String remarks;

    @Column(name = "STATUS")
    @JsonProperty("status")
    private int status;

    @Column(name = "MACE_SUBTYPE")
    @JsonProperty("maceSubtype")
    private int maceSubtype;

    @Column(name = "TRANS_CODE")
    @JsonProperty("transCode")
    private String transCode;

    public long getDiagProcId() {
        return diagProcId;
    }

    public void setDiagProcId(long diagProcId) {
        this.diagProcId = diagProcId;
    }

    public int getReqDiagId() {
        return reqDiagId;
    }

    public void setReqDiagId(int reqDiagId) {
        this.reqDiagId = reqDiagId;
    }

    public long getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(long maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public int getPrescribedTestId() {
        return prescribedTestId;
    }

    public void setPrescribedTestId(int prescribedTestId) {
        this.prescribedTestId = prescribedTestId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public int getRefDiagProcId() {
        return refDiagProcId;
    }

    public void setRefDiagProcId(int refDiagProcId) {
        this.refDiagProcId = refDiagProcId;
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

    public String getProcDesc() {
        return procDesc;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getRuv() {
        return ruv;
    }

    public void setRuv(String ruv) {
        this.ruv = ruv;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public BigDecimal getProcHospAmount() {
        return procHospAmount;
    }

    public void setProcHospAmount(BigDecimal procHospAmount) {
        this.procHospAmount = procHospAmount;
    }

    public BigDecimal getProcDefAmount() {
        return procDefAmount;
    }

    public void setProcDefAmount(BigDecimal procDefAmount) {
        this.procDefAmount = procDefAmount;
    }

    public BigDecimal getProcActualAmount() {
        return procActualAmount;
    }

    public void setProcActualAmount(BigDecimal procActualAmount) {
        this.procActualAmount = procActualAmount;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getProcType() {
        return procType;
    }

    public void setProcType(String procType) {
        this.procType = procType;
    }

    public String getProcTypeDesc() {
        return procTypeDesc;
    }

    public void setProcTypeDesc(String procTypeDesc) {
        this.procTypeDesc = procTypeDesc;
    }

    public String getProcClass() {
        return procClass;
    }

    public void setProcClass(String procClass) {
        this.procClass = procClass;
    }

    public String getProcClassDesc() {
        return procClassDesc;
    }

    public void setProcClassDesc(String procClassDesc) {
        this.procClassDesc = procClassDesc;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMaceSubtype() {
        return maceSubtype;
    }

    public void setMaceSubtype(int maceSubtype) {
        this.maceSubtype = maceSubtype;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public BigDecimal getAmount() {
        return null != this.getProcActualAmount() && !Objects.equals(this.getProcActualAmount(), BigDecimal.ZERO) ? this.getProcActualAmount() :
                null != this.getProcHospAmount() && !Objects.equals(this.getProcHospAmount(), BigDecimal.ZERO) ? this.getProcHospAmount() :
                        this.getProcDefAmount() != null && !Objects.equals(this.getProcDefAmount(), BigDecimal.ZERO) ? this.getProcDefAmount() : BigDecimal.ZERO;
    }
}
