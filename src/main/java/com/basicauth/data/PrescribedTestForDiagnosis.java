package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by service.incuventure on 4/18/2017.
 */
@Entity
@Table(name = "Test_Record")
public class PrescribedTestForDiagnosis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "id")
    private long id;

    @JsonProperty("diagnosisForConsultId")
    @Column(name = "Diagnosis_For_Consult_ID")
    private int diagnosisForConsultId;

    @JsonProperty("procedureCode")
    @Column(name = "Procedure_Code")
    private String procedureCode;

    @JsonProperty("testCode")
    @Column(name = "Test_Code")
    private String testCode;

    @JsonProperty("type")
    @Column(name = "type")
    private String type;

    @JsonProperty("costCenter")
    @Column(name = "Cost_Center")
    private String costCenter;

    @JsonProperty("procedureType")
    @Column(name = "Procedure_Type")
    private String procedureType;

    @JsonProperty("requestedFlag")
    @Column(name = "Requested_Flag")
    private String requestedFlag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDiagnosisForConsultId() {
        return diagnosisForConsultId;
    }

    public void setDiagnosisForConsultId(int diagnosisForConsultId) {
        this.diagnosisForConsultId = diagnosisForConsultId;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getProcedureType() {
        return procedureType;
    }

    public void setProcedureType(String procedureType) {
        this.procedureType = procedureType;
    }

    public String getRequestedFlag() {
        return requestedFlag;
    }

    public void setRequestedFlag(String requestedFlag) {
        this.requestedFlag = requestedFlag;
    }
}
