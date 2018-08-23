package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by IPC_Server on 5/18/2017.
 */
public class BasicOrOtherTestResponseJson implements Serializable {

    @JsonProperty("maceRequest")
    private MaceRequest maceRequest;
    @JsonProperty("icd10Code")
    private String icd10Code;
    @JsonProperty("totalAmount")
    private BigDecimal totalAmount;
    //Basic/OtherTest
    @JsonProperty("diagnosisProcedures")
    private DiagnosisProcedureJson[] diagnosisProcedures;
    //Procedure
    @JsonProperty("diagnosisClinicProcedures")
    private DiagnosisClinicProcedureJson[] diagnosisClinicProcedures;

    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    public MaceRequest getMaceRequest() {
        return maceRequest;
    }

    public void setMaceRequest(MaceRequest maceRequest) {
        this.maceRequest = maceRequest;
    }

    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public DiagnosisProcedureJson[] getDiagnosisProcedures() {
        return diagnosisProcedures;
    }

    public void setDiagnosisProcedures(DiagnosisProcedureJson[] diagnosisProcedures) {
        this.diagnosisProcedures = diagnosisProcedures;
    }

    public DiagnosisClinicProcedureJson[] getDiagnosisClinicProcedures() {
        return diagnosisClinicProcedures;
    }

    public void setDiagnosisClinicProcedures(DiagnosisClinicProcedureJson[] diagnosisClinicProcedures) {
        this.diagnosisClinicProcedures = diagnosisClinicProcedures;
    }
    //</editor-fold>

    public static class DiagnosisProcedureJson {

        @JsonProperty("maceRequestOpDiag")
        private MaceRequestOpDiag maceRequestOpDiag;

        @JsonProperty("maceRequestTest")
        private MaceRequestTest maceRequestTest;

        @JsonProperty("maceRequestOpTest")
        private MaceRequestOpTest maceRequestOpTest;

        @JsonProperty("approvalNo")
        private String approvalNo;

        @JsonProperty("procedureCode")
        private String procedureCode;

        @JsonProperty("diagnosisCode")
        private String diagnosisCode;

        @JsonProperty("amount")
        private Double amount;

        @JsonProperty("status")
        private Integer status;

        @JsonProperty("remarks")
        private String remarks;

        //<editor-fold desc="DiagnosisProcedureJson Getters and Setters" defaultstate="collapsed">
        public MaceRequestOpDiag getMaceRequestOpDiag() {
            return maceRequestOpDiag;
        }

        public void setMaceRequestOpDiag(MaceRequestOpDiag maceRequestOpDiag) {
            this.maceRequestOpDiag = maceRequestOpDiag;
        }

        public MaceRequestTest getMaceRequestTest() {
            return maceRequestTest;
        }

        public void setMaceRequestTest(MaceRequestTest maceRequestTest) {
            this.maceRequestTest = maceRequestTest;
        }

        public MaceRequestOpTest getMaceRequestOpTest() {
            return maceRequestOpTest;
        }

        public void setMaceRequestOpTest(MaceRequestOpTest maceRequestOpTest) {
            this.maceRequestOpTest = maceRequestOpTest;
        }

        public String getApprovalNo() {
            return approvalNo;
        }

        public void setApprovalNo(String approvalNo) {
            this.approvalNo = approvalNo;
        }

        public String getProcedureCode() {
            return procedureCode;
        }

        public void setProcedureCode(String procedureCode) {
            this.procedureCode = procedureCode;
        }

        public String getDiagnosisCode() {
            return diagnosisCode;
        }

        public void setDiagnosisCode(String diagnosisCode) {
            this.diagnosisCode = diagnosisCode;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        //</editor-fold>
    }

    public static class DiagnosisClinicProcedureJson{

        @JsonProperty("maceRequestOpDiag")
        private MaceRequestOpDiag maceRequestOpDiag;

        @JsonProperty("maceRequestProcedure")
        private MaceRequestProcedure maceRequestProcedure;

        @JsonProperty("maceRequestOpProcedure")
        private MaceRequestOpProcedure maceRequestOpProcedure;

        @JsonProperty("approvalNo")
        private String approvalNo;

        @JsonProperty("procedureCode")
        private String procedureCode;

        @JsonProperty("diagnosisCode")
        private String diagnosisCode;

        @JsonProperty("amount")
        private Double amount;

        @JsonProperty("status")
        private Integer status;

        @JsonProperty("remarks")
        private String remarks;

        public MaceRequestOpDiag getMaceRequestOpDiag() {
            return maceRequestOpDiag;
        }

        public void setMaceRequestOpDiag(MaceRequestOpDiag maceRequestOpDiag) {
            this.maceRequestOpDiag = maceRequestOpDiag;
        }

        public MaceRequestProcedure getMaceRequestProcedure() {
            return maceRequestProcedure;
        }

        public void setMaceRequestProcedure(MaceRequestProcedure maceRequestProcedure) {
            this.maceRequestProcedure = maceRequestProcedure;
        }

        public MaceRequestOpProcedure getMaceRequestOpProcedure() {
            return maceRequestOpProcedure;
        }

        public void setMaceRequestOpProcedure(MaceRequestOpProcedure maceRequestOpProcedure) {
            this.maceRequestOpProcedure = maceRequestOpProcedure;
        }

        public String getApprovalNo() {
            return approvalNo;
        }

        public void setApprovalNo(String approvalNo) {
            this.approvalNo = approvalNo;
        }

        public String getProcedureCode() {
            return procedureCode;
        }

        public void setProcedureCode(String procedureCode) {
            this.procedureCode = procedureCode;
        }

        public String getDiagnosisCode() {
            return diagnosisCode;
        }

        public void setDiagnosisCode(String diagnosisCode) {
            this.diagnosisCode = diagnosisCode;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
