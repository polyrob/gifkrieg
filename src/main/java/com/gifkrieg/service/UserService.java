package com.gifkrieg.service;

import com.gifkrieg.model.User;

/**
 * Created by Rob on 4/2/2017.
 */
public interface UserService {
    public User findByUsername(String email);

    public void saveNewUser(User user);

    public void acquireStarterGifs(User user);

}