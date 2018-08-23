package com.basicauth.domain.dups;

import com.basicauth.data.DiagnosticProceduresEntity;
import com.basicauth.domain.TestProcObject;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MACE_CONS_PRESCRIBEDTEST", schema = "dbo", catalog = "Mace")
public class MaceConsPrescribedtest implements Serializable{
    private int prescribedtestId;
    private int reqdiagId;
    private int macerequestId;
    private int consTransactionId;
    private String procCode;
    private String procDesc;
    private String group;
    private String procType;
    private String procTypedesc;
    private String procClass;
    private String procClassdesc;

    public void setApprovalTypeId(Integer approvalTypeId) {
        this.approvalTypeId = approvalTypeId;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public void setCostCenterId(Integer costCenterId) {
        this.costCenterId = costCenterId;
    }

    private Integer approvalTypeId;
    private String approvalType;
    private Integer costCenterId;

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    private String costCenter;

    private Integer status;
    private Integer maceSubtype;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRESCRIBEDTEST_ID")
    public int getPrescribedtestId() {
        return prescribedtestId;
    }

    public void setPrescribedtestId(int prescribedtestId) {
        this.prescribedtestId = prescribedtestId;
    }

    @Basic
    @Column(name = "REQDIAG_ID")
    public int getReqdiagId() {
        return reqdiagId;
    }

    public void setReqdiagId(int reqdiagId) {
        this.reqdiagId = reqdiagId;
    }

    @Basic
    @Column(name = "MACEREQUEST_ID")
    public int getMacerequestId() {
        return macerequestId;
    }

    public void setMacerequestId(int macerequestId) {
        this.macerequestId = macerequestId;
    }

    @Basic
    @Column(name = "CONS_TRANSACTION_ID")
    public int getConsTransactionId() {
        return consTransactionId;
    }

    public void setConsTransactionId(int consTransactionId) {
        this.consTransactionId = consTransactionId;
    }

    @Basic
    @Column(name = "PROC_CODE")
    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    @Basic
    @Column(name = "PROC_DESC")
    public String getProcDesc() {
        return procDesc;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    @Basic
    @Column(name = "`GROUP`")
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Basic
    @Column(name = "PROC_TYPE")
    public String getProcType() {
        return procType;
    }

    public void setProcType(String procType) {
        this.procType = procType;
    }

    @Basic
    @Column(name = "PROC_TYPEDESC")
    public String getProcTypedesc() {
        return procTypedesc;
    }

    public void setProcTypedesc(String procTypedesc) {
        this.procTypedesc = procTypedesc;
    }

    @Basic
    @Column(name = "PROC_CLASS")
    public String getProcClass() {
        return procClass;
    }

    public void setProcClass(String procClass) {
        this.procClass = procClass;
    }

    @Basic
    @Column(name = "PROC_CLASSDESC")
    public String getProcClassdesc() {
        return procClassdesc;
    }

    public void setProcClassdesc(String procClassdesc) {
        this.procClassdesc = procClassdesc;
    }

    @Basic
    @Column(name = "STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "MACE_SUBTYPE")
    public Integer getMaceSubtype() {
        return maceSubtype;
    }

    public void setMaceSubtype(Integer maceSubtype) {
        this.maceSubtype = maceSubtype;
    }

    public void setDiagnosisProcedureEntity(DiagnosticProceduresEntity dpe) {
        setProcCode(dpe.getProcCode());
        setProcDesc(dpe.getProcedureDesc());
        setGroup(dpe.getProcedureGroup());
    }

    public void setTestProcObject(TestProcObject tpo){
        //Add more values to set as the TestProcObject view expands
        setProcCode(tpo.getProcCode());
        setProcDesc(tpo.getProcName());
        setApprovalType(tpo.getApprovalType());
        setCostCenterId(tpo.getCostCenterId());
        setCostCenter(tpo.getCostCenter());
        setMaceSubtype(tpo.getSubType());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaceConsPrescribedtest that = (MaceConsPrescribedtest) o;

        if (prescribedtestId != that.prescribedtestId) return false;
        if (reqdiagId != that.reqdiagId) return false;
        if (macerequestId != that.macerequestId) return false;
        if (consTransactionId != that.consTransactionId) return false;
        if (procCode != null ? !procCode.equals(that.procCode) : that.procCode != null) return false;
        if (procDesc != null ? !procDesc.equals(that.procDesc) : that.procDesc != null) return false;
        if (group != null ? !group.equals(that.group) : that.group != null) return false;
        if (procType != null ? !procType.equals(that.procType) : that.procType != null) return false;
        if (procTypedesc != null ? !procTypedesc.equals(that.procTypedesc) : that.procTypedesc != null) return false;
        if (procClass != null ? !procClass.equals(that.procClass) : that.procClass != null) return false;
        if (procClassdesc != null ? !procClassdesc.equals(that.procClassdesc) : that.procClassdesc != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (maceSubtype != null ? !maceSubtype.equals(that.maceSubtype) : that.maceSubtype != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prescribedtestId;
        result = 31 * result + reqdiagId;
        result = 31 * result + macerequestId;
        result = 31 * result + consTransactionId;
        result = 31 * result + (procCode != null ? procCode.hashCode() : 0);
        result = 31 * result + (procDesc != null ? procDesc.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (procType != null ? procType.hashCode() : 0);
        result = 31 * result + (procTypedesc != null ? procTypedesc.hashCode() : 0);
        result = 31 * result + (procClass != null ? procClass.hashCode() : 0);
        result = 31 * result + (procClassdesc != null ? procClassdesc.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (maceSubtype != null ? maceSubtype.hashCode() : 0);
        return result;
    }
}
