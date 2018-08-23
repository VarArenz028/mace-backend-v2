package com.basicauth.service;

import com.basicauth.data.User;

import java.util.List;


public interface UserService {

    User findById(long id);

    User findByUsername(String username);

    void saveUser(User user);

    void deleteUserById(long id);

    List<User> findAllUsers();

    boolean isUsernameUsed(String username);

}
