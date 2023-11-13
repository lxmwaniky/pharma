package com.lomoni.services;

public class SellService {
    private String medicineName;
    private String dosageSoldValue;
    private String quantitySoldValue;
    private String prescriptionIDSoldValue;
    private String dateOfDispensingValue;
    private String totalCost;

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setDosageSoldValue(String dosageSoldValue) {
        this.dosageSoldValue = dosageSoldValue;
    }

    public void setQuantitySoldValue(String quantitySoldValue) {
        this.quantitySoldValue = quantitySoldValue;
    }

    public void setPrescriptionIDSoldValue(String prescriptionIDSoldValue) {
        this.prescriptionIDSoldValue = prescriptionIDSoldValue;
    }

    public void setDateOfDispensingValue(String dateOfDispensingValue) {
        this.dateOfDispensingValue = dateOfDispensingValue;
    }

    public String getMedicineName() {
        return "Amoxicillin";
    }

    public String getDosageSoldValue() {
        return "30 tablets";
    }

    public String getQuantitySoldValue() {
        return "3";
    }

    public String getPrescriptionIDSoldValue() {
        return  "#33252";
    }

    public String getDateOfDispensingValue() {
        return "24/3/2023";
    }

    public String getTotalCost() {
        return "500";
    }
}
