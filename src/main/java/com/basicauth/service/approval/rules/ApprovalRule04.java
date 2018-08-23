package com.basicauth.service.approval.rules;

import com.basicauth.data.MemberDetails;
import com.basicauth.data.SpecialAccount;
import com.basicauth.service.MaceService;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_APPROVED;
import static com.basicauth.config.Constants.REQUEST_MANUAL;

/**
 * If member is under a special account (e.g. member works for MBAS or is under the Kabayan program), route request for approval.
 */

@Service("approvalRule04")
public class ApprovalRule04 implements ApprovalRule {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MaceService maceService;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        MemberDetails memberDetails = approvalRuleParameter.getMemberDetails();
        SpecialAccount specialAccount = maceService.getSpecialAccount(memberDetails.getACCOUNT_CODE());

        if(specialAccount != null){
            result.setRequestStatus(REQUEST_MANUAL);
            result.setStatusAssignee(specialAccount.getStatusAssignee());

            String reasonMessage = messageSource.getMessage(this.getClass().getSimpleName(),
                    new String[] {memberDetails.getACCOUNT_NAME()}, Locale.getDefault());
            result.setResultMessage(reasonMessage);
        }
        else{
            result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
        }

        return result;
    }
}
