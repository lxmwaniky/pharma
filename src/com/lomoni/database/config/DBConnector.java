package com.lomoni.database.config;

import com.mysql.cj.jdbc.Driver;

import java.awt.*;
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
    public HashMap getMedicineNames(){
        HashMap<String,List> medicineNames = new HashMap<>();
        try{
            result = statement.executeQuery("SELECT * FROM "+medicinesTable);
            while(result.next()){
                List<String> medicinesList = new ArrayList<>();
                int medicine_id = result.getInt("medicine_id");
                String medicine_name = result.getString("medicine_name");

                //0 - Medicine ID
                //1 - Medicine Names
                medicinesList.add(String.valueOf(medicine_id));
                medicinesList.add(medicine_name);

                medicineNames.put("medicine_"+medicine_id, medicinesList);
            }
            statement.close();
        }catch(Exception exception){
            Log("FATAL","SQL Exception : "+exception.getMessage(),exception,DBConnector.class.getName());
        }
        return medicineNames;
    }
}
