package com.basicauth.service;

import com.basicauth.mapper.mace.MaceErMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.basicauth.domain.*;

/**
 * Created by shem.sandiego on 2/6/2018.
 */
@Service("maceRequestErService")
public class MaceRequestErService {

    private static final Logger logger = LoggerFactory.getLogger(MaceRequestErService.class);

    @Autowired
    private MaceErMapper maceErMapper;

    public void saveMaceReqEr(MaceReqEr maceReqEr){
        maceErMapper.saveMaceReqER(maceReqEr);
    }
    public void saveMaceReqERDiag(MaceReqErDiag maceReqErDiag){
        maceErMapper.saveMaceReqERDiag(maceReqErDiag);
    }
    public void saveMaceReqERDoctor(MaceReqErDoctor mrerdoctor){
        maceErMapper.saveMaceReqERDoctor(mrerdoctor);
    }
    public void saveMaceReqEROtherCharges(MaceReqErOtherCharges mrerothercharges){
        maceErMapper.saveMaceReqEROtherCharges(mrerothercharges);
    }
    public void saveMaceReqEROtherInformation(MaceReqErOtherInformation mrerotherinfo){
        maceErMapper.saveMaceReqEROtherInformation(mrerotherinfo);
    }
    public void saveMaceReqERProcedure(MaceReqErProcedure mrerprocedure){
        maceErMapper.saveMaceReqERProcedure(mrerprocedure);
    }
    public void saveMaceReqERRoom(MaceReqErRoom mrerroom){
        maceErMapper.saveMaceReqERRoom(mrerroom);
    }
    public void saveMaceReqERTests(MaceReqErTests mrertests){
        maceErMapper.saveMaceReqERTests(mrertests);
    }

}
