package net.incuventure.service;

import net.incuventure.domain.DecisionApproval;
import net.incuventure.domain.DecisionHolder;
import net.incuventure.domain.DecisionInputHolder;
import net.incuventure.domain.ErrorHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by angulo on 10/27/2016.
 */
@Service("decisionEngineService")
public class DecisionEngineService {


    /***
     *
     * @param inputHolder
     * @return
     */
    public DecisionHolder mossLevelEvaluate(DecisionInputHolder inputHolder){
        Boolean decision = true;
        CopyOnWriteArrayList<ErrorHolder> errorHolder = new CopyOnWriteArrayList<ErrorHolder>();
        DecisionApproval decisionApproval = new DecisionApproval();

        //TODO Evaluation here
        //Condition 1: Check Member and Account Status


        //Condition 2: Check Account and Plan validity


        //Condition 3.001: Check Member and Account Status


        //Get available amount
        BigDecimal availableAmount =  inputHolder.getDdAmount().subtract(inputHolder.getTotalUtilization());
        System.out.println(availableAmount);

        //Get required amount
        //How to get required amount





        DecisionHolder decisionHolder = new DecisionHolder();
        decisionHolder.setDecision(decision);
        decisionHolder.setErrorHolder(errorHolder);
        decisionHolder.setEvaluationDate(new Date());
        decisionHolder.setDecisionApproval(decisionApproval);
        return decisionHolder;
    }

}
