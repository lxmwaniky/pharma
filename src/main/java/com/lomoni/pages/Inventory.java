package com.lomoni.pages;

import com.lomoni.pages.utils.InputFieldFocusListener;
import com.lomoni.pages.utils.TableFilter;
import com.lomoni.services.InventoryService;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;
import static com.lomoni.pages.utils.PharmaDialog.showMessage;

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
    private JButton submitButton;
    private JScrollPane addNewItemScrollPane;
    private JTextField rowFilterTextField;
    private JTextField medicineName;
    private JTextField unitPriceOfMedicine;
    private JTextField quantityInStock;
    private JTextField strengthOfDosage;
    private JButton signOutButton;
    private Container container;
    private CardLayout cardLayout;
    private InventoryService inventoryService;


    public Inventory(InventoryService inventoryService, TableFilter tableFilter, Container container, CardLayout cardLayout){
        try{
            this.container = container;
            this.cardLayout = cardLayout;
            this.inventoryService = inventoryService;

            //Set the event listeners
            signOutButton.addActionListener(e->{setSignOutButton();});
            submitButton.addActionListener(e->{handleInventoryFormData();});
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

    private void setInventoryServiceData(InventoryService inventoryService){
        try{
            inventoryService.getInventoryDisplayData();
            TableModel inventoryModel = new DefaultTableModel(inventoryService.getInventoryDisplayData(), inventoryService.getInventoryDisplayColumns());
            inventoryTable.setModel(inventoryModel);
            Log("INFO", "Prescription data set",null,Prescription.class.getName());
        }catch(Exception e){
            Log("FATAL","Exception occurred while setting the prescription data : "+e.getMessage(),e,Prescription.class.getName());
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
            Log("FATAL","Exception occurred while setting the placeholder functionality : "+e.getMessage(),e,Inventory.class.getName());
        }
    }

    //Sign Out Functionality
    private void setSignOutButton(){
        try {
            Log("TRACE", "User pharma signed out", null, Inventory.class.getName());
            showMessage(container,"You have signed out!","User pharma is signing out...",1);
            cardLayout.show(container, "login");
        }catch (Exception exception){
            Log("ERROR","Error when signOutButton is clicked"+exception.getMessage(),exception,Inventory.class.getName());
            showMessage(container,"Sorry for the inconvenience, try signing out again","Error while signing out",0);
        }
    }

    //Inventory Add New Item Form
    private void handleInventoryFormData(){
        String medicine_name = medicineName.getText();
        String dosage_form = (String) dosageFormComboBox.getSelectedItem();
        String strength_of_dosage = strengthOfDosage.getText();
        String quantity_in_stock = quantityInStock.getText();
        String unit_price_text = unitPriceOfMedicine.getText();

        if(medicine_name.equals("Medicine Name") || strength_of_dosage.equals("Strength ( e.g 500mg amoxicilin )") || quantity_in_stock.equals("Quantity In Stock") || unit_price_text.equals("Unit Price")){
            // User needs to fill all inputs
            Log("FATAL","User didn't fill all inputs",null,Inventory.class.getName());
            showMessage(container,"Fill in all inputs","Blank inputs",0);
        } else {
            Log("TRACE","User input data passed to the service",null,Inventory.class.getName());
            char inventoryFormResult = inventoryService.handleInventoryFormData(medicine_name, dosage_form, strength_of_dosage, quantity_in_stock, unit_price_text);
            String message = null;
            if(inventoryFormResult == 'A'){
                message = "Stock has been updated for "+medicine_name;
            } else if(inventoryFormResult == 'B'){
                message = "A new record has been created for "+medicine_name;
            } else if(inventoryFormResult == 'C'){
                message = "An error occurred :( Please try again";
            }

            showMessage(container,message,"You clicked the submit button...",1);
        }
    }
    public JPanel createMainPanel(){
        return inventoryPanel;
    }
}

