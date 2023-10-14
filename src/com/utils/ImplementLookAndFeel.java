package com.utils;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;

public abstract class ImplementLookAndFeel {
    public static void setThemeAndFont(){
        try{
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            UIManager.put("defaultFont", new Font("Calibri Body", Font.PLAIN, 14));
        } catch(Exception exception){
            System.out.println("Failed to apply theme");
        }
    }
}
