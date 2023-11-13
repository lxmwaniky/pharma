package com.lomoni.pages.utils;

import javax.swing.*;
import java.awt.*;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;
/*
 * Author : Braine Lomoni 168864 22/10/2023
 * Functionality :
 * - Display a message dialog with a specified container and message
 */
public class PharmaDialog{
    public static void showMessage(Container container,String message, String title, int messageType){
        try{
            /*
             * MESSAGE TYPE
             * 0 ERROR_MESSAGE
             * -1 PLAIN_MESSAGE
             * 1 INFORMATION_MESSAGE
             * 2 WARNING_MESSAGE
             */
            JOptionPane.showMessageDialog(container,message,title,messageType);
        }catch(Exception e){
            Log("WARN","Show message dialog init error"+e.getMessage(),e,PharmaDialog.class.getName());
        }
    }
}
