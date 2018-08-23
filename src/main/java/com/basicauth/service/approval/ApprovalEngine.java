package com.basicauth.service.approval;

import com.basicauth.config.Constants;
import com.basicauth.data.*;
import com.basicauth.domain.TestProcObject;
import com.basicauth.service.MaceService;
import com.basicauth.service.approval.rules.ApprovalRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static com.basicauth.config.Constants.*;

/**
 * Approval Engine for V2 Approval Rules
 */
@Service("approvalEngine")
public class ApprovalEngine {

    private static final Logger log = LoggerFactory.getLogger(ApprovalEngine.class);

    @Resource
    private Map<String,ApprovalRule> approvalRulesMap;

    @Autowired
    private MaceService maceService;

    /**
     * Approval Engine execution for consult, maternity, Inpatient, and ER. (Bare minimum)
     * */
    public ApprovalEngineResult executeApprovalEngine(MemberDetails memberDetails, Hospital hospital, Integer serviceType, Integer subType){
        //Get list of approval rules to be executed based on member grouping.
        List<ApprovalRulesDao> approvalRulesDaoList = maceService.getApprovalRules(serviceType, subType);
        List<ApprovalRule> approvalRulesList = new ArrayList<>();

        int index = 0;
        for(ApprovalRulesDao approvalRulesDao : approvalRulesDaoList){
            ApprovalRule addApprovalRule = approvalRulesMap.get(approvalRulesDao.getJavaClassName());
            if(addApprovalRule != null){
                try {
                    System.out.println("Index: " + index++);
                    System.out.println(approvalRulesDao.getApprovalRuleName());
                    System.out.println(approvalRulesDao.getDescription());
                }catch(Exception e){}
                approvalRulesList.add(addApprovalRule);
            }
        }

        //Prepare execution of approval rule checking
        ApprovalRuleParameter approvalRuleParameter = new ApprovalRuleParameter(memberDetails, hospital);
        return executeApprovalEngineMain(approvalRuleParameter, approvalRulesList);
    }

     /**
    * Approval Engine execution for Test and Procedure requests
    * */
    public ApprovalEngineResult executeApprovalEngine(MemberDetails memberDetails,
                                      Diagnosis diagnosis,
                                      Hospital hospital,
                                      Doctor doctor,
                                      TestProcObject testProcObject,
                                      Double totalCosts){

        //Get list of approval rules to be executed based on member grouping.
        System.out.println("ServiceType.");
        System.out.println(testProcObject.getServiceType() + " " + testProcObject.getSubType());
        List<ApprovalRulesDao> approvalRulesDaoList = maceService.getApprovalRules(testProcObject.getServiceType(), testProcObject.getSubType());
        List<ApprovalRule> approvalRulesList = new ArrayList<>();

        int index = 0;
        for(ApprovalRulesDao approvalRulesDao : approvalRulesDaoList){
            ApprovalRule addApprovalRule = approvalRulesMap.get(approvalRulesDao.getJavaClassName());
            if(addApprovalRule != null){
                approvalRulesList.add(addApprovalRule);
                try {
                    System.out.println("Index: " + index++);
                    System.out.println(approvalRulesDao.getApprovalRuleName());
                    System.out.println(approvalRulesDao.getDescription());
                }catch(Exception e){}
            }
        }

        //Prepare execution of approval rule checking
        ApprovalRuleParameter approvalRuleParameter = new ApprovalRuleParameter(memberDetails,
                                                                                diagnosis,
                                                                                hospital,
                                                                                doctor,
                                                                                testProcObject,
                                                                                totalCosts);

        return executeApprovalEngineMain(approvalRuleParameter, approvalRulesList);
    }

