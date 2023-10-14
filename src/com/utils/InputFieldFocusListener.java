package com.utils;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/*
 * Author : Briane Lomoni 168864
 * Functionality :
 * - Clearing the placeholder text when the text field gains focus
 * - Setting the placeholder text when the text field loses focus and is empty
 */
public class InputFieldFocusListener extends FocusAdapter {
    private final JTextField usernameFormattedTextField;
    private final String PLACEHOLDER;
    //Constructor
    public InputFieldFocusListener(JTextField usernameFormattedTextField, String PLACEHOLDER){
        this.usernameFormattedTextField = usernameFormattedTextField;
        this.PLACEHOLDER = PLACEHOLDER;
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
