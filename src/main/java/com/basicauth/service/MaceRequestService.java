package com.basicauth.service;

import com.basicauth.data.MaceRequest;
import com.basicauth.domain.MaceReqOpDiag;
import com.basicauth.domain.dups.MaceConsPrescribedtest;
import com.basicauth.domain.dups.MacereqConsult;
import com.basicauth.repository.MaceConsPrescribedTestRepository;
import com.basicauth.repository.MaceReqConsultRepository;
import com.basicauth.repository.MaceReqOpDiagRepository;
import com.basicauth.repository.MaceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("maceRequestService")
public class MaceRequestService {

    @Autowired
    private MaceRequestRepository maceRequestRepository;

    @Autowired
    private MaceReqConsultRepository maceReqConsultRepository;

    @Autowired
    private MaceConsPrescribedTestRepository maceConsPrescribedTestRepository;

    @Autowired
    private MaceReqOpDiagRepository maceReqOpDiagRepository;

    public MaceRequest save(MaceRequest maceRequest) {
        return maceRequestRepository.save(maceRequest);
    }

    public MacereqConsult save(MacereqConsult macereqConsult) {
        return maceReqConsultRepository.save(macereqConsult);
    }

    public MaceReqOpDiag save(MaceReqOpDiag mrod) {
        return maceReqOpDiagRepository.save(mrod);
    }

    public MaceConsPrescribedtest save(MaceConsPrescribedtest mcpt) {
        return maceConsPrescribedTestRepository.save(mcpt);
    }
}
