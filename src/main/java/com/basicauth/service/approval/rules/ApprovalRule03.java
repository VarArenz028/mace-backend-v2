package com.basicauth.service.approval.rules;

import com.basicauth.data.Blacklist;
import com.basicauth.data.MemberDetails;
import com.basicauth.service.MaceService;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_APPROVED;
import static com.basicauth.config.Constants.REQUEST_BLOCKED;

/**
 * If company of member is in list of blacklisted companies, block request.
 */

@Service("approvalRule03")
public class ApprovalRule03 implements ApprovalRule {

    @Autowired
    private MaceService maceService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        MemberDetails memberDetails = approvalRuleParameter.getMemberDetails();

        List<Blacklist> blacklistedService = maceService.companyBlacklistedServices(memberDetails.getACCOUNT_CODE());
        if(!blacklistedService.isEmpty()){
            result.setRequestStatus(REQUEST_BLOCKED);

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
