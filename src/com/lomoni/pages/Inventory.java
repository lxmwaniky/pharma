package com.lomoni.pages;

import com.lomoni.services.InventoryService;

import javax.swing.*;
import javax.swing.table.*;

public class Inventory {
    private JTable inventoryTable;
    private JPanel inventoryPanel;
    private JPanel addNewItemPanel;
    private JComboBox userRoleComboBox;
    private JTextField usernameFormattedTextField;
    private JPasswordField passwordPasswordField;
    private JButton loginButton;
    private JScrollPane addNewItemScrollPane;

    public Inventory(){

        //Fetch inventory service with inventory data of columns and rows
        InventoryService inventoryService = new InventoryService();
        TableModel inventoryModel = new DefaultTableModel(inventoryService.getInventoryDisplayData(), inventoryService.getInventoryDisplayColumns());

        // Display the columns

        inventoryTable.setModel(inventoryModel);
    }

    public JPanel createMainPanel(){
        return inventoryPanel;
    }
}
