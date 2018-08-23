package com.basicauth.service.approval;

import static com.basicauth.config.Constants.DEFAULT_GROUP;
import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_APPROVED;

/**
 * Return object of an Approval Rule.
 * Contains result information regarding execution of an Approval Rule
 * (e.g. Request Status, Status Assignee, result message to indicate reason why a request was routed or disapproved)
 */

public class ApprovalRuleResult {

    private Integer resultCode;
    private String resultMessage;

    private String requestStatus;
    private String statusAssignee;

    //Set default values here.
    public ApprovalRuleResult(){
        this.statusAssignee = DEFAULT_GROUP;
        this.requestStatus = REQUEST_AUTOMATIC_APPROVED;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getStatusAssignee() {
        return statusAssignee;
    }

    public void setStatusAssignee(String statusAssignee) {
        this.statusAssignee = statusAssignee;
    }
}
