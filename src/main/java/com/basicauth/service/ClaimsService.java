package com.basicauth.service;

import com.basicauth.controller.ListingController;
import com.basicauth.data.*;
import com.basicauth.domain.HospitalProcedureAmountView;
import com.basicauth.domain.TestProcObject;
import com.basicauth.mapper.ClaimsMapper;
import com.basicauth.mapper.MemMapper;
import com.basicauth.mapper.mace.DiagProcMapper;
import com.basicauth.mapper.mace.DocHospMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by angulo on 10/19/2016.
 */
@Service("claimsService")
public class ClaimsService {

    private static Logger logger = LoggerFactory.getLogger(ClaimsService.class);

    @Autowired
    private ClaimsMapper claimsMapper;

    @Autowired
    private DiagProcMapper diagProcMapper;

    @Autowired
    private DocHospMapper docHospMapper;

    @Autowired
    private MemService memService;


    @Autowired
    private MemMapper memMapper;


    @Autowired
    private ListingController listingController;


    /**
     * Return the member info from the memdetails v2 stored procedure
     *
     * @param memberCode
     * @return
     */
    public List getMemberUtilization(String memberCode) {
        System.out.println("getMemberInfo:" + memberCode);
        logger.info("getMemberUtilization", memberCode);
        return claimsMapper.getMemberUtilization(memberCode);
    }


    public Double getRemainingLimit(String memberCode) {
        System.out.println("getRemainingLimit:" + memberCode);
        logger.info("getRemainingLimit", memberCode);
        return claimsMapper.getRemainingLimit(memberCode);
    }

    public List getHospitals() {
        logger.info("getHospitals");
        return docHospMapper.getHospitals();

    }



    public List getDoctors() {
        logger.info("getDoctors");
        return docHospMapper.getDoctors();
    }

    public List getDiagnosisList() {
        logger.info("getDiagnosisList");
        return diagProcMapper.getDiagnosisList();
    }

    public List getAllDiagnosisList() {
        logger.info("getAllDiagnosisList");
        return diagProcMapper.getAllDiagnosisList();
    }

    public List getDoctorsToHospital(String hospitalCode) {
        logger.info("getDoctorsToHospital");
        return docHospMapper.getDoctorsToHospital(hospitalCode);
    }

    public List getAllDoctorsToHospital() {
        logger.info("getAllDoctorsToHospital");
        return docHospMapper.getAllDoctorsToHospital();
    }

    public Hospital getHospital(String hospitalCode) {
        logger.info("getHospital", hospitalCode);
        return docHospMapper.getHospital(hospitalCode);
    }



    public Doctor getDoctor(String doctorCode, Boolean getUnaccreditedDoctors) {
        logger.info("getDoctor", doctorCode);
        return docHospMapper.getDoctor(doctorCode, getUnaccreditedDoctors);
    }

    public List getHospitalOfDoctors(String doctorCode) {
        logger.info("getHospitalOfDoctors");
        return docHospMapper.getHospitalOfDoctors(doctorCode);
    }

    public List getRegions() {
        return docHospMapper.getRegions();
    }

    public List getProvinces() {
        return docHospMapper.getProvinces();
    }

    public List getCities() {
        return docHospMapper.getCities();
    }

    public List getSpecializations() {
        return docHospMapper.getSpecializations();
    }

    public List getDoctorToHospital(String hospitalCode, String doctorCode) {
        logger.info("getAllDoctorsToHospital");
        return docHospMapper.getDoctorToHospital(hospitalCode, doctorCode);
    }

    public DoctorToHospital getDoctorToHospitalObject(String hospitalCode, String doctorCode, Boolean getUnaccreditedDoctors) {
        logger.info("getAllDoctorsToHospitalObject");
        return docHospMapper.getDoctorToHospitalObject(hospitalCode, doctorCode, getUnaccreditedDoctors);
    }

    public Diagnosis getDiagnosisByDiagnosisCode(String diagCode) {
        logger.info("getDiagnosisByDiagnosisCode");
        return diagProcMapper.getDiagnosisByDiagnosisCode(diagCode);
    }

    public Procedure getProcedureByProcedureCode(String code) {
        logger.info("getProcedureByProcedureCode");
        return diagProcMapper.getProcedureByProcedureCodev2(code);
    }

