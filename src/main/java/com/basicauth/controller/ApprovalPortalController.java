package com.basicauth.controller;

import com.basicauth.config.Constants;
import com.basicauth.data.*;
import com.basicauth.domain.*;
import com.basicauth.service.*;
import com.basicauth.service.approval.ApprovalEngine;
import com.basicauth.service.approval.ApprovalEngineResult;
import com.basicauth.types.ConsultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.basicauth.config.Constants.DEFAULT_GROUP;

/**
 * Created by IPC_Laptop056 on 11/10/2017.
 */
@RestController
@RequestMapping(value = "/approvalPortal")
public class ApprovalPortalController {

    @Autowired
    private MaceService maceService;

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private MemService memService;

    @Autowired
    private ClaimsService claimsService;

    @Autowired
    private ApprovalEngine approvalEngine;

    @RequestMapping(value = "/requestLOAConsult/", method = RequestMethod.POST)
    public ResponseEntity<?> requestLOAConsult(@RequestBody OutpatientRequestJson oprj) {
        oprj.setServiceSubtype(1);
        return processMaceRequestApprovalPortal(oprj, 1);
    }

    @RequestMapping(value = "/requestLOAMaternity/", method = RequestMethod.POST)
    public ResponseEntity<?> requestLOAMaternity(@RequestBody OutpatientRequestJson oprj) {
        oprj.setServiceSubtype(2);
        return processMaceRequestApprovalPortal(oprj, 1);
    }

    private HashMap<String, Object> validateOutpatientRequestJson(OutpatientRequestJson oprj) {
        HashMap<String, Object> validationErrors = new HashMap<>();

        // TODO: validate json

        return validationErrors.size() > 0 ? validationErrors : null;
    }

    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/requestBasicOrOtherTest", method = RequestMethod.POST)
    public ResponseEntity<?> requestBasicOrOtherTest(@RequestBody OutpatientRequestJson oprj) {
        //Service type ID for Tests is 2
        return processMaceRequestApprovalPortal(oprj, 2);
    }

    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/requestLoaForProcedures/", method = RequestMethod.POST)
    public ResponseEntity<?> requestLoaForProcedures(@RequestBody OutpatientRequestJson oprj) {
        //Service type ID for Procedures is 3
        return processMaceRequestApprovalPortal(oprj, 3);
    }

    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/requestOperatingRoom", method = RequestMethod.POST)
    public ResponseEntity<?> requestOperatingRoom(@RequestBody OutpatientRequestJson oprj) {
        //Service type ID for OP/OR is 6
        return processMaceRequestApprovalPortal(oprj, 6);
    }

    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/inquireInpatient", method = RequestMethod.POST)
    public ResponseEntity<?> inquireInpatientPortal(@RequestBody MaceInpatientPortalJson mipj) {
        //Service type ID for IP is 4
        return processMaceRequestApprovalPortalInpatient(mipj);
    }

