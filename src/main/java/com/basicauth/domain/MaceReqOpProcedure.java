package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by service.incuventure on 5/29/2017.
 */
@Entity
@Table(name = "MACE_REQ_OP_PROCEDURE")
public class MaceReqOpProcedure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diagproc_id")
    private Integer diagprocId;

    @Column(name = "REQDIAG_ID")
    private Integer requestDiagId;

    @Column(name = "MACEREQUEST_ID")
    private Integer maceReqId;

    @Column(name = "prescribedtest_id")
    private Integer prescribedtestId;

    @Column(name = "TRANSACTION_ID")
    private Integer transactionId;

    @Column(name = "ref_diagproc_id")
    private Integer refDiagprocId;

    @Column(name = "diag_code")
    private String diagCode;

    @Column(name = "proc_code")
    private String procCode;

    @Column(name = "proc_desc")
    private String procDesc;

    @Column(name = "ruv")
    private String ruv;

    @Column(name = "proc_hospamount", precision=10, scale=2)
    private BigDecimal procHospamount;

    @Column(name = "proc_defamount", precision=10, scale=2)
    private BigDecimal procDefamount;

    @Column(name = "proc_actualamount", precision=10, scale=2)
    private BigDecimal procActualamount;

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

    @Column(name = "TRANS_CODE")
    private String transCode;

    @OneToMany(mappedBy = "maceReqOpProcedure")
    @JsonIgnore
    private Set<MaceReqOpDiag> diagnoses = new HashSet<>();


    public Integer getRequestDiagId() {
        return requestDiagId;
    }

    public void setRequestDiagId(Integer requestDiagId) {
        this.requestDiagId = requestDiagId;
    }

    public Integer getMaceReqId() {
        return maceReqId;
    }

    public void setMaceReqId(Integer maceReqId) {
        this.maceReqId = maceReqId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public Integer getDiagprocId() {
        return diagprocId;
    }

    public MaceReqOpProcedure diagprocId(Integer diagprocId) {
        this.diagprocId = diagprocId;
        return this;
    }

    public void setDiagprocId(Integer diagprocId) {
        this.diagprocId = diagprocId;
    }

    public Integer getPrescribedtestId() {
        return prescribedtestId;
    }

    public MaceReqOpProcedure prescribedtestId(Integer prescribedtestId) {
        this.prescribedtestId = prescribedtestId;
        return this;
    }

    public void setPrescribedtestId(Integer prescribedtestId) {
        this.prescribedtestId = prescribedtestId;
    }

    public Integer getRefDiagprocId() {
        return refDiagprocId;
    }

    public MaceReqOpProcedure refDiagprocId(Integer refDiagprocId) {
        this.refDiagprocId = refDiagprocId;
        return this;
    }

    public void setRefDiagprocId(Integer refDiagprocId) {
        this.refDiagprocId = refDiagprocId;
    }

    public String getDiagCode() {
        return diagCode;
    }

    public MaceReqOpProcedure diagCode(String diagCode) {
        this.diagCode = diagCode;
        return this;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    public String getProcCode() {
        return procCode;
    }

    public MaceReqOpProcedure procCode(String procCode) {
        this.procCode = procCode;
        return this;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public MaceReqOpProcedure procDesc(String procDesc) {
        this.procDesc = procDesc;
        return this;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getRuv() {
        return ruv;
    }

    public MaceReqOpProcedure ruv(String ruv) {
        this.ruv = ruv;
        return this;
    }

    public void setRuv(String ruv) {
        this.ruv = ruv;
    }

    public BigDecimal getProcHospamount() {
        return procHospamount;
    }

    public MaceReqOpProcedure procHospamount(BigDecimal procHospamount) {
        this.procHospamount = procHospamount;
        return this;
    }

    public void setProcHospamount(BigDecimal procHospamount) {
        this.procHospamount = procHospamount;
    }

    public BigDecimal getProcDefamount() {
        return procDefamount;
    }

    public MaceReqOpProcedure procDefamount(BigDecimal procDefamount) {
        this.procDefamount = procDefamount;
        return this;
    }

    public void setProcDefamount(BigDecimal procDefamount) {
        this.procDefamount = procDefamount;
    }

    public BigDecimal getProcActualamount() {
        return procActualamount;
    }

    public MaceReqOpProcedure procActualamount(BigDecimal procActualamount) {
        this.procActualamount = procActualamount;
        return this;
    }

    public void setProcActualamount(BigDecimal procActualamount) {
        this.procActualamount = procActualamount;
    }

    public String getProcGroup() {
        return procGroup;
    }

    public MaceReqOpProcedure procGroup(String procGroup) {
        this.procGroup = procGroup;
        return this;
    }

    public void setProcGroup(String procGroup) {
        this.procGroup = procGroup;
    }

    public String getProcType() {
        return procType;
    }

    public MaceReqOpProcedure procType(String procType) {
        this.procType = procType;
        return this;
    }

    public void setProcType(String procType) {
        this.procType = procType;
    }

    public String getProcTypedesc() {
        return procTypedesc;
    }

    public MaceReqOpProcedure procTypedesc(String procTypedesc) {
        this.procTypedesc = procTypedesc;
        return this;
    }

    public void setProcTypedesc(String procTypedesc) {
        this.procTypedesc = procTypedesc;
    }

    public String getProcClass() {
        return procClass;
    }

    public MaceReqOpProcedure procClass(String procClass) {
        this.procClass = procClass;
        return this;
    }

    public void setProcClass(String procClass) {
        this.procClass = procClass;
    }

    public String getProcClassdesc() {
        return procClassdesc;
    }

    public MaceReqOpProcedure procClassdesc(String procClassdesc) {
        this.procClassdesc = procClassdesc;
        return this;
    }

    public void setProcClassdesc(String procClassdesc) {
        this.procClassdesc = procClassdesc;
    }

    public String getRemarks() {
        return remarks;
    }

    public MaceReqOpProcedure remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public MaceReqOpProcedure status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMaceSubtype() {
        return maceSubtype;
    }

    public MaceReqOpProcedure maceSubtype(Integer maceSubtype) {
        this.maceSubtype = maceSubtype;
        return this;
    }

    public void setMaceSubtype(Integer maceSubtype) {
        this.maceSubtype = maceSubtype;
    }

    public Set<MaceReqOpDiag> getDiagnoses() {
        return diagnoses;
    }

    public MaceReqOpProcedure diagnoses(Set<MaceReqOpDiag> maceReqOpDiags) {
        this.diagnoses = maceReqOpDiags;
        return this;
    }

    public MaceReqOpProcedure addDiagnosis(MaceReqOpDiag maceReqOpDiag) {
        this.diagnoses.add(maceReqOpDiag);
        maceReqOpDiag.setMaceReqOpProcedure(this);
        return this;
    }

    public MaceReqOpProcedure removeDiagnosis(MaceReqOpDiag maceReqOpDiag) {
        this.diagnoses.remove(maceReqOpDiag);
        maceReqOpDiag.setMaceReqOpProcedure(null);
        return this;
    }

    public void setDiagnoses(Set<MaceReqOpDiag> maceReqOpDiags) {
        this.diagnoses = maceReqOpDiags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaceReqOpProcedure that = (MaceReqOpProcedure) o;

        if (diagprocId != null ? !diagprocId.equals(that.diagprocId) : that.diagprocId != null) return false;
        if (prescribedtestId != null ? !prescribedtestId.equals(that.prescribedtestId) : that.prescribedtestId != null)
            return false;
        if (refDiagprocId != null ? !refDiagprocId.equals(that.refDiagprocId) : that.refDiagprocId != null)
            return false;
        if (diagCode != null ? !diagCode.equals(that.diagCode) : that.diagCode != null) return false;
        if (procCode != null ? !procCode.equals(that.procCode) : that.procCode != null) return false;
        if (procDesc != null ? !procDesc.equals(that.procDesc) : that.procDesc != null) return false;
        if (ruv != null ? !ruv.equals(that.ruv) : that.ruv != null) return false;
        if (procHospamount != null ? !procHospamount.equals(that.procHospamount) : that.procHospamount != null)
            return false;
        if (procDefamount != null ? !procDefamount.equals(that.procDefamount) : that.procDefamount != null)
            return false;
        if (procActualamount != null ? !procActualamount.equals(that.procActualamount) : that.procActualamount != null)
            return false;
        if (procGroup != null ? !procGroup.equals(that.procGroup) : that.procGroup != null) return false;
        if (procType != null ? !procType.equals(that.procType) : that.procType != null) return false;
        if (procTypedesc != null ? !procTypedesc.equals(that.procTypedesc) : that.procTypedesc != null) return false;
        if (procClass != null ? !procClass.equals(that.procClass) : that.procClass != null) return false;
        if (procClassdesc != null ? !procClassdesc.equals(that.procClassdesc) : that.procClassdesc != null)
            return false;
        if (remarks != null ? !remarks.equals(that.remarks) : that.remarks != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (maceSubtype != null ? !maceSubtype.equals(that.maceSubtype) : that.maceSubtype != null) return false;
        return diagnoses != null ? diagnoses.equals(that.diagnoses) : that.diagnoses == null;
    }

    @Override
    public int hashCode() {
        int result = diagprocId != null ? diagprocId.hashCode() : 0;
        result = 31 * result + (prescribedtestId != null ? prescribedtestId.hashCode() : 0);
        result = 31 * result + (refDiagprocId != null ? refDiagprocId.hashCode() : 0);
        result = 31 * result + (diagCode != null ? diagCode.hashCode() : 0);
        result = 31 * result + (procCode != null ? procCode.hashCode() : 0);
        result = 31 * result + (procDesc != null ? procDesc.hashCode() : 0);
        result = 31 * result + (ruv != null ? ruv.hashCode() : 0);
        result = 31 * result + (procHospamount != null ? procHospamount.hashCode() : 0);
        result = 31 * result + (procDefamount != null ? procDefamount.hashCode() : 0);
        result = 31 * result + (procActualamount != null ? procActualamount.hashCode() : 0);
        result = 31 * result + (procGroup != null ? procGroup.hashCode() : 0);
        result = 31 * result + (procType != null ? procType.hashCode() : 0);
        result = 31 * result + (procTypedesc != null ? procTypedesc.hashCode() : 0);
        result = 31 * result + (procClass != null ? procClass.hashCode() : 0);
        result = 31 * result + (procClassdesc != null ? procClassdesc.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (maceSubtype != null ? maceSubtype.hashCode() : 0);
        result = 31 * result + (diagnoses != null ? diagnoses.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MaceReqOpProcedure{" +
                ", diagprocId='" + diagprocId + "'" +
                ", prescribedtestId='" + prescribedtestId + "'" +
                ", refDiagprocId='" + refDiagprocId + "'" +
                ", diagCode='" + diagCode + "'" +
                ", procCode='" + procCode + "'" +
                ", procDesc='" + procDesc + "'" +
                ", ruv='" + ruv + "'" +
                ", procHospamount='" + procHospamount + "'" +
                ", procDefamount='" + procDefamount + "'" +
                ", procActualamount='" + procActualamount + "'" +
                ", procGroup='" + procGroup + "'" +
                ", procType='" + procType + "'" +
                ", procTypedesc='" + procTypedesc + "'" +
                ", procClass='" + procClass + "'" +
                ", procClassdesc='" + procClassdesc + "'" +
                ", remarks='" + remarks + "'" +
                ", status='" + status + "'" +
                ", maceSubtype='" + maceSubtype + "'" +
                '}';
    }
}
