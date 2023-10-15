package com.lomoni.pages;

import javax.swing.*;
import static com.lomoni.pages.utils.ImplementLookAndFeel.setThemeAndFont;

/*
 * Author : Braine Lomoni 168864 14/10/2023
 * Functionality :
 * - Calls setThemeAndFont() method to set theme
 * - Creates an instance of JFrame class to display the login screen.
 * - Creates an instance of Login class to create the main panel of the login screen.
 * - Sets up the window by setting the content pane to the main panel of the login
 * screen, title, size and visibility of the window.
 */

public class Main{
    private static final int screenWidth = 700;
    private static final int screenHeight = 600;
    public static void main(String[] args) {
        setThemeAndFont();

        JFrame screen = new JFrame();
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Login loginScreen = new Login();

        //Setting up window
        screen.setContentPane(loginScreen.createMainPanel());
        screen.setTitle("Pharma | Pharmacy POS");
        screen.setSize(screenWidth,screenHeight);
        screen.setVisible(true);
    }
}
