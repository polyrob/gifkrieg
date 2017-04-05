package com.gifkrieg.service;

/**
 * Created by robbie on 4/4/17.
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
