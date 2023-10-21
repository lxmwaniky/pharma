package com.lomoni.pages;

import com.lomoni.pages.utils.TableFilter;
import com.lomoni.services.InventoryService;
import com.lomoni.services.PrescriptionService;

import javax.swing.*;
import java.awt.*;

import static com.lomoni.pages.utils.ImplementLookAndFeel.setThemeAndFont;
import static com.lomoni.pages.utils.LogManagerImplementation.Log;

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
    private static final int screenWidth = 1000;
    private static final int screenHeight = 600;
    private static void setUpUI(){
        try{
            //THEME
            setThemeAndFont();

            JFrame screen = new JFrame();
            screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Log("TRACE","Default close operation set to EXIT_ON_CLOSE",null,Main.class.getName());

            //Create CardLayout object to switch between different panels on the same frame
            CardLayout cardLayout = new CardLayout();
            Container container = screen.getContentPane();

            //Add layout to frame
            screen.setLayout(cardLayout);
            Log("TRACE","Card Layout set to frame",null,Main.class.getName());
            //LOGIN SCREEN
            Login loginScreen = new Login(container,cardLayout);
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

            container.add("prescription",prescriptionScreen.createMainPanel());

            container.add("sell-receipt", sellReceiptScreen.createMainPanel());

            cardLayout.show(container,"login");
            container.setLayout(cardLayout);
            screen.setContentPane(container);
            screen.setTitle("Pharma | Pharmacy POS");
            screen.setSize(screenWidth,screenHeight);
            screen.setVisible(true);
            Log("INFO","Initialization complete",null,Main.class.getName());
        }catch(Exception e){
            Log("FATAL","Exception while initializing",e,Main.class.getName());
        }

    }
    public static void main(String[] args) {
        //UI SETUP LOGIC
        setUpUI();
    }
}
