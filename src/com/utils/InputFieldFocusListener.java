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
    private final JTextField usernameFormattedTextField;
    private final String PLACEHOLDER = "Username...";
    //Constructor
    public InputFieldFocusListener(JTextField usernameFormattedTextField){
        this.usernameFormattedTextField = usernameFormattedTextField;
    }
    @Override
    public void focusGained(FocusEvent e) {
        // If placeholder is Username...
        if(PLACEHOLDER.equals(usernameFormattedTextField.getText().trim())){
            //Clear placeholder
            usernameFormattedTextField.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        //If empty when unfocused
        if(usernameFormattedTextField.getText().trim().isEmpty()){
            //Set placeholder
            usernameFormattedTextField.setText(PLACEHOLDER);
        }
    }
}
