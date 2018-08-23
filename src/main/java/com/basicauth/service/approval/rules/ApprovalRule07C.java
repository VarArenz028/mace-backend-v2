package com.basicauth.service.approval.rules;

import com.basicauth.domain.TestProcObject;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_APPROVED;

/**
 * If approval type of Test Procedure is "Auto", approve request.
 */

@Service("approvalRule07C")
public class ApprovalRule07C implements ApprovalRule {

    @Autowired
    private MessageSource messageSource;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        TestProcObject testProcObject = approvalRuleParameter.getTestProcObject();

        if(testProcObject.getApprovalType().equalsIgnoreCase("AUTO")){
            result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
        }
        else{
            result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
        }

        return result;
    }
}