    public HospitalProcedureAmountView getHospProcAmount(String hospitalCode, String procedureCode) {
        logger.info("getHospProcAmount");
        return docHospMapper.getHospProcAmount(hospitalCode, procedureCode);
    }

    public List<String> getTestsProceduresByDiagnosisCodes(String diagCode) {
        logger.info("getTestsProceduresByDiagnosisCodes");
        List<String> list = diagProcMapper.getTestsProceduresCodesByDiagnosisCode(diagCode);
        if (null == list || list.isEmpty())
            return diagProcMapper.getAllTestProceduresCodes();
        else
            return list;
    }

    public List<String> getTestsOnlyByDiagnosisCode(String diagCode) {
        logger.info("getTestsOnlyByDiagnosisCode");
        List<String> list = diagProcMapper.getTestsByDiagnosisCode(diagCode);
        if (null == list || list.isEmpty())
            return new ArrayList<>();
        else
            return list;
    }

    public List<String> getProceduresOnlyByDiagnosisCode(String diagCode) {
        logger.info("getProceduresOnlyByDiagnosisCode");
        List<String> list = diagProcMapper.getProceduresByDiagnosisCode(diagCode);
        if (null == list || list.isEmpty())
            return diagProcMapper.getProceduresOnlyByDiagnosisCode(diagCode);
        else
            return list;
    }

    public List getProceduresByDiagnosisCode(String diagCode) {
        logger.info("getProceduresByDiagnosisCode");
        List<String> list = diagProcMapper.getAllProcedureCodesByDiagnosisCode(diagCode);
        if (null == list || list.isEmpty())
            return new ArrayList<>();
        else
            return list;
    }

    public Integer getDoctorCount() {
        logger.info("getDoctorCount");
        Integer count = docHospMapper.getDoctorCount();
        return count;
    }

    public Boolean checkIfDiagCodeAndProcedureCodeMatches(String diagCode, String procedureCode) {
        return diagProcMapper.checkIfDiagCodeAndProcedureCodeMatches(diagCode, procedureCode);
    }

    public DiagnosisClinicProceduresEntity getDiagnosisClinicProcedureEntity(String diagnosisCode, String procedureCode) {
        return diagProcMapper.getDiagnosisClinicProcedureEntity(diagnosisCode, procedureCode);
    }


    public List getRoomPlans() {
        logger.info("getRoomPlans");
        return claimsMapper.getRoomPlans();
    }

    public List getTestsOnlyList() {
        logger.info("getTestsAndProceduresList");
        List<TestAndProceduresEntity> result = diagProcMapper.getTestsOnlyList();
        return result;
    }

    public List getTestsAndProceduresList() {
        logger.info("getTestsAndProceduresList");
        List<TestAndProceduresEntity> result = diagProcMapper.getDiagTestsProceduresList();
        return result;
    }

    public List getTestsAndProceduresList(String hospCode, String date) {
        logger.info("getTestsAndProceduresList");
        List<TestAndProceduresEntity> result = diagProcMapper.getDiagTestsProceduresListByHospCode(hospCode, date);
        for (TestAndProceduresEntity tpe : result) {
            if (null == tpe.getAmount() || tpe.getAmount() == 0.0)
                tpe.setAmount(777.0);
        }
        return result;
    }

    public List getAllTestsList() {
        logger.info("getAllTestsList");
        return diagProcMapper.getAllTests();
    }

    public TestProcObject getTestProcObject(String procedureCode) {
        logger.info("getTestProcObject:" + procedureCode);
        return diagProcMapper.getTestProcObject(procedureCode);
    }

    public List getAllDoctorsToHospitalByName(String partialDoctorName, Integer max) {
        logger.info("getAllDoctorsToHospitalByName" + " partialDoctorName:" + partialDoctorName + " max:" + max);
        return docHospMapper.getAllDoctorsToHospitalByName(partialDoctorName, max);

    }

    public BigDecimal getCostOfTestProc(String procedureCode) {
        logger.info("getCostOfTestProc:" + procedureCode);
        return diagProcMapper.getCostOfTestProc(procedureCode);
    }

    /**
     * This method returns a List<String> of ProcedureCode if it has a match
     */
    public List<String> getTestsByDiagnosisCode(String diagCode) {
        return diagProcMapper.getTestsByDiagnosisCode(diagCode);
    }


    public List getDoctorsByLastUpdateDate(Date lastUpdateDate) {
        logger.info("getDoctorsByLastUpdateDate");
        return docHospMapper.getDoctorsByLastUpdateDate(lastUpdateDate);
    }

