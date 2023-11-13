package com.lomoni.services;

import com.lomoni.database.config.DBConnector;

import java.util.List;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;

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

    //Handling data from the form
    public char handleInventoryFormData(String medicine_name, String dosage_form, String strength_of_dosage, String quantity_in_stock, String unit_price_text){
        try{
        DBConnector dbConnector = new DBConnector();
        Log("TRACE","Inventory form of data has been passed to database access",null,InventoryService.class.getName());
        return dbConnector.createNewMedicineRecord(medicine_name, dosage_form, strength_of_dosage, quantity_in_stock, unit_price_text);
        }catch(Exception exception){
            Log("FATAL","Error while passing inventory form data to the database access",exception,InventoryService.class.getName());
        }
        return 'C';
    }
}
