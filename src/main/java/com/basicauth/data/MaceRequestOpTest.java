package com.basicauth.data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by IPC_Server on 5/18/2017.
 */
@Entity
@Table(name = "mace_req_op_tests")
public class MaceRequestOpTest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diagtest_id")
    private long diagTestId;

    @Column(name = "reqdiag_id")
    private long reqDiagId;

    @Column(name = "macerequest_id")
    private long maceRequestId;

    @Column(name = "prescribedtest_id")
    private long prescribedTestId;

    @Column(name = "transaction_id")
    private long transactionId;

    @Column(name = "ref_diagproc_id")
    private long refDiagProcId;

    @Column(name = "diag_code")
    private String diagCode;

    @Column(name = "proc_code")
    private String procCode;

    @Column(name = "proc_desc")
    private String procDesc;

    @Column(name = "proc_type")
    private String procType;

    @Column(name = "proc_typedesc")
    private String procTypeDesc;

    @Column(name = "proc_class")
    private String procClass;

    @Column(name = "proc_classdesc")
    private String procClassDesc;

    @Column(name = "proc_hospamount")
    private BigDecimal procHospAmount;

    @Column(name = "proc_defamount")
    private BigDecimal procDefAmount;

    @Column(name = "proc_actualamount")
    private BigDecimal procActualAmount;

    @Column(name = "group")
    private String group;

    @Column(name = "ruv")
    private String ruv;

    @Column(name = "status")
    private int status;

    @Column(name = "mace_subtype")
    private int maceSubtype;

    @Column(name = "trans_code")
    private String transCode;

    @Column(name="TRANSAMOUNT")
    private BigDecimal transAmount;

    @Column(name = "REMARKS")
    private String remarks;

    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    public long getDiagTestId() {
        return diagTestId;
    }

    public void setDiagTestId(long diagTestId) {
        this.diagTestId = diagTestId;
    }

    public long getReqDiagId() {
        return reqDiagId;
    }

    public void setReqDiagId(long reqDiagId) {
        this.reqDiagId = reqDiagId;
    }

    public long getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(long maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public long getPrescribedTestId() {
        return prescribedTestId;
    }

    public void setPrescribedTestId(long prescribedTestId) {
        this.prescribedTestId = prescribedTestId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getRefDiagProcId() {
        return refDiagProcId;
    }

    public void setRefDiagProcId(long refDiagProcId) {
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

    public String getRuv() {
        return ruv;
    }

    public void setRuv(String ruv) {
        this.ruv = ruv;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BigDecimal getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }
    //</editor-fold>

    public void setDiagnosticProceduresEntity(DiagnosticProceduresEntity dpe) {
        setDiagCode(dpe.getDxCode());
        setProcCode(dpe.getProcCode());
        setProcDesc(dpe.getProcedureDesc());
        setProcType("");
        setProcTypeDesc("");
        setProcClass("");
        setProcClassDesc(dpe.getProcedureGroup());
        setProcActualAmount(BigDecimal.valueOf(dpe.getAmount()));
        setGroup(dpe.getCostCenter());
        setRuv("");
        setStatus(0);
    }

    public BigDecimal getAmount(){
        return null != this.getProcActualAmount() && !Objects.equals(this.getProcActualAmount(), BigDecimal.ZERO) ? this.getProcActualAmount() :
                null != this.getProcHospAmount() && !Objects.equals(this.getProcHospAmount(), BigDecimal.ZERO) ? this.getProcHospAmount() :
                        this.getProcDefAmount() != null && !Objects.equals(this.getProcDefAmount(), BigDecimal.ZERO) ? this.getProcDefAmount() : BigDecimal.ZERO;
    }
}