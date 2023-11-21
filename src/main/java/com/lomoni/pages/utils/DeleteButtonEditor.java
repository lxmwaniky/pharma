package com.lomoni.pages.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButtonEditor extends DefaultCellEditor {

    public DeleteButtonEditor() {
        super(new JTextField());
    }

    @Override


    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        JButton button = new JButton("DELETE");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the button click here
                System.out.println("DELETE BUTTON CLICKED");
            }
        });
        return button;
    }
}