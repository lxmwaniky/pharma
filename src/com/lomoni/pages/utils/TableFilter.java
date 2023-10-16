package com.lomoni.pages.utils;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;

public class TableFilter implements KeyListener {
    private JTable inventoryTable;

    private JTextField filterTextInput;
    public TableFilter(JTable inventoryTable,JTextField filterTextInput){
        this.inventoryTable = inventoryTable;
        this.filterTextInput = filterTextInput;
    }
    private void filterTable(String filterText){
        //TABLE ROW SORTER
        TableRowSorter sorter = new TableRowSorter(inventoryTable.getModel());

        //ROW FILTER
        RowFilter rowFilter = RowFilter.regexFilter(filterText);

        //ROW FILTER ON TABLE ROW SORTER
        sorter.setRowFilter(rowFilter);

        //SET TABLE ROW SORTER ON THE TABLE
        inventoryTable.setRowSorter(sorter);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        filterTable(filterTextInput.getText());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        filterTable(filterTextInput.getText());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        filterTable(filterTextInput.getText());
    }
}
