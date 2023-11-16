package com.lomoni.pages;

import com.lomoni.services.SellService;

import javax.swing.*;
import java.awt.*;

public class SellReceipt {
    private JPanel mainPanel;
    private JButton loginButton;
    private JLabel medicineSoldValue;
    private JLabel dosageSoldValue;
    private JLabel quantitySoldValue;
    private JLabel prescriptionIDSoldValue;
    private JLabel dateOfDispensingValue;
    private JLabel totalCostValue;
    private int patientBirthCertNo;

    public SellReceipt(Container container, CardLayout cardLayout){
        SellService sellService = new SellService();

        medicineSoldValue.setText(sellService.getMedicineName());
        dosageSoldValue.setText(sellService.getDosageSoldValue());
        quantitySoldValue.setText(sellService.getQuantitySoldValue());
        prescriptionIDSoldValue.setText(sellService.getPrescriptionIDSoldValue());
        dateOfDispensingValue.setText(sellService.getDateOfDispensingValue());
        totalCostValue.setText(sellService.getTotalCost());
    }

    void setPatientBirthCertNo(int patientBirthCertNo){
        this.patientBirthCertNo = patientBirthCertNo;


    }
    public JPanel createMainPanel(){
        return mainPanel;
    }
}
