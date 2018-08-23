package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by IPC_Server on 5/10/2017.
 */
public class MaceRequestResponseJson implements Serializable {

    @JsonProperty("maceRequestId")
    private long maceRequestId;

    @JsonProperty("memCode")
    private String memCode;

    @JsonProperty("status")
    private String status;

    @JsonProperty("procedures")
    private MaceRequestProcedureResponseJson[] procedures;

    public long getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(long maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public String getMemCode() {
        return memCode;
    }

    public void setMemCode(String memCode) {
        this.memCode = memCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MaceRequestProcedureResponseJson[] getProcedures() {
        return procedures;
    }

    public void setProcedures(MaceRequestProcedureResponseJson[] procedures) {
        this.procedures = procedures;
    }


    public static class MaceRequestProcedureResponseJson {

        @JsonProperty("diagProcId")
        private long diagProcId;

        @JsonProperty("reqDiagId")
        private int reqDiagId;

        @JsonProperty("transactionId")
        private long transactionId;

        @JsonProperty("diagCode")
        private String diagCode;

        @JsonProperty("procCode")
        private String procCode;

        @JsonProperty("procActualAmount")
        private BigDecimal procActualAmount;

        @JsonProperty("status")
        private int status;

        @JsonProperty("validFrom")
        private String validFromDateString;

        @JsonProperty("validTo")
        private String validToDateString;

        public long getDiagProcId() {
            return diagProcId;
        }

        public void setDiagProcId(long diagProcId) {
            this.diagProcId = diagProcId;
        }

        public int getReqDiagId() {
            return reqDiagId;
        }

        public void setReqDiagId(int reqDiagId) {
            this.reqDiagId = reqDiagId;
        }

        public long getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(long transactionId) {
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

        public void setProcCode(String procCode) {
            this.procCode = procCode;
        }

        public BigDecimal getProcActualAmount() {
            return procActualAmount;
        }

        public void setProcActualAmount(BigDecimal procActualAmount) {
            this.procActualAmount = procActualAmount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getValidFromDateString() {
            return validFromDateString;
        }

        public void setValidFromDateString(String validFromDateString) {
            this.validFromDateString = validFromDateString;
        }

        public String getValidToDateString() {
            return validToDateString;
        }

        public void setValidToDateString(String validToDateString) {
            this.validToDateString = validToDateString;
        }
    }
}
