package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by service.incuventure on 5/29/2017.
 */
@Entity
@Table(name = "mace_cons_prescribedtest")
public class MaceConsPrescribedtest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "prescribedtest_id")
    private Integer prescribedtestId;

    @Column(name = "reqdiag_id")
    private Integer reqdiagId;

    @Column(name = "proc_code")
    private String procCode;

    @Column(name = "proc_desc")
    private String procDesc;

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

    @Column(name = "status")
    private Integer status;

    @Column(name = "mace_subtype")
    private Integer maceSubtype;

    @OneToMany(mappedBy = "maceConsPrescribedtest")
    @JsonIgnore
    private Set<MaceReqOpDiag> diagnoses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrescribedtestId() {
        return prescribedtestId;
    }

    public MaceConsPrescribedtest prescribedtestId(Integer prescribedtestId) {
        this.prescribedtestId = prescribedtestId;
        return this;
    }

    public void setPrescribedtestId(Integer prescribedtestId) {
        this.prescribedtestId = prescribedtestId;
    }

    public Integer getReqdiagId() {
        return reqdiagId;
    }

    public MaceConsPrescribedtest reqdiagId(Integer reqdiagId) {
        this.reqdiagId = reqdiagId;
        return this;
    }

    public void setReqdiagId(Integer reqdiagId) {
        this.reqdiagId = reqdiagId;
    }

    public String getProcCode() {
        return procCode;
    }

    public MaceConsPrescribedtest procCode(String procCode) {
        this.procCode = procCode;
        return this;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public MaceConsPrescribedtest procDesc(String procDesc) {
        this.procDesc = procDesc;
        return this;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getProcGroup() {
        return procGroup;
    }

    public MaceConsPrescribedtest procGroup(String procGroup) {
        this.procGroup = procGroup;
        return this;
    }

    public void setProcGroup(String procGroup) {
        this.procGroup = procGroup;
    }

    public String getProcType() {
        return procType;
    }

    public MaceConsPrescribedtest procType(String procType) {
        this.procType = procType;
        return this;
    }

    public void setProcType(String procType) {
        this.procType = procType;
    }

    public String getProcTypedesc() {
        return procTypedesc;
    }

    public MaceConsPrescribedtest procTypedesc(String procTypedesc) {
        this.procTypedesc = procTypedesc;
        return this;
    }

    public void setProcTypedesc(String procTypedesc) {
        this.procTypedesc = procTypedesc;
    }

    public String getProcClass() {
        return procClass;
    }

    public MaceConsPrescribedtest procClass(String procClass) {
        this.procClass = procClass;
        return this;
    }

    public void setProcClass(String procClass) {
        this.procClass = procClass;
    }

    public String getProcClassdesc() {
        return procClassdesc;
    }

    public MaceConsPrescribedtest procClassdesc(String procClassdesc) {
        this.procClassdesc = procClassdesc;
        return this;
    }

    public void setProcClassdesc(String procClassdesc) {
        this.procClassdesc = procClassdesc;
    }

    public Integer getStatus() {
        return status;
    }

    public MaceConsPrescribedtest status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMaceSubtype() {
        return maceSubtype;
    }

    public MaceConsPrescribedtest maceSubtype(Integer maceSubtype) {
        this.maceSubtype = maceSubtype;
        return this;
    }

    public void setMaceSubtype(Integer maceSubtype) {
        this.maceSubtype = maceSubtype;
    }

    public Set<MaceReqOpDiag> getDiagnoses() {
        return diagnoses;
    }

    public MaceConsPrescribedtest diagnoses(Set<MaceReqOpDiag> maceReqOpDiags) {
        this.diagnoses = maceReqOpDiags;
        return this;
    }

    public MaceConsPrescribedtest addDiagnosis(MaceReqOpDiag maceReqOpDiag) {
        this.diagnoses.add(maceReqOpDiag);
        maceReqOpDiag.setMaceConsPrescribedtest(this);
        return this;
    }

    public MaceConsPrescribedtest removeDiagnosis(MaceReqOpDiag maceReqOpDiag) {
        this.diagnoses.remove(maceReqOpDiag);
        maceReqOpDiag.setMaceConsPrescribedtest(null);
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
        MaceConsPrescribedtest maceConsPrescribedtest = (MaceConsPrescribedtest) o;
        if (maceConsPrescribedtest.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, maceConsPrescribedtest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MaceConsPrescribedtest{" +
                "id=" + id +
                ", prescribedtestId='" + prescribedtestId + "'" +
                ", reqdiagId='" + reqdiagId + "'" +
                ", procCode='" + procCode + "'" +
                ", procDesc='" + procDesc + "'" +
                ", procGroup='" + procGroup + "'" +
                ", procType='" + procType + "'" +
                ", procTypedesc='" + procTypedesc + "'" +
                ", procClass='" + procClass + "'" +
                ", procClassdesc='" + procClassdesc + "'" +
                ", status='" + status + "'" +
                ", maceSubtype='" + maceSubtype + "'" +
                '}';
    }
}