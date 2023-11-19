package com.lomoni.database.config;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;

public class DBConnector {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/db_braine_lomoni_168864";
    static final String USER = "root";
    static final String PASSWORD = "briomar2020";
    static final String DB_NAME = "db_braine_lomoni_168864";
    private String usersTable = "tbl_users";
    private String inventoryTable = "tbl_inventory";
    private String prescriptionTable = "tbl_prescription";
    private String prescriptionItemsTable = "tbl_prescription_items";
    private String transactionItemsTable = "tbl_transaction_items";
    private String medicinesTable = "tbl_medicines";
    private Connection conn;
    private Statement statement;
    private ResultSet result;
    public DBConnector(){
        try{
            //Load the driver class
            Class.forName(DRIVER);

            //Establish connection
            conn = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);

            //Create statement for querying database
            statement = conn.createStatement();
        }catch (ClassNotFoundException exception){
            Log("FATAL","Exception on class not found : "+exception.getMessage(),exception,DBConnector.class.getName());
        } catch (SQLException exception){
            Log("FATAL","SQL Exception : "+ exception.getMessage(),exception,DBConnector.class.getName());
        }
    }

    //LoginService methods
    public HashMap getAllUsers(){
        HashMap<String,List> usersObject = new HashMap<String, List>();
        try {
            result = statement.executeQuery("SELECT * FROM "+usersTable);
            while (result.next()) {
                List<String> userList = new ArrayList<>();
                int user_id = result.getInt("user_id");
                String user_name = result.getString("user_name");
                String user_password = result.getString("user_password");
                String user_type = result.getString("user_type");
                //0-Username
                //1-Password
                //2-UserType
                userList.add(user_name);
                userList.add(user_password);
                userList.add(user_type);

                usersObject.put("user_"+user_id, userList);
            }
            statement.close();
        }catch (SQLException exception){
            Log("FATAL","SQL Exception : "+ exception.getMessage(),exception,DBConnector.class.getName());
        }
        return usersObject;
    }


    //InventoryService methods
    public List<String> getMedicineNames(){
        List<String> medicinesList = new ArrayList<>();
        try{
            result = statement.executeQuery("SELECT * FROM "+medicinesTable);
            while(result.next()){
                int medicine_id = result.getInt("medicine_id");
                String medicine_name = result.getString("medicine_name");
                medicinesList.add(medicine_name);
            }
        }catch(Exception exception){
            Log("FATAL","SQL Exception : "+exception.getMessage(),exception,DBConnector.class.getName());
        }
        return medicinesList;
    }

    /*
     * INVENTORY FORM DATA
     * Functionality
     * 1. Check if medicine already exists in the medicines table by executing a SQL query.
     * 2. If medicine exists, update the quantity in stock and unit price in the inventory table.
     * 3. If update is successful, return 'A' to indicate that the stock was updated.
     * 4. If update fails, log a fatal error message.
     * 5. If the medicine doesn't exist, insert a new record into the medicines table and inventory table.
     * 6. If insertion is successful, return 'B' to indicate that a new record was inserted.
     * 7. If insertion fails, log a fatal error message
     */
    public char createNewMedicineRecord(String medicine_name, String dosage_form, String strength_of_dosage, String quantity_in_stock, String unit_price_text){
        char stockUpdatedForMedicine = 'A';
        char newRecordInserted = 'B';
        char anErrorOccurred = 'C';

        //Prepare sql queries
        try{
            //Check if medicine exists
            if (medicineExists(medicine_name)) {
                //Update Quantity in Stock and Unit Price
                Log("INFO","Medicine trying to be inserted already exists in the database",null,DBConnector.class.getName());
                updateInventory(medicine_name, quantity_in_stock, unit_price_text, dosage_form, strength_of_dosage);
                return stockUpdatedForMedicine;
            } else {
                //Create new record in medicinesTable and InventoryTable
                insertNewMedicine(medicine_name, quantity_in_stock, unit_price_text, dosage_form, strength_of_dosage);
                return newRecordInserted;
            }
        }catch(Exception exception){
            Log("FATAL","Exception while handling medicine records data : "+exception.getMessage(),exception,DBConnector.class.getName());

        }
        return anErrorOccurred;
    }

    private boolean medicineExists(String medicine_name) {
        try{
            //Check if medicine exists
            String sql_medicineExists = "SELECT COUNT(*) FROM "+medicinesTable+" WHERE medicine_name = ?";
            PreparedStatement medicineExistsStatement = conn.prepareStatement(sql_medicineExists);
            medicineExistsStatement.setString(1, medicine_name);
            ResultSet medicineExistsResult = medicineExistsStatement.executeQuery();
            medicineExistsResult.next();
            int medicineExistsCount = medicineExistsResult.getInt(1);
            medicineExistsStatement.close();

            return medicineExistsCount > 0;
        } catch(Exception exception){
            Log("FATAL","Exception while checking if medicine exists : "+exception.getMessage(),exception,DBConnector.class.getName());
        }

        return false;
    }

    private void updateInventory(String medicine_name, String quantity_in_stock, String unit_price_text,String dosage_form, String strength_of_dosage){
        try{
            String sql_inventoryTable = "";
            String sql_medicinesTable = "";
            ResultSet result_medicinesTable = null;
            ResultSet result_inventoryTable = null;
            //Update Quantity in Stock and Unit Price
            sql_inventoryTable = "UPDATE "+inventoryTable+" SET medicine_quantity = ?, price = ?, dosageForm = ?, strengthOfDosage = ? WHERE medicine_name = ?";
            PreparedStatement updateInventoryTable = conn.prepareStatement(sql_inventoryTable);
            updateInventoryTable.setInt(1, Integer.parseInt(quantity_in_stock));
            updateInventoryTable.setDouble(2, Double.parseDouble(unit_price_text));
            updateInventoryTable.setString(3, dosage_form);
            updateInventoryTable.setString(4, strength_of_dosage);
            updateInventoryTable.setString(5, medicine_name);
            int sql_inventoryTableSQLResult = updateInventoryTable.executeUpdate();
            updateInventoryTable.close();

            if(sql_inventoryTableSQLResult > 0){
                Log("INFO","Quantity in Stock, Unit price, strength of dosage and dosage form updated in the "+inventoryTable+" table",null,DBConnector.class.getName());
            } else {
                Log("FATAL","Error occurred while updating Quantity in Stock and Unit price in the "+inventoryTable+" table",null,DBConnector.class.getName());
            }
        }catch(SQLException sqlException){
           Log("FATAL","Error while updating the inventory table :"+sqlException.getMessage(),sqlException,DBConnector.class.getName());
        }

    }

    private void insertNewMedicine(String medicine_name, String quantity_in_stock, String unit_price_text, String dosage_form, String strength_of_dosage) {
        try{
            String sql_inventoryTable = "";
            String sql_medicinesTable = "";
            ResultSet result_medicinesTable = null;
            ResultSet result_inventoryTable = null;
            //Create new record in medicinesTable and InventoryTable
            sql_medicinesTable = "INSERT INTO "+medicinesTable+" (medicine_name) VALUES (?)";
            PreparedStatement insertIntoMedicinesTable = conn.prepareStatement(sql_medicinesTable);
            insertIntoMedicinesTable.setString(1, medicine_name);
            int insertIntoMedicinesTableResult = insertIntoMedicinesTable.executeUpdate();
            insertIntoMedicinesTable.close();

            sql_inventoryTable = "INSERT INTO "+inventoryTable+" (medicine_name, medicine_quantity, price, dosage_form, strength_of_dosage) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertIntoInventoryTable = conn.prepareStatement(sql_inventoryTable);
            insertIntoInventoryTable.setString(1, medicine_name);
            insertIntoInventoryTable.setInt(2, Integer.parseInt(quantity_in_stock));
            insertIntoInventoryTable.setDouble(3, Double.parseDouble(unit_price_text));
            insertIntoInventoryTable.setString(4, strength_of_dosage);
            insertIntoInventoryTable.setString(5, medicine_name);
            int insertIntoInventoryTableResult = insertIntoInventoryTable.executeUpdate();
            insertIntoInventoryTable.close();

            if(insertIntoMedicinesTableResult > 0){
                Log("INFO","Insertion occurred into medicines table",null,DBConnector.class.getName());
            } else {
                Log("FATAL","An Exception occurred while inserting data to the medicines table : ",null,DBConnector.class.getName());
            }

            if(insertIntoInventoryTableResult > 0){
                Log("INFO","Insertion occurred into the inventory table",null,DBConnector.class.getName());
            } else {
                Log("FATAL","An Exception occurred while inserting data to the inventory table : ",null,DBConnector.class.getName());
            }

            }
        catch(SQLException sqlException){
            Log("FATAL","Error while updating the inventory table :"+sqlException.getMessage(),sqlException,DBConnector.class.getName());
        }
    }


    /*
     FETCH Inventory Data
     */
    public HashMap getInventoryRows(){
        HashMap<String, List> inventoryRowsObject = new HashMap<>();
        try{
            result = statement.executeQuery("SELECT * FROM "+inventoryTable);
            while(result.next()){
                List<String> inventoryRows = new ArrayList<>();
                int inventory_id = result.getInt("inventory_id");
                String medicine_name = result.getString("medicine_name");
                int quantity = result.getInt("medicine_quantity");
                Double price = result.getDouble("price");
                String dosageForm = result.getString("dosageForm");
                String strengthOfDosage = result.getString("strengthOfDosage");
                inventoryRows.add(medicine_name);
                inventoryRows.add(String.valueOf(quantity));
                inventoryRows.add(String.valueOf(price));
                inventoryRows.add(dosageForm);
                inventoryRows.add(strengthOfDosage);

                inventoryRowsObject.put("inventory_item_"+inventory_id,inventoryRows);
            }
            statement.close();
        }catch(Exception exception){
            Log("FATAL","SQL Exception : "+exception.getMessage(),exception,DBConnector.class.getName());
        }
        return inventoryRowsObject;
    }

    //PRESCRIPTION SERVICE FUNCTIONS
    //Handle Prescription Form Data
    public char createNewPrescriptionRecord(String medicine_name, String patientBirthCertNo, String frequency, String dosage, String quantity){
        char newRecordInserted = 'A';
        char anErrorOccurred = 'C';

        try{
            //Create new prescription
            insertNewPrescription(medicine_name,patientBirthCertNo,frequency,dosage,quantity);
            return newRecordInserted;
        }catch(Exception e){
            Log("TRACE","Exception while handling prescription data : "+e.getMessage(),e,DBConnector.class.getName());
        }
        return anErrorOccurred;
    }

    private void insertNewPrescription(String medicine_name, String patientBirthCertNo, String frequency, String dosage, String quantity){
        try{
            String sql_prescriptionTable = "";
            //Create new record in Prescription Table

            sql_prescriptionTable = "INSERT INTO "+prescriptionTable+" (medicine_name, patient_birth_certificate, frequency, dosage, quantity) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertIntoPrescriptionTable = conn.prepareStatement(sql_prescriptionTable);
            insertIntoPrescriptionTable.setString(1, medicine_name);
            insertIntoPrescriptionTable.setString(2, patientBirthCertNo);
            insertIntoPrescriptionTable.setString(3, frequency);
            insertIntoPrescriptionTable.setString(4, dosage);
            insertIntoPrescriptionTable.setInt(5, Integer.parseInt(quantity));
            int insertIntoPrescriptionTableResult = insertIntoPrescriptionTable.executeUpdate();
            insertIntoPrescriptionTable.close();


            if(insertIntoPrescriptionTableResult > 0){
                Log("INFO","Insertion occurred into the prescription table",null,DBConnector.class.getName());
            } else {
                Log("FATAL","An Exception occurred while inserting data to the prescription table : ",null,DBConnector.class.getName());
            }

        }catch(SQLException sqlException){
            Log("FATAL","Error while updating the prescription table : "+sqlException,sqlException,DBConnector.class.getName());
        }
    }
    //Fetch Prescription Data
    public HashMap getPrescriptionRows(){
        HashMap<String, List> prescriptionRowsObject = new HashMap<>();
        try{
            result = statement.executeQuery("SELECT prescription_id,medicine_name,patient_birth_certificate,frequency,dosage,quantity FROM "+prescriptionTable);
            while(result.next()){
                List<String> prescriptionRows = new ArrayList<>();
                int prescription_id = result.getInt("prescription_id");
                String medicine_name = result.getString("medicine_name");
                int patient_birth_cert_no = result.getInt("patient_birth_certificate");
                String frequency = result.getString("frequency");
                String dosage = result.getString("dosage");
                int quantity = result.getInt("quantity");
                prescriptionRows.add(medicine_name);
                prescriptionRows.add(String.valueOf(patient_birth_cert_no));
                prescriptionRows.add(frequency);
                prescriptionRows.add(dosage);
                prescriptionRows.add(String.valueOf(quantity));

                prescriptionRowsObject.put("prescription_item_"+prescription_id,prescriptionRows);
            }
            statement.close();
        }catch(Exception exception){
            Log("FATAL","SQL Exception : "+exception.getMessage(),exception,DBConnector.class.getName());
        }
        return prescriptionRowsObject;
    }

    //SELL SERVICE FUNCTIONS
    public List<String> getAllPatientBirthCertNo(){
        List<String> allPatientBirthCertNumbers = new ArrayList<>();
        try{
            result = statement.executeQuery("SELECT patient_birth_certificate FROM "+prescriptionTable);
            while(result.next()){
                int patient_birth_certificate = result.getInt(1);
                allPatientBirthCertNumbers.add(String.valueOf(patient_birth_certificate));
            }
        }catch(Exception exception){
            Log("FATAL","SQL Exception : "+exception.getMessage(),exception,DBConnector.class.getName());
        }
        return allPatientBirthCertNumbers;
    }

    public int getPatientDataFromDB(int birthCertNo){
        int patientBirthCertNo = 0;
        try{
            result = statement.executeQuery("SELECT * FROM "+prescriptionTable+" WHERE patient_birth_certificate="+birthCertNo);
            if(result.next()){
                int prescription_id = result.getInt("prescription_id");
                patientBirthCertNo = result.getInt("patient_birth_certificate");
                int quantity = result.getInt("quantity");
            }
        }catch(Exception e){
            Log("FATAL","Exception while selecting patient data from the db : "+e.getMessage(),e,DBConnector.class.getName());
        }
        return patientBirthCertNo;
    }

    public HashMap<String, List> getPatientDataFromPrescription(int birthCertNo){
        HashMap<String, List> patientObject = new HashMap<>();
        try{
            result = statement.executeQuery("SELECT * FROM "+prescriptionTable+" WHERE patient_birth_certificate="+birthCertNo);
            while(result.next()) {
                List<String> prescriptionRows = new ArrayList<>();
                int prescription_id = result.getInt("prescription_id");
                String medicine_name = result.getString("medicine_name");
                int patient_birth_cert_no = result.getInt("patient_birth_certificate");
                String frequency = result.getString("frequency");
                String dosage = result.getString("dosage");
                int quantity = result.getInt("quantity");
                prescriptionRows.add(String.valueOf(prescription_id));
                prescriptionRows.add(medicine_name);
                prescriptionRows.add(String.valueOf(patient_birth_cert_no));
                prescriptionRows.add(frequency);
                prescriptionRows.add(dosage);
                prescriptionRows.add(String.valueOf(quantity));

                patientObject.put(String.valueOf(prescription_id),prescriptionRows);
            }
        }catch(Exception e){
            Log("FATAL","Exception while selecting patient data from the db : "+e.getMessage(),e,DBConnector.class.getName());
        }
        return patientObject;
    }
    public List<String> getPatientDataFromTransactionItems(int prescriptionID){
        List<String> transactionRows = new ArrayList<>();
        try{
            result = statement.executeQuery("SELECT * FROM "+transactionItemsTable+" WHERE prescription_id="+prescriptionID);
            while(result.next()) {
                int prescription_id = result.getInt("prescription_id");
                int transaction_id = result.getInt("transaction_id");
                int medicine_inventory_id = result.getInt("medicine_inventory_id");
                String dosage = result.getString("dosage");
                Date date_of_dispensing = result.getDate("date_of_dispensing");
                int total_cost = result.getInt("total_cost");

                transactionRows.add(String.valueOf(transaction_id));
                transactionRows.add(String.valueOf(medicine_inventory_id));
                transactionRows.add(String.valueOf(prescription_id));
                transactionRows.add(dosage);
                transactionRows.add(String.valueOf(date_of_dispensing));
                transactionRows.add(String.valueOf(total_cost));
            }
        }catch(Exception e){
            Log("FATAL","Exception while selecting transaction data from the db : "+e.getMessage(),e,DBConnector.class.getName());
        }
        return transactionRows;
    }
    public List<String> getMedicineDataFromInventory(int inventoryID){
        List<String> inventoryRows = new ArrayList<>();
        try{
            result = statement.executeQuery("SELECT * FROM "+inventoryTable+" WHERE inventory_id="+inventoryID);
            if(result.next()) {
                String medicine_name = result.getString("medicine_name");
                int medicine_quantity = result.getInt("medicine_quantity");
                double price = result.getInt("price");
                String dosageForm = result.getString("dosageForm");
                String strengthOfDosage = result.getString("strengthOfDosage");
                inventoryRows.add(String.valueOf(inventoryID));
                inventoryRows.add(medicine_name);
                inventoryRows.add(String.valueOf(medicine_quantity));
                inventoryRows.add(String.valueOf(price));
                inventoryRows.add(dosageForm);
                inventoryRows.add(strengthOfDosage);
            }
        }catch(Exception e){
            Log("FATAL","Exception while selecting medicine data from the db : "+e.getMessage(),e,DBConnector.class.getName());
        }
        return inventoryRows;
    }

    public boolean deductQuantityFromStock(int inventoryID, int quantityToBeDeducted) {
        boolean success = false;
        try{
            String sql_inventoryTable = "";

            //Update the quantity
            sql_inventoryTable = "UPDATE "+inventoryTable+" SET medicine_quantity = medicine_quantity - "+quantityToBeDeducted+" WHERE inventory_id=?";
            PreparedStatement updateInventoryTable = conn.prepareStatement(sql_inventoryTable);
            updateInventoryTable.setInt(1,inventoryID);
            int updateInventoryTableResult = updateInventoryTable.executeUpdate();
            updateInventoryTable.close();

            if(updateInventoryTableResult > 0){
                Log("INFO","Quantity sold to patient deduction ",null,DBConnector.class.getName());

            } else {
                Log("FATAL","An Exception occurred while inserting data to the prescription table : ",null,DBConnector.class.getName());
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            Log("FATAL","Exception while deducting sold quantity from db : "+e.getMessage(),e,DBConnector.class.getName());
        }
        return success;
    }
}