    //Todo Diagnosis List by LastUpate Date
    public List <TestAndProceduresEntity> getAllDiagnosisListByLastUpdateDate(Date lastUpdateDate) {
        logger.info("getAllDiagnosisList" );
        return diagProcMapper.getAllDiagnosisListByLastUpdateDate(lastUpdateDate);
    }

    //Todo TestProcedure List by LastUpdate Date
    public List <TestProcObject> getAllTestProceduresListByLastUpdate(Date lastUpDateDate){
        logger.info("getAllTestProceduresListByLastUpdate");
        return diagProcMapper.getAllTestProceduresListByLastUpdateDate(lastUpDateDate);
    }


    //Todo DoctorsHospital List by Update Date
    public List getDoctorsHospitalListByLastUpdateDate(String hospitalCode,Date lastUpdateDate){
        return docHospMapper.getDoctorsHospitalListByLastUpdateDate("".equals(hospitalCode)? null:hospitalCode,lastUpdateDate);
    }


    //Todo Hospital List by Update Date
    public List getHospitalsByLastUpdateDate(Date date) {
        logger.info("getHospitalsByLastUdpdateDate");
        return docHospMapper.getHospitalsByLastUpdateDate(date);
    }

    public Dentist getDentist(String dentistCode) {
        return claimsMapper.getDentist(dentistCode);
    }

    public List<DiagnosisProceduresViewEntity> getDiagnosisProcedureList() {
        logger.info("getDiagnosisProcedureList");
        return diagProcMapper.getDiagnosisProcedureList();
    }

    public List getDoctorsToHospitalV2(String hospitalCode, String lastUpdateDate) {
        return docHospMapper.getDoctorsToHospitalV2("".equals(hospitalCode)? null:hospitalCode, lastUpdateDate);
    }

    public List<Dentist> getDentistList() {
        return docHospMapper.getDentistList();
    }



    //TODO Create List services for listing of (Hospitals, Diagnosis, DoctorsToHospital, Procedures, TestProcedures)
    //Diagnosis list
    public List getDiagnosisListPaginated(int count, int offset, String searchString){
        logger.info("getDiagnosisListPaginated");
        return diagProcMapper.getDiagnosisListPaginated(count,offset,"%"+searchString+"%");
    }

    public List getTestProceduresListPaginated(int count, int offset, String searchString, int serviceSubType){
        return diagProcMapper.getTestProceduresListPaginated(count,offset,"%"+searchString+"%", serviceSubType);
    }

    public List getDoctorsToHospitalPaginated(String hospitalCode, int count, int offset, String searchString, boolean includeAccredited) {
            return docHospMapper.getDoctorsToHospitalPaginated("".equals(hospitalCode)? null:hospitalCode,count,offset,"%"+searchString+"%", includeAccredited);
    }

    public List<Dentist> getDentistListPaginated(int count, int offset, String searchString) {
        return docHospMapper.getDentistListPaginated(count, offset, "%"+searchString+"%");
    }

    public void addTempDoctor(TempDoctorModel tempDoc) {
        docHospMapper.addTempDoctor(tempDoc);
    }

    //Hospital Paginated exluded..
    public List getHospitalsPaginated(int count, int offset, String searchString, List<String> exList){
        List<Hospital> hospitals = docHospMapper.getHospitalsPaginated(count,offset,"%"+ searchString +"%");
        for (Hospital hospital : hospitals) {
            if(exList.contains(hospital.getHospitalCode()))
                hospital.setExcludedForMember(true);
            else
                hospital.setExcludedForMember(false);
        }
        return hospitals;
    }

//    //Hospital Paginated with excluded
//    public List getHospitalsPaginated(){
////                return docHospMapper.getHospitalsPaginated(count,offset,"%"+ searchString +"%");
//    }

    public TempProcModel findTempProc(String procCode) {
        return diagProcMapper.findTempProc(procCode);
    }

    public void addTempProcedure(TempProcModel tpm) {
        diagProcMapper.addTempProcedure(tpm);
    }

    public TempDiagModel findTempDiag(String diagCode) {
        return diagProcMapper.findTempDiag(diagCode);
    }

    public void addTempDiag(TempDiagModel tempDiag) {
        diagProcMapper.addTempDiag(tempDiag);
    }
}
