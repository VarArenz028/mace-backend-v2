package com.basicauth.service.approval.rules;

import com.basicauth.data.Hospital;
import com.basicauth.data.MemberDetails;
import com.basicauth.service.MemService;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_APPROVED;
import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_DISAPPROVED;

/**
 * If member has no Outpatient access to Hospital, disapprove request.
 * Check if the hospital is in the member's outpatient exclusion list.
 */

@Service("approvalRule05C")
public class ApprovalRule05C implements ApprovalRule {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MemService memService;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        MemberDetails memberDetails = approvalRuleParameter.getMemberDetails();
        Hospital hospital = approvalRuleParameter.getHospital();

        List opExclusionList = memService.getOutpatientHospitalExclusionList(memberDetails.getRSPROOMRATE_ID().toString());
        if(opExclusionList.contains(hospital.getHospitalCode())){
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
