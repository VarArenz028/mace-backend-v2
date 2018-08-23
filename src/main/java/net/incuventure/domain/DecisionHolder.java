package net.incuventure.domain;

import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by angulo on 10/27/2016.
 */
public class DecisionHolder {


    private Boolean decision;
    private CopyOnWriteArrayList<ErrorHolder> errorHolder;
    private Date evaluationDate;
    private DecisionApproval decisionApproval;

    public DecisionHolder() {
    }

    public Boolean getDecision() {
        return decision;
    }

    public void setDecision(Boolean decision) {
        this.decision = decision;
    }

    public CopyOnWriteArrayList<ErrorHolder> getErrorHolder() {
        return errorHolder;
    }

    public void setErrorHolder(CopyOnWriteArrayList<ErrorHolder> errorHolder) {
        this.errorHolder = errorHolder;
    }

    public Date getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public DecisionApproval getDecisionApproval() {
        return decisionApproval;
    }

    public void setDecisionApproval(DecisionApproval decisionApproval) {
        this.decisionApproval = decisionApproval;
    }
}
