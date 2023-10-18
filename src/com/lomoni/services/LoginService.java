package com.lomoni.services;

public class LoginService {
    private final String userName;
    private final String passWord;
    private final Object userType;


    public LoginService(String userName, String passWord, Object userType) {
        this.userName = userName;
        this.passWord = passWord;
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public Object getUserType() {
        return userType;
    }
}
