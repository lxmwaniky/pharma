package com.lomoni.pages;

import com.lomoni.pages.utils.InputFieldFocusListener;
import com.lomoni.pages.utils.TableFilter;
import com.lomoni.services.InventoryService;

import javax.swing.*;
import javax.swing.table.*;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;

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

    public Inventory(InventoryService inventoryService, TableFilter tableFilter){
        try{
            setTableFilter(tableFilter);
            setInventoryServiceData(inventoryService);
            setPlaceholderFunctionality();
        } catch(Exception e){
            Log("FATA","Exception occured while initializing Inventory : "+e.getMessage(), e, Inventory.class.getName());
        }
    }

    private void setTableFilter(TableFilter tableFilter){
        try{
            tableFilter.setInventoryTable(inventoryTable);
            tableFilter.setFilterTextInput(rowFilterTextField);
            rowFilterTextField.addKeyListener(tableFilter);
        }catch(Exception e){
            Log("FATAL","Exception occurred while setting the table filter : "+e.getMessage(),e,Inventory.class.getName());
        }

    }

    private void setInventoryServiceData(InventoryService inventoryService){
        try{
            TableModel inventoryModel = new DefaultTableModel(inventoryService.getInventoryDisplayData(), inventoryService.getInventoryDisplayColumns());
            inventoryTable.setModel(inventoryModel);
        }catch(Exception e){
            Log("FATAL","Exception occurred while setting the inventory service data : "+e.getMessage(),e,Inventory.class.getName());
        }

    }

    private void setPlaceholderFunctionality(){
        try{
            InputFieldFocusListener rowFilterTextFieldPlaceholder = new InputFieldFocusListener(rowFilterTextField, "Search...");
            InputFieldFocusListener medicineNamePlaceholder = new InputFieldFocusListener(medicineName, "Medicine Name");
            InputFieldFocusListener unitPriceOfMedicinePlaceholder = new InputFieldFocusListener(unitPriceOfMedicine, "Unit Price");
            InputFieldFocusListener strengthOfDosagePlaceholder = new InputFieldFocusListener(strengthOfDosage, "Strength ( e.g 500mg amoxicilin )");
            InputFieldFocusListener quantityInStockPlaceholder = new InputFieldFocusListener(quantityInStock, "Quantity In Stock");

            rowFilterTextField.addFocusListener(rowFilterTextFieldPlaceholder);
            medicineName.addFocusListener(medicineNamePlaceholder);
            strengthOfDosage.addFocusListener(strengthOfDosagePlaceholder);
            quantityInStock.addFocusListener(quantityInStockPlaceholder);
            unitPriceOfMedicine.addFocusListener(unitPriceOfMedicinePlaceholder);
        }catch(Exception e){
            Log("FATAL","Exception occured while setting the placeholder functionality : "+e.getMessage(),e,Inventory.class.getName());
        }
    }
    public JPanel createMainPanel(){
        return inventoryPanel;
    }
}
