package com.lomoni.pages;

import javax.swing.*;
import javax.swing.table.*;

public class Inventory {
    private JTable inventoryTable;
    private JPanel inventoryPanel;
    private JPanel addNewItemPanel;
    private JComboBox userRoleComboBox;
    private JTextField usernameFormattedTextField;
    private JPasswordField passwordPasswordField;
    private JButton loginButton;
    private JScrollPane addNewItemScrollPane;

    public Inventory(){
        //C0LUMNS
        String[] inventoryDisplayColumns = new String[]{
                "Medicine name",
                "Quantity",
                "Price"
        };

        //DATA
        Object[][] inventoryDisplayData = new Object[][]{
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
        TableModel inventoryModel = new DefaultTableModel(inventoryDisplayData, inventoryDisplayColumns);

        // Display the columns

        inventoryTable.setModel(inventoryModel);
    }

    public JPanel createMainPanel(){
        return inventoryPanel;
    }
}
