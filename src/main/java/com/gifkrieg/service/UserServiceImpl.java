package com.gifkrieg.service;


import java.util.Arrays;
import java.util.HashSet;

import com.gifkrieg.data.RoleRepository;
import com.gifkrieg.data.UserRepository;
import com.gifkrieg.model.Role;
import com.gifkrieg.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Rob on 4/2/2017.
 */

@Service("userService")
public class UserServiceImpl implements UserService {
    Logger log = LoggerFactory.getLogger("UserServiceImpl");

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}