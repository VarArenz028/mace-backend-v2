package com.basicauth.data;

import java.io.Serializable;

/**
 * Created by Rall Llobrera on 20/07/2017.
 */
public class SpecialDiagnosis implements Serializable {

    private Long id;
    private String diagCode;
    private String diagDesc;
    private Long approvalRuleId;
    private String approvalRuleClassName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getApprovalRuleId() {
        return approvalRuleId;
    }

    public void setApprovalRuleId(Long approvalRuleId) {
        this.approvalRuleId = approvalRuleId;
    }

    public String getApprovalRuleClassName() {
        return approvalRuleClassName;
    }

    public void setApprovalRuleClassName(String approvalRuleClassName) {
        this.approvalRuleClassName = approvalRuleClassName;
    }
}
