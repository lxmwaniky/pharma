package com.lomoni.pages;

import com.lomoni.pages.utils.InputFieldFocusListener;
import com.lomoni.pages.utils.TableFilter;
import com.lomoni.services.InventoryService;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.table.*;
import javax.swing.DefaultComboBoxModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

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
    private JTextField medicineName2;
    private JTextField unitPriceOfMedicine;
    private JTextField quantityInStock;
    private JTextField strengthOfDosage;


    public Inventory(InventoryService inventoryService, TableFilter tableFilter){
        try{
            setPlaceholderFunctionality();
            setTableFilter(tableFilter);
            setInventoryServiceData(inventoryService);
            Log("INFO","Table filter set on prescription data",null,InventoryService.class.getName());
        }catch (Exception e){
            Log("FATAL","Exception while setting table filter on Prescription data",e,Inventory.class.getName());
        }

    }

    private void setTableFilter(TableFilter tableFilter){
        try{
            tableFilter.setInventoryTable(inventoryTable);
            tableFilter.setFilterTextInput(rowFilterTextField);
            rowFilterTextField.addKeyListener(tableFilter);
            Log("INFO","Set table filter occurred",null,Prescription.class.getName());
        }catch(Exception e){
            Log("FATAL","Exception occurred while setting the table filter : "+e.getMessage(),e,Prescription.class.getName());
        }

    }

    private void setInventoryServiceData(InventoryService prescriptionService){
        try{
            TableModel inventoryModel = new DefaultTableModel(prescriptionService.getInventoryDisplayData(), prescriptionService.getInventoryDisplayColumns());
            inventoryTable.setModel(inventoryModel);
            Log("INFO", "Prescription data set",null,Prescription.class.getName());
        }catch(Exception e){
            Log("FATAL","Exception occurred while setting the prescription data : "+e.getMessage(),e,Prescription.class.getName());
        }
    }

    private void setPlaceholderFunctionality(){
        try{
            InputFieldFocusListener rowFilterTextFieldPlaceholder = new InputFieldFocusListener(rowFilterTextField, "Search...");
            InputFieldFocusListener medicineNamePlaceholder = new InputFieldFocusListener(medicineName2, "Medicine Name");
            InputFieldFocusListener unitPriceOfMedicinePlaceholder = new InputFieldFocusListener(unitPriceOfMedicine, "Unit Price");
            InputFieldFocusListener strengthOfDosagePlaceholder = new InputFieldFocusListener(strengthOfDosage, "Strength ( e.g 500mg amoxicilin )");
            InputFieldFocusListener quantityInStockPlaceholder = new InputFieldFocusListener(quantityInStock, "Quantity In Stock");

            rowFilterTextField.addFocusListener(rowFilterTextFieldPlaceholder);
            medicineName2.addFocusListener(medicineNamePlaceholder);
            strengthOfDosage.addFocusListener(strengthOfDosagePlaceholder);
            quantityInStock.addFocusListener(quantityInStockPlaceholder);
            unitPriceOfMedicine.addFocusListener(unitPriceOfMedicinePlaceholder);
        }catch(Exception e){
            Log("FATAL","Exception occurred while setting the placeholder functionality : "+e.getMessage(),e,Inventory.class.getName());
        }
    }
    public JPanel createMainPanel(){
        return inventoryPanel;
    }
}
