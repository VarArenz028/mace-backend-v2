package com.basicauth.service.approval.rules;

import com.basicauth.data.MemberDetails;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.basicauth.config.Constants.HSC_GROUP;
import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_APPROVED;
import static com.basicauth.config.Constants.REQUEST_MANUAL;

/**
 * If Member is under a Corporate program, route request for approval.
 */

@Service("approvalRule06C")
public class ApprovalRule06C implements ApprovalRule {

    @Autowired
    private MessageSource messageSource;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        MemberDetails memberDetails = approvalRuleParameter.getMemberDetails();

        //Check Account Type
        if(memberDetails.getACCT_TYPE().equalsIgnoreCase("Corporate")){
            //20170921 Handling for specific plans to follow
            result.setRequestStatus(REQUEST_MANUAL);
            result.setStatusAssignee(HSC_GROUP);

            String reasonMessage = messageSource.getMessage(this.getClass().getSimpleName(),
                    null, Locale.getDefault());
            result.setResultMessage(reasonMessage);
        }
        else{
            result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
        }

        return result;
    }
}
