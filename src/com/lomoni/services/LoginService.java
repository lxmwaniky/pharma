package com.lomoni.services;

import com.lomoni.database.config.DBConnector;

import java.util.*;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;

public class LoginService {
    private final String userName;
    private final String passWord;
    private final String userType;


    public LoginService(String userName, String passWord, String userType) {
        this.userName = userName;
        this.passWord = passWord;
        this.userType = userType;
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

    public boolean authenticateUser(){
        try{
        HashMap userObject = getDBUserData();

        for(Object i : userObject.keySet()){
            ArrayList userList = new ArrayList<>();
            userList = (ArrayList) userObject.get(i);
            String db_userName = (String) userList.get(0);
            String db_passWord = (String) userList.get(1);
            String db_userType = (String) userList.get(2);

            if(db_userName.equals(userName)){
                if(db_passWord.equals(passWord)){
                    if(db_userType.equals(userType)){
                        Log("TRACE","User successfully authenticated",null,LoginService.class.getName());
                        return true;
                    }
                }
            }

        }

        }catch(Exception exception){
            Log("FATAL","Exception while authenticating user : "+exception.getMessage(),exception,LoginService.class.getName());
        }
        return false;
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
