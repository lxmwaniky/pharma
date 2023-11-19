package com.lomoni.pages;

import com.lomoni.pages.utils.InputFieldFocusListener;
import com.lomoni.pages.utils.TableFilter;
import com.lomoni.services.PrescriptionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.util.List;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;
import static com.lomoni.pages.utils.PharmaDialog.showMessage;

public class Prescription {
    private JTable prescriptionTable;
    private JTextField rowFilterTextField;
    private JScrollPane addNewItemScrollPane;
    private JPanel addNewItemPanel;
    private JButton submitButton;
    private JTextField patientBirthCertNo;
    private JComboBox medicineNameComboBox;
    private JTextField unitPriceOfMedicine;
    private JTextField frequency;
    private JTextField dosage;
    private JPanel mainPanel;
    private JComboBox medicineName;
    private JButton signOutButton;
    private JTextField quantity;
    private ComboBoxModel<String> comboBoxModel;

    private Container container;
    private CardLayout cardLayout;
    private PrescriptionService prescriptionService;

    public Prescription(PrescriptionService prescriptionService, TableFilter tableFilter,Container container, CardLayout cardLayout){
        try{
            this.container = container;
            this.cardLayout = cardLayout;
            this.prescriptionService  = prescriptionService;

            //Event listeners
            signOutButton.addActionListener(e->{setSignOutButton();});
            submitButton.addActionListener(e->{
                handlePrescriptionFormData();
                //Reload the data
                setPrescriptionServiceData(prescriptionService);
                clearInputsWhenSubmitButtonIsClicked();
            });
            setPlaceholderFunctionality();
            setTableFilter(tableFilter);
            setPrescriptionServiceData(prescriptionService);

            //ComboBoxModel to add model items to the model
            List<String> medicineNames = prescriptionService.getMedicineNames();
            medicineNames.add(0,"Select a medicine");
            comboBoxModel = new DefaultComboBoxModel(medicineNames.toArray());
            medicineName.setModel(comboBoxModel);

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
            InputFieldFocusListener patientBirthCertNoPlaceholder = new InputFieldFocusListener(patientBirthCertNo, "Patient Birth Certificate No.");
            InputFieldFocusListener dosagePlaceholder = new InputFieldFocusListener(dosage, "Dosage ( e.g 500mg amoxicillin )");
            InputFieldFocusListener frequencyPlaceholder = new InputFieldFocusListener(frequency, "Frequency ( e.g twice daily )");
            InputFieldFocusListener quantityPlaceholder = new InputFieldFocusListener(quantity, "Quantity ( e.g 50 )");
            rowFilterTextField.addFocusListener(rowFilterTextFieldPlaceholder);
            patientBirthCertNo.addFocusListener(patientBirthCertNoPlaceholder);
            dosage.addFocusListener(dosagePlaceholder);
            frequency.addFocusListener(frequencyPlaceholder);
            quantity.addFocusListener(quantityPlaceholder);
            Log("INFO", "Placeholders set",null,Prescription.class.getName());
        }catch(Exception e){
            Log("FATAL","Exception occurred while setting placeholders : "+e.getMessage(),e,Prescription.class.getName());
        }
    }

    private void setSignOutButton(){
        try{
            Log("TRACE","User signed out successfully",null,Prescription.class.getName());
            showMessage(container,"Signing out successful!","User doc signing out",1);
            cardLayout.show(container,"login");
        }catch (Exception exception){
            Log("ERROR","Error signing user out"+exception.getMessage(),exception,Prescription.class.getName());
        }
    }

    private void handlePrescriptionFormData(){
        String medicine_name = (String) medicineName.getSelectedItem();
        String patientBirthCert = patientBirthCertNo.getText();
        String dosageValue  = dosage.getText();
        String frequencyValue = frequency.getText();
        String quantityValue = quantity.getText();

        if(patientBirthCert.equals("Patient Birth Certificate No.") || dosageValue.equals("Dosage ( e.g 500mg amoxicillin )") || frequencyValue.equals("Frequency ( e.g twice daily )") || quantityValue.equals("Quantity ( e.g 50 )")){
            // User needs to fill all inputs
            Log("FATAL","User didn't fill all inputs",null,Prescription.class.getName());
            showMessage(container,"Fill in all inputs","Blank inputs",0);
        } else {
            Log("TRACE","User input data passed to the service",null,Prescription.class.getName());
            char prescriptionFormResult = prescriptionService.handlePrescriptionFormData(medicine_name, patientBirthCert, dosageValue, frequencyValue, quantityValue);
            String message = null;
            if(prescriptionFormResult == 'A'){
                message = "A new prescription has been created for "+patientBirthCert;
            } else if(prescriptionFormResult == 'C'){
                message = "An error occurred :( Please try again";
            }

            showMessage(container,message,"You clicked the submit button...",1);
        }
    }

    private void clearInputsWhenSubmitButtonIsClicked(){
        try{
            //Set the values back to the default placeholders.
            frequency.setText("Frequency ( e.g twice daily )");
            dosage.setText("Dosage ( e.g 500mg amoxicillin )");
            patientBirthCertNo.setText("Patient Birth Certificate No.");

        }catch(Exception e){
            Log("ERROR","Exception occurred while setting the placeholder functionality : "+e.getMessage(),e,Prescription.class.getName());
        }
    }
    public JPanel createMainPanel(){
        return mainPanel;
    }
}
