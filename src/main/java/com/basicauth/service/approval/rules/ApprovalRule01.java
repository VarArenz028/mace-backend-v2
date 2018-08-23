package com.basicauth.service.approval.rules;

import com.basicauth.data.MemberDetails;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.basicauth.config.Constants.*;

/**
 * If member's status is ACTIVE, proceed with approval routing.
 * Else, disapprove request or route request for approval based on member's status.
 */

@Service("approvalRule01")
public class ApprovalRule01 implements ApprovalRule {

    @Autowired
    private MessageSource messageSource;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        MemberDetails memberDetails = approvalRuleParameter.getMemberDetails();
        String reasonMessage = messageSource.getMessage(this.getClass().getSimpleName(),
                new String[] {memberDetails.getMem_OStat_Code()}, Locale.getDefault());

        switch (memberDetails.getMem_OStat_Code().toUpperCase()) {
            case "MEDICAL EVALUATION":
            case "ON HOLD":
            case "FOR APPROVAL":
            case "PENDING (E-MEDICARD)":
                result.setRequestStatus(REQUEST_MANUAL);
                result.setStatusAssignee(HSC_GROUP);
                result.setResultMessage(reasonMessage);
                break;
            case "LAPSE (NON RENEW)":
            case "FOR REACTIVATION":
            case "VERIFY MEMBERSHIP":
                result.setRequestStatus(REQUEST_MANUAL);
                result.setStatusAssignee(URG_GROUP);
                result.setResultMessage(reasonMessage);
                break;
            case "VERIFY PAYMENT WITH RMD":
                result.setRequestStatus(REQUEST_MANUAL);
                result.setStatusAssignee(RMD_GROUP);
                result.setResultMessage(reasonMessage);
                break;
            case "VERIFY RENEWAL FROM MARKETING / SALES":
                result.setRequestStatus(REQUEST_MANUAL);
                result.setStatusAssignee(SBD_GROUP);
                result.setResultMessage(reasonMessage);
                break;
            case "FOR ENCODING":
            case "DISAPPROVED":
            case "RESIGNED":
            case "CANCELLED":
                result.setRequestStatus(REQUEST_AUTOMATIC_DISAPPROVED);
                reasonMessage = messageSource.getMessage(this.getClass().getSimpleName(),
                        new String[] {"Not Active"}, Locale.getDefault());
                result.setResultMessage(reasonMessage);
                break;
            case "ACTIVE":
                result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
                break;
            default:
                result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
                break;
        }

        return result;
    }
}
