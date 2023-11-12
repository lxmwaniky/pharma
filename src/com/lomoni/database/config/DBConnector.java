package com.lomoni.database.config;

import com.mysql.cj.jdbc.Driver;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
    private String transactionItemsTable = "tbl_transactions_items";
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

    public void createNewMedicineRecord(String medicine_name, String dosage_form, String strength_of_dosage, String quantity_in_stock, String unit_price_text){
        String sql_inventoryTable = "";
        String sql_medicinesTable = "";
        ResultSet result_medicinesTable = null;
        ResultSet result_inventoryTable = null;
        //Prepare sql queries
        try{
            //Check if medicine exists
            String sql_medicineExists = "SELECT COUNT(*) FROM "+medicinesTable+" WHERE medicine_name = ?";
            PreparedStatement medicineExistsStatement = conn.prepareStatement(sql_medicineExists);
            medicineExistsStatement.setString(1, medicine_name);
            ResultSet medicineExistsResult = medicineExistsStatement.executeQuery();
            medicineExistsResult.next();
            int medicineExistsCount = medicineExistsResult.getInt(1);
            medicineExistsStatement.close();

            if (medicineExistsCount > 0) {
                //Update Quantity in Stock and Unit Price
                Log("INFO","Medicine trying to be inserted already exists in the database",null,DBConnector.class.getName());
                sql_inventoryTable = "UPDATE "+inventoryTable+" SET medicine_quantity = ?, price = ? WHERE medicine_name = ?";
                PreparedStatement updateInventoryTable = conn.prepareStatement(sql_inventoryTable);
                updateInventoryTable.setInt(1, Integer.parseInt(quantity_in_stock));
                updateInventoryTable.setDouble(2, Double.parseDouble(unit_price_text));
                updateInventoryTable.setString(3, medicine_name);
                int sql_inventoryTableSQLResult = updateInventoryTable.executeUpdate();
                updateInventoryTable.close();

                if(sql_inventoryTableSQLResult > 0){
                    Log("INFO","Quantity in Stock and Unit price updated in the "+inventoryTable+" table",null,DBConnector.class.getName());
                } else {
                    Log("FATAL","Error occured while updating Quantity in Stock and Unit price in the "+inventoryTable+" table",null,DBConnector.class.getName());
                }
            } else {
                //Create new record in medicinesTable and InventoryTable
                sql_medicinesTable = "INSERT INTO "+medicinesTable+" (medicine_name) VALUES (?)";
                PreparedStatement insertIntoMedicinesTable = conn.prepareStatement(sql_medicinesTable);
                insertIntoMedicinesTable.setString(1, medicine_name);
                int insertIntoMedicinesTableResult = insertIntoMedicinesTable.executeUpdate();
                insertIntoMedicinesTable.close();

                sql_inventoryTable = "INSERT INTO "+inventoryTable+" (medicine_name, medicine_quantity, price) VALUES (?, ?, ?)";
                PreparedStatement insertIntoInventoryTable = conn.prepareStatement(sql_inventoryTable);
                insertIntoInventoryTable.setString(1, medicine_name);
                insertIntoInventoryTable.setInt(2, Integer.parseInt(quantity_in_stock));
                insertIntoInventoryTable.setDouble(3, Double.parseDouble(unit_price_text));
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
            statement.close();
        }catch(Exception exception){
            Log("FATAL","Exception while handling medicine records data : "+exception.getMessage(),exception,DBConnector.class.getName());

        }
    }
}
