package com.lomoni.database.config;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
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
    
    public ResultSet getAllUsers(){
        List userList = new ArrayList();
        try {
            result = statement.executeQuery("SELECT * FROM "+usersTable);
            while (result.next()) {
                return result;
            }
        }catch (SQLException exception){
            Log("FATAL","SQL Exception : "+ exception.getMessage(),exception,DBConnector.class.getName());
        }
    }
}
