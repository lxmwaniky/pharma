package com.lomoni.services;


public class PrescriptionService {
    //C0LUMNS
    private final String[] inventoryDisplayColumns = new String[]{
            "Medicine Name",
            "Patient Name",
            "Frequency",
            "Dosage"
    };

    //DATA
    private final Object[][] inventoryDisplayData = new Object[][]{
            {"Amoxicillin", "John Doe", "3 times a day", "500mg"},
            {"Penicillin", "Jane Doe", "4 times a day", "250mg"},
            {"Ibuprofen", "Mary Smith", "Every 4-6 hours as needed", "200mg"},
            {"Metformin", "David Jones", "2 times a day", "500mg"},
            {"Simvastatin", "Peter Williams", "Once a day", "40mg"},
            {"Lisinopril", "Susan Miller", "Once a day", "10mg"},
            {"Loratadine", "Robert Brown", "Once a day", "10mg"}
    };

    //Getter functions

    public String[] getInventoryDisplayColumns() {
        return inventoryDisplayColumns;
    }

    public Object[][] getInventoryDisplayData() {
        return inventoryDisplayData;
    }
}
