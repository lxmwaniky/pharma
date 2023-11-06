package com.lomoni.services;

import com.lomoni.database.config.DBConnector;

import java.util.Arrays;
import java.util.HashMap;

import static com.lomoni.pages.utils.HashPassword.hashPass;
import static com.lomoni.pages.utils.LogManagerImplementation.Log;

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

    //Get DB user data
    private HashMap getDBUserData(){
        HashMap<String,String> userObject = null;
        try{
            DBConnector dbConnector = new DBConnector();
            userObject = dbConnector.getAllUsers();
        }catch (Exception exception){
            Log("FATAL","Exception while getting users from the database : "+exception.getMessage(),exception,LoginService.class.getName());
        }
        return userObject;
    }

    public void authenticateUser(){
        HashMap userObject = getDBUserData();
        String db_userName = (String) userObject.get("user_name");
        String db_passWord = (String) userObject.get("user_password");
        String db_userType = (String) userObject.get("user_type");
        System.out.println("USer Input :"+this.userName+this.hashedPassword+this.userType);
        System.out.println("DB :"+db_userName+db_passWord+db_userType);

        if(db_userName == this.userName){
            if(db_passWord == this.passWord){
                System.out.println("User Exists");
            }
        }

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
