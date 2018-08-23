package com.basicauth.repository;

import com.basicauth.data.AppUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Giancarlo Angulo.
 */
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    AppUser findByUsername(String username);

    AppUser findByDoctorCode(String doctorCode);
}
