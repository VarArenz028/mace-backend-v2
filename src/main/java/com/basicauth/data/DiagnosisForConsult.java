package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by service.incuventure on 4/18/2017.
 */
@Entity
@Table(name = "Test_Record")
public class DiagnosisForConsult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "id")
    private long id;

    @JsonProperty("type")
    @Column(name="type")
    private String type;

    @JsonProperty("loaMaceRequestId")
    @Column(name = "LOA_MACE_REQUEST_ID")
    private int loaMaceRequestId;

    @JsonProperty("diagnosisCode")
    @Column(name = "Diagnosis_Code")
    private String diagnosisCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLoaMaceRequestId() {
        return loaMaceRequestId;
    }

    public void setLoaMaceRequestId(int loaMaceRequestId) {
        this.loaMaceRequestId = loaMaceRequestId;
    }

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }
}
