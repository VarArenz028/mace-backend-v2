package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by service.incuventure on 4/18/2017.
 */
@Entity
@Table(name = "Test_Record")
public class TestRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "id")
    private long id;

    @JsonProperty("type")
    @Column(name="type")
    private String type;

    @JsonProperty("referenceNo")
    @Column(name = "Reference_No")
    private String referenceNo;

    @JsonProperty("hospitalCode")
    @Column(name="Hospital_Code")
    private String hospitalCode;

    @JsonProperty("doctorCode")
    @Column(name="Doctor_Code")
    private String doctorCode;

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

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }
}
