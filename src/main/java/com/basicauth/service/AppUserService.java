package com.basicauth.service;

import com.basicauth.data.AppUser;
import com.basicauth.data.User;

import java.util.List;


public interface AppUserService {

    AppUser findById(long id);

    AppUser findByUsername(String username);

    void saveUser(AppUser appUser);

    List<AppUser> findAllUsers();

    boolean isUsernameUsed(String username);

    boolean isDoctorCodeUsed(String doctorCode);
}
