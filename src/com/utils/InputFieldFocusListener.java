package com.utils;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class InputFieldFocusListener implements FocusListener {
    private JTextField usernameFormattedTextField;

    //Constructor
    public InputFieldFocusListener(JTextField usernameFormattedTextField){
        this.usernameFormattedTextField = usernameFormattedTextField;
    }
    @Override
    public void focusGained(FocusEvent e) {
        // If placeholder is Username...
        if(usernameFormattedTextField.getText().trim().equals("Username...")){
            //Clear placeholder
            usernameFormattedTextField.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
