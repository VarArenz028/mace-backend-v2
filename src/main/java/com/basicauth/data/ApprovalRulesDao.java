package com.basicauth.data;

import java.io.Serializable;

/**
 * Created by Rall Llobrera on 20/07/2017.
 */
public class ApprovalRulesDao implements Serializable {

    private String approvalRuleName;

    private String javaClassName;

    private String description;

    public String getApprovalRuleName() {
        return approvalRuleName;
    }

    public void setApprovalRuleName(String approvalRuleName) {
        this.approvalRuleName = approvalRuleName;
    }

    public String getJavaClassName() {
        return javaClassName;
    }

    public void setJavaClassName(String javaClassName) {
        this.javaClassName = javaClassName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
