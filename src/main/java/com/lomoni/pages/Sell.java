package com.lomoni.pages;

import com.lomoni.services.SellService;

import javax.swing.*;

import java.awt.*;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;
import static com.lomoni.pages.utils.PharmaDialog.showMessage;

public class Sell {
    private JComboBox patientBirthCertNoComboBox;
    private JButton submitButton;
    private JPanel mainPanel;

    private final Container container;
    private final CardLayout cardLayout;

    /*
     * The pharmacist will enter the patient's information and the prescription information into the sell page.
     * The sell page will check the prescription against the tbl_prescription table to verify that the prescription is valid.
     * The sell page will check the medicine inventory against the tbl_inventory table to verify that the medicine is in stock.
     * If the prescription is valid and the medicine is in stock, the sell page will create a new record in the tbl_transaction_items table.
     * The sell page will update the medicine inventory in the tbl_inventory table.
     * The sell page will move to the sell-receipt
     */
    public Sell(Container container, CardLayout cardLayout){
        this.container = container;
        this.cardLayout = cardLayout;


        submitButton.addActionListener(e->{

            //Notify user if patient had no record
            if(grabPatientBirthCertNo(patientBirthCertNoComboBox) == 0){
                showMessage(this.container,"Prescription for this patient doesn't exist","Trying to transact?",0);
            } else {
                // Redirect user to the sell receipt screen
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

    public JPanel createMainPanel(){
        return mainPanel;
    }
}
