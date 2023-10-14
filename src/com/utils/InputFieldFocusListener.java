package com.utils;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/*
 * Author : Briane Lomoni 168864
 * Functionality :
 * - Clearing the placeholder text when the text field gains focus
 * - Setting the placeholder text when the text field loses focus and is empty
 */
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
        //If empty when unfocused
        if(usernameFormattedTextField.getText().trim().equals("")){
            //Set placeholder
            usernameFormattedTextField.setText("Username...");
        }
    }
}
