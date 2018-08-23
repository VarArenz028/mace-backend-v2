package com.basicauth.service;

import com.basicauth.data.AppUser;
import com.basicauth.data.VerifiedMember;
import com.basicauth.repository.VerifiedMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("verifiedMemberService")
public class VerifiedMemberServiceImpl implements VerifiedMemberService {


    @Autowired
    private VerifiedMemberRepository verifiedMemberRepository;


    public void saveMember(VerifiedMember user) {
        verifiedMemberRepository.save(user);
    }

}
