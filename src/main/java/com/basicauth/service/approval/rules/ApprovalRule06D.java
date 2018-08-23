package com.basicauth.service.approval.rules;

import com.basicauth.data.MemberDetails;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.stereotype.Service;

import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_APPROVED;

/**
 * If Member is under an Individual program, approve request.
 */

@Service("approvalRule06D")
public class ApprovalRule06D implements ApprovalRule {

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        MemberDetails memberDetails = approvalRuleParameter.getMemberDetails();

        //Check Account Type
        if(memberDetails.getACCT_TYPE().equalsIgnoreCase("Individual")){
            result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
        }
        else{
            result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
        }

        return result;
    }
}
