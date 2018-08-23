package com.basicauth.controller;

/**
 * Created by Giancarlo Angulo.
 */

import com.basicauth.data.*;
import com.basicauth.domain.*;
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
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping(value = "/coordinator/v2")
public class CoordinatorVersion2Controller {

    private static final Logger logger = LoggerFactory.getLogger(CoordinatorVersion2Controller.class);
    private static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    private MaceService maceService;

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private MemService memService;

    @Autowired
    private LoaService loaService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClaimsService claimsService;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/getHasDisclaimerByMemberCode", method = RequestMethod.GET)
    public ResponseEntity<?> getHasDisclaimerByMemberCode(@RequestParam("memberCode") String memberCode) {

        HashMap<String, Object> response = new HashMap<>();

        Integer hasDisclaimer = maceService.getHasDisclaimerByMemberCode(memberCode);
        if (null == hasDisclaimer)
            hasDisclaimer = 0;
        response.put("hasDisclaimer", hasDisclaimer);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully Updated Disclaimer Status");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateHasDisclaimerByMemberCode", method = RequestMethod.POST)
    public ResponseEntity<?> updateHasDisclaimerByMemberCode(@RequestParam("memberCode") String memberCode,
                                                             @RequestParam("hasDisclaimer") Integer hasDisclaimer) {
        HashMap<String, Object> response = new HashMap<>();

        maceService.checkDisclaimerForChanges(memberCode, hasDisclaimer);

        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully Updated Disclaimer Status");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getFilteredLoaByMemberCode", method = RequestMethod.GET)
    private ResponseEntity<?> getFilteredLoaByMemberCode(@RequestParam("memberCode") String memberCode, @RequestParam("filterType") String filterType) {
        HashMap<String, Object> response = new HashMap<>();
        //CONSULTATION, MATERNITY, OTHER TEST, BASIC TEST, PROCEDURES

        List<MaceRequest> loaList = maceService.getFilteredLoaByMemberCode(memberCode, filterType);
        List<MaceRequestListsJson> loaTests = new ArrayList<>();
        if (null != loaList) {
            for (int x = 0; x < loaList.size(); x++) {
                List<MaceRequestOpDiag> diagnosisList = maceService.getMaceReqOpDiagByMaceReqId(loaList.get(x).getRequestId());
                List<MaceRequestOpProcedure> proceduresList = maceService.getMaceReqOpProcByMaceReqId(loaList.get(x).getRequestId());
                List<MaceRequestOpTest> testList = maceService.getMaceReqOpTestsByMaceReqId(loaList.get(x).getRequestId());

                MaceRequestListsJson mrlj = new MaceRequestListsJson();
                mrlj.setDiagnosisList(diagnosisList);
                mrlj.setProceduresList(proceduresList);
                mrlj.setTestList(testList);

                loaTests.add(mrlj);
            }
        }
        if (null != loaList) {
            response.put("loaList", loaList);
            response.put("loaTests", loaTests);
            response.put("responseDesc", "Successfully retrieved list.");
        } else {
            response.put("responseCode", 210);
            response.put("responseDesc", "No list found.");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<?> getResponseEntityForBlacklist(HashMap<String, Object> response, CustomerCare c) {
        String remarks;
        String responseCode;
        String responseDesc;
        remarks = "Please call 841-8080 for approval";
        responseCode = "205";
        responseDesc = "Company is blacklisted";

        response.put("remarks", remarks);
        response.put("responseCode", responseCode);
        response.put("responseDesc", responseDesc);
        c.setActionTaken(1);
        c.setRemarks(remarks);
        c.setBatchCode(maceService.generateID("BATCHNO"));
        c.setCallerId(maceService.generateID("CALLERID"));
        maceService.saveTransactionForCall(c);
        customerServiceService.saveTransactionForCall(c);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/lockMember/", method = RequestMethod.POST)
    public ResponseEntity<?> lockMember(@RequestParam("memberCode") String memberCode) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            LockMember lockMember = maceService.getLastLoginAttempt(memberCode);
            if(null != lockMember) {
                maceService.lockMember(lockMember);
                response.put("responseCode", "200");
                response.put("responseDesc", "Member is locked.");
            }else{
                response.put("responseCode", "210");
                response.put("responseDesc", "Member has no log of invalid attempts");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("responseCode", "230");
            response.put("responseDesc", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/inquireInpatient/", method = RequestMethod.POST)
    public ResponseEntity<?> inquireInpatient(@RequestBody InpatientJson inpatientJson) {
        HashMap<String, Object> response = new HashMap<>();
        ResponseEntity<?> responseEntity = getResponseEntityForInpatient(inpatientJson, response);
        return responseEntity;
    }

    @RequestMapping(value = "/dischargeErToOutpatient/", method = RequestMethod.POST)
    public ResponseEntity<?> dischargeErToOutpatient(@RequestParam("requestId") String requestId,
                                                     @RequestParam("userName") String userName) {
        HashMap<String, Object> response = new HashMap<>();

        //TODO change ER status to DISCHARGED
        //TODO Retrieve Mace Request ER for memberCode
        //TODO Change Mace Request ER status to Discharged
        //TODO Change Mace Request ER Discharged on To current


        MaceReqEr mrer = maceService.getMaceRequestErByRequestId(requestId);

        mrer.setStatus("Discharged");
        mrer.setStatusRemarks("Discharged to Outpatient");
        mrer.setDischargedOn(new Date());
        mrer.setDischargeupdateBy(userName);
        mrer.setLastupdateOn(new Date());
        mrer.setLastupdateBy(userName);

        maceService.updateMaceReqEr(mrer);
        response.put("maceReq", mrer);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully Discharge");


        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/dischargeErToInpatient/", method = RequestMethod.POST)
    public ResponseEntity<?> dischargeErToInpatient(@RequestParam("requestId") String requestId,
                                                    @RequestParam("userName") String userName) {
        HashMap<String, Object> response = new HashMap<>();

        //TODO change ER status to DISCHARGED
        //TODO Retrieve Mace Request ER for memberCode
        //TODO Change Mace Request ER status to Discharged
        //TODO Change Mace Request ER Discharged on To current


        MaceReqEr mrer = maceService.getMaceRequestErByRequestId(requestId);

        mrer.setStatus("Discharged");
        mrer.setStatusRemarks("Discharged to Inpatient");
        mrer.setDischargedOn(new Date());
        mrer.setDischargeupdateBy(userName);
        mrer.setLastupdateOn(new Date());
        mrer.setLastupdateBy(userName);

        maceService.updateMaceReqEr(mrer);
        response.put("maceReq", mrer);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully Discharge");


        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/dischargeEr/", method = RequestMethod.POST)
    public ResponseEntity<?> dischargeEr(@RequestParam("requestId") String requestId,
                                         @RequestParam("userName") String userName) {
        HashMap<String, Object> response = new HashMap<>();

        //TODO change ER status to DISCHARGED
        //TODO Retrieve Mace Request ER for memberCode
        //TODO Change Mace Request ER status to Discharged
        //TODO Change Mace Request ER Discharged on To current

        MaceReqEr mrer = maceService.getMaceRequestErByRequestId(requestId);

        mrer.setStatus("Discharged");
        mrer.setStatusRemarks("Discharged from ER");
        mrer.setDischargedOn(new Date());
        mrer.setDischargeupdateBy(userName);
        mrer.setLastupdateOn(new Date());
        mrer.setLastupdateBy(userName);

        maceService.updateMaceReqEr(mrer);
        response.put("maceReq", mrer);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully Discharge");


        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private ResponseEntity<?> getResponseEntityForInpatient(@RequestBody InpatientJson inpatientJson, HashMap<String, Object> response) {
        try {

            MemberDetails memberDetails = memService.getMember(inpatientJson.getMemberCode());

            CustomerCare c = maceService.processInpatient(
                    memberDetails,
                    inpatientJson.getHospitalCode(),
                    inpatientJson.getDoctorCode(),
                    inpatientJson.getDiagnosisCode(),
                    inpatientJson.getRoomType(),
                    inpatientJson.getRoomNumber(),
                    inpatientJson.getRoomPrice(),
                    inpatientJson.getUsername(),
                    inpatientJson.getProcedureCode(),
                    inpatientJson.getDateAdmitted(),
                    inpatientJson.getCategory()
            );

            Integer retCode = memService.validateInpatient(inpatientJson.getMemberCode(), inpatientJson.getHospitalCode());
            String remarks = "";
            String responseCode = "";
            String responseDesc = "";

            c.setActionTaken(4);
            c.setType(1);
            c.setBatchCode(maceService.generateID("BATCHNO"));
            c.setCallerId(maceService.generateID("CALLERID"));
            c.setApprovalNo("");

            if (retCode.compareTo(Integer.valueOf("0")) == 0) {
                //02/28 - GMeneses - Transaction is not approved but rather just an inquiry
                //remarks = "Approved";
                remarks = "Inpatient - Inquiry";
                String notes = "Inpatient Admission Details Submitted.";
                c.setRemarks(remarks);
                c.setNotes(notes);

                MaceRequest mr = new MaceRequest(c, memberDetails, 4, "COORDINATOR", inpatientJson.getUsername());
                MaceReqInpatient mrip = new MaceReqInpatient(c, memberDetails, inpatientJson);

                DiagnosisEntity de = maceService.getDiagnosisEntity(inpatientJson.getDiagnosisCode());
                MaceReqIpDiag mrid = new MaceReqIpDiag();
                if (null != de) {
                    mrid.setDiagnosisEntity(de, 1);
                    response.put("DxCodeExist", true);

                } else {
                    response.put("DxCodeExist", false);
                    response.put("responseDesc", "Diagnosis does not exist.");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }

                DoctorToHospital docHosp = claimsService.getDoctorToHospitalObject(inpatientJson.getHospitalCode(), inpatientJson.getDoctorCode(), false);
                if (null != docHosp) {
                    mrip.setDocHospId(docHosp.getDocHospId());
                    response.put("DocHospExist", true);
                } else {
                    response.put("DocHospExist", false);
                    response.put("responseDesc", "DocHosp ID Invalid.");
                    response.put("responseCode", 220);
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }

                MaceReqIpDiagproc mridp = new MaceReqIpDiagproc();

                response.put("ProcedureExist", null != mridp ? true : false);
                DiagnosisClinicProceduresEntity dcpe = claimsService.getDiagnosisClinicProcedureEntity(inpatientJson.getDiagnosisCode(), inpatientJson.getProcedureCode());
                if (null != dcpe) {
                    mridp.setDiagnosisClinicProceduresEntity(dcpe);
                } else {
                    response.put("responseDesc", "Diagnosis and Procedure did not match.");
                }

                MaceReqIpDoctor mridc = new MaceReqIpDoctor(claimsService.getDoctor(docHosp.getDoctorCode(), false),
                        claimsService.getHospital(docHosp.getHospitalCode()),
                        docHosp, inpatientJson.getUsername(), inpatientJson.getDateAdmitted());

                MaceReqIpRoom mrir = new MaceReqIpRoom();
                mrir.setHospitalCode(inpatientJson.getHospitalCode());

                mrir.setRoomtype(inpatientJson.getRoomType());
                mrir.setRoomplan(inpatientJson.getRoomNumber());
                if (inpatientJson.getRoomPrice().equals(""))
                    inpatientJson.setRoomPrice("0.0");
                mrir.setRate(BigDecimal.valueOf(Double.valueOf(inpatientJson.getRoomPrice())));

                maceService.saveMaceRequest(mr);
                maceService.saveMaceRequestInpatient(mr, mrip);
//                maceService.saveMaceReqIpDiag(mrip, mrid);
//                maceService.saveMaceReqIpDoctor(mr, mrip, mridp, mridc);
                if (dcpe != null)
//                    maceService.saveMaceReqIpDiagProc(mrip, mrid, mridp);
                maceService.saveMaceRequestIpRoom(mrip, mrir);
                maceService.saveTransaction(c, memberDetails);
                customerServiceService.saveTransaction(c, memberDetails);

                String requestCode = maceService.getRequestCodeByRequestId(mr.getRequestId());
                if (null != requestCode) {
                    response.put("requestCode", requestCode);
                }
                response.put("responseCode", "200");
                response.put("responseDesc", remarks);
                response.put("remarks", remarks);

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {

                if (Integer.valueOf("37").compareTo(retCode) == 0) {
                    remarks = "Member is Resigned";
                    responseCode = "201";
                    responseDesc = "Disapproved. Member no longer a MEDICard member";

                } else if (Integer.valueOf("58").compareTo(retCode) == 0) {
                    remarks = "Disapproved. Member has no inpatient access.";
                    responseCode = "202";
                    responseDesc = "Member has no inpatient access";
                } else if (Integer.valueOf("39").compareTo(retCode) == 0) {
                    remarks = "Disapproved. Member has no access to inpatient benefit.";
                    responseCode = "203";
                    responseDesc = "Member has no access to inpatient benefit";
                } else if (Integer.valueOf("42").compareTo(retCode) == 0) {
                    remarks = "Membership is on hold. Please call 841-8080 for approval";
                    responseCode = "204";
                    responseDesc = "Member is on hold";
                } else if (Integer.valueOf("38").compareTo(retCode) == 0) {
                    remarks = "No access to hospital. Please call 841-8080 for approval";
                    responseCode = "206";
                    responseDesc = "No access to hospital";
                } else {
                    remarks = "Unhandled Message. Please call 841-8080 for approval";
                    responseCode = "250";
                    responseDesc = "Unhandled Message Code";
                }
                c.setRemarks("Inpatient - Please call 841-8080 for approval.");
                c.setNotes(remarks);
                c.setActionTaken(1);
                maceService.saveTransactionForCall(c);
                customerServiceService.saveTransactionForCall(c);
            }
            response.put("responseCode", responseCode);
            response.put("responseDesc", responseDesc);
            response.put("remarks", remarks);

            //Check if company is blacklisted, call service to check blacklisted company
            boolean isBlacklisted = maceService.checkifCompanyIsBlacklisted(memberDetails.getACCOUNT_CODE(), "Inpatient");
            System.out.println("isBlacklisted:" + isBlacklisted);

            if (isBlacklisted) {
                c.setRemarks("Inpatient - ");
                return getResponseEntityForBlacklist(response, c);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            response.put("responseCode", "230");
            response.put("responseDesc", "Error processing request.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/getInpatientInfo", method = RequestMethod.GET)
    private ResponseEntity<?> getInpatientInfo(@RequestParam("requestCode") String requestCode) {
        HashMap<String, Object> response = new HashMap<>();

        MaceRequest mr = maceService.getMaceRequestByRequestCode(requestCode);
        if (null == mr) {
            response.put("responseDesc", "MaceRequest Code invalid.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        MaceReqInpatient mri = maceService.getMaceReqInpatientByReqId(mr.getRequestId());
        if (null == mri) {
            response.put("responseDesc", "Mace Request Inpatient not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        List<MaceReqIpDiag> mrid = maceService.getMaceReqIpDiagByReqId(mr.getRequestId());
        if (null == mrid) {
            response.put("responseDesc", "Mace Request IP Diag not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        List<MaceReqIpDoctor> mridc = maceService.getMaceReqIpDoctors(mr.getRequestId());
        MaceReqIpRoom mrip = maceService.getMaceReqIpRoom(mr.getRequestId(), mri.getTransactionId());
        if (null == mrip) {
            response.put("responseDesc", "Mace Request IP Room not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        //
        response.put("maceReq", mr);
        response.put("maceReqInpatient", mri);
        response.put("maceReqIpDiags", mrid == null ? new ArrayList<MaceReqIpDiag>() : mrid);
        response.put("maceReqIpDoctors", mridc == null ? new ArrayList<MaceReqIpDoctor>() : mridc);
        response.put("maceReqIpRoom", mrip);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully retrieved details");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //<editor-fold desc="private HashMap<String, Object> validateInpatientRequestRequestJson(InpatientRequestRequestJson irrj)" defaultstate="collapsed">
    private static final String NOT_FOUND_MSG = "Not found";

    private HashMap<String, Object> validateInpatientRequestRequestJson(InpatientRequestRequestJson irrj) {
        HashMap<String, Object> validationErrors = new HashMap<>();

        if (irrj.getMemberCode() == null) validationErrors.put("memberCode", NOT_FOUND_MSG);
        if (irrj.getHospitalCode() == null) validationErrors.put("hospitalCode", NOT_FOUND_MSG);
        if (irrj.getPrimaryDoctorCode() == null) validationErrors.put("primaryDoctorCode", NOT_FOUND_MSG);
        if (irrj.getPrimaryDiagnosisCode() == null) validationErrors.put("primaryDiagnosisCode", NOT_FOUND_MSG);
        if (irrj.getPrimaryDiagnosisType() == null) validationErrors.put("primaryDiagnosisType", NOT_FOUND_MSG);
        if (irrj.getPrimaryDiagnosisRemarks() == null) validationErrors.put("primaryDiagnosisRemarks", NOT_FOUND_MSG);
        if (irrj.getNotes() == null) validationErrors.put("notes", NOT_FOUND_MSG);

        if (irrj.getDoctors() == null)
            validationErrors.put("doctors", NOT_FOUND_MSG);

        if (irrj.getRooms() == null)
            validationErrors.put("rooms", NOT_FOUND_MSG);
        else {
            for (RoomJson rj : irrj.getRooms()) {
                if (rj.getCategory() == null) validationErrors.put("room.category", NOT_FOUND_MSG);
                if (rj.getPlan() == null) validationErrors.put("room.plan", NOT_FOUND_MSG);
                if (rj.getRate() == null) validationErrors.put("room.rate", NOT_FOUND_MSG);
                if (rj.getNumber() == null) validationErrors.put("room.number", NOT_FOUND_MSG);
                if (rj.getStartDate() == null) validationErrors.put("room.startDate", NOT_FOUND_MSG);
                if (rj.getEndDate() == null) validationErrors.put("room.endDate", NOT_FOUND_MSG);
            }
        }

        if (irrj.getDiagProcedureDoctorJson() == null)
            validationErrors.put("diagProcedureDoctors", NOT_FOUND_MSG);
        else {
            for (int i = 0; i < irrj.getDiagProcedureDoctorJson().length; i++) {
                DiagnosisProcedureDoctorJson dpdj = irrj.getDiagProcedureDoctorJson()[i];
                if (dpdj.getDiagnosisCode() == null)
                    validationErrors.put("diagProcedureDoctors[%d].diagnosisCode", NOT_FOUND_MSG);
                if (dpdj.getProcedureCode() == null)
                    validationErrors.put("diagProcedureDoctors[%d].procedureCode", NOT_FOUND_MSG);
                if (dpdj.getDoctorCode() == null)
                    validationErrors.put("diagProcedureDoctors[%d].doctorCode", NOT_FOUND_MSG);
                if (dpdj.getFee() == null) validationErrors.put("diagProcedureDoctors[%d].fee", NOT_FOUND_MSG);
                if (dpdj.getType() == null) validationErrors.put("diagProcedureDoctors[%d].type", NOT_FOUND_MSG);
                if (dpdj.getRemarks() == null) validationErrors.put("diagProcedureDoctors[%d].remarks", NOT_FOUND_MSG);
            }
        }

        if (irrj.getOtherServices() == null)
            validationErrors.put("otherServices", NOT_FOUND_MSG);
        else {
            for (int i = 0; i < irrj.getOtherServices().length; i++) {
                OtherServiceJson osj = irrj.getOtherServices()[i];
                if (osj.getAmount() == null) validationErrors.put("otherServices[%d].amount", NOT_FOUND_MSG);
                if (osj.getServiceType() == null) validationErrors.put("otherServices[%d].serviceType", NOT_FOUND_MSG);
                if (osj.getRemarks() == null) validationErrors.put("otherServices[%d].remarks", NOT_FOUND_MSG);
                if (osj.getStatus() == null) validationErrors.put("otherServices[%d].status", NOT_FOUND_MSG);
            }
        }

        return validationErrors.size() > 0 ? validationErrors : null;
        //</editor-fold>
    }

    @RequestMapping(value = "/inquireEmergencyRoomNoValidation/", method = RequestMethod.POST)
    public ResponseEntity<?> inquireEmergencyRoomNoValidation(@RequestBody EmergencyRoomInquiryJson erInquiryJson) {
        System.out.println(erInquiryJson);
        System.out.println(erInquiryJson.getUsername());
        System.out.println(erInquiryJson.getMemberCode());
//        System.out.println(erInquiryJson.getDeviceId());
//        System.out.println(erInquiryJson.getDiagnosisCode());
//        System.out.println(erInquiryJson.getDoctorCode());
        System.out.println(erInquiryJson.getHospitalCode());
//        System.out.println(erInquiryJson.getProcedureCode());
        System.out.println(erInquiryJson.getErReason());
        System.out.println(erInquiryJson.getDateAdmitted());


        HashMap<String, Object> response = new HashMap<>();
        try {

            MemberDetails memberDetails = memService.getMember(erInquiryJson.getMemberCode());

            //TODO Add Reason for Emergency
            //TODO Add Date Admitted
            CustomerCare c = maceService.processEmergencyRoomInquiry(
                    memberDetails,
                    erInquiryJson.getHospitalCode(),
                    erInquiryJson.getUsername(),
                    erInquiryJson.getErReason(),
                    erInquiryJson.getDateAdmitted()
            );

            String remarks = "INQUIRY FOR ER";
            String responseCode = "200";

            c.setRemarks(remarks);
            c.setActionTaken(5);
            c.setMemberCode(erInquiryJson.getMemberCode());

            c.setBatchCode(maceService.generateID("BATCHNO"));
            c.setCallerId(maceService.generateID("CALLERID"));


            maceService.saveTransactionForCall(c);
            customerServiceService.saveTransactionForCall(c);

            //TODO Save Mace Request
            //TODO Save Mace Request Er

            MaceRequest mr = new MaceRequest(c, memberDetails, 5, "COORDINATOR", erInquiryJson.getUsername());
            MaceReqEr mrer = new MaceReqEr(c, memberDetails, erInquiryJson);
            maceService.saveMaceRequest(mr);
            maceService.saveMaceRequestER(mr, mrer);

            response.put("memberDetails", memberDetails);
            response.put("referenceNo", c.getBatchCode());
            response.put("responseCode", responseCode);
            response.put("reasonOfER", erInquiryJson.getErReason());
            response.put("dateAdmitted", erInquiryJson.getDateAdmitted());
            response.put("responseDesc", remarks);
            response.put("remarks", remarks);
            response.put("maceReqId", mr.getRequestId());
            String requestCode = maceService.getRequestCodeByRequestId(mr.getRequestId());
            if (null != requestCode) {
                response.put("requestCode", requestCode);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            response.put("memberDetails", "");
            response.put("responseCode", "230");
            response.put("responseDesc", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "getLoaByHospitalAndMemberCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getLoaByHospitalAndMemberCode(@RequestParam("hospitalCode") String hospitalCode, @RequestParam("memberCode") String memberCode) {
        logger.info("getLoaByHospitalAndMemberCode");

        List loaList = maceService.getMemberLOAList(hospitalCode, memberCode);
        HashMap<String, Object> response = new HashMap<>();
        response.put("loaList", loaList);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully retrieved List.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/registerInpatient/", method = RequestMethod.POST)
    public ResponseEntity<?> registerInpatient(@RequestBody InpatientJson inpatientJson) {
        System.out.println(inpatientJson);
        System.out.println(inpatientJson.getUsername());
        System.out.println(inpatientJson.getMemberCode());
        System.out.println(inpatientJson.getDeviceId());
        System.out.println(inpatientJson.getDiagnosisCode());
        System.out.println(inpatientJson.getDoctorCode());
        System.out.println(inpatientJson.getHospitalCode());
        System.out.println(inpatientJson.getRoomType());
        System.out.println(inpatientJson.getProcedureCode());

        HashMap<String, Object> response = new HashMap<>();
        try {

            MemberDetails memberDetails = memService.getMember(inpatientJson.getMemberCode());

            CustomerCare c = maceService.processInpatient(
                    memberDetails,
                    inpatientJson.getHospitalCode(),
                    inpatientJson.getDoctorCode(),
                    inpatientJson.getDiagnosisCode(),
                    inpatientJson.getRoomType(),
                    inpatientJson.getRoomNumber(),
                    inpatientJson.getRoomPrice(),
                    inpatientJson.getUsername(),
                    inpatientJson.getProcedureCode(),
                    inpatientJson.getDateAdmitted(),
                    inpatientJson.getCategory()
            );

            Integer retCode = memService.validateInpatient(inpatientJson.getMemberCode(), inpatientJson.getHospitalCode());
            String remarks = "Inpatient";
            String responseCode = "200";
            String responseDesc = remarks;

            c.setRemarks(remarks);
            c.setActionTaken(4);
            c.setType(1);
            c.setBatchCode(maceService.generateID("BATCHNO"));
            c.setCallerId(maceService.generateID("CALLERID"));
            maceService.saveTransactionForCall(c);
            customerServiceService.saveTransactionForCall(c);
            loaService.insertInpatientRecord(c, inpatientJson);

            response.put("responseCode", responseCode);
            response.put("responseDesc", responseDesc);
            response.put("remarks", remarks);
            response.put("retCode", retCode);

            String email = env.getProperty("inpatient.email");
            String userEmail = maceService.getAppUserEmailByUsername(inpatientJson.getUsername());
            System.out.println("email:" + email);
            emailService.sendInPatientNotification(
                    email,
                    userEmail,
                    inpatientJson.getMemberCode(),
                    inpatientJson.getMemberName(),
                    inpatientJson.getHospitalName(),
                    inpatientJson.getDoctorCode(), //free text
                    inpatientJson.getDiagnosisCode(),   //free text
                    inpatientJson.getRoomNumber(),        //free text
                    inpatientJson.getRoomPrice(),       //free text
                    inpatientJson.getCategory(),       //free text
                    inpatientJson.getProcedureCode(),
                    inpatientJson.getDateAdmitted()
            );
            //Duplicate Email for testing.
            String email2 = env.getProperty("inpatient.email2");
            emailService.sendInPatientNotification(
                    email2,
                    userEmail,
                    inpatientJson.getMemberCode(),
                    inpatientJson.getMemberName(),
                    inpatientJson.getHospitalName(),
                    inpatientJson.getDoctorCode(), //free text
                    inpatientJson.getDiagnosisCode(),   //free text
                    inpatientJson.getRoomNumber(),        //free text
                    inpatientJson.getRoomPrice(),       //free text
                    inpatientJson.getCategory(),       //free text
                    inpatientJson.getProcedureCode(),
                    inpatientJson.getDateAdmitted()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            response.put("responseCode", "230");
            response.put("responseDesc", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/updateInpatient/", method = RequestMethod.POST)
    public ResponseEntity<?> updateInpatient(@RequestBody UpdateInpatientJson inpatientJson) {
        HashMap<String, Object> response = new HashMap<>();
        //Start
        MaceRequest mr = maceService.getMaceRequestByRequestCode(inpatientJson.getMaceRequestCode());
        if (null == mr) {
            response.put("responseDesc", "Invalid Mace Request Code.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        MemberDetails member = memService.getMember(mr.getRequestFrommem());
        if (null == member) {
            response.put("resonseDesc", "Invalid Member Code.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Hospital hospital = claimsService.getHospital(mr.getRequestFromhosp());
        if (null == hospital) {
            response.put("responseDesc", "Invalid Hospital Code.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        MaceReqInpatient mri = maceService.getMaceReqInpatientByReqId(mr.getRequestId());
        if (null == mri) {
            response.put("responseDesc", "MaceReqInpatient not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        List<MaceReqIpDiag> mrids = maceService.getMaceReqIpDiagByReqId(mr.getRequestId());
        if (null == mrids || mrids.isEmpty()) {
            response.put("responseDesc", "No diagnosis found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        List<MaceReqIpDiagproc> oldDiagProcs = maceService.getMaceReqIpDiagProcs(mr.getRequestId());

        List<MaceReqIpDoctor> mridcs = maceService.getMaceReqIpDoctors(mr.getRequestId());
        if (null == mridcs || mridcs.isEmpty()) {
            response.put("responseDesc", "No Doctors found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        //DoctorToHospital primaryDocHosp = claimsService.getDoctorToHospitalObject(hospital.getHospitalCode(), inpatientJson.getPrimaryDoctor());
        mr.setUpdateDetails(inpatientJson);
        mri.setUpdateDetails(inpatientJson, member, mrids.get(0), hospital.getHospitalCode());
        List<MaceReqIpDiag> oldDiags = maceService.getMaceReqIpDiagByReqId(mr.getRequestId());
        if (null == oldDiags || oldDiags.isEmpty()) {
            response.put("responseDesc", "No Diagnosis found with RequestCode.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        List<MaceReqIpDoctor> oldDocs = maceService.getMaceReqIpDoctors(mr.getRequestId());
        if (null == oldDocs || oldDocs.isEmpty()) {
            response.put("responseDesc", "No Doctors found with RequestCode.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        List<String> diagTemp = inpatientJson.getDiagnosisCodes();
        diagTemp.add(inpatientJson.getPrimaryDiag());
        inpatientJson.setDiagnosisCodes(diagTemp);
        List<MaceReqIpOtherservices> oldServices = maceService.getMaceReqIpServices(mr.getRequestId());
        List<Integer> maceReqIpDiagHits = new ArrayList<>();
        //Start improvement
        if (inpatientJson.getDiagnosisCodes() != null && !inpatientJson.getDiagnosisCodes().isEmpty()) {
            for (String newDiag : inpatientJson.getDiagnosisCodes()) {
                boolean hasMatch = false;
                for (MaceReqIpDiag oldDiag : oldDiags) {
                    if (newDiag.equals(oldDiag.getDiagCode())) {
                        maceReqIpDiagHits.add(oldDiag.getIpReqdiagId());
                        hasMatch = true;
                        break;
                    }
                }
                if (!hasMatch) {
                    //Create new maceReqIpDiag
                    MaceReqIpDiag mrid = new MaceReqIpDiag();
                    mrid.setMaceRequestId(mr.getRequestId());
                    mrid.setTransactionId(mri.getTransactionId());
                    mrid.setDiagnosisEntity(maceService.getDiagnosisEntity(newDiag), newDiag.equals(inpatientJson.getPrimaryDiag()) ? 1 : 2);
//                    maceService.saveMaceReqIpDiag(mri, mrid);
                }
            }
        }
        for (MaceReqIpDiag oldDiag : oldDiags) {
            boolean checkhit = false;
            for (Integer maceReqIpDiagHit : maceReqIpDiagHits) {
                if (oldDiag.getIpReqdiagId() == maceReqIpDiagHit)
                    checkhit = true;
            }
            if (!checkhit)
                maceService.deleteMaceReqIpDiag(oldDiag.getIpReqdiagId());
        }
        //MaceReqDiagProc Process
        List<Integer> maceReqIpDiagprocHits = new ArrayList<>();
        UpdateInpatientJson.CodeAndCost[] newDiagProcs = inpatientJson.getProcedureAndTestCodes();
        for (UpdateInpatientJson.CodeAndCost newDiagProc : newDiagProcs) {
            boolean hasMatch = false;
            for (MaceReqIpDiagproc oldDiagProc : oldDiagProcs) {
                if (newDiagProc.equals(oldDiagProc.getProcCode())) {
                    maceReqIpDiagHits.add(oldDiagProc.getIpReqdiagprocId());
                    hasMatch = true;
                }
            }
            if (!hasMatch) {
                //Create new MaceReqIpDiagProc
            }
        }
        //Delete no matched IpDiagProcHits
        for (MaceReqIpDiagproc oldDiagProc : oldDiagProcs) {
            for (Integer maceReqIpDiagprocHit : maceReqIpDiagprocHits) {
                boolean hasMatch = false;
                if (oldDiagProc.getReqDiagId() == maceReqIpDiagprocHit)
                    hasMatch = true;
                if (!hasMatch) {
                    maceService.deleteMaceReqIpDiagproc(oldDiagProc.getReqDiagId());
                }
            }
        }

        //MaceReqIpDoctor process
        List<Integer> maceReqIpDocHits = new ArrayList<>();
        if (inpatientJson.getDoctorCodes() != null) {
            for (String newDoc : inpatientJson.getDoctorCodes()) {
                boolean hasMatch = false;
                for (MaceReqIpDoctor oldDoc : oldDocs) {
                    if (newDoc.equals(oldDoc.getDoctorCode())) {
                        maceReqIpDocHits.add(oldDoc.getIpReqdocId());
                    }
                }
                if (!hasMatch) {
                    //Create new maceReqIpDoc
                    MaceReqIpDoctor mrid = new MaceReqIpDoctor();
                    DoctorToHospital dth = claimsService.getDoctorToHospitalObject(hospital.getHospitalCode(), newDoc, false);
                    mrid.setDocHospId(dth != null ? dth.getDocHospId() : Long.valueOf(0));
                    mrid.setDoctorCode(newDoc);
                    mrid.setHospitalCode(hospital.getHospitalCode());
//                    maceService.saveMaceReqIpDoctor(mr, mri, null, mrid);
                }
            }
        }
        //MaceReqIpDoctor process
        for (MaceReqIpDoctor oldDoc : oldDocs) {
            for (Integer maceReqIpDocHit : maceReqIpDocHits) {
                boolean hasMatch = false;
                if (oldDoc.getIpReqdocId() == maceReqIpDocHit)
                    hasMatch = true;
                if (!hasMatch)
                    maceService.deleteMaceReqIpDoctor(oldDoc.getIpReqdocId());
            }
        }

        maceService.updateMaceRequest(mr);
        maceService.updateMaceReqInpatient(mri);

        String email = env.getProperty("inpatient.email");
        String userEmail = maceService.getAppUserEmailByUsername(inpatientJson.getUsername());
        System.out.println("email:" + email);
        emailService.sendInPatientNotification(
                email,
                userEmail,
                member.getPRIN_CODE(),
                member.getMEM_NAME(),
                hospital.getHospitalName(),
                "", //free text
                "",   //free text
                inpatientJson.getRoomNumber(),        //free text
                inpatientJson.getRoomPrice(),       //free text
                inpatientJson.getCategory(),       //free text
                "",
                inpatientJson.getDateAdmitted()
        );
        //Duplicate Email for testing.
        String email2 = env.getProperty("inpatient.email2");
        emailService.sendInPatientNotification(
                email2,
                userEmail,
                member.getPRIN_CODE(),
                member.getMEM_NAME(),
                hospital.getHospitalName(),
                "", //free text
                "",   //free text
                inpatientJson.getRoomNumber(),        //free text
                inpatientJson.getRoomPrice(),       //free text
                inpatientJson.getCategory(),       //free text
                "",
                inpatientJson.getDateAdmitted()
        );

        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully updated inpatient info.");
        response.put("maceReqId", mr.getRequestId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/verifyInPatient/", method = RequestMethod.POST)
    public ResponseEntity<?> verifyInPatient(@RequestParam("hospitalCode") String hospitalCode,
                                             @RequestParam("memberCode") String memberCode,
                                             @RequestParam("coordinatorUsername") String coordinatorUsername
    ) {

        logger.info("checkInPatient");
        logger.info("checkInPatient" + hospitalCode);
        logger.info("checkInPatient" + memberCode);
        logger.info("checkInPatient" + coordinatorUsername);

        HashMap<String, Object> response = new HashMap<>();
        try {

            loaService.checkInpatientRecord(hospitalCode, memberCode);

            return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception e) {
            e.printStackTrace();

            response.put("responseCode", "230");
            response.put("responseDesc", "Incorrect username or password.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/releaseInPatient/", method = RequestMethod.POST)
    public ResponseEntity<?> releaseInPatient(@RequestParam("hospitalCode") String hospitalCode,
                                              @RequestParam("memberCode") String memberCode,
                                              @RequestParam("coordinatorUsername") String coordinatorUsername
    ) {

        logger.info("releaseInPatient");
        logger.info("releaseInPatient" + hospitalCode);
        logger.info("releaseInPatient" + memberCode);
        logger.info("releaseInPatient" + coordinatorUsername);

        HashMap<String, Object> response = new HashMap<>();
        try {

            loaService.releaseInpatientRecord(hospitalCode, memberCode);
            response.put("responseCode", "200");
            response.put("responseDesc", "Patient successfully released!");
            return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception e) {
            e.printStackTrace();

            response.put("responseCode", "230");
            response.put("responseDesc", "Incorrect username or password.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}