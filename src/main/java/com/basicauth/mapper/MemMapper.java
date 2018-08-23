package com.basicauth.mapper;

import com.basicauth.data.AccountsEntity;
import com.basicauth.data.MemberDetails;
import com.basicauth.domain.Member;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by igan_long on 10/6/2016.
 */
public interface MemMapper {

    int getAge(@Param("birthDate") String birthDate);

//    List getAll();

    HashMap getMemDetails(@Param("@MemCode") String memberCode);

    List getDependents(@Param("memberCode") String memberCode);

    MemberDetails getMember(@Param("memCode") String memberCode);

    String mossConsultation(@Param("@member_cd") String memberCode,@Param("@maternity_type") double maternityType, @Param("@hosp_code") String hospitalCode);

    Integer mossProcessInpx(@Param("@member_cd") String memberCode, @Param("@hosp_code") String hospitalCode);

    Integer mossInPatient(@Param("@member_cd") String memberCode, @Param("@hosp_code") String hospitalCode);

    Integer mossPeclDdl(@Param("@member_cd") String memberCode);

    boolean checkIfDependent(@Param("prin_code") String prin_code, @Param("depMemberCode") String depMemberCode);

    boolean hasInPatientAccess(@Param("hospitalCode") String hospitalCode, @Param("roomRateId") String roomRateId);

    boolean hasOutPatientAccess(@Param("hospitalCode") String hospitalCode, @Param("roomRateId") String roomRateId);

    Member getMemberSelect(@Param("memberNumber") String memberNumber);

    boolean hasMaternityFromRiderCode(@Param("membercode") String membercode);

    List getInpatientExclusionList(@Param("roomRateId") String roomRateId);

    List getOutpatientExclusionList(@Param("roomRateId") String roomRateId);

    List getInpatientHospitalExclusionList(@Param("roomRateId") String roomRateId);

    List getOutpatientHospitalExclusionList(@Param("roomRateId") String roomRateId);

    Map getMemberFromUWDependent(@Param("memCode")String memberCode);

    Map getMemberFromUWPrincipal(@Param("memCode")String memberCode);

    List<String> getRoomCategories();

    List<AccountsEntity> getAllAccounts();
}
