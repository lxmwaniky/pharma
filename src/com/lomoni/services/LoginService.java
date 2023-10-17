package com.lomoni.services;

public class LoginService {
    private final String userName;
    private final String passWord;
    private final String userType;

    public LoginService(String userName, String passWord, String userType) {
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

    public String getUserType() {
        return userType;
    }
}
