package com.basicauth.types;

import com.basicauth.data.OutpatientRequestJson;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.DefaultProperty;
import org.hibernate.annotations.ParamDef;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Giancarlo Angulo.
 */
public class ConsultJson implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("memberCode")
    private String memberCode;

    @JsonProperty("doctorCode")
    private String doctorCode;

    @JsonProperty("username")
    private String username;

    @JsonProperty("deviceID")
    private String deviceId;

    @JsonProperty("hospitalCode")
    private String hospitalCode;

    @JsonProperty("diagnosisCode")
    private String diagnosisCode;

    @JsonProperty("procedureCode")
    private String procedureCode;

    @JsonProperty("procedureAmount")
    private BigDecimal procedureAmount;

    @JsonProperty("locationCode")
    private String locationCode;

    @JsonProperty("procedureDesc")
    private String procedureDesc;

    @JsonProperty("diagnosisDesc")
    private String diagnosisDesc;

    @JsonProperty("primaryComplaint")
    private String primaryComplaint;

    @JsonProperty("searchType")
    private String searchType;

    @JsonProperty("consultSubtype")
    private int consultSubtype;

    @JsonProperty("duplicateTag")
    private String duplicateTag;

    @JsonProperty("coorContact")
    private String coorContact;

    @JsonProperty("coorEmail")
    private String coorEmail;

    @JsonProperty("requestOrigin")
    private String requestOrigin;

    @JsonProperty("requestBy")
    private String requestBy;

    @JsonProperty("submitForApproval")
    private Boolean submitForApproval;

    public ConsultJson() {
    }

    public ConsultJson(OutpatientRequestJson outpatientRequestJson) {
        this.memberCode = outpatientRequestJson.getMemberCode();
        this.doctorCode = outpatientRequestJson.getDoctorCode();
        this.username = outpatientRequestJson.getAppUsername();
        this.hospitalCode = outpatientRequestJson.getHospitalCode();
        this.consultSubtype = outpatientRequestJson.getServiceSubtype();

        //Temporary setting of diagnosis details
        this.diagnosisCode = outpatientRequestJson.getDiagnosisProcedures().length > 0 ? outpatientRequestJson.getDiagnosisProcedures()[0].getDiagnosisCode() : null;
        this.procedureCode = outpatientRequestJson.getDiagnosisProcedures().length > 0 ? outpatientRequestJson.getDiagnosisProcedures()[0].getProcedureCode() : null;
        this.procedureAmount = outpatientRequestJson.getDiagnosisProcedures().length > 0 ? BigDecimal.valueOf(outpatientRequestJson.getDiagnosisProcedures()[0].getAmount()) : null;

        this.coorContact = outpatientRequestJson.getCoorContact();
        this.coorEmail = outpatientRequestJson.getCoorEmail();
        this.requestOrigin = outpatientRequestJson.getRequestOrigin();
        this.requestBy = outpatientRequestJson.getRequestBy();
        this.locationCode = null;
        this.primaryComplaint = null;
        this.submitForApproval = outpatientRequestJson.getSubmitForApproval();
    }

    public String getDuplicateTag() {
        return duplicateTag;
    }

    public void setDuplicateTag(String duplicateTag) {
        this.duplicateTag = duplicateTag;
    }

    public int getConsultSubtype() {
        return consultSubtype;
    }

    public void setConsultSubtype(int consultSubtype) {
        this.consultSubtype = consultSubtype;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public BigDecimal getProcedureAmount() {
        return procedureAmount;
    }

    public void setProcedureAmount(BigDecimal procedureAmount) {
        this.procedureAmount = procedureAmount;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getProcedureDesc() {
        return procedureDesc;
    }

    public void setProcedureDesc(String procedureDesc) {
        this.procedureDesc = procedureDesc;
    }

    public String getDiagnosisDesc() {
        return diagnosisDesc;
    }

    public void setDiagnosisDesc(String diagnosisDesc) {
        this.diagnosisDesc = diagnosisDesc;
    }

    public String getPrimaryComplaint() {
        return primaryComplaint;
    }

    public void setPrimaryComplaint(String primaryComplaint) {
        this.primaryComplaint = primaryComplaint;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getCoorContact() {
        return coorContact;
    }

    public void setCoorContact(String coorContact) {
        this.coorContact = coorContact;
    }

    public String getCoorEmail() {
        return coorEmail;
    }

    public void setCoorEmail(String coorEmail) {
        this.coorEmail = coorEmail;
    }

    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public Boolean getSubmitForApproval() {
        return submitForApproval;
    }

    public void setSubmitForApproval(Boolean submitForApproval) {
        this.submitForApproval = submitForApproval;
    }
}
