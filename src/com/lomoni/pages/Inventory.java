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
    private JComboBox dosageFormComboBox;
    private JButton loginButton;
    private JScrollPane addNewItemScrollPane;
    private JTextField rowFilterTextField;
    private JTextField medicineName;
    private JTextField unitPriceOfMedicine;
    private JTextField quantityInStock;
    private JTextField strengthOfDosage;

    //Dependencies
//    InventoryService inventoryService;


    public Inventory(InventoryService inventoryService, TableFilter tableFilter){
        tableFilter.setInventoryTable(inventoryTable);
        tableFilter.setFilterTextInput(rowFilterTextField);

        //Implement the placeholder functionality
        InputFieldFocusListener rowFilterTextFieldPlaceholder = new InputFieldFocusListener(rowFilterTextField, "Search...");
        InputFieldFocusListener medicineNamePlaceholder = new InputFieldFocusListener(medicineName, "Medicine Name");
        InputFieldFocusListener unitPriceOfMedicinePlaceholder = new InputFieldFocusListener(unitPriceOfMedicine, "Unit Price");
        InputFieldFocusListener strengthOfDosagePlaceholder = new InputFieldFocusListener(strengthOfDosage, "Strength ( e.g 500mg amoxicilin )");
        InputFieldFocusListener quantityInStockPlaceholder = new InputFieldFocusListener(quantityInStock, "Quantity In Stock");

        rowFilterTextField.addFocusListener(rowFilterTextFieldPlaceholder);
        //Instantiate TableFilter - Set before the table model
        rowFilterTextField.addKeyListener(tableFilter);

        medicineName.addFocusListener(medicineNamePlaceholder);
        strengthOfDosage.addFocusListener(strengthOfDosagePlaceholder);
        quantityInStock.addFocusListener(quantityInStockPlaceholder);
        unitPriceOfMedicine.addFocusListener(unitPriceOfMedicinePlaceholder);


        //Fetch inventory service with inventory data of columns and rows
        TableModel inventoryModel = new DefaultTableModel(inventoryService.getInventoryDisplayData(), inventoryService.getInventoryDisplayColumns());

        // Set the table model
        inventoryTable.setModel(inventoryModel);

    }

    public JPanel createMainPanel(){
        return inventoryPanel;
    }
}
