package com.lomoni.services;


import com.lomoni.database.config.DBConnector;
import com.lomoni.pages.Prescription;

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
            "Frequency"
    };

    //DATA
    private final Object[][] inventoryDisplayData = new Object[][]{
            {"Amoxicillin", "123456", "3 times a day", "500mg"},
            {"Penicillin", "123456", "4 times a day", "250mg"},
            {"Ibuprofen", "123456", "Every 4-6 hours as needed", "200mg"},
            {"Metformin", "123456", "2 times a day", "500mg"},
            {"Simvastatin", "123456", "Once a day", "40mg"},
            {"Lisinopril", "123456", "Once a day", "10mg"},
            {"Loratadine", "123456", "Once a day", "10mg"}
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

                prescriptionData[counter] = new Object[]{medicine_name,patientBirthCertNo,frequency,dosage};
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
    public char handlePrescriptionFormData(String medicine_name,String patientBirthCertNo, String dosageForm, String frequency){
        try{
            DBConnector dbConnector = new DBConnector();
            Log("TRACE","Prescription form data has been passed to the database access",null,PrescriptionService.class.getName());
            return dbConnector.createNewPrescriptionRecord(medicine_name,patientBirthCertNo,dosageForm,frequency);
        }catch(Exception e){
            Log("FATAL","Error while passing inventory form data to the database access",e,PrescriptionService.class.getName());
        }
        return 'C';
    }
}
