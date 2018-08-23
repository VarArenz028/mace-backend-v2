package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class RequestDentalJson implements Serializable {

    @JsonProperty("memberCode")
    private String memberCode;
    @JsonProperty("requestThrough")
    private String requestThrough;
    @JsonProperty("requestFrom")
    private String requestFrom;
    @JsonProperty("requestBy")
    private String requestBy;
    @JsonProperty("requestDateTime")
    private String requestDateTime;
    @JsonProperty("totalUtilization")
    private BigDecimal totalUtilization;
    @JsonProperty("clinicCode")
    private String clinicCode;
    @JsonProperty("dentistCode")
    private String dentistCode;
    @JsonProperty("dentalProcedures")
    private List<DentalProcedures> dentalProcedures;
    @JsonProperty("otherInformation")
    private List<OtherInformation> otherInformation;
    @JsonProperty("appUsername")
    private String appUsername;
    @JsonProperty("notes")
    private String notes;

    public static class DentalProcedures implements Serializable{
        @JsonProperty("diagnosisId")
        private int diagnosisId;
        @JsonProperty("procedureId")
        private int procedureId;
        @JsonProperty("tooth")
        private int tooth;
        @JsonProperty("surfaceNo")
        private int surfaceNo;
        @JsonProperty("totalAmount")
        private BigDecimal totalAmount;

        //<editor-fold desc="Getters and Setters" defaultstate="collapsed">


        public int getDiagnosisId() {
            return diagnosisId;
        }

        public void setDiagnosisId(int diagnosisId) {
            this.diagnosisId = diagnosisId;
        }

        public int getProcedureId() {
            return procedureId;
        }

        public void setProcedureId(int procedureId) {
            this.procedureId = procedureId;
        }

        public int getTooth() {
            return tooth;
        }

        public void setTooth(int tooth) {
            this.tooth = tooth;
        }

        public int getSurfaceNo() {
            return surfaceNo;
        }

        public void setSurfaceNo(int surfaceNo) {
            this.surfaceNo = surfaceNo;
        }

        public BigDecimal getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
        }

        //</editor-fold>
    }

    public static class OtherInformation implements Serializable{
        @JsonProperty("infoType")
        private String infoType;
        @JsonProperty("details")
        private String details;

        //<editor-fold desc="Getters and Setters" defaultstate="collapsed">

        public String getInfoType() {
            return infoType;
        }

        public void setInfoType(String infoType) {
            this.infoType = infoType;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        //</editor-fold>
    }
    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getRequestThrough() {
        return requestThrough;
    }

    public void setRequestThrough(String requestThrough) {
        this.requestThrough = requestThrough;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public String getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(String requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public BigDecimal getTotalUtilization() {
        return totalUtilization;
    }

    public void setTotalUtilization(BigDecimal totalUtilization) {
        this.totalUtilization = totalUtilization;
    }

    public String getClinicCode() {
        return clinicCode;
    }

    public void setClinicCode(String clinicCode) {
        this.clinicCode = clinicCode;
    }

    public String getDentistCode() {
        return dentistCode;
    }

    public void setDentistCode(String dentistCode) {
        this.dentistCode = dentistCode;
    }

    public List<DentalProcedures> getDentalProcedures() {
        return dentalProcedures;
    }

    public void setDentalProcedures(List<DentalProcedures> dentalProcedures) {
        this.dentalProcedures = dentalProcedures;
    }

    public List<OtherInformation> getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(List<OtherInformation> otherInformation) {
        this.otherInformation = otherInformation;
    }

    public String getAppUsername() {
        return appUsername;
    }

    public void setAppUsername(String appUsername) {
        this.appUsername = appUsername;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

//</editor-fold>
}
