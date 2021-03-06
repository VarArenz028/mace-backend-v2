package com.basicauth.service.approval.rules;

import com.basicauth.domain.TestProcObject;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import static com.basicauth.config.Constants.*;

/**
 * If approval type of Test Procedure is "Manual", route request for approval.
 * Only use this approval rule for Test requests.
 */

@Service("approvalRule07B")
public class ApprovalRule07B implements ApprovalRule {

    @Autowired
    private MessageSource messageSource;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        TestProcObject testProcObject = approvalRuleParameter.getTestProcObject();

        if(testProcObject.getApprovalType().equalsIgnoreCase("MANUAL")){
            result.setRequestStatus(REQUEST_MANUAL);
            result.setStatusAssignee(HSC_GROUP);

            //No reason message for 07B?
            /*String reasonMessage = messageSource.getMessage(this.getClass().getSimpleName(),
                    null, Locale.getDefault());
            result.setResultMessage(reasonMessage);*/
        }
        else{
            result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
        }

        return result;
    }
}
