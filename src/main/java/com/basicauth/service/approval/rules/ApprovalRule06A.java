package com.basicauth.service.approval.rules;

import com.basicauth.data.MemberDetails;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.stereotype.Service;

import static com.basicauth.config.Constants.*;

/**
 * If Member is under the Kabayan program, approve request.
 * 20170921 This rule effectively does nothing because APPROVED is the default status in the V2 Approval Engine.
 */

@Service("approvalRule06A")
public class ApprovalRule06A implements ApprovalRule {

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        MemberDetails memberDetails = approvalRuleParameter.getMemberDetails();

        //Check Account Type
        //20170927 Checking of Kabayan moved to ApprovalRule04.
        /*if(memberDetails.getACCT_TYPE().equalsIgnoreCase("Kabayan")){
            result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
        }
        else{
            result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
        }*/

        return result;
    }
}
