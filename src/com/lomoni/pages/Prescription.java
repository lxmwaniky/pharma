package com.lomoni.pages;

import com.lomoni.pages.utils.InputFieldFocusListener;
import com.lomoni.pages.utils.TableFilter;
import com.lomoni.services.PrescriptionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;

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
        try{
            setPlaceholderFunctionality();
            setTableFilter(tableFilter);
            setPrescriptionServiceData(prescriptionService);
            Log("INFO","Table filter set on prescription data",null,Prescription.class.getName());
        }catch (Exception e){
            Log("FATAL","Exception while setting table filter on Prescription data",e,Prescription.class.getName());
        }

    }

    private void setTableFilter(TableFilter tableFilter){
        try{
            tableFilter.setInventoryTable(prescriptionTable);
            tableFilter.setFilterTextInput(rowFilterTextField);
            rowFilterTextField.addKeyListener(tableFilter);
            Log("INFO","Set table filter occured",null,Prescription.class.getName());
        }catch(Exception e){
            Log("FATAL","Exception occurred while setting the table filter : "+e.getMessage(),e,Prescription.class.getName());
        }

    }

    private void setPrescriptionServiceData(PrescriptionService prescriptionService){
        try{
            TableModel inventoryModel = new DefaultTableModel(prescriptionService.getInventoryDisplayData(), prescriptionService.getInventoryDisplayColumns());
            prescriptionTable.setModel(inventoryModel);
            Log("INFO", "Prescription data set",null,Prescription.class.getName());
        }catch(Exception e){
            Log("FATAL","Exception occurred while setting the prescription data : "+e.getMessage(),e,Prescription.class.getName());
        }
    }

    private void setPlaceholderFunctionality(){
        try{
            InputFieldFocusListener rowFilterTextFieldPlaceholder = new InputFieldFocusListener(rowFilterTextField, "Search...");
            InputFieldFocusListener patientNamePlaceholder = new InputFieldFocusListener(patientName, "Patient Name");
            InputFieldFocusListener dosagePlaceholder = new InputFieldFocusListener(strengthOfDosage, "Dosage ( e.g 500mg amoxicillin )");
            InputFieldFocusListener frequencyPlaceholder = new InputFieldFocusListener(frequency, "Frequency ( e.g twice daily )");
            rowFilterTextField.addFocusListener(rowFilterTextFieldPlaceholder);
            patientName.addFocusListener(patientNamePlaceholder);
            strengthOfDosage.addFocusListener(dosagePlaceholder);
            frequency.addFocusListener(frequencyPlaceholder);
            Log("INFO", "Placeholders set",null,Prescription.class.getName());
        }catch(Exception e){
            Log("FATAL","Exception occurred while setting placeholders : "+e.getMessage(),e,Prescription.class.getName());
        }

    }

    public JPanel createMainPanel(){
        return mainPanel;
    }
}
