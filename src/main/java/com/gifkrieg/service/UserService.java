package com.gifkrieg.service;

import com.gifkrieg.model.User;

/**
 * Created by Rob on 4/2/2017.
 */
public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}