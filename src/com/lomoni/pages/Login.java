package com.lomoni.pages;
import com.lomoni.pages.utils.InputFieldFocusListener;

import javax.swing.*;
/*
 * Author : Briane Lomoni 168864 14/10/2023
 * Functionality :
 * - Login form with input fields for Username, Password, User role
 * - Set Focus Listeners on Username and Password fields
 * - Handle focus events to clear and set placeholder text in input fields
 *
 */
public class Login{
    private JTextField usernameFormattedTextField;
    private JButton loginButton;
    private JComboBox userRoleComboBox;
    private JPasswordField passwordPasswordField;
    private JPanel mainPanel;
    private JLabel lbl_error_login;


    //Constructor
    public Login(){
        //Can be run privately
        setFocusListeners();
    }

    //Set focus listeners
    private void setFocusListeners(){
        final InputFieldFocusListener usernameFieldListener = new InputFieldFocusListener(usernameFormattedTextField, "Username...");
        usernameFormattedTextField.addFocusListener(usernameFieldListener);

        final InputFieldFocusListener passwordFieldListener = new InputFieldFocusListener(passwordPasswordField, "Password...");
        passwordPasswordField.addFocusListener(passwordFieldListener);
    }

    public JPanel createMainPanel(){
        return mainPanel;
    }
}
