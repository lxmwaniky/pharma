package com.lomoni.pages;

import javax.swing.*;

public class Sell {
    private JComboBox patientIdComboBox;
    private JButton submitButton;
    private JPanel mainPanel;

    /*
     * The pharmacist will enter the patient's information and the prescription information into the sell page.
     * The sell page will check the prescription against the tbl_prescription table to verify that the prescription is valid.
     * The sell page will check the medicine inventory against the tbl_inventory table to verify that the medicine is in stock.
     * If the prescription is valid and the medicine is in stock, the sell page will create a new record in the tbl_transaction_items table.
     * The sell page will update the medicine inventory in the tbl_inventory table.
     * The sell page will move to the sell-receipt
     */
    public JPanel createMainPanel(){
        return mainPanel;
    }
}
