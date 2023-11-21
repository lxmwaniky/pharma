package com.lomoni.services;


import com.lomoni.database.config.DBConnector;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;

public class PrescriptionService {
    //C0LUMNS
    private final String[] inventoryDisplayColumns = new String[]{
            "Medicine Name",
            "Patient Birth Cert. No",
            "Dosage",
            "Frequency",
            "Quantity",
            "DELETE"
    };


    //Getter functions

    public String[] getInventoryDisplayColumns() {
        return inventoryDisplayColumns;
    }

    public Object[][] getInventoryDisplayData() {
        HashMap prescriptionRowsObject;
        Object[][] prescriptionData = new Object[300][];
        try{
            DBConnector dbConnector = new DBConnector();
            prescriptionRowsObject = dbConnector.getPrescriptionRows();
            int counter = 0;

            for(Object i : prescriptionRowsObject.keySet()){
                ArrayList prescriptionRowList = new ArrayList<>();
                prescriptionRowList = (ArrayList) prescriptionRowsObject.get(i);
                String medicine_name = (String) prescriptionRowList.get(0);
                String patientBirthCertNo = (String) prescriptionRowList.get(1);
                String frequency = (String) prescriptionRowList.get(2);
                String dosage = (String) prescriptionRowList.get(3);
                String quantity = (String) prescriptionRowList.get(4);
                JButton deleteButton = new JButton("DELETE");

                prescriptionData[counter] = new Object[]{medicine_name,patientBirthCertNo,frequency,dosage,quantity, deleteButton};
                counter++;
            }
            return prescriptionData;
        }catch(Exception exception) {
            Log("FATAL", "Exception while getting prescription rows from the db : " + exception.getMessage(), exception, PrescriptionService.class.getName());
        }
        return prescriptionData;
    }

    public List<String> getMedicineNames(){
        DBConnector dbConnector = new DBConnector();
        return dbConnector.getMedicineNames();
    }

    //Handling data from the form
    public char handlePrescriptionFormData(String medicine_name, String patientBirthCertNo, String dosageForm, String frequency, String quantityValue){
        try{
            DBConnector dbConnector = new DBConnector();
            Log("TRACE","Prescription form data has been passed to the database access",null,PrescriptionService.class.getName());
            return dbConnector.createNewPrescriptionRecord(medicine_name,patientBirthCertNo,dosageForm,frequency,quantityValue);
        }catch(Exception e){
            Log("FATAL","Error while passing inventory form data to the database access",e,PrescriptionService.class.getName());
        }
        return 'C';
    }
}
