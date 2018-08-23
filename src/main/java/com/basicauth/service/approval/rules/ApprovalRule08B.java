package com.basicauth.service.approval.rules;

import com.basicauth.data.Diagnosis;
import com.basicauth.data.Doctor;
import com.basicauth.data.SpecialDiagnosis;
import com.basicauth.domain.TestProcObject;
import com.basicauth.service.MaceService;
import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.basicauth.config.Constants.*;

/**
 * If diagnosis is regarded as a special case, route request for approval.
 */

@Service("approvalRule08B")
public class ApprovalRule08B implements ApprovalRule {

    @Autowired
    private MaceService maceService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter) {
        ApprovalRuleResult result = new ApprovalRuleResult();

        TestProcObject testProcObject = approvalRuleParameter.getTestProcObject();
        Doctor doctor = approvalRuleParameter.getDoctor();
        Diagnosis diagnosis = approvalRuleParameter.getDiagnosis();

        //Custom validation rule. Approve request if the procedure is slit lamp and the doctor is an opthalmologist.
        if(testProcObject.getProcCode().equals(SLIT_LAMP_PROCEDURE_CODE) && doctor.getSpecCode().equals(OPTHALMOLOGY_SPECIALIZATION_CODE)){
            result.setRequestStatus(REQUEST_AUTOMATIC_APPROVED);
            return result;
        }

        SpecialDiagnosis specialDiagnosis = maceService.getSpecialDiagnosis(diagnosis.getDiagCode());
        if(specialDiagnosis != null){
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