    private ApprovalEngineResult executeApprovalEngineMain(ApprovalRuleParameter approvalRuleParameter,
                                                       List<ApprovalRule> approvalRulesList){

        //Initialize ApprovalEngineResult with initial values
        ApprovalEngineResult result = new ApprovalEngineResult();
        String finalRequestStatus = REQUEST_AUTOMATIC_APPROVED;
        String finalStatusAssignee = DEFAULT_GROUP;
        Map<String, List<String>> reasonMessagesPerStatus = new HashMap<>();
        Map<String, List<MaceRequestApproval>> maceRequestApprovalsPerStatus = new HashMap<>();
        for(String s : Constants.RequestStatusPriority.getAllStatusNames()){
            reasonMessagesPerStatus.put(s, new ArrayList<>());
            maceRequestApprovalsPerStatus.put(s, new ArrayList<>());
        }

        //Execute approval rules and resolve results
        ApprovalRuleResult approvalRuleResult;
        int index = 0;
        for (ApprovalRule approvalRule : approvalRulesList){
            try{
                approvalRuleResult = approvalRule.check(approvalRuleParameter);
                finalRequestStatus = updateFinalRequestStatus(finalRequestStatus, approvalRuleResult.getRequestStatus());
                finalStatusAssignee = updateFinalStatusAssignee(finalStatusAssignee, approvalRuleResult.getStatusAssignee(), approvalRuleParameter.getHospital());

                if(approvalRuleResult.getResultMessage() != null){
                    List<String> reasonMessages = reasonMessagesPerStatus.get(approvalRuleResult.getRequestStatus());
                    reasonMessages.add((reasonMessages.size()+1) +". " + approvalRuleResult.getResultMessage());
                    reasonMessagesPerStatus.put(approvalRuleResult.getRequestStatus(), reasonMessages);

                    MaceRequestApproval mra = new MaceRequestApproval(approvalRule.getClass().getSimpleName(),
                            new Date(), "SYSTEM", approvalRuleResult.getRequestStatus(), approvalRuleResult.getStatusAssignee(), approvalRuleResult.getResultMessage());
                    if(approvalRuleParameter.getTestProcObject() != null){
                        mra.setProcedureCode(approvalRuleParameter.getTestProcObject().getProcCode());
                    }
                    List<MaceRequestApproval> maceRequestApprovals = maceRequestApprovalsPerStatus.get(approvalRuleResult.getRequestStatus());
                    maceRequestApprovals.add(mra);
                    maceRequestApprovalsPerStatus.put(approvalRuleResult.getRequestStatus(), maceRequestApprovals);
                }

                log.info("EngineMain:" + index++);
                log.info(approvalRuleResult.getRequestStatus());
            }
            catch(Exception e){
                log.error("Error in " + approvalRule.getClass().getSimpleName(), e);
            }
        }

        result.setFinalRequestStatus(finalRequestStatus);
        result.setFinalStatusAssignee(finalStatusAssignee);
        result.setReasonMessages(reasonMessagesPerStatus.get(finalRequestStatus));
        result.setReasonMessagesString(String.join("\n", result.getReasonMessages()));
        result.setMaceRequestApprovals(maceRequestApprovalsPerStatus.get(finalRequestStatus));
        return result;
    }

    private String updateFinalRequestStatus(String finalRequestStatus, String newRequestStatus){
        Integer newRequestStatusPriority = Constants.RequestStatusPriority.getByStatusName(newRequestStatus).getPriority();
        Integer finalRequestStatusPriority = Constants.RequestStatusPriority.getByStatusName(finalRequestStatus).getPriority();

        return newRequestStatusPriority > finalRequestStatusPriority ? newRequestStatus : finalRequestStatus;
    }

    //Given a current status assignee, update it to the new status assignee if the new status assignee has a higher priority than the current one.
    public String updateFinalStatusAssignee(String finalStatusAssignee, String newStatusAssignee, Hospital hospital){
        Integer newStatusAssigneePriority = StatusAssigneePriority.getByStatusAssignee(newStatusAssignee).getPriority();
        Integer finalStatusAssigneePriority = StatusAssigneePriority.getByStatusAssignee(finalStatusAssignee).getPriority();

        if(newStatusAssigneePriority > finalStatusAssigneePriority){
            if(newStatusAssignee.equals(HSC_GROUP)){
                //If HSC/CMG, check if requesting hospital is an MPI Clinic. If true, set to HSC. Else, set to CMG.
                newStatusAssignee = hospital.isMpiClinic() ? HSC_GROUP : CMG_GROUP;
            }

            return newStatusAssignee;
        }
        else{
            return finalStatusAssignee;
        }
    }
}
