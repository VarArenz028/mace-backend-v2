package com.basicauth.repository;

import com.basicauth.data.VerifiedMember;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Giancarlo Angulo.
 */
public interface VerifiedMemberRepository extends CrudRepository<VerifiedMember, Long> {

    VerifiedMember findByMemCode(String memCode);
}
