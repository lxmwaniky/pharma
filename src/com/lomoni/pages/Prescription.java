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
    private JTextField patientName;
    private JComboBox medicineNameComboBox;
    private JTextField unitPriceOfMedicine;
    private JTextField frequency;
    private JTextField strengthOfDosage;
    private JPanel mainPanel;
    private JLabel lbl_error_login;

    public Prescription(PrescriptionService prescriptionService, TableFilter tableFilter){
        tableFilter.setInventoryTable(prescriptionTable);
        tableFilter.setFilterTextInput(rowFilterTextField);

        //Implement the placeholder functionality
        InputFieldFocusListener rowFilterTextFieldPlaceholder = new InputFieldFocusListener(rowFilterTextField, "Search...");
        InputFieldFocusListener patientNamePlaceholder = new InputFieldFocusListener(patientName, "Patient Name");
        InputFieldFocusListener dosagePlaceholder = new InputFieldFocusListener(strengthOfDosage, "Dosage ( e.g 500mg amoxicilin )");
        InputFieldFocusListener frequencyPlaceholder = new InputFieldFocusListener(frequency, "Frequency ( e.g twice daily )");

        rowFilterTextField.addFocusListener(rowFilterTextFieldPlaceholder);
        //Instantiate TableFilter - Set before the table model
        rowFilterTextField.addKeyListener(tableFilter);

        patientName.addFocusListener(patientNamePlaceholder);
        strengthOfDosage.addFocusListener(dosagePlaceholder);
        frequency.addFocusListener(frequencyPlaceholder);


        //Fetch inventory service with inventory data of columns and rows
        TableModel inventoryModel = new DefaultTableModel(prescriptionService.getInventoryDisplayData(), prescriptionService.getInventoryDisplayColumns());

        // Set the table model
        prescriptionTable.setModel(inventoryModel);
    }
    public JPanel createMainPanel(){
        return mainPanel;
    }
}
