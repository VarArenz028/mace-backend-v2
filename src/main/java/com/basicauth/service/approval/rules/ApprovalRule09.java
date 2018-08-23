package com.basicauth.service.approval.rules;

import com.basicauth.data.MemberDetails;
import com.basicauth.domain.TestProcObject;
import com.basicauth.service.ClaimsService;
import com.basicauth.service.MaceService;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Locale;

import static com.basicauth.config.Constants.*;

/**
 * Verify member's remaining limit against specified inner limits.
 * If remaining limit is below inner limit, route request for approval.
 * Otherwise, approve request.
 */

@Service("approvalRule09")
public class ApprovalRule09 implements ApprovalRule{

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MaceService maceService;

    @Autowired
    private ClaimsService claimsService;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        TestProcObject testProcObject = approvalRuleParameter.getTestProcObject();
        MemberDetails memberDetails = approvalRuleParameter.getMemberDetails();

        BigDecimal remainingLimit = new BigDecimal(claimsService.getRemainingLimit(memberDetails.getPRIN_CODE()));
        Double innerLimit = maceService.getInnerLimit(testProcObject.getCostCenter());

        if(remainingLimit.compareTo(new BigDecimal(innerLimit)) < 0){
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
