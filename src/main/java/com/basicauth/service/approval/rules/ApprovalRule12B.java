package com.basicauth.service.approval.rules;

import com.basicauth.data.Hospital;
import com.basicauth.data.MemberDetails;
import com.basicauth.mapper.MemMapper;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_APPROVED;
import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_DISAPPROVED;

/**
 * If Member has no access to Hospital's Maternity OP benef, disapprove request.
 * Apply this rule only to Maternity Consultation.
 */

@Service("approvalRule12B")
public class ApprovalRule12B implements ApprovalRule {

    @Autowired
    private MemMapper memMapper;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        MemberDetails memberDetails = approvalRuleParameter.getMemberDetails();
        Hospital hospital = approvalRuleParameter.getHospital();

        String msgCode = memMapper.mossConsultation(memberDetails.getPRIN_CODE(), 1, hospital.getHospitalCode());

        if(!msgCode.equals("0")){
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
