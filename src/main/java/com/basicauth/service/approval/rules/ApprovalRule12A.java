package com.basicauth.service.approval.rules;

import com.basicauth.data.MemberDetails;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_APPROVED;
import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_DISAPPROVED;

/**
 * If Member is not Female, disapprove request.
 * Apply this rule only to Maternity Consultation.
 */

@Service("approvalRule12A")
public class ApprovalRule12A implements ApprovalRule {

    @Autowired
    private MessageSource messageSource;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        MemberDetails memberDetails = approvalRuleParameter.getMemberDetails();

        if(memberDetails.getMEM_SEX() == 1){
            result.setRequestStatus(REQUEST_AUTOMATIC_DISAPPROVED);

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
