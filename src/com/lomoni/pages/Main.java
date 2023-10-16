package com.lomoni.pages;

import com.lomoni.pages.utils.InputFieldFocusListener;
import com.lomoni.pages.utils.TableFilter;
import com.lomoni.services.InventoryService;
import com.lomoni.services.PrescriptionService;

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

        /*
         * LOGIN SCREEN
         * Login loginScreen = new Login();
         * screen.setContentPane(loginScreen.createMainPanel());
         */

        /*
         * INVENTORY SCREEN
         * Inventory inventoryScreen = new Inventory(new InventoryService(), new TableFilter());
         * screen.setContentPane(inventoryScreen.createMainPanel());
         */

        /*
         * SELL SCREEN
         * Sell sellScreen = new Sell();
         * screen.setContentPane(sellScreen.createMainPanel());
         */

        /*
         * SELL RECEIPT
         * SellReceipt sellReceiptScreen = new SellReceipt();
         * screen.setContentPane(sellReceiptScreen.createMainPanel());
         */

        //Setting up window
        Prescription prescriptionScreen = new Prescription(new PrescriptionService(), new TableFilter());
        screen.setContentPane(prescriptionScreen.createMainPanel());
        screen.setTitle("Pharma | Pharmacy POS");
        screen.setSize(screenWidth,screenHeight);
        screen.setVisible(true);
    }
}
