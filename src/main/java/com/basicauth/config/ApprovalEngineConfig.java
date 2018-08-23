package com.basicauth.config;

import com.basicauth.service.approval.rules.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for Approval Engine.
 */
@Configuration
public class ApprovalEngineConfig {

    private final Logger log = LoggerFactory.getLogger(ApprovalEngineConfig.class);

    //Autowire all ApprovalRule implementation beans here to be stored into a @Resource map to be used elsewhere.

    @Autowired
    private ApprovalRule01 approvalRule01;

    @Autowired
    private ApprovalRule02 approvalRule02;

    @Autowired
    private ApprovalRule03 approvalRule03;

    @Autowired
    private ApprovalRule04 approvalRule04;

    @Autowired
    private ApprovalRule05A approvalRule05A;

    @Autowired
    private ApprovalRule05B approvalRule05B;

    @Autowired
    private ApprovalRule05C approvalRule05C;

    @Autowired
    private ApprovalRule06A approvalRule06A;

    @Autowired
    private ApprovalRule06B approvalRule06B;

    @Autowired
    private ApprovalRule06C approvalRule06C;

    @Autowired
    private ApprovalRule06D approvalRule06D;

    @Autowired
    private ApprovalRule06E approvalRule06E;

    @Autowired
    private ApprovalRule07A approvalRule07A;

    @Autowired
    private ApprovalRule07B approvalRule07B;

    @Autowired
    private ApprovalRule07C approvalRule07C;

    @Autowired
    private ApprovalRule08A approvalRule08A;

    @Autowired
    private ApprovalRule08B approvalRule08B;

    @Autowired
    private ApprovalRule09 approvalRule09;

    @Autowired
    private ApprovalRule10 approvalRule10;

    @Autowired
    private ApprovalRule11A approvalRule11A;

    @Autowired
    private ApprovalRule11B approvalRule11B;

    @Autowired
    private ApprovalRule12A approvalRule12A;

    @Autowired
    private ApprovalRule12B approvalRule12B;

    /*
    * Map of approval rules.
    * Retrieve this map as a @Resource in the ApprovalEngine and get the ApprovalRule to be used based on its
    * Java class name.
    * */

    @Bean("approvalRulesMap")
    public Map<String, ApprovalRule> approvalRulesMap(){
        Map<String, ApprovalRule> approvalRules = new HashMap<>();

        approvalRules.put(approvalRule01.getClass().getSimpleName(), approvalRule01);
        approvalRules.put(approvalRule02.getClass().getSimpleName(), approvalRule02);
        approvalRules.put(approvalRule03.getClass().getSimpleName(), approvalRule03);
        approvalRules.put(approvalRule04.getClass().getSimpleName(), approvalRule04);
        approvalRules.put(approvalRule05A.getClass().getSimpleName(), approvalRule05A);
        approvalRules.put(approvalRule05B.getClass().getSimpleName(), approvalRule05B);
        approvalRules.put(approvalRule05C.getClass().getSimpleName(), approvalRule05C);
        approvalRules.put(approvalRule06A.getClass().getSimpleName(), approvalRule06A);
        approvalRules.put(approvalRule06B.getClass().getSimpleName(), approvalRule06B);
        approvalRules.put(approvalRule06C.getClass().getSimpleName(), approvalRule06C);
        approvalRules.put(approvalRule06D.getClass().getSimpleName(), approvalRule06D);
        approvalRules.put(approvalRule06E.getClass().getSimpleName(), approvalRule06E);
        approvalRules.put(approvalRule07A.getClass().getSimpleName(), approvalRule07A);
        approvalRules.put(approvalRule07B.getClass().getSimpleName(), approvalRule07B);
        approvalRules.put(approvalRule07C.getClass().getSimpleName(), approvalRule07C);
        approvalRules.put(approvalRule08A.getClass().getSimpleName(), approvalRule08A);
        approvalRules.put(approvalRule08B.getClass().getSimpleName(), approvalRule08B);
        approvalRules.put(approvalRule09.getClass().getSimpleName(), approvalRule09);
        approvalRules.put(approvalRule10.getClass().getSimpleName(), approvalRule10);
        approvalRules.put(approvalRule11A.getClass().getSimpleName(), approvalRule11A);
        approvalRules.put(approvalRule11B.getClass().getSimpleName(), approvalRule11B);
        approvalRules.put(approvalRule12A.getClass().getSimpleName(), approvalRule12A);
        approvalRules.put(approvalRule12B.getClass().getSimpleName(), approvalRule12B);

        return approvalRules;
    }

}
