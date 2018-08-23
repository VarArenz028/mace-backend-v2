package com.basicauth.controller;

import com.basicauth.data.MaceInsertOrder;
import com.basicauth.data.OtherLimit;
import com.basicauth.service.*;
import com.basicauth.service.approval.ApprovalEngine;
import com.basicauth.types.ConsultJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by IPC_Laptop056 on 9/21/2017.
 */
@RestController
@RequestMapping(value = "/memberloa/v2")
public class MemberLoaVersion2Controller {

    private static final Logger logger = LoggerFactory.getLogger(MemberLoaVersion2Controller.class);

    @Autowired
    private MaceService maceService;

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private MemService memService;

    @Autowired
    private LoaService loaService;

    @Autowired
    private ApprovalEngine approvalEngine;

    @Autowired
    private ClaimsService claimsService;

    @RequestMapping(value = "/validateLimits/", method = RequestMethod.GET)
    public ResponseEntity<?> validateLimits(@RequestParam("memberCode") String memberCode, @RequestParam("amount") BigDecimal amount) {
        HashMap<String, Object> response = new HashMap<>();
        BigDecimal remainingLimit = new BigDecimal(claimsService.getRemainingLimit(memberCode));
        OtherLimit otherLimit = maceService.getOtherLimit();
        Boolean isPecEqualToDdl = memService.isPecEqualToDdl(memberCode);
        response.put("isValidated", loaService.validateLimit(amount, otherLimit, remainingLimit, isPecEqualToDdl));


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/requestLOAConsult/", method = RequestMethod.POST)
    public ResponseEntity<?> requestLOAConsult(@RequestBody ConsultJson consultJson) {
        String remarks = "CONSULTATION";
        ResponseEntity<?> response = maceService.processConsultationV2(consultJson, 1, "MEMBER", remarks);
        HashMap<String, Object> responseBody = (HashMap) response.getBody();
        //Remove instead of get so that the MaceInsertObject won't be part of the final response
        if(responseBody.containsKey("maceInsertOrder")){
            maceService.maceInsertProcessTests((MaceInsertOrder) responseBody.remove("maceInsertOrder"));
        }

        return response;
    }

    @RequestMapping(value = "/requestLOAMaternity/", method = RequestMethod.POST)
    public ResponseEntity<?> requestLOAMaternity(@RequestBody ConsultJson consultJson) {
        String remarks = "MATERNITY CONSULTATION";
        ResponseEntity<?> response = maceService.processConsultationV2(consultJson, 2, "MEMBER", remarks);
        HashMap<String, Object> responseBody = (HashMap) response.getBody();
        //Remove instead of get so that the MaceInsertObject won't be part of the final response
        if(responseBody.containsKey("maceInsertOrder")){
            maceService.maceInsertProcessTests((MaceInsertOrder) responseBody.remove("maceInsertOrder"));
        }

        return response;
    }
}
