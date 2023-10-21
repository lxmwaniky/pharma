package com.lomoni.pages.utils;

import java.security.MessageDigest;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;

public abstract class HashPassword {
    public static byte[] hashPass(String password){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(password.getBytes());
        }catch(Exception e){
            Log("WARN","Exception while hashing password"+e.getMessage(),e,HashPassword.class.getName());
        }
        return new byte[0];
    }
}
