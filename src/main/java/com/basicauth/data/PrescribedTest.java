package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jabito on 3/27/2017.
 */
@Entity
@Table(name = "PRESCRIBED_TEST")
public class PrescribedTest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "id")
    private long id;

    @JsonProperty("testCode")
    @Column(name="testCode")
    private String testCode;

    @JsonProperty("testName")
    @Column(name="testName")
    private String testName;

    @JsonProperty("amount")
    @Column(name="amount")
    private BigDecimal amount;

    @JsonProperty("costCenter")
    @Column(name="costCenter")
    private String costCenter;

    @JsonProperty("diagnosis")
    @Column(name="diagnosis")
    private String diagnosis;

    @JsonProperty("consultReferenceNo")
    @Column(name="consultReferenceNo")
    private String consultReferenceNo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getConsultReferenceNo() {
        return consultReferenceNo;
    }

    public void setConsultReferenceNo(String consultReferenceNo) {
        this.consultReferenceNo = consultReferenceNo;
    }
}
