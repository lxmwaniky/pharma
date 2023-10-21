package com.lomoni.services;

import static com.lomoni.pages.utils.HashPassword.hashPass;

public class LoginService {
    private final String userName;
    private final String passWord;
    private final Object userType;
    private final byte[] hashedPassword;


    public LoginService(String userName, String passWord, Object userType) {
        this.userName = userName;
        this.passWord = passWord;
        this.userType = userType;
        this.hashedPassword = hashPass(this.passWord);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    public Object getUserType() {
        return userType;
    }
}
