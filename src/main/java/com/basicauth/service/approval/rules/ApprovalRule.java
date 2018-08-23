package com.basicauth.service.approval.rules;

import com.basicauth.service.approval.ApprovalRuleParameter;
import com.basicauth.service.approval.ApprovalRuleResult;

/**
 * Interface for approval rules.
 */
public interface ApprovalRule {

    /**
     * Accepts ApprovalRuleParameter that contains member and transaction data
     */
    ApprovalRuleResult check(ApprovalRuleParameter approvalRuleParameter);
}
