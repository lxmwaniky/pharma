package com.lomoni.pages;

import com.lomoni.services.SellService;

import javax.swing.*;

import java.awt.*;
import java.util.List;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;
import static com.lomoni.pages.utils.PharmaDialog.showMessage;

public class Sell {
    private JComboBox patientBirthCertNoComboBox;
    private JButton submitButton;
    private JPanel mainPanel;
    private JButton signOutButton;
    private JButton backToInventoryButton;

    private final Container container;
    private final CardLayout cardLayout;
    private final SellReceipt sellReceipt;

    public int patient_birth_certNO;

    /*
     * The pharmacist will enter the patient's information and the prescription information into the sell page.
     * The sell page will check the prescription against the tbl_prescription table to verify that the prescription is valid.
     * The sell page will check the medicine inventory against the tbl_inventory table to verify that the medicine is in stock.
     * If the prescription is valid and the medicine is in stock, the sell page will create a new record in the tbl_transaction_items table.
     * The sell page will update the medicine inventory in the tbl_inventory table.
     * The sell page will move to the sell-receipt
     */
    public Sell(Container container, CardLayout cardLayout, SellReceipt sellReceipt){
        this.container = container;
        this.cardLayout = cardLayout;
        this.sellReceipt = sellReceipt;

        SellService sellService = new SellService();
        List<String> patientBirthCertInString = sellService.getAllPatientBirthCertNo();
        patientBirthCertInString.add(0,"Select a patient's birth certificate number :");
        ComboBoxModel<String> patientBirthCertNoComboBoxModel = new DefaultComboBoxModel(patientBirthCertInString.toArray());
        patientBirthCertNoComboBox.setModel(patientBirthCertNoComboBoxModel);
        //Sign Out Button
        signOutButton.addActionListener(e->{setSignOutButton();});

        //Back To Inventory Button
        backToInventoryButton.addActionListener(e->{
            //Redirect back to the inventory screen
            showMessage(this.container,"...Going back to the inventory screen","Redirecting back to the inventory screen",1);
            this.cardLayout.show(this.container,"inventory");
        });
        submitButton.addActionListener(e->{

            //Notify user if patient had no record
            if(grabPatientBirthCertNo(patientBirthCertNoComboBox) == 0){
                showMessage(this.container,"Prescription for this patient doesn't exist","Trying to transact?",0);
            } else {
                // Redirect user to the sell receipt screen

                //Pass the patient receipt data
                sellReceipt.setPatientBirthCertNo(grabPatientBirthCertNo(patientBirthCertNoComboBox));
                this.cardLayout.show(container,"sell-receipt");
            }
        });
    }
    private int grabPatientBirthCertNo(JComboBox birthCertComboBox){
        try {
            String birthCertNoSelected = (String) birthCertComboBox.getSelectedItem();
            return getPatientDataFromService(Integer.parseInt(birthCertNoSelected));
        }catch(Exception e){
            Log("FATAL","Exception while grabbing data from the input : "+e.getMessage(),e,Sell.class.getName());
        }
        return 0;
    }

    private int getPatientDataFromService(int birthCertNo){
        SellService sellService = new SellService();
        return sellService.getPatientDataFromDatabaseAccess(birthCertNo);
    }

    public int getPatient_birth_certNO() {
        return patient_birth_certNO;
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

    public JPanel createMainPanel(){
        return mainPanel;
    }
}
