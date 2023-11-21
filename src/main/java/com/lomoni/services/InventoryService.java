package com.lomoni.services;

import com.lomoni.database.config.DBConnector;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;

public class InventoryService {
    //C0LUMNS
    private final String[] inventoryDisplayColumns = new String[]{
            "Medicine name",
            "Quantity",
            "Price",
            "Dosage Form",
            "Strength of Dosage"
    };

    //Getter functions

    public String[] getInventoryDisplayColumns() {
        return inventoryDisplayColumns;
    }

    public Object[][] getInventoryDisplayData() {
        HashMap inventoryRowsObject;
        Object[][] inventoryData = new Object[300][];
        try{
            DBConnector dbConnector = new DBConnector();
            inventoryRowsObject = dbConnector.getInventoryRows();
            int counter = 0;

            for(Object i : inventoryRowsObject.keySet()){
                ArrayList inventoryRowList = new ArrayList<>();
                inventoryRowList = (ArrayList) inventoryRowsObject.get(i);
                String medicine_name = (String) inventoryRowList.get(0);
                String medicine_quantity = (String) inventoryRowList.get(1);
                String price = (String) inventoryRowList.get(2);
                String dosageForm = (String) inventoryRowList.get(3);
                String strengthOfDosage = (String) inventoryRowList.get(4);


                inventoryData[counter] = new Object[]{medicine_name,medicine_quantity,price,dosageForm,strengthOfDosage};
                counter++;
            }
            return inventoryData;
        }catch(Exception exception) {
            Log("FATAL", "Exception while getting inventory rows from the db : " + exception.getMessage(), exception, InventoryService.class.getName());
        }
        return inventoryData;
    }

    //Handling data from the form
    public char handleInventoryFormData(String medicine_name, String dosage_form, String strength_of_dosage, String quantity_in_stock, String unit_price_text){
        try{
        DBConnector dbConnector = new DBConnector();
        Log("TRACE","Inventory form data has been passed to database access",null,InventoryService.class.getName());
        return dbConnector.createNewMedicineRecord(medicine_name, dosage_form, strength_of_dosage, quantity_in_stock, unit_price_text);
        }catch(Exception exception){
            Log("FATAL","Error while passing inventory form data to the database access",exception,InventoryService.class.getName());
        }
        return 'C';
    }
}
