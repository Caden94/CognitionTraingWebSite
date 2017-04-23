package com.caden.cognitionTraining2.authentication;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
