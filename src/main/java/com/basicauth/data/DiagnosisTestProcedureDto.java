package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by angulo on 3/9/2017.
 */
public class DiagnosisTestProcedureDto implements Serializable {


    @JsonProperty("id")
    private int id;

    @JsonProperty("consultation_record_id")
    private int consultationRecordId;
    
    @JsonProperty(value = "diagnosisCode")
    private String diagnosisCode;

    @JsonProperty(value = "prescribedTestOrProceduresForPrimaryDiagnosisList")
    private List<String> prescribedTestOrProcedureCodeList;

    public DiagnosisTestProcedureDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConsultationRecordId() {
        return consultationRecordId;
    }

    public void setConsultationRecordId(int consultationRecordId) {
        this.consultationRecordId = consultationRecordId;
    }

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public List<String> getPrescribedTestOrProcedureCodeList() {
        return prescribedTestOrProcedureCodeList;
    }

    public void setPrescribedTestOrProcedureCodeList(List<String> prescribedTestOrProcedureCodeList) {
        this.prescribedTestOrProcedureCodeList = prescribedTestOrProcedureCodeList;
    }
}
