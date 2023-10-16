package com.lomoni.pages;

import com.lomoni.pages.utils.InputFieldFocusListener;
import com.lomoni.pages.utils.TableFilter;
import com.lomoni.services.PrescriptionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Prescription {
    private JTable prescriptionTable;
    private JTextField rowFilterTextField;
    private JScrollPane addNewItemScrollPane;
    private JPanel addNewItemPanel;
    private JButton submitButton;
    private JTextField medicineName;
    private JComboBox dosageFormComboBox;
    private JTextField unitPriceOfMedicine;
    private JTextField quantityInStock;
    private JTextField strengthOfDosage;
    private JPanel mainPanel;

    public Prescription(PrescriptionService prescriptionService, TableFilter tableFilter){
        tableFilter.setInventoryTable(prescriptionTable);
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
        TableModel inventoryModel = new DefaultTableModel(prescriptionService.getInventoryDisplayData(), prescriptionService.getInventoryDisplayColumns());

        // Set the table model
        prescriptionTable.setModel(inventoryModel);
    }
    public JPanel createMainPanel(){
        return mainPanel;
    }
}
