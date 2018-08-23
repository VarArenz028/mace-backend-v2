package com.basicauth.data;

import java.io.Serializable;

/**
 * Created by angulo on 11/16/2016.
 */
public class DiagnosticProcedure implements Serializable {

    private String diagProcCode;
    private String diagProcDesc;
    private String diagProcGroupDesc;
    private String diagProcGroupCode;

    public String getDiagProcCode() {
        return diagProcCode;
    }

    public void setDiagProcCode(String diagProcCode) {
        this.diagProcCode = diagProcCode;
    }

    public String getDiagProcDesc() {
        return diagProcDesc;
    }

    public void setDiagProcDesc(String diagProcDesc) {
        this.diagProcDesc = diagProcDesc;
    }

    public String getDiagProcGroupDesc() {
        return diagProcGroupDesc;
    }

    public void setDiagProcGroupDesc(String diagProcGroupDesc) {
        this.diagProcGroupDesc = diagProcGroupDesc;
    }

    public String getDiagProcGroupCode() {
        return diagProcGroupCode;
    }

    public void setDiagProcGroupCode(String diagProcGroupCode) {
        this.diagProcGroupCode = diagProcGroupCode;
    }
}
