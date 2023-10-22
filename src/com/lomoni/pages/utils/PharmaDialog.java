package com.lomoni.pages.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static com.lomoni.pages.utils.ImplementLookAndFeel.setThemeAndFont;

public class PharmaDialog extends JOptionPane{
    public static void showMessage(Container container,String message){
        showMessageDialog(container,message);
    }
}
