package com.basicauth.service;

import com.basicauth.data.*;
import com.basicauth.mapper.ClaimsMapper;
import com.basicauth.mapper.MemMapper;
import com.basicauth.mapper.mace.DocHospMapper;
import com.basicauth.mapper.mace.MaceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by angulo on 10/19/2016.
 */
@Service("loaService")
public class LoaService {

    private static Logger logger = LoggerFactory.getLogger(LoaService.class);

    @Autowired
    private MemMapper memMapper;

    @Autowired
    private MaceMapper maceMapper;

    @Autowired
    private ClaimsMapper claimsMapper;

    @Autowired
    private DocHospMapper docHospMapper;


    public String requestApprovalConsultation(String memberCode, double maternityType, String hospitalCode) {
        logger.info("requestApprovalConsultation", memberCode);
        logger.info("requestApprovalConsultation", maternityType);
        logger.info("requestApprovalConsultation", hospitalCode);
        return memMapper.mossConsultation(memberCode, maternityType, hospitalCode);
    }

    public Integer requestApprovalProcedure(String memberCode, String hospitalCode) {
        logger.info("requestApprovalProcedure", memberCode);
        logger.info("requestApprovalProcedure", hospitalCode);
        return memMapper.mossProcessInpx(memberCode, hospitalCode);
    }


    /**
     * @param totalProcAmount
     * @param otherLimit
     * @param remainingLimit
     * @param innerLimit
     * @param isPecEqualDdl
     * @return
     */
    public boolean validateLimitOneProc(BigDecimal totalProcAmount,
                                        OtherLimit otherLimit, BigDecimal remainingLimit, BigDecimal innerLimit,
                                        boolean isPecEqualDdl
    ) {
        System.out.println("totalProcAmount:" + totalProcAmount);
        System.out.println("remainingLimit:" + remainingLimit);
        System.out.println("innerLimit:" + innerLimit);
        System.out.println("isPecEqualDdl:" + isPecEqualDdl);
        /**
         * Setting Limit Variables
         */
        BigDecimal allowLimit = otherLimit.getAllowLimit();
        BigDecimal stdLimit = otherLimit.getStdLimit();
        BigDecimal PECEqualDDL = otherLimit.getLimitWhenPecEqualDdl();
        BigDecimal PECUnequalDDL = otherLimit.getLimitWhenPecNotEqualDdl();

        System.out.println("allowLimit:" + allowLimit);
        System.out.println("stdLimit:" + stdLimit);
        System.out.println("PECEqualDDL:" + PECEqualDDL);
        System.out.println("PECUnequalDDL:" + PECUnequalDDL);

        boolean isApproved = false;
        System.out.println("A:" + (remainingLimit.compareTo(allowLimit) >= 0));
        System.out.println("B:" + (totalProcAmount.compareTo(remainingLimit) <= 0));
        if (remainingLimit.compareTo(allowLimit) >= 0 && totalProcAmount.compareTo(remainingLimit) <= 0) {

            if (isPecEqualDdl) {
                System.out.println("C" + (totalProcAmount.compareTo(innerLimit) <= 0));
                if (totalProcAmount.compareTo(innerLimit) <= 0) {
                    System.out.println("D");
                    isApproved = true;
                }
            } else {
                System.out.println("E" + (totalProcAmount.compareTo(stdLimit) <= 0));
                isApproved = compareWithLimit(totalProcAmount, stdLimit);
            }
        }
        System.out.println("isApproved:" + isApproved);
        return isApproved;
    }