    private ResponseEntity<?> processMaceRequestApprovalPortalInpatient(MaceInpatientPortalJson mipj){
        HashMap<String, Object> response = new HashMap<>();

        MemberDetails member = memService.getMember(mipj.getMemberCode());
        if(null == member){
            response.put("responseCode", HttpStatus.NOT_FOUND);
            response.put("responseDesc", "Member ID Invalid.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Hospital hospital = claimsService.getHospital(mipj.getHospitalCode());
        if(null == hospital){
            response.put("responseCode", HttpStatus.NOT_FOUND);
            response.put("responseDesc", "Invalid Hospital Code.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //Todo Insert MaceRequest
        MaceRequest mr = new MaceRequest();
        mr.setMemberDetails(member);
        mr.setRequestOrigin(mipj.getRequestOrigin());
        mr.setHospContact(hospital.getPhoneNo());
        mr.setRequestFromhosp(mipj.getHospitalCode());
        mr.setRequestBy(mipj.getRequestBy());
        mr.setRequestByCode(mipj.getRequestByCode());
        mr.setRequestDatetime(mipj.getRequestDate());
        mr.setHospEmail(hospital.getCoordinator());
        mr.setServiceType("INPATIENT");
        mr.setServiceTypeId(4);
        mr.setStatus(mipj.getSubmitForApproval() ? "PENDING" : "DRAFT");
        mr.setAdmissionDate(mipj.getAdmissionDate());
        mr.setAdmissionType(mipj.getAdmissionType());
        maceService.saveMaceRequest(mr);
        //Todo Insert MaceReqInpatient
        String requestCode = maceService.generateID("APPROVALNO");

        MaceReqInpatient mri = new MaceReqInpatient(mipj);

        //For IP, unaccredited doctors may be retrieved
        DoctorToHospital requestingDocHosp = claimsService.getDoctorToHospitalObject(mipj.getRequestingHospCode(), mri.getDoctorCode(), true);
        mri.setLoaNo(requestCode);
        mri.setRequestCode(requestCode);
        mri.setDocHospId(null == requestingDocHosp ? 0: requestingDocHosp.getDocHospId());
        mri.setRequestFrom("MACE APPROVAL PORTAL");
        mri.setRecordSubmittedOn(new Date());
        String transCode = maceService.generateID("INPATIENT");
        mri.setTransCode(transCode);
        mri.setStatus(mipj.getSubmitForApproval() ? "PENDING" : "DRAFT");
        mri.setDischargedOn(mipj.getDischargeDate());
        mri.setDisposition(mipj.getDisposition());
        mri.setIpReasonRemarks(mipj.getReasonForAdmission());
        Diagnosis primaryDiagnosis = claimsService.getDiagnosisByDiagnosisCode(mipj.getPrimaryDiagnosisCode());
        if(null != primaryDiagnosis){
            mri.setPrimaryDiagnosisCode(primaryDiagnosis.getDiagCode());
            mri.setPrimaryDiagnosisIcd10(primaryDiagnosis.getIcd10Code());
            mri.setPrimaryDiagnosisDesc(primaryDiagnosis.getDiagDesc());
            mri.setDxRemarks(primaryDiagnosis.getDiagRemarks());
        }
        maceService.saveMaceRequestInpatient(mr,mri);

        //Todo Insert MaceReqIpDiagnoses
        HashMap<String,MaceReqIpDiag> ipDiagDiagCodeMap = new HashMap<>();
        for (MaceInpatientPortalJson.IPDiagnosis ipDiagnosis : mipj.getDiagnoses()) {
            MaceReqIpDiag mridg = new MaceReqIpDiag();
            mridg.setDiagnosisEntity(maceService.getDiagnosisEntity(ipDiagnosis.getDiagnosisCode()), 1);
            mridg.setDiagCaseType(ipDiagnosis.getDiagCaseType());
            mridg.setTransCode(transCode);

            mridg.setDiagCaseType(ipDiagnosis.getDiagCaseType());
            mridg.setDiagRemarks(ipDiagnosis.getDiagRemarks());
            mridg.setToRuleOut(ipDiagnosis.getToRuleOut());
            mridg.setToConsider(ipDiagnosis.getToConsider());
            mridg.setVersus(ipDiagnosis.getVersus());
            mridg.setDiseaseLimit(ipDiagnosis.getDiseaseLimit() != null ? BigDecimal.valueOf(ipDiagnosis.getDiseaseLimit()) : null);
            mridg.setRemLimit(ipDiagnosis.getRemLimit() != null ? BigDecimal.valueOf(ipDiagnosis.getRemLimit()) : null);
            mridg.setIsPrimary(ipDiagnosis.getIsPrimary());
            mridg.setIsAdmitting(ipDiagnosis.getIsAdmitting());
            mridg.setIsFinal(ipDiagnosis.getIsFinal());

            maceService.saveMaceReqIpDiag(mr, mri, mridg);
            //Assume diagnosis entries must be unique (no duplicate diagnosis entries)
            ipDiagDiagCodeMap.put(ipDiagnosis.getDiagnosisCode(),mridg);
        }

        HashMap<String,MaceReqIpDiagproc> ipDiagProcProcCodeMap = new HashMap<>();
        for (MaceInpatientPortalJson.IPProcedure ipProcedure : mipj.getProcedures()) {
            MaceReqIpDiagproc mridp = new MaceReqIpDiagproc();

            TestProcObject testProcObject = claimsService.getTestProcObject(ipProcedure.getProcedureCode());
            HospitalProcedureAmountView hpav = claimsService.getHospProcAmount(hospital.getHospitalCode(), testProcObject.getProcCode());
            Diagnosis diag = claimsService.getDiagnosisByDiagnosisCode(ipProcedure.getDiagnosisCode());

            mridp.setHospAmount(null != hpav ? hpav.getHospAmount() : null);
            mridp.setDefAmount(testProcObject != null ? testProcObject.getAmount() : null);
            mridp.setActualAmount(null != ipProcedure.getAmount() ? BigDecimal.valueOf(ipProcedure.getAmount()) : null);
            mridp.setProcCode(testProcObject.getProcCode());
            mridp.setProcDesc(testProcObject.getProcName());
            mridp.setDiagCode(diag.getDiagCode());
            mridp.setReqDiagId(ipDiagDiagCodeMap.get(diag.getDiagCode()).getIpReqdiagId());
            //Assume that every procedure must be mapped to an already saved diagnosis entry.
            //Consider error checks to ensure this assumption
            maceService.saveMaceReqIpDiagProc(mri, mridp);
            ipDiagProcProcCodeMap.put(ipProcedure.getDiagnosisCode(),mridp);
        }

        /**
         * Save doctors inputted by Approval Portal user.
         * */
        for(MaceInpatientPortalJson.IPDoctor doctorJson : mipj.getDoctors()){
            //For IP, unaccredited doctors may be retrieved
            DoctorToHospital doctorGridEntry = claimsService.getDoctorToHospitalObject(mipj.getHospitalCode(), doctorJson.getDoctorCode(), true);
            TestProcObject doctorTpo = null;
            Integer refDiagProcId = null;
            if(doctorJson.getProcedureCode() != null){
                doctorTpo = claimsService.getTestProcObject(doctorJson.getProcedureCode());
                MaceReqIpDiagproc refDiagProc = ipDiagProcProcCodeMap.get(doctorJson.getProcedureCode());
                refDiagProcId = refDiagProc != null ? refDiagProc.getReqDiagId() : null;
            }
            MaceReqIpDoctor mriDoc = new MaceReqIpDoctor();
            mriDoc.setDoctorEntity(doctorGridEntry, mipj.getHospitalCode(), doctorTpo);
            mriDoc.setAddedOn(new Date());
            mriDoc.setAddedBy(mipj.getRequestBy());
            maceService.saveMaceReqIpDoctor(mri, refDiagProcId, mriDoc);
        }

        /**
         * Save rooms inputted by Approval Portal user.
         * */
        for(MaceInpatientPortalJson.IPRoom roomJson : mipj.getRooms()){
            MaceReqIpRoom mrir = new MaceReqIpRoom();
            mrir.setRoom(roomJson, mipj.getHospitalCode());
            mrir.setLastUpdateOn(new Date());
            mrir.setLastUpdateBy(mipj.getRequestBy());
            mrir.setAddedOn(new Date());
            mrir.setAddedBy(mipj.getRequestBy());
            maceService.saveMaceRequestIpRoom(mri, mrir);
        }

        /**
         * Save other services inputted by Approval Portal user.
         * */
        for(MaceInpatientPortalJson.IPOtherCharges otherChargeJson : mipj.getOtherCharges()){
            MaceReqIpOtherCharge mriOc = new MaceReqIpOtherCharge();
            mriOc.setChargeType(otherChargeJson.getOtherChargeType());
            mriOc.setDiagCode(otherChargeJson.getDiagnosisCode());
            Diagnosis otherChargeDiagnosis = claimsService.getDiagnosisByDiagnosisCode(otherChargeJson.getDiagnosisCode());
            if(otherChargeDiagnosis != null){
                mriOc.setDiagDesc(otherChargeDiagnosis.getDiagDesc());
            }
            mriOc.setDetails(otherChargeJson.getOtherChargeDetails());
            mriOc.setChargeAmount(otherChargeJson.getAmount() != null ?
                    BigDecimal.valueOf(otherChargeJson.getAmount()) : null);
            maceService.saveMaceRequestIpOtherCharge(mri, mriOc);
        }

        /**
         * Save other information inputted by Approval Portal user.
         * */
        for(MaceInpatientPortalJson.IPOtherInformation otherInformationJson : mipj.getOtherInformation()){
            MaceReqIpOtherInformation mriOi = new MaceReqIpOtherInformation();
            mriOi.setInformationType(otherInformationJson.getOtherInformationType());
            mriOi.setDetails(otherInformationJson.getOtherInformationDetails());
            maceService.saveMaceRequestIpOtherInformation(mri, mriOi);
        }


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<?> processMaceRequestApprovalPortal(OutpatientRequestJson oprj, Integer serviceType){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> validationErrors = validateOutpatientRequestJson(oprj);
        //Do not get unaccredited doctors if request is consultation
        Boolean getUnaccreditedDoctors = serviceType != 1;
        Boolean submitForApproval = oprj.getSubmitForApproval();

        if (validationErrors != null) {
            response.put("validationErrors", validationErrors);
            response.put("responseCode", 412);
            response.put("responseDesc", "The request body contains validation errors.");

            return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
        }

        //TODO: Get from cache and not the database
        //For OP/OR, unaccredited doctors may be retrieved
        Doctor doctor = claimsService.getDoctor(oprj.getDoctorCode(), getUnaccreditedDoctors);
        if (doctor == null) {
            if(serviceType == 1){
                //Requesting doctor is not required in consultation requests
                doctor = new Doctor();
            }
            else{
                response.put("responseCode", 404);
                response.put("responseDesc", String.format("The doctor (code: %s) " +
                        "was not found in the database.", oprj.getDoctorCode()));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }

        //TODO: Get from cache and not the database
        Hospital hospital = claimsService.getHospital(oprj.getHospitalCode());
        if (hospital == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The hospital (code: %s) " +
                    "was not found in the database.", oprj.getHospitalCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        MemberDetails memberDetails = memService.getMember(oprj.getMemberCode());
        if (memberDetails == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The member (code: %s) " +
                    "was not found in the database.", oprj.getMemberCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //TODO: Get from cache and not the database
        //Consultation requests do not have a primary diagnosis to validate
        Diagnosis primaryDiagnosis = new Diagnosis();
        if(serviceType != 1){
            Diagnosis checkPrimaryDiagnosis = claimsService.getDiagnosisByDiagnosisCode(oprj.getPrimaryDiagnosisCode());
            if (checkPrimaryDiagnosis == null) {
                response.put("responseCode", 404);
                response.put("responseDesc", String.format("The primary diagnosis (code: %s) " +
                        "was not found in the database.", oprj.getPrimaryDiagnosisCode()));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            primaryDiagnosis = checkPrimaryDiagnosis;
        }

        //TODO: Get from cache and not the database
        //For OP/OR, unaccredited doctors may be retrieved
        DoctorToHospital requestingDocHosp = claimsService.getDoctorToHospitalObject(oprj.getRequestingHospCode(), oprj.getDoctorCode(), getUnaccreditedDoctors);
        //Mapping of Doctor to Hospital is not yet strict, this may be null

        String finalStatus = "APPROVED";
        String finalDesc = "";
        String finalAssignee = Constants.DEFAULT_GROUP;

        String responseCode = "";
        String responseDesc = "";
        String remarks = "";

        /**
         * Get the Collated cost of all tests/procedures of request
         * */
        BigDecimal collatedCost = BigDecimal.ZERO;
        for (OutpatientRequestJson.DiagnosisProcedureJson dpj : oprj.getDiagnosisProcedures()) {
            collatedCost = collatedCost.add(null == dpj.getAmount() || dpj.getAmount() == 0 ? claimsService.getCostOfTestProc(dpj.getProcedureCode()) : BigDecimal.valueOf(dpj.getAmount()));
        }
        System.out.println("COLLATED " + String.valueOf(collatedCost));
        //Initialize CustomerCare
        CustomerCare c = maceService.initCustomerCare(memberDetails, hospital.getHospitalCode(),
                oprj.getDoctorCode(), oprj.getPrimaryDiagnosisCode(), oprj.getDiagnosisProcedures().length > 0 ? oprj.getDiagnosisProcedures()[0].getProcedureCode() : null,
                collatedCost, "", oprj.getAppUsername(),
                "",
                memberDetails.getPRIN_CODE(),
                primaryDiagnosis.getDiagDesc(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                oprj.getPrimaryComplaint(),
                oprj.getRequestBy());
        c.setRequestOrigin(oprj.getRequestOrigin());

        //Initialize header request data. Overriding of status and status assignee will be done after V2 Approval Engine executions
        HashMap<String, List<String>> reasonMessagesMap = new HashMap<>();
        LocalDate birthday = LocalDate.parse(memberDetails.getBDAY(), DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        Period period = Period.between(birthday, LocalDate.now());

        MaceRequest mr = new MaceRequest();
        //TODO
        if(!oprj.getIsOverriden()){
            mr = maceService.retrieveHeaderRequest(oprj.getParRequestId());
        }

        mr.setServiceTypeId(serviceType);
        mr.setMemCode(oprj.getMemberCode());
        mr.setMemLname(memberDetails.getMEM_LNAME());
        mr.setMemFname(memberDetails.getMEM_FNAME());
        mr.setMemMi("");
        mr.setMemCompany(memberDetails.getACCOUNT_NAME());
        mr.setMemAcct(memberDetails.getACCOUNT_CODE());
        mr.setMemStat(memberDetails.getMem_OStat_Code());
        mr.setMemGender(memberDetails.getMEM_SEX() == 1 ? "MALE" : memberDetails.getMEM_SEX() == 0 ? "FEMALE" : "UNKNOWN");
        mr.setMemAge(period.getYears());
        mr.setMemType(memberDetails.getACCT_TYPE());
        mr.setIdremarks(memberDetails.getID_REM());
        mr.setHospContact(oprj.getCoorContact());
        mr.setHospEmail(oprj.getCoorEmail());
        try {
            mr.setMemBdate(new SimpleDateFormat("MMM dd, yyyy").parse(memberDetails.getBDAY()));
            mr.setAcctValidity(new SimpleDateFormat("MMM dd, yyyy").parse(memberDetails.getVAL_DATE()));
            mr.setAcctEffectivity(new SimpleDateFormat("MMM dd, yyyy").parse(memberDetails.getEFF_DATE()));
        } catch (Exception e) {
        }
        mr.setRequestOrigin(oprj.getRequestOrigin());
        mr.setRequestFrommem(oprj.getMemberCode());
        mr.setRequestFromhosp(oprj.getHospitalCode());
        mr.setRequestByCode(oprj.getRequestByCode());
        mr.setRequestBy(oprj.getRequestBy());
        mr.setRequestDevice(oprj.getRequestDevice());
        //If requested via approval portal, set request datetime to requestDate sent from Approval Portal (defaulted to current datetime when the outpatient page was opened)
        mr.setRequestDatetime(oprj.getRequestDate());
        mr.setDisclaimerTicked(oprj.getDisclaimerTicked());//set to true
        mr.setLastupdateOn(new Date());
        mr.setLastupdateBy(oprj.getRequestBy());
        mr.setOverride(false);
        if(oprj.getParRequestId() != null && oprj.getIsOverriden()){
            mr.setParRequestId(oprj.getParRequestId());
            maceService.overrideParentRequest(oprj.getParRequestId());
        }
        else{
            mr.setParRequestId(0);
        }
        mr.setMbasCode(null);
        mr.setMbasApprover(null);
        mr.setMbasupdateOn(null);
        //Save additional input fields from new Approval Portal Outpatient UI
        mr.setTotalUtilization(oprj.getTotalUtilization());
        mr.setAdmissionType(oprj.getAdmissionType());
        mr.setAdmissionDate(oprj.getAdmissionDate());

        //Save MaceRequest Audit Log
        MaceRequestAudit maceRequestAudit = new MaceRequestAudit();
        maceRequestAudit.setLogDateTime(new Date());
        maceRequestAudit.setHostname("");
        maceRequestAudit.setUserId(oprj.getRequestBy());
        maceRequestAudit.setDeviceId(oprj.getRequestDevice());
        maceRequestAudit.setUserType(oprj.getRequestOrigin());
        maceRequestAudit.setFacility("");
        maceRequestAudit.setFunctionName("requestOperatingRoom");
        maceRequestAudit.setDetail("Created MaceRequest for requestOperatingRoom.");
        maceRequestAudit.setTranstype("");
        maceRequestAudit.setMemberId(oprj.getMemberCode());
        maceRequestAudit.setOldValue("");
        maceRequestAudit.setNewValue("");
        maceService.saveMaceRequestAuditLog(mr, maceRequestAudit);

        //Loop through the list of DiagnosisProcedureJson and process MaceReqTest or MaceReqProc
        List<BasicOrOtherTestResponseJson.DiagnosisProcedureJson> diagProcJson = new ArrayList<>();
        List<BasicOrOtherTestResponseJson.DiagnosisClinicProcedureJson> diagClinicProcJson = new ArrayList<>();

        //Flag for all test approved
        boolean allApproved = true;

        for (OutpatientRequestJson.DiagnosisProcedureJson diagnosisProcedureJson : oprj.getDiagnosisProcedures()) {
            TestProcObject tpo = claimsService.getTestProcObject(diagnosisProcedureJson.getProcedureCode());
            diagnosisProcedureJson.setDiagType(diagnosisProcedureJson.getDiagnosisCode().equals(primaryDiagnosis.getDiagCode()) ? 1 : 2);
            //If request is done for procedures, force cost center to be blank so any tests submitted would not be grouped separately from any submitted clinic procedures
            diagnosisProcedureJson.setCostCenter(serviceType == 3 ? "" : tpo.getCostCenter());
        }

        //Group by Diagnosis
        /** ===================================
         *  Major revision in insertion process
         *  ===================================*/
        MaceRequestReturn mrr = new MaceRequestReturn(mr);
        mrr.setServiceType("OTHER TEST");
        //MaceInsertOrder holds all Mace Objects for an ordered insertion later on for arranged dependencies(PK/FK).
        MaceInsertOrder mio = new MaceInsertOrder();
        BigDecimal totalAmount = BigDecimal.ZERO;

        //Process consultation before any tests/procedures
        if(serviceType == 1){
            ConsultJson consultJson = new ConsultJson(oprj);
            String consultRemarks = "CONSULTATION VIA APPROVAL PORTAL";

            /*
            * Run Service for Consult.
            * Assume that we will always get a maceInsertOrder element because the two causes a maceInsertOrder value
            * could not be retrieved are already checked at the start of the processMaceRequestApprovalPortal method.
            * (DB queries for Hospital and MemberDetails)
            * Consider optimizing this by bypassing those redundant DB queries.
            * */
            ResponseEntity<?> responseCons = maceService.processConsultationV2(consultJson, consultJson.getConsultSubtype(), consultJson.getRequestOrigin(), consultRemarks);
            HashMap<String, Object> consultResponseBody = (HashMap) responseCons.getBody();
            MaceInsertOrder mioc = (MaceInsertOrder) consultResponseBody.get("maceInsertOrder");
            mio.setMrcObj(mioc.getMrcObj());

            //Set allApproved and finalAssignee values to the values from the Consult transaction
            //They will be used if no clinic procedures were added to the consult request
            List<String> reasonsList = new ArrayList<>();
            for(MaceRequestApproval mra : mio.getMrcObj().getMraObjs()){
                if (!mra.getResultStatus().equals(Constants.REQUEST_AUTOMATIC_APPROVED)) {
                    allApproved = false;

                    //Update final status assignee
                    finalAssignee = approvalEngine.updateFinalStatusAssignee(finalAssignee, mra.getResultAssignee(), hospital);
                }
            }
            reasonsList.addAll(mio.getMrcObj().getReasonMessages());
            reasonMessagesMap.put(mio.getMrcObj().getMrc().getTransCode(), reasonsList);
        }

        /**
         * Save doctors inputted by Approval Portal user.
         * */
        List<MaceRequestOpDoctor> mrdObjs = new ArrayList<>();
        for(OutpatientRequestJson.DoctorJson doctorJson : oprj.getDoctors()){
            //For OP/OR, unaccredited doctors may be retrieved
            DoctorToHospital doctorGridEntry = claimsService.getDoctorToHospitalObject(oprj.getHospitalCode(), doctorJson.getDoctorCode(), getUnaccreditedDoctors);
            TestProcObject doctorTpo = null;
            if(doctorJson.getProcedureCode() != null){
                doctorTpo = claimsService.getTestProcObject(doctorJson.getProcedureCode());
            }
            MaceRequestOpDoctor mroDoc = new MaceRequestOpDoctor();
            mroDoc.setDoctorEntity(doctorGridEntry, oprj.getHospitalCode(), doctorTpo);
            mroDoc.setAddedOn(new Date());
            mroDoc.setAddedBy(oprj.getRequestBy());
            mrdObjs.add(mroDoc);
        }
        mio.setMrdObjs(mrdObjs);

        /**
         * Save rooms inputted by Approval Portal user.
         * */
        List<MaceRequestOpRoom> mrRoomObjs = new ArrayList<>();
        for(OutpatientRequestJson.RoomJson roomJson : oprj.getRooms()){
            MaceRequestOpRoom mror = new MaceRequestOpRoom();
            mror.setRoom(roomJson, oprj.getHospitalCode());
            mror.setLastUpdateOn(new Date());
            mror.setLastUpdateBy(oprj.getRequestBy());
            mror.setAddedOn(new Date());
            mror.setAddedBy(oprj.getRequestBy());
            mrRoomObjs.add(mror);
        }
        mio.setMrRoomObjs(mrRoomObjs);

        /**
         * Save other services inputted by Approval Portal user.
         * */
        List<MaceRequestOpOtherCharge> mrOsObjs = new ArrayList<>();
        for(OutpatientRequestJson.OtherChargeJsonOutpatient otherChargeJson : oprj.getOtherCharges()){
            MaceRequestOpOtherCharge mroOs = new MaceRequestOpOtherCharge();
            mroOs.setChargeType(otherChargeJson.getOtherChargeType());
            mroOs.setDiagCode(otherChargeJson.getDiagnosisCode());
            Diagnosis otherChargeDiagnosis = claimsService.getDiagnosisByDiagnosisCode(otherChargeJson.getDiagnosisCode());
            if(otherChargeDiagnosis != null){
                mroOs.setDiagDesc(otherChargeDiagnosis.getDiagDesc());
            }
            mroOs.setDetails(otherChargeJson.getOtherChargeDetails());
            mroOs.setChargeAmount(otherChargeJson.getAmount() != null ?
                    BigDecimal.valueOf(otherChargeJson.getAmount()) : null);
            mrOsObjs.add(mroOs);
        }
        mio.setMrOsObjs(mrOsObjs);

        /**
         * Save other information inputted by Approval Portal user.
         * */
        List<MaceRequestOpOtherInformation> mrOiObjs = new ArrayList<>();
        for(OutpatientRequestJson.OtherInformationJson otherInformationJson : oprj.getOtherInformation()){
            MaceRequestOpOtherInformation mroOi = new MaceRequestOpOtherInformation();
            mroOi.setInformationType(otherInformationJson.getOtherInformationType());
            mroOi.setDetails(otherInformationJson.getOtherInformationDetails());
            mrOiObjs.add(mroOi);
        }
        mio.setMrOiObjs(mrOiObjs);

        //TODO: Save Other Services and Other Information

        /**
         * Group by Cost Center First.
         * Assume that Procedures have an empty cost center string.
         * If executed for a Procedures request, any Tests submitted should have their cost centers treated as blank.
         * */
        Map<String, List<OutpatientRequestJson.DiagnosisProcedureJson>> byCostCenter = Arrays.stream(oprj.getDiagnosisProcedures())
                .collect(Collectors.groupingBy(OutpatientRequestJson.DiagnosisProcedureJson::getCostCenter, Collectors.toList()));
        List<MaceRequestTestObj> mrtObjs = new ArrayList<>();
        List<MaceRequestProcObj> mrpObjs = new ArrayList<>();
        List<MaceRequestReturn.GroupedByCostCenter> gbccArrayList = new ArrayList<>();
        //Iterate through the list grouped by CostCenter and process and further group.
        //Adding CostCenter(MACEREQ_TEST) insert inside diagnosis iteration to separate ApprovalNumbers for different diagnoses
        for (Map.Entry<String, List<OutpatientRequestJson.DiagnosisProcedureJson>> perCostCenter : byCostCenter.entrySet()) {
            //Save procedure requests here.
            //Do not save any procedures if request is Tests
            if(perCostCenter.getKey().isEmpty() && serviceType != 2){

                Map<String, List<OutpatientRequestJson.DiagnosisProcedureJson>> dpjGroupedByDiag = perCostCenter.getValue().stream()
                        .collect(Collectors.groupingBy(OutpatientRequestJson.DiagnosisProcedureJson::getDiagnosisCode, Collectors.toList()));

                DiagnosisEntity de = new DiagnosisEntity();
                BigDecimal total = BigDecimal.ZERO;
                String requestCode = "";
                String costCenterStatus = "APPROVED";
                MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[] gbdList = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[dpjGroupedByDiag.size()];
                int x = 0;

                for (Map.Entry<String, List<OutpatientRequestJson.DiagnosisProcedureJson>> groupedDpj : dpjGroupedByDiag.entrySet()) {
                    Diagnosis groupDiag = claimsService.getDiagnosisByDiagnosisCode(groupedDpj.getValue().get(0).getDiagnosisCode());
                    MaceRequestProcObj mrpObj = new MaceRequestProcObj();
                    //Process Procedures
                    List<TestProcObject> procObjects = new ArrayList<>();
                    for (OutpatientRequestJson.DiagnosisProcedureJson procs : groupedDpj.getValue()) {
                        TestProcObject tpo = claimsService.getTestProcObject(procs.getProcedureCode());
                        if (tpo != null) {
                            //Set service type to 3 (Procedures)
                            tpo.setServiceType(3);
                            //Set procedures subtype = 0(Procedures has no subtype)
                            tpo.setSubType(0);
                            //If amount passed from DiagProcJson for this specific procedure is not null, override value of testProc
                            if (procs.getAmount() != null) {
                                tpo.setAmount(BigDecimal.valueOf(procs.getAmount()));
                            }
                            procObjects.add(tpo);
                        }
                    }
                    requestCode = maceService.generateID("APPROVALNO");
                    MaceRequestProcedure mrp = new MaceRequestProcedure();
                    mrp.setApprovalNo(requestCode);
                    mrp.setConsultReason(oprj.getPrimaryComplaint());
                    mrp.setDoctorCode(oprj.getDoctorCode());
                    mrp.setHospitalCode(oprj.getHospitalCode());
                    //If doctorToHospitalObject is null(Doctor not mapped to Hospital), set docHospId value to 0
                    mrp.setDocHospId(null == requestingDocHosp ? 0 : requestingDocHosp.getDocHospId());
                    mrp.setPrimaryDiagnosisCode(groupDiag.getDiagCode());
                    mrp.setPrimaryDiagnosisDesc(groupDiag.getDiagDesc());
                    mrp.setPrimaryDiagnosisICD10(groupDiag.getIcd10Code());
                    mrp.setDxRemarks(groupDiag.getDiagRemarks());
                    mrp.setAvailHospId(hospital.getHospitalCode());
                    mrp.setNotes(oprj.getRemarks());
                    //Generate transCode and map reason messages to this transCode
                    String transCode = maceService.generateID("PROCEDUREREQUEST");
                    mrp.setTransCode(transCode);
                    //Procedures has no equivalent MaceReqSubtype
                    mrp.setTestSubtype(0);

                    MaceRequestOpDiag mrod = new MaceRequestOpDiag();
                    de = maceService.getDiagnosisEntity(groupDiag.getDiagCode());
                    mrod.setDiagnosisEntity(de);
                    //Set case type, remarks, diag type flags, and limits sent from approval portal
                    //Assume remarks, case type, diag type flags, and limits are the same when grouped by diagnosis
                    mrod.setDiagCaseType(groupedDpj.getValue().get(0).getDiagCaseType());
                    mrod.setDiagRemarks(groupedDpj.getValue().get(0).getDiagRemarks());
                    mrod.setToRuleOut(groupedDpj.getValue().get(0).getToRuleOut());
                    mrod.setToConsider(groupedDpj.getValue().get(0).getToConsider());
                    mrod.setVersus(groupedDpj.getValue().get(0).getVersus());
                    mrod.setDiseaseLimit(groupedDpj.getValue().get(0).getDiseaseLimit() != null ? BigDecimal.valueOf(groupedDpj.getValue().get(0).getDiseaseLimit()) : null);
                    mrod.setRemLimit(groupedDpj.getValue().get(0).getRemLimit() != null ? BigDecimal.valueOf(groupedDpj.getValue().get(0).getRemLimit()) : null);
                    mrod.setIsPrimary(groupedDpj.getValue().get(0).getIsPrimary());
                    mrod.setIsAdmitting(groupedDpj.getValue().get(0).getIsAdmitting());
                    mrod.setIsFinal(groupedDpj.getValue().get(0).getIsFinal());

                    mrod.setTransCode(transCode);
                    //MaceDiagnosisType 1 is the Primary Diagnosis, 2 is Other Diagnosis
                    mrod.setMaceDiagType(groupDiag.getDiagCode().equals(primaryDiagnosis.getDiagCode()) ? 1 : 2);
                    List<String> reasonsList = new ArrayList<>();

                    //Group Procedures By Cost Center
                    Map<String, List<TestProcObject>> tpoGroupedByCostCenter = procObjects.stream()
                            .collect(Collectors.groupingBy(TestProcObject::getCostCenter, Collectors.toList()));
                    List<MaceRequestOpProcedure> mrops = new ArrayList<>();

                    List<OutpatientRequestJson.DiagnosisProcedureJson> currentList = groupedDpj.getValue();
                    MaceRequestReturn.MappedTest[] mtList = new MaceRequestReturn.MappedTest[currentList.size()];
                    int y = 0;
                    //Process Approvals for Procedures
                    BigDecimal subTotal = BigDecimal.ZERO;
                    for (TestProcObject testProcObject : procObjects) {
                        //TODO: Do not execute Approval Engine if data will be sent as draft

                        ApprovalEngineResult approvalEngineResult = new ApprovalEngineResult();
                        if(submitForApproval){
                            //Execute V2 approval engine and process results per execution
                            approvalEngineResult = approvalEngine.executeApprovalEngine(memberDetails,
                                    groupDiag, hospital, doctor, testProcObject, testProcObject.getAmount().doubleValue());
                            //Update final status assignee
                            finalAssignee = approvalEngine.updateFinalStatusAssignee(finalAssignee, approvalEngineResult.getFinalStatusAssignee(), hospital);

                            //Set allApproved to false if V2 Approval Engine does not return a request status of "APPROVED"
                            if (!approvalEngineResult.getFinalRequestStatus().equals(Constants.REQUEST_AUTOMATIC_APPROVED)) {
                                allApproved = false;
                                mrpObj.setOrAppendMraObjs(approvalEngineResult.getMaceRequestApprovals());
                            }
                            reasonsList.addAll(approvalEngineResult.getReasonMessages());
                        }

                        //Save MaceReqProc with generated transCode
                        mrp.setTransCode(transCode);

                        TestProcObject proc = claimsService.getTestProcObject(testProcObject.getProcCode());
                        MaceRequestOpProcedure mrop = new MaceRequestOpProcedure();
                        mrop.setProcActualAmount(testProcObject.getAmount());
                        subTotal = subTotal.add(mrop.getProcActualAmount());
                        mrop.setProcDefAmount(proc.getAmount());
                        mrop.setDiagCode(groupDiag.getDiagCode());
                        mrop.setProcCode(testProcObject.getProcCode());
                        mrop.setProcDesc(testProcObject.getProcName());
                        mrop.setProcClass(""); //TODO
                        mrop.setRuv(testProcObject.getRuv());
                        mrop.setArea(testProcObject.getArea());
                        //If diagnosis is not mapped to procedure, auto 0(PENDING), else if approvalType = "auto", 1(REQUESTED), else 0(PENDING)
                        mrop.setStatus(0);
                        //Populate return Json Object
                        BasicOrOtherTestResponseJson.DiagnosisClinicProcedureJson dcpj = new BasicOrOtherTestResponseJson.DiagnosisClinicProcedureJson();
                        dcpj.setMaceRequestProcedure(mrp);
                        dcpj.setMaceRequestOpDiag(mrod);
                        dcpj.setMaceRequestOpProcedure(mrop);
                        diagClinicProcJson.add(dcpj);
                        mrops.add(mrop);

                        //For Return
                        MaceRequestReturn.MappedTest mt = new MaceRequestReturn.MappedTest();
                        mt.setAmount(new BigDecimal(0));
                        mt.setCostCenter("");
                        mt.setProcCode(testProcObject.getProcCode());
                        mt.setProcDesc(testProcObject.getProcName());
                        mt.setDiagType(mrod.getMaceDiagType());
                        mtList[y++] = mt;
                    }
                    total = total.add(subTotal);
                    reasonMessagesMap.put(transCode, reasonsList);
                    mrp.setTransamount(total);
                    mrp.setStatus(submitForApproval ? (allApproved ? "APPROVED" : "PENDING") : "DRAFT");
                    costCenterStatus = mrp.getStatus();
                    MaceRequestProcObj.MaceRequestOpDiagObj mrodObj = new MaceRequestProcObj.MaceRequestOpDiagObj();
                    mrodObj.setMrod(mrod);
                    mrodObj.setMrops(mrops);
                    mrpObj.setMrp(mrp);
                    mrpObj.setMrodObjs(mrodObj);
                    mrpObjs.add(mrpObj);

                    MaceRequestReturn.GroupedByCostCenter.GroupedByDiag gbd = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag();
                    gbd.setApprovalNo(mrp.getApprovalNo());
                    gbd.setDiagDesc(mrod.getDiagDesc());
                    gbd.setDiagType(mrod.getMaceDiagType());
                    gbd.setMappedTests(mtList);
                    gbdList[x++] = gbd;
                }
                MaceRequestReturn.GroupedByCostCenter gbcc = new MaceRequestReturn.GroupedByCostCenter();
                gbcc.setCostCenter("");
                gbcc.setSubTotal(new BigDecimal(0));
                gbcc.setGroupedByDiag(gbdList);
                gbcc.setStatus(costCenterStatus);
                gbccArrayList.add(gbcc);
            }
            //Save tests here
            //Do not save any tests if request is Procedures
            else if(serviceType != 3){
                MaceRequestReturn.GroupedByCostCenter gbcc = new MaceRequestReturn.GroupedByCostCenter();
                gbcc.setCostCenter(perCostCenter.getKey());
                /**
                 * Group by DiagType within Cost Center.
                 * */
                Map<Integer, List<OutpatientRequestJson.DiagnosisProcedureJson>> byDiagType = perCostCenter.getValue().stream()
                        .collect(Collectors.groupingBy(OutpatientRequestJson.DiagnosisProcedureJson::getDiagType, Collectors.toList()));
                for (Map.Entry<Integer, List<OutpatientRequestJson.DiagnosisProcedureJson>> perDiagtype : byDiagType.entrySet()) {
                    /**
                     * Group futher by DiagCode. Generate ApprovalNo per Diagnosis
                     * */
                    Map<String, List<OutpatientRequestJson.DiagnosisProcedureJson>> byDiagCode = perDiagtype.getValue().stream()
                            .collect(Collectors.groupingBy(OutpatientRequestJson.DiagnosisProcedureJson::getDiagnosisCode, Collectors.toList()));

                    List<MaceRequestTestObj.MaceRequestOpDiagObj> mrodObjs = new ArrayList<>();
                    String costCenterStatus = "APPROVED";
                    MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[] gbdList = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[byDiagCode.size()];
                    int x = 0;
                    for (Map.Entry<String, List<OutpatientRequestJson.DiagnosisProcedureJson>> perDiagCode : byDiagCode.entrySet()) {
                        MaceRequestTestObj mrtObj = new MaceRequestTestObj();
                        MaceRequestReturn.GroupedByCostCenter.GroupedByDiag gbd = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag();
                        gbd.setDiagType(perDiagtype.getKey());
                        //Generate ApprovalNo for each Diagnosis
                        String requestCode = maceService.generateID("APPROVALNO");
                        gbd.setApprovalNo(requestCode);
                        mrr.setApprovalNo(byCostCenter.size() == 1 ? requestCode : "Multiple Approvals");
                        BigDecimal subTotal = BigDecimal.ZERO;
                        MaceRequestTest mrt = new MaceRequestTest();
                        mrt.setApprovalNo(requestCode);
                        mrt.setConsultReason(oprj.getPrimaryComplaint());
                        mrt.setDoctorCode(oprj.getDoctorCode());
                        mrt.setHospitalCode(oprj.getHospitalCode());
                        mrt.setDocHospId(null == requestingDocHosp ? 0 : requestingDocHosp.getDocHospId());
                        DiagnosisEntity de = maceService.getDiagnosisEntity(oprj.getPrimaryDiagnosisCode());
                        if (null != de) {
                            mrt.setPrimaryDiagnosisCode(de.getDiagCode());
                            mrt.setPrimaryDiagnosisICD10(de.getIcd10Code());
                            mrt.setPrimaryDiagnosisDesc(de.getDiagDesc());
                            mrt.setDxRemarks(de.getDiagRemarks());
                        }
                        mrt.setAvailHospId(hospital.getHospitalCode());
                        //Generate transCode and map reason messages to this transCode
                        String transCode = maceService.generateID("TESTREQUEST");
                        mrt.setTransCode(transCode);

                        List<String> reasonsList = new ArrayList<>();

                        List<OutpatientRequestJson.DiagnosisProcedureJson> currentList = perDiagCode.getValue();
                        if (byCostCenter.size() == 1 && byDiagType.size() == 1) {
                            mr.setApprovalNo(requestCode);
                            mrr.setApprovalNo(requestCode);
                        }

                        DiagnosisEntity groupDiag = maceService.getDiagnosisEntity(currentList.get(0).getDiagnosisCode());
                        MaceRequestTestObj.MaceRequestOpDiagObj mrodObj = new MaceRequestTestObj.MaceRequestOpDiagObj();

                        MaceRequestOpDiag mrod = new MaceRequestOpDiag();
                        mrod.setDiagnosisEntity(maceService.getDiagnosisEntity(groupDiag.getDiagCode()));
                        //Set case type, remarks, diag type flags, and limits sent from approval portal
                        //Assume that they are the same accross the grouped diagnosis list
                        mrod.setDiagCaseType(currentList.get(0).getDiagCaseType());
                        mrod.setDiagRemarks(currentList.get(0).getDiagRemarks());
                        mrod.setToRuleOut(currentList.get(0).getToRuleOut());
                        mrod.setToConsider(currentList.get(0).getToConsider());
                        mrod.setVersus(currentList.get(0).getVersus());
                        mrod.setDiseaseLimit(currentList.get(0).getDiseaseLimit() != null ? BigDecimal.valueOf(currentList.get(0).getDiseaseLimit()) : null);
                        mrod.setRemLimit(currentList.get(0).getRemLimit() != null ? BigDecimal.valueOf(currentList.get(0).getRemLimit()) : null);
                        mrod.setIsPrimary(currentList.get(0).getIsPrimary());
                        mrod.setIsAdmitting(currentList.get(0).getIsAdmitting());
                        mrod.setIsFinal(currentList.get(0).getIsFinal());

                        gbd.setDiagType(groupDiag.getDiagCode().equals(primaryDiagnosis.getDiagCode()) ? 1 : 2);
                        gbd.setDiagDesc(mrod.getDiagDesc());
                        mrod.setMaceDiagType(groupDiag.getDiagCode().equals(primaryDiagnosis.getDiagCode()) ? 1 : 2);
                        mrod.setTransCode(transCode);
                        List<MaceRequestOpTest> mrots = new ArrayList<>();
                        MaceRequestReturn.MappedTest[] mtList = new MaceRequestReturn.MappedTest[currentList.size()];
                        int y = 0;
                        /**
                         * Iterate through each test inside Diagnosis
                         * */
                        for (OutpatientRequestJson.DiagnosisProcedureJson dpj : currentList) {
                            MaceRequestReturn.MappedTest mt = new MaceRequestReturn.MappedTest();
                            TestProcObject testProcObject = claimsService.getTestProcObject(dpj.getProcedureCode());
                            HospitalProcedureAmountView hpav = claimsService.getHospProcAmount(hospital.getHospitalCode(), testProcObject.getProcCode());
                            DiagnosticProceduresEntity dpe = maceService.getDiagProcedureByProcedureCode(testProcObject.getProcCode(), groupDiag.getDiagCode());
                            Diagnosis diag = claimsService.getDiagnosisByDiagnosisCode(groupDiag.getDiagCode());

                            MaceRequestOpTest mrot = new MaceRequestOpTest();
                            mrot.setProcHospAmount(null != hpav ? hpav.getHospAmount() : null);
                            mrot.setProcDefAmount(testProcObject != null ? testProcObject.getAmount() : null);
                            mrot.setProcActualAmount(null != dpj.getAmount() ? BigDecimal.valueOf(dpj.getAmount()) : null);
                            mrot.setDiagCode(groupDiag.getDiagCode());
                            mrot.setProcCode(testProcObject.getProcCode());
                            mrot.setProcDesc(testProcObject.getProcName());
                            testProcObject.setServiceType(2);
                            testProcObject.setSubType(4);
                            mrot.setMaceSubtype(4);
                            mrot.setStatus(0);
                            mrot.setTransCode(transCode);
                            BigDecimal procAmount = mrot.getProcActualAmount() != null && !mrot.getProcActualAmount().equals(BigDecimal.ZERO) ? mrot.getProcActualAmount() :
                                    mrot.getProcHospAmount() != null && !mrot.getProcHospAmount().equals(BigDecimal.ZERO) ? mrot.getProcHospAmount() :
                                            mrot.getProcDefAmount() != null && !mrot.getProcDefAmount().equals(BigDecimal.ZERO) ? mrot.getProcDefAmount() : BigDecimal.ZERO;
                            //For Return
                            mt.setAmount(procAmount);
                            mt.setCostCenter(perCostCenter.getKey());
                            mt.setProcCode(testProcObject.getProcCode());
                            mt.setProcDesc(testProcObject.getProcName());
                            mt.setDiagType(perDiagtype.getKey());
                            //Execute V2 approval engine and process results per execution
                            //TODO: Do not execute Approval Engine if data will be sent as draft
                            ApprovalEngineResult approvalEngineResult = new ApprovalEngineResult();
                            if(submitForApproval){
                                //Execute V2 approval engine and process results per execution
                                approvalEngineResult = approvalEngine.executeApprovalEngine(memberDetails,
                                        diag, hospital, doctor, testProcObject, Double.valueOf(String.valueOf(collatedCost)));
                                //Set allApproved to false if V2 Approval Engine does not return a request status of "APPROVED"
                                if (!approvalEngineResult.getFinalRequestStatus().equals(Constants.REQUEST_AUTOMATIC_APPROVED)) {
                                    allApproved = false;
                                    costCenterStatus = "PENDING";
                                    reasonsList.addAll(approvalEngineResult.getReasonMessages());
                                    //Update final status assignee
                                    finalAssignee = approvalEngine.updateFinalStatusAssignee(finalAssignee, approvalEngineResult.getFinalStatusAssignee(), hospital);
                                    mrodObj.setMraObjs(approvalEngineResult.getMaceRequestApprovals());
                                }
                            }
                            else{
                                costCenterStatus = "DRAFT";
                            }
                            subTotal = subTotal.add(procAmount);
                            mrots.add(mrot);
                            BasicOrOtherTestResponseJson.DiagnosisProcedureJson dpjObj = new BasicOrOtherTestResponseJson.DiagnosisProcedureJson();
                            dpjObj.setMaceRequestTest(mrt);
                            dpjObj.setMaceRequestOpDiag(mrod);
                            dpjObj.setMaceRequestOpTest(mrot);
                            diagProcJson.add(dpjObj);
                            mtList[y++] = mt;
                        }
                        mrodObj.setMrod(mrod);
                        mrodObj.setMrots(mrots);
                        mrodObjs.add(mrodObj);
                        mrtObj.setMrodObjs(mrodObj);
                        System.out.println("REASONS : ");
                        for (String s : reasonsList) {
                            System.out.println(s);
                        }
                        reasonMessagesMap.put(transCode, reasonsList);
                        mrt.setTransamount(subTotal);
                        mrt.setStatus(costCenterStatus);
                        mrt.setTestSubtype(4);
                        totalAmount = totalAmount.add(subTotal);
                        mrtObj.setMrt(mrt);
                        mrtObjs.add(mrtObj);
                        gbd.setMappedTests(mtList);
                        gbcc.setSubTotal(subTotal);
                        gbdList[x++] = gbd;
                    }
                    gbcc.setGroupedByDiag(gbdList);
                    gbcc.setStatus(costCenterStatus);
                    gbccArrayList.add(gbcc);
                }
            }
        }
        MaceRequestReturn.GroupedByCostCenter[] gbccList = new MaceRequestReturn.GroupedByCostCenter[gbccArrayList.size()];
        for (int i = 0; i < gbccArrayList.size(); i++) {
            gbccList[i] = gbccArrayList.get(i);
        }
        mrr.setGroupedByCostCenters(gbccList);
        mrr.setTotalAmount(totalAmount);
        mr.setStatus(submitForApproval ? (allApproved ? "APPROVED" : "PENDING") : "DRAFT");
        mr.setStatusAssignee(!finalAssignee.equals(DEFAULT_GROUP) ? finalAssignee : null);
        mio.setMrtObjs(mrtObjs);
        mio.setMrpObjs(mrpObjs);
        mio.setMr(mr);
        maceService.maceInsertProcessTests(mio);

        /** ============
         *  End Revision
         *  ============*/

        BasicOrOtherTestResponseJson bootrj = new BasicOrOtherTestResponseJson();
        //Set Doctor for return only
        String fullName = doctor.getDocLname() == null ? "" : doctor.getDocLname() + ", ";
        fullName += doctor.getDocFname() == null ? "" : doctor.getDocFname() + " ";
        fullName += doctor.getDocMname() == null ? "" : doctor.getDocMname() + ".";
        mr.setDoctorName(fullName);
        mrr.setDoctorName(fullName);
        mrr.setDoctorSpec(doctor.getSpecDesc());
        bootrj.setMaceRequest(mr);
        bootrj.setDiagnosisProcedures(diagProcJson.toArray(
                new BasicOrOtherTestResponseJson.DiagnosisProcedureJson[diagProcJson.size()]));
        bootrj.setDiagnosisClinicProcedures(diagClinicProcJson.toArray(
                new BasicOrOtherTestResponseJson.DiagnosisClinicProcedureJson[diagClinicProcJson.size()]));

        //Remarks to be returned to app should be the final status of the header request
        remarks = mr.getStatus();
        responseCode = "200";

        //Response and notes will be the list of transCodes
        String transCodeString = String.join(", ", reasonMessagesMap.entrySet().stream().map(entry -> entry.getKey())
                .collect(Collectors.toList()));
        responseDesc = transCodeString;

        //Save customer care data
        c.setType(2);
        c.setActionTaken((submitForApproval && !allApproved) ? 1 : 0);
        c.setRemarks("Tests - Please call 841-8080 for approval");
        c.setNotes(transCodeString);
        c.setBatchCode(maceService.generateID("BATCHNO"));
        c.setCallerId(maceService.generateID("CALLERID"));
        c.setApprovalNo(mr.getApprovalNo());

        if(submitForApproval && !allApproved) {
            maceService.saveTransactionForCall(c);
            customerServiceService.saveTransactionForCall(c);
        }

        //Set response data
        response.put("icd10Code", primaryDiagnosis.getIcd10Code());
        response.put("totalAmount", totalAmount);
        response.put("data", bootrj);
        response.put("loaList", mrr);
        response.put("approvalNo", mr.getApprovalNo());
        response.put("allApproved", submitForApproval && allApproved);
        response.put("responseCode", responseCode);
        response.put("reasonMessagesMap", reasonMessagesMap);
        response.put("responseDesc", responseDesc);
        response.put("remarks", remarks);
        response.put("status", finalStatus);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
