package com.basicauth.controller;

/**
 * Created by Giancarlo Angulo.
 */

import com.basicauth.data.*;
import com.basicauth.service.*;
import net.incuventure.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;


@RestController
@RequestMapping(value = "/coordinator")
public class CoordinatorController {

    private static final Logger logger = LoggerFactory.getLogger(CoordinatorController.class);

    @Autowired
    private MaceService maceService;

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private MemService memService;

    @Autowired
    private LoaService loaService;

    @RequestMapping(value = "/mossConsultTester/", method = RequestMethod.GET)
    public ResponseEntity<?> mossConsultTester(@RequestParam("memberCode") String memberCode,
                                               @RequestParam("maternityType") double maternityType,
                                               @RequestParam("hospitalCode") String hospitalCode) {
        System.out.println("mossConsultTester:" + memberCode);
        System.out.println("memberCode:" + memberCode);
        System.out.println("maternityType:" + maternityType);
        System.out.println("hospitalCode:" + hospitalCode);
        HashMap<String, Object> response = new HashMap<>();

        String msgCode = loaService.requestApprovalConsultation(memberCode, maternityType, hospitalCode);
        response.put("msgCode", msgCode);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @RequestMapping(value = "/generateIdTester/", method = RequestMethod.GET)
    public ResponseEntity<?> generateIdTester(@RequestParam("type") String type) {
        System.out.println("generateIdTester:" + type);
        HashMap<String, Object> response = new HashMap<>();
        if (true) {
            response.put("responseCode", 200);
            response.put("responseDesc", "Succesfully executed method.");
        } else {

        }
        response.put("generatedID", maceService.generateID(type));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @RequestMapping(value = "/logBlockedRequest/", method = RequestMethod.POST)
    public ResponseEntity<?> blockedRequestLogger(@RequestParam("memberCode") String memberCode, @RequestParam("hospitalCode") String hospitalCode,
                                                  @RequestParam("appUsername") String appUsername, @RequestParam("transaction") String transaction, @RequestParam("deviceID") String deviceID) {

        //Get MemberDetails
        MemberDetails member = memService.getMember(memberCode);
        //Prepare log details
        CustomerCare c = maceService.initCustomerCare(member, hospitalCode, "", "", "", BigDecimal.ZERO,
                "", appUsername, "", member.getPRIN_CODE(), null, null, null, null, null, null, deviceID, null, null, null);
        c.setActionTaken(4);
        //ServiceTypeBlock
        String msgCode = maceService.getServiceTypeBlock(memberCode, hospitalCode, member, transaction);
        String t = transaction.toLowerCase();
        String remarks = "";
        if (!msgCode.equals("0")) {
            if (t.equals("consultation") || t.equals("maternity") || t.equals("outpatient")) c.setType(0);
            else if (t.equals("basictest") || t.equals("othertest")) c.setType(2);
            else if (t.equals("inpatient")) c.setType(1);
            else if (t.equals("er")) c.setType(-1);

            c.setBatchCode(maceService.generateID("BATCHNO"));
            c.setCallerId(maceService.generateID("CALLERID"));
            //Responses
            GetResponseEntityValues getResponseEntityValues = new GetResponseEntityValues(msgCode).invoke();
            remarks = getResponseEntityValues.getRemarks();
            String responseDesc = getResponseEntityValues.getResponseDesc();
            c.setRemarks(transaction.toUpperCase() + " - " + responseDesc);
            c.setNotes(remarks);
            //Log in MTBL/LTBL
            maceService.saveTransactionForCall(c);
            customerServiceService.saveTransactionForCall(c);

        } else if (msgCode.equals("0")) {
            boolean isBlacklisted = maceService.checkifCompanyIsBlacklisted(member.getACCOUNT_CODE(), transaction);
            if (isBlacklisted) {
                c.setRemarks(transaction.toUpperCase() + " - ");
                getResponseEntityForBlacklist(new HashMap<>(), c);
            }
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("responseCode", 200);
        response.put("responseDesc", remarks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private ResponseEntity<?> getResponseEntityForBlacklist(HashMap<String, Object> response, CustomerCare c) {
        String remarks;
        String responseCode;
        String responseDesc;
        remarks = "Please call 841-8080 for approval";
        responseCode = "205";
        responseDesc = "Company is blacklisted.";

        response.put("remarks", remarks);
        response.put("responseCode", responseCode);
        response.put("responseDesc", responseDesc);
        c.setActionTaken(1);
        c.setRemarks(c.getRemarks().concat(remarks));
        c.setNotes(responseDesc);
        c.setBatchCode(maceService.generateID("BATCHNO"));
        c.setCallerId(maceService.generateID("CALLERID"));
        maceService.saveTransactionForCall(c);
        customerServiceService.saveTransactionForCall(c);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private class GetResponseEntityValues {
        private String msgCode;
        private String responseCode;
        private String responseDesc;
        private String remarks;

        public GetResponseEntityValues(String msgCode) {
            this.msgCode = msgCode;
        }

        public String getResponseCode() {
            return responseCode;
        }

        public String getResponseDesc() {
            return responseDesc;
        }

        public String getRemarks() {
            return remarks;
        }

        public GetResponseEntityValues invoke() {
            try {
                BlockingMessages blocker = maceService.getBlockingMessageByCode(msgCode);
                remarks = blocker.getRemarks();
                responseCode = blocker.getResponseCode().toString();
                responseDesc = blocker.getResponseDesc();
                return this;
            } catch (Exception e) {
                remarks = "Unhandled Message. Please call 841-8080 for approval";
                responseCode = "250";
                responseDesc = "Unhandled Message Code";
                return this;
            }
        }
    }

    private class GetResponseEntityValuesInteger {
        private Integer msgCode;
        private String responseCode;
        private String responseDesc;
        private String remarks;

        public GetResponseEntityValuesInteger(Integer msgCode) {
            this.msgCode = msgCode;
        }

        public String getResponseCode() {
            return responseCode;
        }

        public String getResponseDesc() {
            return responseDesc;
        }

        public String getRemarks() {
            return remarks;
        }

        public GetResponseEntityValuesInteger invoke() {

            try {
                BlockingMessages blocker = maceService.getBlockingMessageByCode(String.valueOf(msgCode));
                remarks = blocker.getRemarks();
                responseCode = blocker.getResponseCode().toString();
                responseDesc = blocker.getResponseDesc();
                return this;
            } catch (Exception e) {
                remarks = "Unhandled Message. Please call 841-8080 for approval";
                responseCode = "250";
                responseDesc = "Unhandled Message Code";
                return this;
            }
        }
    }
}