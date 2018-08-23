package com.basicauth.domain;

import java.io.Serializable;

/**
 * Created by Jabito on 29/06/2017.
 */
public class AddPrescribedTestJson implements Serializable{

    private String hospitalCode;
    private String doctorCode;
    private String primaryDiagnosisCode;
    private String[] primaryDiagProcedures;
    private String otherDiagnosisCode;
    private String[] otherDiagProcedures;
    private String requestCode;

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

    public String getPrimaryDiagnosisCode() {
        return primaryDiagnosisCode;
    }

    public void setPrimaryDiagnosisCode(String primaryDiagnosisCode) {
        this.primaryDiagnosisCode = primaryDiagnosisCode;
    }

    public String[] getPrimaryDiagProcedures() {
        return primaryDiagProcedures;
    }

    public void setPrimaryDiagProcedures(String[] primaryDiagProcedures) {
        this.primaryDiagProcedures = primaryDiagProcedures;
    }

    public String getOtherDiagnosisCode() {
        return otherDiagnosisCode;
    }

    public void setOtherDiagnosisCode(String otherDiagnosisCode) {
        this.otherDiagnosisCode = otherDiagnosisCode;
    }

    public String[] getOtherDiagProcedures() {
        return otherDiagProcedures;
    }

    public void setOtherDiagProcedures(String[] otherDiagProcedures) {
        this.otherDiagProcedures = otherDiagProcedures;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }
}
