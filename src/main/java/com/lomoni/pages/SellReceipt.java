package com.lomoni.pages;

import com.lomoni.services.SellService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;
import static com.lomoni.pages.utils.PharmaDialog.showMessage;

public class SellReceipt {
    private JPanel mainPanel;
    private JButton loginButton;
    private JLabel medicineSoldValue;
    private JLabel dosageSoldValue;
    private JLabel quantitySoldValue;
    private JLabel prescriptionIDSoldValue;
    private JLabel dateOfDispensingValue;
    private JLabel totalCostValue;
    private JButton signOutButton;
    private JButton backToSellScreenButton;
    private JComboBox datesOfDispensingComboBox;
    private int patientBirthCertNo;

    private HashMap<String, List> patientDataFromPrescription;
    private HashMap<String, List> transactionsData;
    private HashMap<String, List> medicineInventoryData;
    private boolean patientBirthCertNoSet;
    private final Container container;
    private final CardLayout cardLayout;

    public SellReceipt(Container container, CardLayout cardLayout){
        this.container = container;
        this.cardLayout = cardLayout;
    }

    private void sellReceiptFunctions(){
        SellService sellService = new SellService();

        //Get Patient Info and Display on Screen Then Return Quantity To Be Deducted From DB
        // -> True if Successful
        destructurePatientDataFromPrescription(sellService.getPatientDataFromPrescription(this.patientBirthCertNo), sellService);

        backToSellScreenButton.addActionListener(e->{
            //Redirect back to the inventory screen
            showMessage(this.container,"...Going back to the sell screen","Redirecting back to the sell screen",1);
            this.cardLayout.show(this.container,"sell");
        });

        signOutButton.addActionListener(e->{setSignOutButton();});
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

    private boolean destructurePatientDataFromPrescription(HashMap<String, java.util.List> patientDataFromPrescription, SellService sellService){
        String prescription_id = null;
        String medicine_inventory_id = null;
        String transaction_id = null;
        String dosageFormAndValue = null;
        int Patient_quantity = 0;
        int inventory_id = 0;
        String medicine_quantity = null;
        String date_of_dispensing = null;
        String total_cost = null;

        for(Object i : patientDataFromPrescription.keySet()){
            ArrayList patientRowList = new ArrayList<>();
            patientRowList = (ArrayList) patientDataFromPrescription.get(i);
            prescription_id = (String) patientRowList.get(0);
            String medicine_name = (String) patientRowList.get(1);
            String patient_birth_cert_no = (String) patientRowList.get(2);
            String frequency = (String) patientRowList.get(3);
            String dosage = (String) patientRowList.get(4);
            String quantity = (String) patientRowList.get(5);

            for(Object j : sellService.getPatientDataFromTransactionItems(Integer.parseInt(prescription_id))){
                ArrayList transactionRowsList = new ArrayList<>();
                transactionRowsList = (ArrayList) sellService.getPatientDataFromTransactionItems(Integer.parseInt(prescription_id));
                transaction_id = (String) transactionRowsList.get(0);
                medicine_inventory_id = (String) transactionRowsList.get(1);
                dosageFormAndValue = (String) transactionRowsList.get(2);
                date_of_dispensing = (String) transactionRowsList.get(4);
                total_cost = (String) transactionRowsList.get(5);

                medicineSoldValue.setText(medicine_name);
                dosageSoldValue.setText(dosageFormAndValue);
                quantitySoldValue.setText(quantity);
                prescriptionIDSoldValue.setText(prescription_id);
                dateOfDispensingValue.setText(date_of_dispensing);
                totalCostValue.setText(total_cost);

                //Set the patient quantity
                Patient_quantity = Integer.parseInt(quantity);
                inventory_id = Integer.parseInt(medicine_inventory_id);
            }
        }
        return sellService.deductQuantityFromStock(inventory_id, Patient_quantity);
    }
    void setPatientBirthCertNo(int patientBirthCertNo){
        this.patientBirthCertNo = patientBirthCertNo;
        sellReceiptFunctions();
    }

    public JPanel createMainPanel(){
        return mainPanel;
    }
}
