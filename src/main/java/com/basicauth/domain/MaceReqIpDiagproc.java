package com.basicauth.domain;

import com.basicauth.data.DiagnosisClinicProceduresEntity;
import com.basicauth.data.DiagnosticProceduresEntity;
import com.basicauth.data.Procedure;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by service.incuventure on 5/29/2017.
 */
@Entity
@Table(name = "mace_req_ip_diagproc")
public class MaceReqIpDiagproc implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ip_reqdiagproc_id")
    private Integer ipReqdiagprocId;

    @Column(name = "reqdiag_id")
    private Integer reqDiagId;

    @Column(name = "macerequest_id")
    private Integer maceRequestId;

    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "diag_code")
    private String diagCode;

    @Column(name = "proc_code")
    private String procCode;

    @Column(name = "proc_desc")
    private String procDesc;

    @Column(name = "ruv")
    private String ruv;

    @Column(name = "hosp_amount", precision=10, scale=2)
    private BigDecimal hospAmount;

    @Column(name = "def_amount", precision=10, scale=2)
    private BigDecimal defAmount;

    @Column(name = "actual_amount", precision=10, scale=2)
    private BigDecimal actualAmount;

    @Column(name = "proc_group")
    private String procGroup;

    @Column(name = "proc_type")
    private String procType;

    @Column(name = "proc_typedesc")
    private String procTypedesc;

    @Column(name = "proc_class")
    private String procClass;

    @Column(name = "proc_classdesc")
    private String procClassdesc;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "status")
    private Integer status;

    @Column(name = "mace_subtype")
    private Integer maceSubtype;

    @Column(name = "covered")
    private Integer covered;

    private String transCode;

    private Date addedOn;

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public MaceReqIpDiagproc() { }

    public MaceReqIpDiagproc(Procedure procedure) {
        setProcCode(procedure.getProcedureCode());
        setProcDesc(procedure.getProcedureDesc());
        setProcClass(procedure.getProClassCode());
        setActualAmount(procedure.getProcedureRate());
    }


    public Integer getIpReqdiagprocId() {
        return ipReqdiagprocId;
    }

    public MaceReqIpDiagproc ipReqdiagprocId(Integer ipReqdiagprocId) {
        this.ipReqdiagprocId = ipReqdiagprocId;
        return this;
    }

    public void setIpReqdiagprocId(Integer ipReqdiagprocId) {
        this.ipReqdiagprocId = ipReqdiagprocId;
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

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
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

    public MaceReqIpDiagproc procCode(String procCode) {
        this.procCode = procCode;
        return this;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public MaceReqIpDiagproc procDesc(String procDesc) {
        this.procDesc = procDesc;
        return this;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getRuv() {
        return ruv;
    }

    public MaceReqIpDiagproc ruv(String ruv) {
        this.ruv = ruv;
        return this;
    }

    public void setRuv(String ruv) {
        this.ruv = ruv;
    }

    public BigDecimal getHospAmount() {
        return hospAmount;
    }

    public MaceReqIpDiagproc hospAmount(BigDecimal hospAmount) {
        this.hospAmount = hospAmount;
        return this;
    }

    public void setHospAmount(BigDecimal hospAmount) {
        this.hospAmount = hospAmount;
    }

    public BigDecimal getDefAmount() {
        return defAmount;
    }

    public MaceReqIpDiagproc defAmount(BigDecimal defAmount) {
        this.defAmount = defAmount;
        return this;
    }

    public void setDefAmount(BigDecimal defAmount) {
        this.defAmount = defAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public MaceReqIpDiagproc actualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
        return this;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getProcGroup() {
        return procGroup;
    }

    public MaceReqIpDiagproc procGroup(String procGroup) {
        this.procGroup = procGroup;
        return this;
    }

    public void setProcGroup(String procGroup) {
        this.procGroup = procGroup;
    }

    public String getProcType() {
        return procType;
    }

    public MaceReqIpDiagproc procType(String procType) {
        this.procType = procType;
        return this;
    }

    public void setProcType(String procType) {
        this.procType = procType;
    }

    public String getProcTypedesc() {
        return procTypedesc;
    }

    public MaceReqIpDiagproc procTypedesc(String procTypedesc) {
        this.procTypedesc = procTypedesc;
        return this;
    }

    public void setProcTypedesc(String procTypedesc) {
        this.procTypedesc = procTypedesc;
    }

    public String getProcClass() {
        return procClass;
    }

    public MaceReqIpDiagproc procClass(String procClass) {
        this.procClass = procClass;
        return this;
    }

    public void setProcClass(String procClass) {
        this.procClass = procClass;
    }

    public String getProcClassdesc() {
        return procClassdesc;
    }

    public MaceReqIpDiagproc procClassdesc(String procClassdesc) {
        this.procClassdesc = procClassdesc;
        return this;
    }

    public void setProcClassdesc(String procClassdesc) {
        this.procClassdesc = procClassdesc;
    }

    public String getRemarks() {
        return remarks;
    }

    public MaceReqIpDiagproc remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public MaceReqIpDiagproc status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMaceSubtype() {
        return maceSubtype;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public MaceReqIpDiagproc maceSubtype(Integer maceSubtype) {
        this.maceSubtype = maceSubtype;
        return this;
    }

    public void setMaceSubtype(Integer maceSubtype) {
        this.maceSubtype = maceSubtype;
    }

    public Integer getCovered() {
        return covered;
    }

    public MaceReqIpDiagproc covered(Integer covered) {
        this.covered = covered;
        return this;
    }

    public void setCovered(Integer covered) {
        this.covered = covered;
    }

    @JsonIgnore
    public void setDiagnosticProceduresEntity(DiagnosticProceduresEntity dpe) {
        this.procCode = dpe.getProcCode();
        this.procDesc = dpe.getProcedureDesc();
        this.procType = dpe.getApprovalType();
        this.procTypedesc = "";
        this.actualAmount = BigDecimal.valueOf(dpe.getAmount());
        this.procClassdesc = dpe.getProcedureGroup();
        this.procGroup = dpe.getCostCenter();
        this.procClass = "";
        this.ruv = "";
        this.status = 0;
    }

    @JsonIgnore
    public void setDiagnosisClinicProceduresEntity(DiagnosisClinicProceduresEntity dcpe) {
        this.procCode = dcpe.getProcCode();
        this.procDesc = dcpe.getProcedureName();
        this.procTypedesc = "";
        this.actualAmount = BigDecimal.valueOf(dcpe.getAmount());
        this.procClass = "";
        this.ruv = "";
        this.status = 0;
    }
}
