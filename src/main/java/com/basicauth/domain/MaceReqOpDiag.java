package com.basicauth.domain;

import com.basicauth.data.Diagnosis;
import com.basicauth.data.DiagnosisEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by service.incuventure on 5/29/2017.
 */
@Entity
@Table(name = "mace_req_op_diag")
public class MaceReqOpDiag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "reqdiag_id")
    private Integer reqdiagId;

    @Column(name = "macerequest_id")
    private Integer macerequestId;

    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "diag_code")
    private String diagCode;

    @Column(name = "icd_10_code")
    private String icd10Code;

    @Column(name = "icd_10_class")
    private String icd10Class;

    @Column(name = "icd_104_c")
    private String icd104C;

    @Column(name = "diag_desc")
    private String diagDesc;

    @Column(name = "diag_type_old")
    private String diagTypeOld;

    @Column(name = "diag_type")
    private String diagType;

    @Column(name = "disease_type")
    private String diseaseType;

    @Column(name = "type_desc")
    private String typeDesc;

    @Column(name = "proc_group_desc")
    private String procGroupDesc;

    @Column(name = "diag_class")
    private String diagClass;

    @Column(name = "diag_remarks")
    private String diagRemarks;

    @Column(name = "mace_diag_type")
    private Integer maceDiagType;

    @ManyToOne
    private MaceConsPrescribedtest maceConsPrescribedtest;

    @ManyToOne
    private MaceReqOpProcedure maceReqOpProcedure;

    @ManyToOne
    private MaceReqOpTests maceReqOpTests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReqdiagId() {
        return reqdiagId;
    }

    public MaceReqOpDiag reqdiagId(Integer reqdiagId) {
        this.reqdiagId = reqdiagId;
        return this;
    }

    public void setReqdiagId(Integer reqdiagId) {
        this.reqdiagId = reqdiagId;
    }

    public Integer getMacerequestId() {
        return macerequestId;
    }

    public MaceReqOpDiag macerequestId(Integer macerequestId) {
        this.macerequestId = macerequestId;
        return this;
    }

    public void setMacerequestId(Integer macerequestId) {
        this.macerequestId = macerequestId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public MaceReqOpDiag transactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getDiagCode() {
        return diagCode;
    }

    public MaceReqOpDiag diagCode(String diagCode) {
        this.diagCode = diagCode;
        return this;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    public String getIcd10Code() {
        return icd10Code;
    }

    public MaceReqOpDiag icd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
        return this;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getIcd10Class() {
        return icd10Class;
    }

    public MaceReqOpDiag icd10Class(String icd10Class) {
        this.icd10Class = icd10Class;
        return this;
    }

    public void setIcd10Class(String icd10Class) {
        this.icd10Class = icd10Class;
    }

    public String getIcd104C() {
        return icd104C;
    }

    public MaceReqOpDiag icd104C(String icd104C) {
        this.icd104C = icd104C;
        return this;
    }

    public void setIcd104C(String icd104C) {
        this.icd104C = icd104C;
    }

    public String getDiagDesc() {
        return diagDesc;
    }

    public MaceReqOpDiag diagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
        return this;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
    }

    public String getDiagTypeOld() {
        return diagTypeOld;
    }

    public MaceReqOpDiag diagTypeOld(String diagTypeOld) {
        this.diagTypeOld = diagTypeOld;
        return this;
    }

    public void setDiagTypeOld(String diagTypeOld) {
        this.diagTypeOld = diagTypeOld;
    }

    public String getDiagType() {
        return diagType;
    }

    public MaceReqOpDiag diagType(String diagType) {
        this.diagType = diagType;
        return this;
    }

    public void setDiagType(String diagType) {
        this.diagType = diagType;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public MaceReqOpDiag diseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
        return this;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public MaceReqOpDiag typeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
        return this;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getProcGroupDesc() {
        return procGroupDesc;
    }

    public MaceReqOpDiag procGroupDesc(String procGroupDesc) {
        this.procGroupDesc = procGroupDesc;
        return this;
    }

    public void setProcGroupDesc(String procGroupDesc) {
        this.procGroupDesc = procGroupDesc;
    }

    public String getDiagClass() {
        return diagClass;
    }

    public MaceReqOpDiag diagClass(String diagClass) {
        this.diagClass = diagClass;
        return this;
    }

    public void setDiagClass(String diagClass) {
        this.diagClass = diagClass;
    }

    public String getDiagRemarks() {
        return diagRemarks;
    }

    public MaceReqOpDiag diagRemarks(String diagRemarks) {
        this.diagRemarks = diagRemarks;
        return this;
    }

    public void setDiagRemarks(String diagRemarks) {
        this.diagRemarks = diagRemarks;
    }

    public Integer getMaceDiagType() {
        return maceDiagType;
    }

    public MaceReqOpDiag maceDiagType(Integer maceDiagType) {
        this.maceDiagType = maceDiagType;
        return this;
    }

    public void setMaceDiagType(Integer maceDiagType) {
        this.maceDiagType = maceDiagType;
    }

    public MaceConsPrescribedtest getMaceConsPrescribedtest() {
        return maceConsPrescribedtest;
    }

    public MaceReqOpDiag maceConsPrescribedtest(MaceConsPrescribedtest maceConsPrescribedtest) {
        this.maceConsPrescribedtest = maceConsPrescribedtest;
        return this;
    }

    public void setMaceConsPrescribedtest(MaceConsPrescribedtest maceConsPrescribedtest) {
        this.maceConsPrescribedtest = maceConsPrescribedtest;
    }

    public MaceReqOpProcedure getMaceReqOpProcedure() {
        return maceReqOpProcedure;
    }

    public MaceReqOpDiag maceReqOpProcedure(MaceReqOpProcedure maceReqOpProcedure) {
        this.maceReqOpProcedure = maceReqOpProcedure;
        return this;
    }

    public void setMaceReqOpProcedure(MaceReqOpProcedure maceReqOpProcedure) {
        this.maceReqOpProcedure = maceReqOpProcedure;
    }

    public MaceReqOpTests getMaceReqOpTests() {
        return maceReqOpTests;
    }

    public MaceReqOpDiag maceReqOpTests(MaceReqOpTests maceReqOpTests) {
        this.maceReqOpTests = maceReqOpTests;
        return this;
    }

    public void setMaceReqOpTests(MaceReqOpTests maceReqOpTests) {
        this.maceReqOpTests = maceReqOpTests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MaceReqOpDiag maceReqOpDiag = (MaceReqOpDiag) o;
        if (maceReqOpDiag.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, maceReqOpDiag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MaceReqOpDiag{" +
                "id=" + id +
                ", reqdiagId='" + reqdiagId + "'" +
                ", macerequestId='" + macerequestId + "'" +
                ", transactionId='" + transactionId + "'" +
                ", diagCode='" + diagCode + "'" +
                ", icd10Code='" + icd10Code + "'" +
                ", icd10Class='" + icd10Class + "'" +
                ", icd104C='" + icd104C + "'" +
                ", diagDesc='" + diagDesc + "'" +
                ", diagTypeOld='" + diagTypeOld + "'" +
                ", diagType='" + diagType + "'" +
                ", diseaseType='" + diseaseType + "'" +
                ", typeDesc='" + typeDesc + "'" +
                ", procGroupDesc='" + procGroupDesc + "'" +
                ", diagClass='" + diagClass + "'" +
                ", diagRemarks='" + diagRemarks + "'" +
                ", maceDiagType='" + maceDiagType + "'" +
                '}';
    }

    public void setDiagnosisEntity(DiagnosisEntity diagnosisEntity) {
        setDiagCode(diagnosisEntity.getDiagCode());
        setDiagDesc(diagnosisEntity.getDiagDesc());
        setDiagType(diagnosisEntity.getDiagType());
        setDiagTypeOld(diagnosisEntity.getDiagTypeOld());
        setDiagClass("");
        setDiagRemarks(diagnosisEntity.getDiagRemarks());
        setIcd10Code(diagnosisEntity.getIcd10Code());
        setIcd10Class(diagnosisEntity.getIcd10Desc());
        setIcd104C(diagnosisEntity.getIcd104c());
        setTypeDesc(diagnosisEntity.getTypeDesc());
        setDiseaseType("");
        setProcGroupDesc(diagnosisEntity.getGroupDesc());
    }
}
