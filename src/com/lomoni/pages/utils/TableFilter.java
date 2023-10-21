package com.lomoni.pages.utils;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import static com.lomoni.pages.utils.LogManagerImplementation.Log;

/*
 * Author : Braine Lomoni 168864 21/10/2023
 * Functionality :
 * - Filtering JTable based on user input in JTextField
 * - Applying RowFilter on JTable using TableRowSorter
 */
public class TableFilter implements KeyListener {
    private JTable inventoryTable;

    private JTextField filterTextInput;
    public void setInventoryTable(JTable inventoryTable) {
        this.inventoryTable = inventoryTable;
    }

    public void setFilterTextInput(JTextField filterTextInput) {
        this.filterTextInput = filterTextInput;
    }
    private void filterTable(String filterText){
        try {
            //TABLE ROW SORTER
            TableRowSorter sorter = new TableRowSorter(inventoryTable.getModel());

            //ROW FILTER
            RowFilter rowFilter = RowFilter.regexFilter(filterText);

            //ROW FILTER ON TABLE ROW SORTER
            sorter.setRowFilter(rowFilter);

            //SET TABLE ROW SORTER ON THE TABLE
            inventoryTable.setRowSorter(sorter);
        } catch (Exception e){
            Log("FATAL","Exception occurred while setting row filter on table: "+e.getMessage(),e);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        filterTable(filterTextInput.getText());
    }
}