    /**
     * @param totalProcAmount
     * @param otherLimit
     * @param remainingLimit
     * @param isPecEqualToDdl
     * @return
     */
    public boolean validateLimitMultipleProc(BigDecimal totalProcAmount, OtherLimit otherLimit, BigDecimal remainingLimit, boolean isPecEqualToDdl) {

        System.out.println("totalProcAmount:" + totalProcAmount);
        System.out.println("remainingLimit:" + remainingLimit);
        System.out.println("isPecEqualToDdl:" + isPecEqualToDdl);
        /**
         * Setting Limit Variables
         */
        BigDecimal allowLimit = otherLimit.getAllowLimit();
        BigDecimal stdLimit = otherLimit.getStdLimit();
        BigDecimal PECEqualDDL = otherLimit.getLimitWhenPecEqualDdl();
        BigDecimal PECUnequalDDL = otherLimit.getLimitWhenPecNotEqualDdl();
        System.out.println("allowLimit:" + allowLimit);
        System.out.println("stdLimit:" + stdLimit);
        System.out.println("PECEqualDDL:" + PECEqualDDL);
        System.out.println("PECUnequalDDL:" + PECUnequalDDL);
        boolean isApproved = false;

        System.out.println("A:" + (remainingLimit.compareTo(allowLimit) >= 0));
        if (remainingLimit.compareTo(allowLimit) >= 0) {
            System.out.println("B:" + (isPecEqualToDdl));
            if (isPecEqualToDdl) {
                System.out.println("C:" + (totalProcAmount.compareTo(PECEqualDDL) <= 0));
                isApproved = compareWithLimit(totalProcAmount, PECEqualDDL);
            } else {
                System.out.println("D:" + (totalProcAmount.compareTo(PECUnequalDDL) <= 0));
                isApproved = compareWithLimit(totalProcAmount, PECUnequalDDL);
            }
        } else {
            System.out.println("E:" + (remainingLimit.compareTo(stdLimit) >= 0));
            if (remainingLimit.compareTo(stdLimit) >= 0) {
                System.out.println("F:" + (totalProcAmount.compareTo(PECUnequalDDL) <= 0));
                isApproved = compareWithLimit(totalProcAmount, PECUnequalDDL);

            }
        }

        System.out.println("isApproved:" + isApproved);
        return isApproved;
    }


    /**
     * @param totalProcAmount
     * @param otherLimit
     * @param remainingLimit
     * @param isPecEqualToDdl
     * @return
     */
    public boolean validateLimit(BigDecimal totalProcAmount, OtherLimit otherLimit, BigDecimal remainingLimit, boolean isPecEqualToDdl) {
        logger.info("validateLimits");
        System.out.println("totalProcAmount:" + totalProcAmount);
        System.out.println("remainingLimit:" + remainingLimit);
        System.out.println("isPecEqualToDdl:" + isPecEqualToDdl);
        /**
         * Setting Limit Variables
         */
        BigDecimal allowLimit = otherLimit.getAllowLimit(); //20k
        BigDecimal stdLimit = otherLimit.getStdLimit(); //1k
        BigDecimal limitPECEqualDDL = otherLimit.getLimitWhenPecEqualDdl(); //10k
//        BigDecimal PECUnequalDDL = otherLimit.getLimitWhenPecNotEqualDdl();
        BigDecimal limitWhenRemLimitIsLessThan20k = otherLimit.getProcLimitWhenRemLimitIsLessThan20k(); //10k
        BigDecimal limitWhenRemLimitIsLessThan20kLessThan10k = otherLimit.getProcLimitWhenRemLimitIsLessThan20kLessThan10k();  //2.5k
        System.out.println("allowLimit:" + allowLimit);
        System.out.println("stdLimit:" + stdLimit);
        System.out.println("PECEqualDDL:" + limitPECEqualDDL);
//        System.out.println("PECUnequalDDL:"+PECUnequalDDL);
        boolean isApproved = false;

        if (remainingLimit.compareTo(allowLimit) >= 0) {
            if (isPecEqualToDdl) {
                isApproved = compareWithLimit(totalProcAmount, limitPECEqualDDL);
            } else {
                isApproved = compareWithLimit(totalProcAmount, limitWhenRemLimitIsLessThan20kLessThan10k);

            }
        } else {
            if (remainingLimit.compareTo(limitWhenRemLimitIsLessThan20k) >= 0) {
                if (isPecEqualToDdl) {
                    isApproved = compareWithLimit(totalProcAmount, limitWhenRemLimitIsLessThan20kLessThan10k);
                } else {
                    isApproved = compareWithLimit(totalProcAmount, stdLimit);
                }
            } else {
                isApproved = compareWithLimit(totalProcAmount, stdLimit);
            }
        }

        System.out.println("isApproved:" + isApproved);
        return isApproved;
    }

    private boolean compareWithLimit(BigDecimal totalProcAmount, BigDecimal stdLimit) {
        boolean isApproved = false;
        if (totalProcAmount.compareTo(stdLimit) <= 0) {
            isApproved = true;
        }
        return isApproved;
    }


    /*
    TODO
     */
    boolean isPECEqualsDDL(String memberCode) {
        return true;
    }

