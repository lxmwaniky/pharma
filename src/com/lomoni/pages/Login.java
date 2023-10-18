package com.lomoni.pages;

import com.lomoni.pages.utils.InputFieldFocusListener;
import com.lomoni.services.LoginService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * Author : Briane Lomoni 168864 14/10/2023
 * Functionality :
 * - Login form with input fields for Username, Password, User role
 * - Set Focus Listeners on Username and Password fields
 * - Handle focus events to clear and set placeholder text in input fields
 * - Handle login action when login button is clicked
 *
 */
public class Login {
    private JTextField usernameFormattedTextField;
    private String userNameValue;
    private JButton loginButton;
    private JComboBox userRoleComboBox;
    private JPasswordField passwordPasswordField;
    private char[] passWordValue;
    private JPanel mainPanel;
    private JLabel lbl_error_login;

    //Constructor
    public Login() {
        //Add PLACEHOLDERS to inputs
        setFocusListeners();
        //Add document listeners to grab input data on change.
        //USERNAME
        usernameFormattedTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                userNameValue = usernameFormattedTextField.getText(); // Update the input text when focus is lost
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                userNameValue = usernameFormattedTextField.getText(); // Update the input text when focus is lost
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                userNameValue = usernameFormattedTextField.getText(); // Update the input text when focus is lost
            }
        });

        //PASSWORD
        passwordPasswordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                passWordValue = passwordPasswordField.getPassword();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                passWordValue = passwordPasswordField.getPassword();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                passWordValue = passwordPasswordField.getPassword();
            }
        });

        //Add action ( click ) listener to button
        loginButton.addActionListener(e -> handleLoginAction());
    }

    //Focus Listeners on Button
    private void setFocusListeners() {
        final InputFieldFocusListener usernameFieldListener = new InputFieldFocusListener(usernameFormattedTextField, "Username...");
        usernameFormattedTextField.addFocusListener(usernameFieldListener);

        final InputFieldFocusListener passwordFieldListener = new InputFieldFocusListener(passwordPasswordField, "Password...");
        passwordPasswordField.addFocusListener(passwordFieldListener);
    }

    //Login Action
    private void handleLoginAction() {
        Object userType = userRoleComboBox.getSelectedItem();
        new LoginService(userNameValue, new String(passWordValue), userType);
    }


    public JPanel createMainPanel() {
        return mainPanel;
    }
}
