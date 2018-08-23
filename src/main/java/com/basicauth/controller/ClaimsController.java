package com.basicauth.controller;

import com.basicauth.data.MaceRequest;
import com.basicauth.data.MaceRequestProcedure;
import com.basicauth.data.MemberDetails;
import com.basicauth.domain.MaceClaims;
import com.basicauth.domain.MaceClaimsJson;
import com.basicauth.domain.MaceReqConsult;
import com.basicauth.domain.SubmitClaimsJson;
import com.basicauth.service.MaceService;
import com.basicauth.service.MemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jabito on 12/01/2018.
 */
@RestController
@RequestMapping(value = "/claims")
public class ClaimsController {

    private static final Logger logger = LoggerFactory.getLogger(ClaimsController.class);

    @Autowired
    private MaceService maceService;

    @Autowired
    private MemService memService;

    @RequestMapping(value = "/submitClaims", method = RequestMethod.POST)
    public ResponseEntity<?> submitClaims(@RequestBody SubmitClaimsJson claimsJson) {
        HashMap<String, Object> response = new HashMap<>();

        List<MaceRequest> requests = maceService.getMaceRequestsByRequestCodes(claimsJson.getRequestCodes(), claimsJson.getServiceType().equalsIgnoreCase("CONSULTATION") ? 1 : 3);
        if (null == requests || requests.isEmpty()) {
            response.put("responseCode", 404);
            response.put("responseDesc", "No MaceRequests Found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        if (claimsJson.getRequestCodes().size() != requests.size()) {
            response.put("responseCode", 404);
            response.put("responseDesc", "RequestCode count does not match MaceRequests retrieved.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        MemberDetails member = memService.getMember(requests.get(0).getMemCode());
        String controlCode = maceService.generateID("CONTROL_CODE");
        List<MaceClaims> claims = new ArrayList<>();
        for (MaceRequest request : requests) {
            MaceClaims claim = new MaceClaims(request);
            claim.setControlCode(controlCode);
            claim.setMemberName(member.getMEM_NAME());
            claim.setMemType(member.getMEM_TYPE());
            claim.setSubmittedBy(claimsJson.getDoctorCode());
            switch (request.getServiceTypeId()) {
                case 1:
                    claim.setType("CONSULTATION");
                    MaceReqConsult mrc = maceService.getMaceReqConsult(request.getRequestId());
                    if (mrc != null)
                        claim.setLoaNumber(mrc.getReferenceNo());
                    break;
                case 3:
                    claim.setType("PROCEDURE");
                    List<MaceRequestProcedure> mrp = maceService.getMaceRequestProcedures(request.getRequestId());
                    if (mrp != null)
                        claim.setLoaNumber(mrp.get(0).getApprovalNo());
                    break;
                default:
                    break;
            }
            maceService.addClaim(claim);
            claims.add(claim);
        }

        response.put("controlCode", controlCode);
        response.put("claims", claims);
        response.put("responseCode", 200);
        response.put("responseDesc", "Claims submitted.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
