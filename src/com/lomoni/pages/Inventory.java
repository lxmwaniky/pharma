package com.lomoni.pages;

import com.lomoni.pages.utils.InputFieldFocusListener;
import com.lomoni.pages.utils.TableFilter;
import com.lomoni.services.InventoryService;

import javax.swing.*;
import javax.swing.table.*;
/*
 * Author : Braine Lomoni 168864 16/10/2023
 * Functionality :
 * - Creates and manages inventory panel
 * - Implements functionality for filtering the inventory table based on user input
 */
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

    //Dependencies
//    InventoryService inventoryService;


    public Inventory(InventoryService inventoryService, TableFilter tableFilter){
        tableFilter.setInventoryTable(inventoryTable);
        tableFilter.setFilterTextInput(rowFilterTextField);

        //Implement the placeholder functionality
        InputFieldFocusListener rowFilterTextFieldPlaceholder = new InputFieldFocusListener(rowFilterTextField, "Search...");
        InputFieldFocusListener usernameFormattedTextFieldPlaceholder = new InputFieldFocusListener(usernameFormattedTextField, "Username...");
        InputFieldFocusListener passwordPasswordFieldPlaceholder = new InputFieldFocusListener(passwordPasswordField, "Password...");

        rowFilterTextField.addFocusListener(rowFilterTextFieldPlaceholder);
        //Instantiate TableFilter - Set before the table model
        rowFilterTextField.addKeyListener(tableFilter);

        usernameFormattedTextField.addFocusListener(usernameFormattedTextFieldPlaceholder);
        passwordPasswordField.addFocusListener(passwordPasswordFieldPlaceholder);


        //Fetch inventory service with inventory data of columns and rows
        TableModel inventoryModel = new DefaultTableModel(inventoryService.getInventoryDisplayData(), inventoryService.getInventoryDisplayColumns());

        // Set the table model
        inventoryTable.setModel(inventoryModel);

    }

    public JPanel createMainPanel(){
        return inventoryPanel;
    }
}
