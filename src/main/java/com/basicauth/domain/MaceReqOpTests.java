package com.basicauth.domain;

import com.basicauth.data.DiagnosticProceduresEntity;
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
@Table(name = "mace_req_op_tests")
public class MaceReqOpTests implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "diagtest_id")
    private Integer diagtestId;

    @Column(name = "prescribedtest_id")
    private Integer prescribedtestId;

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

    @OneToMany(mappedBy = "maceReqOpTests")
    @JsonIgnore
    private Set<MaceReqOpDiag> diagnoses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDiagtestId() {
        return diagtestId;
    }

    public MaceReqOpTests diagtestId(Integer diagtestId) {
        this.diagtestId = diagtestId;
        return this;
    }

    public void setDiagtestId(Integer diagtestId) {
        this.diagtestId = diagtestId;
    }

    public Integer getPrescribedtestId() {
        return prescribedtestId;
    }

    public MaceReqOpTests prescribedtestId(Integer prescribedtestId) {
        this.prescribedtestId = prescribedtestId;
        return this;
    }

    public void setPrescribedtestId(Integer prescribedtestId) {
        this.prescribedtestId = prescribedtestId;
    }

    public Integer getRefDiagprocId() {
        return refDiagprocId;
    }

    public MaceReqOpTests refDiagprocId(Integer refDiagprocId) {
        this.refDiagprocId = refDiagprocId;
        return this;
    }

    public void setRefDiagprocId(Integer refDiagprocId) {
        this.refDiagprocId = refDiagprocId;
    }

    public String getDiagCode() {
        return diagCode;
    }

    public MaceReqOpTests diagCode(String diagCode) {
        this.diagCode = diagCode;
        return this;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    public String getProcCode() {
        return procCode;
    }

    public MaceReqOpTests procCode(String procCode) {
        this.procCode = procCode;
        return this;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public MaceReqOpTests procDesc(String procDesc) {
        this.procDesc = procDesc;
        return this;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getRuv() {
        return ruv;
    }

    public MaceReqOpTests ruv(String ruv) {
        this.ruv = ruv;
        return this;
    }

    public void setRuv(String ruv) {
        this.ruv = ruv;
    }

    public BigDecimal getProcHospamount() {
        return procHospamount;
    }

    public MaceReqOpTests procHospamount(BigDecimal procHospamount) {
        this.procHospamount = procHospamount;
        return this;
    }

    public void setProcHospamount(BigDecimal procHospamount) {
        this.procHospamount = procHospamount;
    }

    public BigDecimal getProcDefamount() {
        return procDefamount;
    }

    public MaceReqOpTests procDefamount(BigDecimal procDefamount) {
        this.procDefamount = procDefamount;
        return this;
    }

    public void setProcDefamount(BigDecimal procDefamount) {
        this.procDefamount = procDefamount;
    }

    public BigDecimal getProcActualamount() {
        return procActualamount;
    }

    public MaceReqOpTests procActualamount(BigDecimal procActualamount) {
        this.procActualamount = procActualamount;
        return this;
    }

    public void setProcActualamount(BigDecimal procActualamount) {
        this.procActualamount = procActualamount;
    }

    public String getProcGroup() {
        return procGroup;
    }

    public MaceReqOpTests procGroup(String procGroup) {
        this.procGroup = procGroup;
        return this;
    }

    public void setProcGroup(String procGroup) {
        this.procGroup = procGroup;
    }

    public String getProcType() {
        return procType;
    }

    public MaceReqOpTests procType(String procType) {
        this.procType = procType;
        return this;
    }

    public void setProcType(String procType) {
        this.procType = procType;
    }

    public String getProcTypedesc() {
        return procTypedesc;
    }

    public MaceReqOpTests procTypedesc(String procTypedesc) {
        this.procTypedesc = procTypedesc;
        return this;
    }

    public void setProcTypedesc(String procTypedesc) {
        this.procTypedesc = procTypedesc;
    }

    public String getProcClass() {
        return procClass;
    }

    public MaceReqOpTests procClass(String procClass) {
        this.procClass = procClass;
        return this;
    }

    public void setProcClass(String procClass) {
        this.procClass = procClass;
    }

    public String getProcClassdesc() {
        return procClassdesc;
    }

    public MaceReqOpTests procClassdesc(String procClassdesc) {
        this.procClassdesc = procClassdesc;
        return this;
    }

    public void setProcClassdesc(String procClassdesc) {
        this.procClassdesc = procClassdesc;
    }

    public String getRemarks() {
        return remarks;
    }

    public MaceReqOpTests remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public MaceReqOpTests status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMaceSubtype() {
        return maceSubtype;
    }

    public MaceReqOpTests maceSubtype(Integer maceSubtype) {
        this.maceSubtype = maceSubtype;
        return this;
    }

    public void setMaceSubtype(Integer maceSubtype) {
        this.maceSubtype = maceSubtype;
    }

    public Set<MaceReqOpDiag> getDiagnoses() {
        return diagnoses;
    }

    public MaceReqOpTests diagnoses(Set<MaceReqOpDiag> maceReqOpDiags) {
        this.diagnoses = maceReqOpDiags;
        return this;
    }

    public MaceReqOpTests addDiagnosis(MaceReqOpDiag maceReqOpDiag) {
        this.diagnoses.add(maceReqOpDiag);
        maceReqOpDiag.setMaceReqOpTests(this);
        return this;
    }

    public MaceReqOpTests removeDiagnosis(MaceReqOpDiag maceReqOpDiag) {
        this.diagnoses.remove(maceReqOpDiag);
        maceReqOpDiag.setMaceReqOpTests(null);
        return this;
    }

    public void setDiagnoses(Set<MaceReqOpDiag> maceReqOpDiags) {
        this.diagnoses = maceReqOpDiags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MaceReqOpTests maceReqOpTests = (MaceReqOpTests) o;
        if (maceReqOpTests.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, maceReqOpTests.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MaceReqOpTests{" +
                "id=" + id +
                ", diagtestId='" + diagtestId + "'" +
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

    public void setDiagnosticProceduresEntity(DiagnosticProceduresEntity dpe) {
        setDiagCode(dpe.getDxCode());
        setProcCode(dpe.getProcCode());
        setProcDesc(dpe.getProcedureDesc());
        setProcType("");
        setProcTypedesc("");
        setProcClass("");
        setProcClassdesc(dpe.getProcedureGroup());
        setProcActualamount(BigDecimal.valueOf(dpe.getAmount()));
        setProcGroup(dpe.getCostCenter());
        setRuv("");
        setStatus(0);
    }
}
