package com.basicauth.service.approval.rules;

import com.basicauth.data.Diagnosis;
import com.basicauth.data.DiagnosisClinicProceduresEntity;
import com.basicauth.domain.TestProcObject;
import com.basicauth.service.ClaimsService;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.basicauth.config.Constants.*;

/**
 * Check if Diagnosis does not match with Clinic Procedure
 * If there is no match, route request for Approval.
 * Apply this rule only to Procedures.
 */

@Service("approvalRule11B")
public class ApprovalRule11B implements ApprovalRule {

    @Autowired
    private ClaimsService claimsService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        TestProcObject testProcObject = approvalRuleParameter.getTestProcObject();
        Diagnosis diagnosis = approvalRuleParameter.getDiagnosis();

        DiagnosisClinicProceduresEntity dcpe = claimsService.getDiagnosisClinicProcedureEntity(testProcObject.getProcCode(), diagnosis.getDiagCode());

        if(dcpe != null || approvalRuleParameter.getTotalCosts() <= 1000){
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
