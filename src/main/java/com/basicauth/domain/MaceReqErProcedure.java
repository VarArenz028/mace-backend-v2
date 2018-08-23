package com.basicauth.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 */
@Entity
public class MaceReqErProcedure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DIAGPROC_ID")
    private Integer diagProcId;

    @Column(name = "REQDIAG_ID")
    private Integer reqDiagId;

    @Column(name = "MACEREQUEST_ID")
    private Integer maceRequestId;

    @Column(name = "PRESCRIBEDTEST_ID")
    private Integer prescribedTestId;

    @Column(name = "TRANSACTION_ID")
    private Integer transactionId;

    @Column(name = "REF_DIAGPROC_ID")
    private Integer refDiagProcId;

    @Column(name = "DIAG_CODE")
    private String diagCode;

    @Column(name = "PROC_CODE")
    private String procCode;

    @Column(name = "PROC_DESC")
    private String procDesc;

    @Column(name = "RUV")
    private String ruv;

    @Column(name = "PROC_HOSPAMOUNT")
    private String procHospAmount;

    @Column(name = "PROC_DEFAMOUNT", precision=18, scale=2)
    private BigDecimal procDefAmount;

    @Column(name = "PROC_ACTUALAMOUNT", precision=18, scale=2)
    private BigDecimal procActualAmount;

    @Column(name = "GROUP")
    private String group;

    @Column(name = "PROC_TYPE")
    private String procType;

    @Column(name = "PROC_TYPEDESC")
    private String procTypeDesc;

    @Column(name = "PROC_CLASS")
    private String procClass;

    @Column(name = "PROC_CLASSDESC")
    private String procClassDesc;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "MACE_SUBTYPE")
    private Integer maceSubType;

    @Column(name = "TRANS_CODE")
    private String transCode;

    @Column(name = "AREA")
    private String area;

    @Column(name = "EXCLUDED")
    private Boolean excluded;

    public Integer getDiagProcId() {
        return diagProcId;
    }

    public void setDiagProcId(Integer diagProcId) {
        this.diagProcId = diagProcId;
    }

    public Integer getReqDiagId() {
        return reqDiagId;
    }

    public void setReqDiagId(Integer reqDiagId) {
        this.reqDiagId = reqDiagId;
    }

    public Integer getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(Integer maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public Integer getPrescribedTestId() {
        return prescribedTestId;
    }

    public void setPrescribedTestId(Integer prescribedTestId) {
        this.prescribedTestId = prescribedTestId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getRefDiagProcId() {
        return refDiagProcId;
    }

    public void setDefRiagProcId(Integer refDiagProcId) {
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

    public String getProcHospAmount() {
        return procHospAmount;
    }

    public void setProcHospAmount(String procHospAmount) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMaceSubType() {
        return maceSubType;
    }

    public void setMaceSubType(Integer maceSubType) {
        this.maceSubType = maceSubType;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Boolean getExcluded() {
        return excluded;
    }

    public void setExcluded(Boolean excluded) {
        this.excluded = excluded;
    }
}
