package com.lomoni.pages;

import com.lomoni.pages.utils.InputFieldFocusListener;
import com.lomoni.pages.utils.TableFilter;
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
    private JTextField rowFilterTextField;

    public Inventory(){
        //Implement the placeholder functionality
        new InputFieldFocusListener(rowFilterTextField, "Search for medicine...");
//        new InputFieldFocusListener(usernameFormattedTextField, "Username...");
//        new InputFieldFocusListener(passwordPasswordField, "Password...");
        //Instantiate TableFilter - Set before the table model
        TableFilter tableFilter = new TableFilter(inventoryTable,rowFilterTextField);

        rowFilterTextField.addKeyListener(tableFilter);

        //Fetch inventory service with inventory data of columns and rows
        InventoryService inventoryService = new InventoryService();
        TableModel inventoryModel = new DefaultTableModel(inventoryService.getInventoryDisplayData(), inventoryService.getInventoryDisplayColumns());

        // Set the table model
        inventoryTable.setModel(inventoryModel);

    }

    public JPanel createMainPanel(){
        return inventoryPanel;
    }
}
