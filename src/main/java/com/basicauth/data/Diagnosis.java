package com.basicauth.data;

import java.io.Serializable;

/**
 * Created by angulo on 11/16/2016.
 */
public class Diagnosis implements Serializable{

    /**
     *
     DIAG_CODE,
     DIAG_DESC,
     DIAG_REMARKS,
     TYPE,
     TYPE_DESC,
     ICD10_CODE,
     ICD10_DESC,
     STATUS
     */

    private String diagCode;
    private String diagDesc;
    private String diagRemarks;
    private String groupDesc;
    private String type;
    private String typeOld;
    private String typeDesc;
    private String icd10Code;
    private String icd10Desc;
    private String icd104c;
    private String status;

    public String getDiagCode() {
        return diagCode;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    public String getDiagDesc() {
        return diagDesc;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
    }

    public String getDiagRemarks() {
        return diagRemarks;
    }

    public void setDiagRemarks(String diagRemarks) {
        this.diagRemarks = diagRemarks;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeOld() {
        return typeOld;
    }

    public void setTypeOld(String typeOld) {
        this.typeOld = typeOld;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getIcd10Desc() {
        return icd10Desc;
    }

    public void setIcd10Desc(String icd10Desc) {
        this.icd10Desc = icd10Desc;
    }

    public String getIcd104c() {
        return icd104c;
    }

    public void setIcd104c(String icd104c) {
        this.icd104c = icd104c;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
