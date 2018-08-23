package com.basicauth.service.approval;

import com.basicauth.config.Constants;
import com.basicauth.data.MaceRequestApproval;

import java.util.ArrayList;
import java.util.List;

/**
 * Result object for the execution of the approval engine based on the results of multiple approval rules.
 * Contains final request status and status assignee to be used, as well as a collection of reason messages based on
 * failed approval rules.
 */
public class ApprovalEngineResult {

    private List<String> reasonMessages;

    private String reasonMessagesString;

    private String finalRequestStatus;

    private String finalStatusAssignee;

    private List<MaceRequestApproval> maceRequestApprovals;

    public ApprovalEngineResult(){
        this.reasonMessages = new ArrayList<>();
        this.reasonMessagesString = "";
        //Default final status is DRAFT until Approval Engine is executed
        this.finalRequestStatus = Constants.REQUEST_DRAFT;
        this.finalStatusAssignee = Constants.DEFAULT_GROUP;
        this.maceRequestApprovals = new ArrayList<>();
    }

    public List<String> getReasonMessages() {
        return reasonMessages;
    }

    public void setReasonMessages(List<String> reasonMessages) {
        this.reasonMessages = reasonMessages;
    }

    public String getReasonMessagesString() {
        return reasonMessagesString;
    }

    public void setReasonMessagesString(String reasonMessagesString) {
        this.reasonMessagesString = reasonMessagesString;
    }

    public String getFinalRequestStatus() {
        return finalRequestStatus;
    }

    public void setFinalRequestStatus(String finalRequestStatus) {
        this.finalRequestStatus = finalRequestStatus;
    }

    public String getFinalStatusAssignee() {
        return finalStatusAssignee;
    }

    public void setFinalStatusAssignee(String finalStatusAssignee) {
        this.finalStatusAssignee = finalStatusAssignee;
    }

    public List<MaceRequestApproval> getMaceRequestApprovals() {
        return maceRequestApprovals;
    }

    public void setMaceRequestApprovals(List<MaceRequestApproval> maceRequestApprovals) {
        this.maceRequestApprovals = maceRequestApprovals;
    }
}
