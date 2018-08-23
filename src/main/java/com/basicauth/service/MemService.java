package com.basicauth.service;

import com.basicauth.data.AccountsEntity;
import com.basicauth.data.Hospital;
import com.basicauth.data.MemberDetails;
import com.basicauth.domain.Member;
import com.basicauth.mapper.MemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by angulo on 10/19/2016.
 */
@Service("memService")
public class MemService {

    @Autowired
    private MemMapper memMapper;


    /**
     * Return the member info from the memdetails v2 stored procedure
     * @param memberCode
     * @return
     */
    public Map getMemberInfo(String memberCode){
        System.out.println("getMemberInfo:"+memberCode);
        return memMapper.getMemDetails(memberCode);
    }

    /**
     *
     * @param memberStatusCode
     * @return integer equivalent of status
     */
    public int validateMemStat(String memberStatusCode) {
        if("ACTIVE".equalsIgnoreCase(memberStatusCode)){
            return 200;
        }

         // "PENDING (E-MEDICARD)" possible source of error
         // "LAPSE (NON RENEW)" possible source of error
        if("DISAPPROVED".equalsIgnoreCase(memberStatusCode)
                || "RESIGNED".equalsIgnoreCase(memberStatusCode)
                || "CANCELLED".equalsIgnoreCase(memberStatusCode)
                || "PENDING (E-MEDICARD)".equalsIgnoreCase(memberStatusCode)
                || "LAPSE (NON RENEW)".equalsIgnoreCase(memberStatusCode)
                ){
            return 220;
        }


        // "FOR ENCODING" possible source of error
        // "MEDICAL EVALUATION" possible source of error
        // "ON HOLD" possible source of error
        // "FOR APPROVAL" possible source of error
        // "FOR REACTIVATION" possible source of error
        // "VERIFY MEMBERSHIP" possible source of error
        if("FOR ENCODING".equalsIgnoreCase(memberStatusCode)
                || "MEDICAL EVALUATION".equalsIgnoreCase(memberStatusCode)
                || "ON HOLD".equalsIgnoreCase(memberStatusCode)
                || "FOR APPROVAL".equalsIgnoreCase(memberStatusCode)
                || "VERIFY MEMBERSHIP".equalsIgnoreCase(memberStatusCode)
                || "FOR REACTIVATION".equalsIgnoreCase(memberStatusCode)
                ){
            return 230;
        }

        // "VERIFY PAYMENT WITH RMD" possible source of error
        if("VERIFY PAYMENT WITH RMD".equalsIgnoreCase(memberStatusCode)){
            return 240;
        }

        // "VERIFY RENEWAL FROM MARKETING / SALES" possible source of error
        if("VERIFY RENEWAL FROM MARKETING / SALES".equalsIgnoreCase(memberStatusCode)){
            return 240;
        }

        return 500;
    }

    public List getDependents(String memberCode){
        return memMapper.getDependents(memberCode);
    }

    public MemberDetails getMember (String memberCode){
        return memMapper.getMember(memberCode);
    }

    public Integer validateInpatient(String memberCode, String hospitalCode) {
        return memMapper.mossProcessInpx(memberCode, hospitalCode);
    }

    public Integer validateInpatient2(String memberCode, String hospitalCode) {
        return memMapper.mossInPatient(memberCode, hospitalCode);
    }

    public boolean isPecEqualToDdl(String memberCode) {
        Integer result = memMapper.mossPeclDdl(memberCode);
        return result.compareTo(new Integer("1"))==0;
    }

    public boolean checkIfDependent(String prin_code, String depMemberCode) {
        return memMapper.checkIfDependent(prin_code, depMemberCode);
    }


    public List getInpatientHospitalExclusionList(String roomRateId) {
        return memMapper.getInpatientHospitalExclusionList(roomRateId);
    }

    public List getOutpatientHospitalExclusionList(String roomRateId) {
        return memMapper.getOutpatientHospitalExclusionList(roomRateId);
    }

    public Map getMemberFromUWDependent(String memberCode) {
        return memMapper.getMemberFromUWDependent(memberCode);
    }

    public Map getMemberFromUWPrincipal(String memberCode) {
        return memMapper.getMemberFromUWPrincipal(memberCode);
    }

    public List<String> getRoomCategories() {
        return memMapper.getRoomCategories();
    }

    public List<AccountsEntity> getAllAccounts() {
        return memMapper.getAllAccounts();
    }

    public Member getMemberSelect(String memCode) {
        return memMapper.getMemberSelect(memCode);
    }
}
