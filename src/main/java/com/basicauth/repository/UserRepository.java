package com.basicauth.repository;

import com.basicauth.data.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Giancarlo Angulo.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
