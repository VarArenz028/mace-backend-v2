package com.basicauth.service.approval.rules;

import com.basicauth.service.MaceService;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.basicauth.config.Constants.*;

/**
 * Check if total costs is 1000 or more.
 * If true, route request for Approval.
 * However, this rule counts as checking whether Diagnosis matches with Procedure.
 * Apply this rule only to Other Tests.
 */

@Service("approvalRule11A")
public class ApprovalRule11A implements ApprovalRule {

    @Autowired
    private MaceService maceService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        /*TestProcObject testProcObject = approvalRuleParameter.getTestProcObject();
        Diagnosis diagnosis = approvalRuleParameter.getDiagnosis();

        DiagnosticProceduresEntity dpe = maceService.getDiagProcedureByProcedureCode(testProcObject.getProcCode(), diagnosis.getDiagCode());*/

        //Even if diagnosis matches with procedure, request must still be routed for approval if total costs are more than 1000.
        //Thus, just validate total costs instead. Reason for disapproval should still be related to matching of diagnosis to procedure.
        if(approvalRuleParameter.getTotalCosts() <= 1000){
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
