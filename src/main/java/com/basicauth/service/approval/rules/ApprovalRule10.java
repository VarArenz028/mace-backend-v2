package com.basicauth.service.approval.rules;

import com.basicauth.data.MemberDetails;
import com.basicauth.data.OtherLimit;
import com.basicauth.service.ClaimsService;
import com.basicauth.service.LoaService;
import com.basicauth.service.MaceService;
import com.basicauth.service.MemService;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Locale;

import static com.basicauth.config.Constants.*;

/**
 * Verify member's total costs against limits for automatic approval of request.
 * If remaining limit is within that limit, approve request.
 * Otherwise, route request for approval.
 */

@Service("approvalRule10")
public class ApprovalRule10 implements ApprovalRule{

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MaceService maceService;

    @Autowired
    private MemService memService;

    @Autowired
    private ClaimsService claimsService;

    @Autowired
    private LoaService loaService;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        MemberDetails memberDetails = approvalRuleParameter.getMemberDetails();
        Double totalCosts = approvalRuleParameter.getTotalCosts();

        BigDecimal remainingLimit = new BigDecimal(claimsService.getRemainingLimit(memberDetails.getPRIN_CODE()));

        OtherLimit otherLimit = maceService.getOtherLimit();
        Boolean isPecEqualToDdl = memService.isPecEqualToDdl(memberDetails.getPRIN_CODE());
        boolean withinLimit = loaService.validateLimit(new BigDecimal(totalCosts), otherLimit, remainingLimit, isPecEqualToDdl);

        if(withinLimit){
            result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
        }
        else{
            result.setRequestStatus(REQUEST_MANUAL);
            result.setStatusAssignee(HSC_GROUP);

            String reasonMessage = messageSource.getMessage(this.getClass().getSimpleName(),
                    null, Locale.getDefault());
            result.setResultMessage(reasonMessage);
        }

        return result;
    }
}
