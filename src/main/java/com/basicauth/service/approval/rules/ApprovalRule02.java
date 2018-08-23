package com.basicauth.service.approval.rules;

import com.basicauth.data.MemberDetails;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_APPROVED;
import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_DISAPPROVED;

/**
 * If current date is past member's validity date (member is no longer valid), disapprove request.
 */

@Service("approvalRule02")
public class ApprovalRule02 implements ApprovalRule {

    private static final Logger log = LoggerFactory.getLogger(ApprovalRule02.class);

    @Autowired
    private MessageSource messageSource;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        MemberDetails memberDetails = approvalRuleParameter.getMemberDetails();

        try{
            Date memberValidityDate = new SimpleDateFormat("MMM dd, yyyy").parse(memberDetails.getVAL_DATE());
            if (new Date().compareTo(memberValidityDate) > 0){
                result.setRequestStatus(REQUEST_AUTOMATIC_DISAPPROVED);

                String reasonMessage = messageSource.getMessage(this.getClass().getSimpleName(),
                        null, Locale.getDefault());
                result.setResultMessage(reasonMessage);
            }
            else {
                result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
            }
        }
        catch (Exception e){
            //Log formatting error here!
            log.error("Error!", e);
        }

        return result;
    }
}
