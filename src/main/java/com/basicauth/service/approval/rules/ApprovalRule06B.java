package com.basicauth.service.approval.rules;

import com.basicauth.data.MemberDetails;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.stereotype.Service;

import static com.basicauth.config.Constants.*;

/**
 * If Member is under the RxEr program, route request for approval.
 */

@Service("approvalRule06B")
public class ApprovalRule06B implements ApprovalRule {

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        MemberDetails memberDetails = approvalRuleParameter.getMemberDetails();

        //Check Account Type
        //20170927 Checking of Rxer moved to ApprovalRule04
        /*if(memberDetails.getACCT_TYPE().equalsIgnoreCase("RxEr")){
            result.setRequestStatus(REQUEST_MANUAL);
            result.setStatusAssignee(HSC_GROUP);
        }
        else{
            result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
        }*/

        return result;
    }
}
