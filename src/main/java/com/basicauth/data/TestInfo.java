package com.basicauth.data;

import com.basicauth.domain.ImageHolder;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by service.incuventure on 3/27/2017.
 */
@Entity
@Table(name = "TEST_INFO")
public class TestInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "id")
    private long id;

    @JsonProperty("diagnosis")
    @Column(name="diagnosis")
    private String diagnosis;

    @JsonProperty("hospital")
    @Column(name="hospital")
    private String hospital;

    @JsonProperty("doctor")
    @Column(name="doctor")
    private String doctor;

    @JsonProperty("hospitalForAvail")
    @Column(name="hospitalForAvail")
    private String hospitalForAvail;

    @JsonProperty("prescribedTests")
    @Column(name="prescribedTests")
    private ArrayList<PrescribedTest> prescribedTests;

    @JsonProperty("attachments")
    @Column(name="attachments")
    private ArrayList<ImageHolder> attachments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHospitalForAvail() {
        return hospitalForAvail;
    }

    public void setHospitalForAvail(String hospitalForAvail) {
        this.hospitalForAvail = hospitalForAvail;
    }

    public ArrayList<PrescribedTest> getPrescribedTests() {
        return prescribedTests;
    }

    public void setTests(ArrayList<PrescribedTest> prescribedTests) {
        this.prescribedTests = prescribedTests;
    }

    public void setPrescribedTests(ArrayList<PrescribedTest> prescribedTests) {
        this.prescribedTests = prescribedTests;
    }

    public ArrayList<ImageHolder> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<ImageHolder> attachments) {
        this.attachments = attachments;
    }
}
