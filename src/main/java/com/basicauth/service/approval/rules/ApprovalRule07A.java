package com.basicauth.service.approval.rules;

import com.basicauth.data.Diagnosis;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.basicauth.config.Constants.*;

/**
 * For certain diagnosis types, route request for approval.
 */

@Service("approvalRule07A")
public class ApprovalRule07A implements ApprovalRule {

    @Autowired
    private MessageSource messageSource;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        Diagnosis diagnosis = approvalRuleParameter.getDiagnosis();

        String reasonMessage = "";

        switch (diagnosis.getTypeDesc()){
            case "PEC":
            case "EXCLUSION":
            case "CONGENITAL":
                result.setRequestStatus(REQUEST_MANUAL);
                result.setStatusAssignee(HSC_GROUP);

                reasonMessage = messageSource.getMessage(this.getClass().getSimpleName(),
                        new String[] {diagnosis.getTypeDesc()}, Locale.getDefault());
                result.setResultMessage(reasonMessage);

                break;
            default:
                result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
                break;
        }

        return result;
    }
}
