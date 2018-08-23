package com.basicauth.controller;

import com.basicauth.data.MaceRequest;
import com.basicauth.data.MaceRequestErJson;
import com.basicauth.domain.MaceReqEr;
import com.basicauth.domain.MaceReqErDiag;
import com.basicauth.domain.MaceReqErDoctor;
import com.basicauth.domain.MaceReqErProcedure;
import com.basicauth.service.MaceRequestErService;
import com.basicauth.service.MaceRequestService;
import com.basicauth.service.MaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by shem.sandiego on 2/8/2018.
 */
@RestController
@RequestMapping("/approvalPortal")
public class MaceRequestErController {
    private static final Logger logger = LoggerFactory.getLogger(MaceRequestErController.class);

    @Autowired
    private MaceRequestErService maceRequestErService;

    @Autowired
    private MaceService maceService;

    @RequestMapping(value = "/createMaceRequestEr", method = RequestMethod.POST)
    public ResponseEntity<?> createMaceRequestEr (@RequestBody MaceRequestErJson maceRequestErJson){

        System.out.println("createMaceRequestEr RUN---");

        HashMap<String, Object> response = new HashMap<>();

        MaceRequest maceRequest = new MaceRequest();
        maceRequest.setServiceType(maceRequestErJson.getServiceType());
        maceRequest.setServiceTypeId(5);
        maceRequest.setMemCode(maceRequestErJson.getMemCode());
        maceRequest.setMemLname(maceRequestErJson.getMemLname());
        maceRequest.setMemFname(maceRequestErJson.getMemFname());
        maceRequest.setMemMi(maceRequestErJson.getMemMi());
        maceRequest.setMemCompany(maceRequestErJson.getMemCompany());
        maceRequest.setMemStat(maceRequestErJson.getMemStat());
        maceRequest.setMemBdate(maceRequestErJson.getMemBdate());
        maceRequest.setMemGender(maceRequestErJson.getMemGender());
        maceRequest.setMemAge(maceRequestErJson.getMemAge());
        maceRequest.setMemType(maceRequestErJson.getMemType());
        maceRequest.setIdremarks(maceRequestErJson.getRemarks());
        maceRequest.setAcctValidity(maceRequestErJson.getAcctValidity());
        maceRequest.setAcctEffectivity(maceRequestErJson.getAcctEffectivity());
        maceRequest.setRequestDevice(maceRequestErJson.getRequestDevice());
        maceRequest.setRequestOrigin(maceRequestErJson.getRequestOrigin());
        maceRequest.setRequestDatetime(maceRequestErJson.getRequestDatetime());
        maceRequest.setRequestBy(maceRequestErJson.getRequestBy());
        maceRequest.setRequestByCode(maceRequestErJson.getRequestByCode());
        maceRequest.setTotalUtilization(maceRequestErJson.getTotalUtilization());
        maceRequest.setRequestFromhosp(maceRequestErJson.getRequestFromHosp());
        maceRequest.setStatus(maceRequestErJson.getStatus());
        maceRequest.setOverride(false);

        maceService.saveMaceRequest(maceRequest);

        MaceReqEr maceReqEr = new MaceReqEr();
        maceReqEr.setMaceRequestId(maceRequest.getRequestId());
        maceReqEr.setRequestFrom(maceRequestErJson.getRequestFrom());
        maceReqEr.setHospitalCode(maceRequestErJson.getRequestFromHosp());
        maceReqEr.setErReasonRemarks(maceRequestErJson.getReasonRemarks());

        maceRequestErService.saveMaceReqEr(maceReqEr);

        if (maceRequestErJson.getMaceReqErDiagList() != null) {
            List<MaceReqErDiag> maceReqErDiagList = maceRequestErJson.getMaceReqErDiagList();
            for (MaceReqErDiag mred : maceReqErDiagList) {
                mred.setMaceRequestId(maceRequest.getRequestId());
                maceRequestErService.saveMaceReqERDiag(mred);
            }
        }

        if (maceRequestErJson.getMaceReqErProcedureList() != null) {
            List<MaceReqErProcedure> maceReqErProcedures = maceRequestErJson.getMaceReqErProcedureList();
            for (MaceReqErProcedure mrep : maceReqErProcedures) {
                mrep.setMaceRequestId(maceRequest.getRequestId());
                maceRequestErService.saveMaceReqERProcedure(mrep);
            }
        }

        if(maceRequestErJson.getMaceReqErDoctorList() != null){
            List<MaceReqErDoctor> maceReqErDoctorList = maceRequestErJson.getMaceReqErDoctorList();
            for (MaceReqErDoctor mredr : maceReqErDoctorList) {
                mredr.setMaceRequestId(maceRequest.getRequestId());
                maceRequestErService.saveMaceReqERDoctor(mredr);
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
