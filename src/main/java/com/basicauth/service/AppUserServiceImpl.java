package com.basicauth.service;

import com.basicauth.data.AppUser;
import com.basicauth.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("appUserService")
public class AppUserServiceImpl implements AppUserService {

    private static Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);

    @Autowired
    private AppUserRepository userRepository;

    public List<AppUser> findAllUsers() {
        logger.info("findAllUsers");
        List<AppUser> users = new ArrayList<>();
        for (AppUser user : userRepository.findAll()) {
            users.add(user);
        }
        logger.info("findAllUsers",users);
        return users;
    }

    public AppUser findById(long id) {
        logger.info("findById",id);
        return userRepository.findOne(id);
    }

    public AppUser findByUsername(String username) {
        logger.info("findByUsername",username);
        return userRepository.findByUsername(username);
    }

    public void saveUser(AppUser user) {
        logger.info("saveUser",user);
        userRepository.save(user);
    }

    public boolean isUsernameUsed(String username) {

        if (userRepository.findByUsername(username) == null) {
            logger.info("isUsernameUsed",false);
            return false;
        }

        logger.info("isUsernameUsed",true);
        return true;
    }

    @Override
    public boolean isDoctorCodeUsed(String doctorCode) {
        if (userRepository.findByDoctorCode(doctorCode) == null) {
            logger.info("isDoctorCodeUsed",false);
            return false;
        }

        logger.info("isDoctorCodeUsed",true);
        return true;
    }

}
