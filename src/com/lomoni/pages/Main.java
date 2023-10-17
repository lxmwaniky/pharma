package com.lomoni.pages;

import com.lomoni.pages.utils.InputFieldFocusListener;
import com.lomoni.pages.utils.TableFilter;
import com.lomoni.services.InventoryService;
import com.lomoni.services.PrescriptionService;

import javax.swing.*;
import java.awt.*;

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
    private static void setUpUI(){
        //THEME
        setThemeAndFont();

        JFrame screen = new JFrame();
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Create CardLayout object to switch between different panels on the same frame
        CardLayout cardLayout = new CardLayout();
        Container container = screen.getContentPane();

        //Add layout to frame
        screen.setLayout(cardLayout);
        //LOGIN SCREEN
        Login loginScreen = new Login(cardLayout,container);
        //INVENTORY SCREEN
        TableFilter tableFilter = new TableFilter();
        InventoryService inventoryService = new InventoryService();
        Inventory inventoryScreen = new Inventory(inventoryService, tableFilter);
        //SELL SCREEN
        Sell sellScreen = new Sell();
        //SELL RECEIPT SCREEN
        SellReceipt sellReceiptScreen = new SellReceipt();
        //PRESCRIPTION SCREEN
        Prescription prescriptionScreen = new Prescription(new PrescriptionService(), tableFilter);




        container.add("login",loginScreen.createMainPanel());

        container.add("inventory",inventoryScreen.createMainPanel());

        container.add("sell-receipt", sellReceiptScreen.createMainPanel());

        cardLayout.show(container,"sell-receipt");
        container.setLayout(cardLayout);
        screen.setContentPane(container);
        screen.setTitle("Pharma | Pharmacy POS");
        screen.setSize(screenWidth,screenHeight);
        screen.setVisible(true);
    }
    public static void main(String[] args) {
        //UI SETUP LOGIC
        setUpUI();
    }
}
