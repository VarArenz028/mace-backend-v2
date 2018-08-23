package com.basicauth.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jabito on 19/07/2017.
 */
public class HospitalProcedureAmountView implements Serializable{

    private String procedureCode;
    private String procedureName;
    private String hospitalCode;
    private String hospitalName;
    private BigDecimal hospAmount;
    private BigDecimal defaultAmount;
    private String CostCenter;
    private String approvalType;

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public BigDecimal getHospAmount() {
        return hospAmount;
    }

    public void setHospAmount(BigDecimal hospAmount) {
        this.hospAmount = hospAmount;
    }

    public BigDecimal getDefaultAmount() {
        return defaultAmount;
    }

    public void setDefaultAmount(BigDecimal defaultAmount) {
        this.defaultAmount = defaultAmount;
    }

    public String getCostCenter() {
        return CostCenter;
    }

    public void setCostCenter(String costCenter) {
        CostCenter = costCenter;
    }

    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }
}
