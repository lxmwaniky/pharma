package com.lomoni.pages.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class DeleteButtonRenderer extends DefaultTableCellRenderer {
    private JTable prescriptionTable;
    public DeleteButtonRenderer(JTable prescriptionTable){
        this.prescriptionTable = prescriptionTable;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (checkIfRowHasData(row)) {
            JButton button = new JButton("DELETE");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle the button click here
                }
            });
            return button;
        } else {
            return this;
        }
    }

    private boolean checkIfRowHasData(int row) {
        // Check the values in the row and return true if there is data, false otherwise
        for (int i = 0; i < prescriptionTable.getModel().getColumnCount(); i++) {
            if (prescriptionTable.getModel().getValueAt(row, i) != null && !prescriptionTable.getModel().getValueAt(row, i).toString().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