    public void saveLoa(CustomerCare c, String username, String primaryComplaint) {
        LoaMace loaMace = createLoa(c);
        //TODO:Because all request are approved Change with manual approval
        loaMace.setStatus("APPROVED");
        /*if (c.getActionTaken() == 0)
            loaMace.setStatus("PENDING");
        else if (c.getActionTaken() == 1)
            loaMace.setStatus("DISAPPROVED");
        else
            loaMace.setStatus("NO STATUS");*/
        loaMace.setApprovalDate(new Date());
        loaMace.setUpdatedDate(new Date());
        loaMace.setUpdatedBy(username);
        loaMace.setPrimaryComplaint(primaryComplaint);
        loaMace.setRequestOrigin(c.getRequestOrigin());
        if (null == loaMace.getPrimaryComplaint())
            loaMace.setPrimaryComplaint("");
        if (null == loaMace.getRequestOrigin())
            loaMace.setRequestOrigin("");
        maceMapper.saveLoaMace(loaMace);
    }

    public LoaMace createLoa(CustomerCare c) {
        LoaMace l = new LoaMace();
        l.setApprovalNo(c.getApprovalNo());
        l.setBatchCode(c.getBatchCode());
        l.setCallerId(c.getCallerId());
        l.setCallTypeId(c.getCallTypeId());
        l.setMemberCode(c.getMemberCode());
        l.setHospitalCode(c.getHospitalCode());
        l.setCompanyCode(c.getCompanyCode());
        l.setDoctorCode(c.getDoctorCode());
        l.setDiagnosisCode(c.getDiagnosisCode());
        l.setProcedureCode(c.getProcedureCode());
        l.setType(c.getType());
        l.setRoom(c.getRoom());
        l.setDateAdmitted(c.getDateAdmitted());
        l.setDiagnosis(c.getDiagnosis());
        l.setProcedureDesc(c.getProcedureDesc());
        l.setProcedureAmount(c.getProcedureAmount());
        l.setActionTaken(c.getActionTaken());
        l.setUpdatedBy(c.getUpdatedBy());
        l.setUpdatedDate(c.getUpdatedDate());
        l.setRemarks(c.getRemarks());
        l.setRunningBill(c.getRunningBill());
        l.setNotes(c.getNotes());
        l.setReason(c.getReason());
        l.setCategory(c.getCategory());
        l.setMemLname(c.getMemLname());
        l.setMemFname(c.getMemFname());
        l.setMemMi(c.getMemMi());
        l.setMemCompany(c.getMemCompany());
        l.setTerminalNo(c.getTerminalNo());
        l.setCallDate(c.getCallDate());

        return l;
    }

    public List getLoaByProvider(String doctorCode) {
        return maceMapper.getLoaByProvider(doctorCode);
    }

