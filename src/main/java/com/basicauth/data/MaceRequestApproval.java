package com.basicauth.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "mace_request_approvals")
public class MaceRequestApproval implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "APPROVAL_RULE_CLASS_NAME")
    private String approvalRuleClassName;

    @Column(name = "REQUEST_ID")
    private long requestId;

    @Column(name = "REQUEST_CODE")
    private String requestCode;

    @Column(name = "TRANSACTION_ID")
    private long transactionId;

    @Column(name = "TRANS_CODE")
    private String transCode;

    @Column(name = "PROCEDURE_CODE")
    private String procedureCode;

    @Column(name = "RUN_ON")
    private Date runOn;

    @Column(name = "RUN_BY")
    private String runBy;

    @Column(name = "RESULT_STATUS")
    private String resultStatus;

    @Column(name = "RESULT_ASSIGNEE")
    private String resultAssignee;

    @Column(name = "RESULT_REASON_MESSAGE")
    private String resultReasonMessage;

    public MaceRequestApproval(String approvalRuleClassName, Date runOn, String runBy, String resultStatus, String resultAssignee, String resultReasonMessage) {
        this.approvalRuleClassName = approvalRuleClassName;
        this.runOn = runOn;
        this.runBy = runBy;
        this.resultStatus = resultStatus;
        this.resultAssignee = resultAssignee;
        this.resultReasonMessage = resultReasonMessage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApprovalRuleClassName() {
        return approvalRuleClassName;
    }

    public void setApprovalRuleClassName(String approvalRuleClassName) {
        this.approvalRuleClassName = approvalRuleClassName;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public Date getRunOn() {
        return runOn;
    }

    public void setRunOn(Date runOn) {
        this.runOn = runOn;
    }

    public String getRunBy() {
        return runBy;
    }

    public void setRunBy(String runBy) {
        this.runBy = runBy;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getResultAssignee() {
        return resultAssignee;
    }

    public void setResultAssignee(String resultAssignee) {
        this.resultAssignee = resultAssignee;
    }

    public String getResultReasonMessage() {
        return resultReasonMessage;
    }

    public void setResultReasonMessage(String resultReasonMessage) {
        this.resultReasonMessage = resultReasonMessage;
    }
}
