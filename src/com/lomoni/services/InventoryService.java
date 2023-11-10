package com.lomoni.services;

import com.lomoni.database.config.DBConnector;

public class InventoryService {
    //C0LUMNS
    private final String[] inventoryDisplayColumns = new String[]{
            "Medicine name",
            "Quantity",
            "Price"
    };

    //DATA
    private final Object[][] inventoryDisplayData = new Object[][]{
            {"Penicillin", "43", "907"},
            {"Ibuprofen", "16", "396"},
            {"Amoxicillin", "44", "854"},
            {"Penicillin", "86", "831"},
            {"Ibuprofen", "69", "735"},
            {"Amoxicillin", "62", "307"},
            {"Amoxicillin", "42", "541"},
            {"Amoxicillin", "30", "208"},
            {"Ibuprofen", "100", "589"},
            {"Amoxicillin", "50", "738"}
    };

    //Getter functions

    public String[] getInventoryDisplayColumns() {
        return inventoryDisplayColumns;
    }

    public Object[][] getInventoryDisplayData() {
        return inventoryDisplayData;
    }

    public void getMedicineNames(){
        DBConnector dbConnector = new DBConnector();
        System.out.println(dbConnector.getMedicineNames());
    }
}
