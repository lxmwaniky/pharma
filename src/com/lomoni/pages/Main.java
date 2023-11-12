package com.lomoni.pages;

import com.lomoni.database.config.DBConnector;
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
            Log("TRACE","cardLayout and container Objects initialized",null,Main.class.getName());

            //Add layout to frame
            screen.setLayout(cardLayout);
            Log("TRACE","Card Layout set to frame",null,Main.class.getName());

            //LOGIN SCREEN
            Login loginScreen = new Login(container,cardLayout);
            //SELL SCREEN
            Sell sellScreen = new Sell();
            //PRESCRIPTION SCREEN
            Prescription prescriptionScreen = new Prescription(new PrescriptionService(), new TableFilter());
            //INVENTORY SCREEN
            Inventory inventoryScreen = new Inventory(new InventoryService(), new TableFilter(),container,cardLayout);
            //SELL RECEIPT SCREEN
            SellReceipt sellReceiptScreen = new SellReceipt();
            Log("TRACE","Screens initialized",null,Main.class.getName());

            container.add("login",loginScreen.createMainPanel());

            container.add("inventory",inventoryScreen.createMainPanel());

            container.add("prescription",prescriptionScreen.createMainPanel());

            container.add("sell", sellScreen.createMainPanel());

            container.add("sell-receipt", sellReceiptScreen.createMainPanel());
            Log("TRACE","Screen main panels added to the container",null,Main.class.getName());

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

    private static void setUpDB(){
        new DBConnector();
    }
    public static void main(String[] args) {
        //UI SETUP
        setUpUI();

        //DATABASE SETUP
//        setUpDB();
    }
}
