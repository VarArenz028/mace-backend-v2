package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Jabito on 13/06/2017.
 */
public class UpdateInpatientJson implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "maceRequestCode", required = true)
    private String maceRequestCode;

    @JsonProperty(value = "username", required = true)
    private String username;

    @JsonProperty("roomType")
    private String roomType;

    @JsonProperty("deviceId")
    private String deviceId;

    @JsonProperty("roomNumber")
    private String roomNumber;

    @JsonProperty("roomPrice")
    private String roomPrice;

    @JsonProperty("category")
    private String category;

    @JsonProperty("dateAdmitted")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date dateAdmitted;

    @JsonProperty("searchType")
    private String searchType;

    @JsonProperty(value = "otherDoctorCodes")
    private List<String> doctorCodes;

    @JsonProperty("primaryDiag")
    private String primaryDiag;

    @JsonProperty("primaryDoctor")
    private String primaryDoctor;

    @JsonProperty(value = "otherDiagnosisCodes")
    private List<String> diagnosisCodes;

    @JsonProperty("procedureAndTestCodes")
    private CodeAndCost[] procedureAndTestCodes;

    @JsonProperty("serviceCodes")
    private List<String> serviceCodes;

    @JsonProperty("remarks")
    private String remarks;

    @JsonProperty("dischargeDate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date dischargeDate;

    public static class CodeAndCost{
        public CodeAndCost(){}
        @JsonProperty("procCode")
        public String procCode;
        @JsonProperty("amount")
        public BigDecimal amount;

        public String getProcCode() {
            return procCode;
        }

        public void setProcCode(String procCode) {
            this.procCode = procCode;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
    }

    public String getMaceRequestCode() {
        return maceRequestCode;
    }

    public void setMaceRequestCode(String maceRequestCode) {
        this.maceRequestCode = maceRequestCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(Date dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public List<String> getDoctorCodes() {
        return doctorCodes;
    }

    public void setDoctorCodes(List<String> doctorCodes) {
        this.doctorCodes = doctorCodes;
    }

    public List<String> getDiagnosisCodes() {
        return diagnosisCodes;
    }

    public void setDiagnosisCodes(List<String> diagnosisCodes) {
        this.diagnosisCodes = diagnosisCodes;
    }

    public CodeAndCost[] getProcedureAndTestCodes() {
        return procedureAndTestCodes;
    }

    public void setProcedureAndTestCodes(CodeAndCost[] procedureAndTestCodes) {
        this.procedureAndTestCodes = procedureAndTestCodes;
    }

    public List<String> getServiceCodes() {
        return serviceCodes;
    }

    public void setServiceCodes(List<String> serviceCodes) {
        this.serviceCodes = serviceCodes;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getPrimaryDiag() {
        return primaryDiag;
    }

    public void setPrimaryDiag(String primaryDiag) {
        this.primaryDiag = primaryDiag;
    }

    public String getPrimaryDoctor() {
        return primaryDoctor;
    }

    public void setPrimaryDoctor(String primaryDoctor) {
        this.primaryDoctor = primaryDoctor;
    }
}
