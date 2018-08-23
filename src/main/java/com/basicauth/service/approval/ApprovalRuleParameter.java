package com.basicauth.service.approval;

import com.basicauth.data.Diagnosis;
import com.basicauth.data.Doctor;
import com.basicauth.data.Hospital;
import com.basicauth.data.MemberDetails;
import com.basicauth.domain.TestProcObject;

/**
 * Parameter object for Approval Engine.
 * Contains all the data entities needed for the various approval rules.
 * Request information, member details, diagnosis details, hospital details, and total costs
 */
public class ApprovalRuleParameter {

    private TestProcObject testProcObject;
    private MemberDetails memberDetails;
    private Diagnosis diagnosis;
    private Hospital hospital;
    private Doctor doctor;
    private Double totalCosts;

    public ApprovalRuleParameter(){}

    public ApprovalRuleParameter(MemberDetails memberDetails,
                                 Hospital hospital){

        this.memberDetails = memberDetails;
        this.hospital = hospital;
    }

    public ApprovalRuleParameter(MemberDetails memberDetails,
                                 Diagnosis diagnosis,
                                 Hospital hospital,
                                 Doctor doctor,
                                 TestProcObject testProcObject,
                                 Double totalCosts){

        this.memberDetails = memberDetails;
        this.diagnosis = diagnosis;
        this.hospital = hospital;
        this.doctor = doctor;

        this.testProcObject = testProcObject;
        this.totalCosts = totalCosts;
    }

    public TestProcObject getTestProcObject() {
        return testProcObject;
    }

    public void setTestProcObject(TestProcObject testProcObject) {
        this.testProcObject = testProcObject;
    }

    public MemberDetails getMemberDetails() {
        return memberDetails;
    }

    public void setMemberDetails(MemberDetails memberDetails) {
        this.memberDetails = memberDetails;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Double getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(Double totalCosts) {
        this.totalCosts = totalCosts;
    }
}
