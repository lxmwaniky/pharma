package com.lomoni.pages.utils;

import com.formdev.flatlaf.FlatIntelliJLaf;
import org.apache.logging.log4j.Level;

import javax.swing.*;
import java.awt.*;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;


/*
 * Author : Braine Lomoni 168864 14/10/2023
 * Functionality :
 * - Method sets look and feel of user interface to Flat IntelliJ theme
 * - It then changes the default font to "Calibri Body" with a plain size and font size of 14px
 * - AT THE MOMENT it prints out an error that occurs and uses the system default theme
 */
public abstract class ImplementLookAndFeel{
    public static void setThemeAndFont(){
        try{
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            UIManager.put("defaultFont", new Font("Calibri Body", Font.PLAIN, 14));
        } catch(Exception exception){
            Log("FATAL","Failed to apply theme : "+ exception.getMessage(), exception);
        }
    }
}
