package com.basicauth.service;

import com.basicauth.data.User;
import com.basicauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(user);
        }

        return users;
    }

    public User findById(long id) {
        return userRepository.findOne(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserById(long id) {
        userRepository.delete(id);
    }

    public boolean isUsernameUsed(String username) {
        if (userRepository.findByUsername(username) == null) {
            return false;
        }

        return true;
    }

}
