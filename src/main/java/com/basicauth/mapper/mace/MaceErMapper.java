package com.basicauth.mapper.mace;

import com.basicauth.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by shem.sandiego on 2/02/2018.
 */
public interface MaceErMapper {

    void saveMaceReqER(@Param("mrer") MaceReqEr mrer);
    void saveMaceReqERDiag(@Param("maceReqErDiag") MaceReqErDiag maceReqErDiag);
    void saveMaceReqERDoctor(@Param("maceReqErDoctor") MaceReqErDoctor maceReqErDoctor);
    void saveMaceReqEROtherCharges(@Param("maceReqErOtherCharges") MaceReqErOtherCharges maceReqErOtherCharges);
    void saveMaceReqEROtherInformation(@Param("maceReqErOtherInformation") MaceReqErOtherInformation maceReqErOtherInformation);
    void saveMaceReqERProcedure(@Param("maceReqErProcedure") MaceReqErProcedure maceReqErProcedure);
    void saveMaceReqERRoom(@Param("maceReqErRoom") MaceReqErRoom maceReqErRoom);
    void saveMaceReqERTests(@Param("maceReqErTests") MaceReqErTests maceReqErTests);

}