    public List getLoaByMemberCodeJson(String memberCode) {
        List<LoaMaceJson> loaList = maceMapper.getLoaByMemberCodeJson(memberCode);
        for (int x = 0; x < loaList.size(); x++) {
            Doctor doc = docHospMapper.getDoctor(loaList.get(x).getDoctorCode(), false);
            if (null != doc) {
                try {
                    loaList.get(x).setDoctor(doc);
                    if (maceMapper.checkIfProviderHasAppUserAccount(loaList.get(x).getDoctorCode()))
                        loaList.get(x).setWithProvider(true);
                    else
                        loaList.get(x).setWithProvider(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                loaList.get(x).setDoctor(null);
            }
        }

        return loaList;
    }

    public List getLoaByProviderAndMemberCode(String doctorCode, String memberCode) {
        return maceMapper.getLoaByProviderAndMemberCode(doctorCode, memberCode);
    }


    public LoaMace getLoaByApprovalNumberAndDoctorCode(String approvalNumber, String doctorCode) {
        return maceMapper.getLoaByApprovalNumberAndDoctorCode(approvalNumber, doctorCode);
    }

    public void insertInpatientRecord(CustomerCare c, InpatientJson inpatientJson) {
        InpatientJson i = new InpatientJson();
        i.setMemberCode(c.getMemberCode());
        i.setMemberName(inpatientJson.getMemberName());
        i.setHospitalCode(c.getHospitalCode());
        i.setHospitalName(inpatientJson.getHospitalName());
        i.setUsername(inpatientJson.getUsername());
        i.setDoctorCode(c.getDoctorCode());
        i.setDiagnosisCode(c.getDiagnosisCode());
        i.setRoomType(inpatientJson.getRoomType());
        i.setDeviceId(inpatientJson.getDeviceId());
        i.setRoomNumber(inpatientJson.getRoomNumber());
        i.setRoomPrice(inpatientJson.getRoomPrice());
        i.setCategory(c.getCategory());
        i.setProcedureCode(c.getProcedureCode());
        i.setStatus("ADMITTED");
        maceMapper.insertInpatientRecord(i);
    }

    public InpatientJson checkInpatientRecord(String hospitalCode, String memberCode) {
        return maceMapper.checkInpatientRecord(hospitalCode, memberCode);
    }


    public void releaseInpatientRecord(String hospitalCode, String memberCode) {
        maceMapper.releaseInpatientRecord(hospitalCode, memberCode);
    }

    public void insertConsultationRecord(ConsultationRecordJson consultationRecordJson) {
        maceMapper.insertConsultationRecord(consultationRecordJson);
    }

    @Transactional(value = "transactionManager")
    public void insertConsultationRecordVersion2(ConsultationRecordVersion02Json consultationRecordJson) {
        int before = consultationRecordJson.getId();
        System.out.println("inserted:" + maceMapper.insertConsultationRecordVersion2(consultationRecordJson));
        System.out.println("consultationRecordJson:" + consultationRecordJson.getId());
        try {
            System.out.println("SET TO AVAILED APPROVAL NO 2: " + consultationRecordJson.getApprovalNumber());
            maceMapper.setLoaStatusByApprovalNo(consultationRecordJson.getApprovalNumber(), "AVAILED", consultationRecordJson.getDisclaimerTicked());
        } catch (Exception e) {
            e.printStackTrace();
        }
        int after = consultationRecordJson.getId();
        if (before != after) {
            if (null != consultationRecordJson.getPrescribedTestOrProcedures()) {
                if (!consultationRecordJson.getPrescribedTestOrProcedures().isEmpty()) {
                    maceMapper.insertConsultationRecordVersion2PrescribedTestOrProcedures(consultationRecordJson.getPrescribedTestOrProcedures(), consultationRecordJson.getId());
                }
            }

            if (null != consultationRecordJson.getOtherDiagnosisContributoryToChiefComplaint()) {
                if (!consultationRecordJson.getOtherDiagnosisContributoryToChiefComplaint().isEmpty()) {
                    maceMapper.insertConsultationRecordVersion2OtherDiagnosisContributoryToChiefComplaint(consultationRecordJson.getOtherDiagnosisContributoryToChiefComplaint(), consultationRecordJson.getId());
                }
            }

            if (null != consultationRecordJson.getOtherDiagnosisNonContributoryToChiefComplaint()) {
                if (!consultationRecordJson.getOtherDiagnosisNonContributoryToChiefComplaint().isEmpty()) {
                    maceMapper.insertConsultationRecordVersion2OtherDiagnosisNonContributoryToChiefComplaint(consultationRecordJson.getOtherDiagnosisNonContributoryToChiefComplaint(), consultationRecordJson.getId());
                }
            }

            if (null != consultationRecordJson.getProceduresDoneInClinic()) {
                if (!consultationRecordJson.getProceduresDoneInClinic().isEmpty()) {
                    maceMapper.insertConsultationRecordVersion2ProceduresDoneInClinic(consultationRecordJson.getProceduresDoneInClinic(), consultationRecordJson.getId());
                }
            }
        }
    }

    public ConsultationRecordVersion02Json getConsultationRecordVersion2(int id) {
        ConsultationRecordVersion02Json consultationRecordVersion02Json = maceMapper.getConsultationRecordVersion2(id);
        if (null != consultationRecordVersion02Json) {
            List<String> otherDiagnosisContributoryToChiefComplaint = maceMapper.getConsultationRecordVersion2OtherDiagnosisContributoryToChiefComplaint(id);
            if (null != otherDiagnosisContributoryToChiefComplaint) {
                if (!otherDiagnosisContributoryToChiefComplaint.isEmpty()) {
                    consultationRecordVersion02Json.setOtherDiagnosisContributoryToChiefComplaint(
                            otherDiagnosisContributoryToChiefComplaint
                    );
                }
            }
            List<String> otherDiagnosisNonContributoryToChiefComplaint = maceMapper.getConsultationRecordVersion2OtherDiagnosisNonContributoryToChiefComplaint(id);
            if (null != otherDiagnosisNonContributoryToChiefComplaint) {
                if (!otherDiagnosisNonContributoryToChiefComplaint.isEmpty()) {
                    consultationRecordVersion02Json.setOtherDiagnosisNonContributoryToChiefComplaint(
                            otherDiagnosisNonContributoryToChiefComplaint
                    );
                }
            }

            List<String> prescribedTestOrProcedures = maceMapper.getConsultationRecordVersion2PrescribedTestOrProcedures(id);
            if (null != prescribedTestOrProcedures) {
                if (!prescribedTestOrProcedures.isEmpty()) {
                    consultationRecordVersion02Json.setPrescribedTestOrProcedures(
                            prescribedTestOrProcedures
                    );
                }
            }
            ArrayList<String> proceduresDoneInClinic = maceMapper.getConsultationRecordVersion2ProceduresDoneInClinic(id);
            if (null != proceduresDoneInClinic) {
                if (!proceduresDoneInClinic.isEmpty()) {
                    consultationRecordVersion02Json.setProceduresDoneInClinic(
                            proceduresDoneInClinic
                    );
                }
            }
            return consultationRecordVersion02Json;
        } else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public void insertConsultationRecordVersion3(ConsultationRecordVersion03Json consultationRecordJson) {
        int before = consultationRecordJson.getId();
        int consultationRecordId = consultationRecordJson.getId();
        try {
            maceMapper.setLoaStatusByApprovalNo(consultationRecordJson.getApprovalNumber(), "AVAILED", consultationRecordJson.getDisclaimerTicked());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (before != consultationRecordId) {
            if (null != consultationRecordJson.getPrescribedTestOrProceduresForPrimaryDiagnosisCodeList()) {
                if (!consultationRecordJson.getPrescribedTestOrProceduresForPrimaryDiagnosisCodeList().isEmpty()) {
                    maceMapper.insertConsultationRecordVersion3PrescribedTestOrProceduresForPrimaryDiagnosisCodeList(consultationRecordJson.getPrescribedTestOrProceduresForPrimaryDiagnosisCodeList(), consultationRecordJson.getId());
                }
            }

            if (null != consultationRecordJson.getProceduresDoneInClinic()) {
                if (!consultationRecordJson.getProceduresDoneInClinic().isEmpty()) {
                    maceMapper.insertConsultationRecordVersion3ProceduresDoneInClinic(consultationRecordJson.getProceduresDoneInClinic(), consultationRecordJson.getId());
                }
            }

            if (null != consultationRecordJson.getOtherDiagnosisContributoryToChiefComplaint()) {
                List<DiagnosisTestProcedureDto> contributoryChiefComplaintList = consultationRecordJson.getOtherDiagnosisContributoryToChiefComplaint();
                for (DiagnosisTestProcedureDto diagnosisTestProcedureDto : contributoryChiefComplaintList) {
                    String diagnosisCode = diagnosisTestProcedureDto.getDiagnosisCode();
                    maceMapper.insertConsultationRecordVersion3OtherDiagnosisContributoryToChiefComplaintListDiagnosisCode(diagnosisCode, consultationRecordId);
                    if (null != diagnosisTestProcedureDto.getPrescribedTestOrProcedureCodeList()) {
                        if (!diagnosisTestProcedureDto.getPrescribedTestOrProcedureCodeList().isEmpty()) {
                            List<String> prescribedTestOrProcedureCodeList = diagnosisTestProcedureDto.getPrescribedTestOrProcedureCodeList();
                            maceMapper.insertConsultationRecordVersion3OtherDiagnosisContributoryToChiefComplaintListPerDiagnosisCode(diagnosisCode, consultationRecordId, prescribedTestOrProcedureCodeList);
                        }
                    }
                }
            }

            if (null != consultationRecordJson.getOtherDiagnosisNonContributoryToChiefComplaint()) {
                List<DiagnosisTestProcedureDto> nonContributoryChiefComplaintList = consultationRecordJson.getOtherDiagnosisNonContributoryToChiefComplaint();
                for (DiagnosisTestProcedureDto diagnosisTestProcedureDto : nonContributoryChiefComplaintList) {
                    String diagnosisCode = diagnosisTestProcedureDto.getDiagnosisCode();
                    maceMapper.insertConsultationRecordVersion3OtherDiagnosisNonContributoryToChiefComplaintListDiagnosisCode(diagnosisCode, consultationRecordId);
                    if (null != diagnosisTestProcedureDto.getPrescribedTestOrProcedureCodeList()) {
                        if (!diagnosisTestProcedureDto.getPrescribedTestOrProcedureCodeList().isEmpty()) {
                            List<String> prescribedTestOrProcedureCodeList = diagnosisTestProcedureDto.getPrescribedTestOrProcedureCodeList();
                            maceMapper.insertConsultationRecordVersion3OtherDiagnosisNonContributoryToChiefComplaintListPerDiagnosisCode(diagnosisCode, consultationRecordId, prescribedTestOrProcedureCodeList);
                        }

                    }

                }
            }
        }
    }


    public ConsultationRecordVersion03Json getConsultationRecordVersion03(int consultationRecordId) {
        ConsultationRecordVersion03Json consultationRecordJson = maceMapper.getConsultationRecordVersion3(consultationRecordId);
        if (null != consultationRecordJson) {

            List<String> prescribedTestOrProcedure = maceMapper.getConsultationRecordVersion3PrescribedTestOrProcedures(consultationRecordId);
            if (null != prescribedTestOrProcedure) {
                if (!prescribedTestOrProcedure.isEmpty()) {
                    consultationRecordJson.setPrescribedTestOrProceduresForPrimaryDiagnosisCodeList(
                            prescribedTestOrProcedure
                    );
                }
            }

            List<String> proceduresDoneInClinic = maceMapper.getConsultationRecordVersion3ProceduresDoneInClinic(consultationRecordId);
            if (null != proceduresDoneInClinic) {
                if (!proceduresDoneInClinic.isEmpty()) {
                    consultationRecordJson.setProceduresDoneInClinic(
                            proceduresDoneInClinic
                    );
                }
            }

            List<DiagnosisTestProcedureDto> otherDiagnosisNonContributoryToChiefComplaintList = new ArrayList<>();
            List<String> diagnosisCodesList = maceMapper.getConsultationRecordVersion3OtherDiagnosisNonContributoryToChiefComplaintList(consultationRecordId);
            if (null != diagnosisCodesList) {
                if (!diagnosisCodesList.isEmpty()) {
                    for (String diagnosisCode : diagnosisCodesList) {
                        DiagnosisTestProcedureDto diagnosisTestProcedureDto = new DiagnosisTestProcedureDto();
                        diagnosisTestProcedureDto.setDiagnosisCode(diagnosisCode);
                        diagnosisTestProcedureDto.setConsultationRecordId(consultationRecordId);
                        List<String> testOrProcedureCodesList = maceMapper.getConsultationRecordVersion3OtherDiagnosisNonContributoryToChiefComplaintListPerDiagnosisCode(consultationRecordId, diagnosisCode);
                        if (null != testOrProcedureCodesList) {
                            if (!testOrProcedureCodesList.isEmpty()) {
                                diagnosisTestProcedureDto.setPrescribedTestOrProcedureCodeList(testOrProcedureCodesList);
                            }
                        }
                        otherDiagnosisNonContributoryToChiefComplaintList.add(diagnosisTestProcedureDto);
                    }
                }
                consultationRecordJson.setOtherDiagnosisNonContributoryToChiefComplaint(
                        otherDiagnosisNonContributoryToChiefComplaintList
                );
            }


            List<DiagnosisTestProcedureDto> otherDiagnosisContributoryToChiefComplaintList = new ArrayList<>();
            diagnosisCodesList = maceMapper.getConsultationRecordVersion3OtherDiagnosisContributoryToChiefComplaintList(consultationRecordId);
            if (null != diagnosisCodesList) {
                if (!diagnosisCodesList.isEmpty()) {
                    for (String diagnosisCode : diagnosisCodesList) {
                        DiagnosisTestProcedureDto diagnosisTestProcedureDto = new DiagnosisTestProcedureDto();
                        diagnosisTestProcedureDto.setDiagnosisCode(diagnosisCode);
                        diagnosisTestProcedureDto.setConsultationRecordId(consultationRecordId);
                        List<String> testOrProcedureCodesList = maceMapper.getConsultationRecordVersion3OtherDiagnosisContributoryToChiefComplaintListPerDiagnosisCode(consultationRecordId, diagnosisCode);
                        if (null != testOrProcedureCodesList) {
                            if (!testOrProcedureCodesList.isEmpty()) {
                                diagnosisTestProcedureDto.setPrescribedTestOrProcedureCodeList(testOrProcedureCodesList);
                            }
                        }
                        otherDiagnosisContributoryToChiefComplaintList.add(diagnosisTestProcedureDto);
                    }
                }
                consultationRecordJson.setOtherDiagnosisContributoryToChiefComplaint(
                        otherDiagnosisContributoryToChiefComplaintList
                );
            }


            return consultationRecordJson;
        } else {
            return null;
        }
    }
}
