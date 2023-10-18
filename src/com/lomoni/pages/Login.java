package com.lomoni.pages;
import com.lomoni.pages.utils.ButtonClicked;
import com.lomoni.pages.utils.InputChanged;
import com.lomoni.pages.utils.InputFieldFocusListener;
import com.lomoni.services.LoginService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;

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
    private String userNameValue;
    private JButton loginButton;
    private JComboBox userRoleComboBox;
    private JPasswordField passwordPasswordField;
    private char[] passWordValue;
    private JPanel mainPanel;
    private JLabel lbl_error_login;

    private LoginService loginService;

    //Constructor
    public Login(CardLayout cardLayout, Container container){
        //Can be run privately
        setFocusListeners();


        //Inputs Action Listener
        //USERNAME
        InputChanged userNameInputListener = new InputChanged(usernameFormattedTextField);
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
        InputChanged passWordInputListener = new InputChanged(passwordPasswordField);
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

        //USER TYPE
//        InputChanged userRoleListener = new InputChanged(userRoleComboBox);


        //Button Action Listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLoginAction();
            }
        });
    }

    //Set focus listeners
    private void setFocusListeners(){
        final InputFieldFocusListener usernameFieldListener = new InputFieldFocusListener(usernameFormattedTextField, "Username...");
        usernameFormattedTextField.addFocusListener(usernameFieldListener);

        final InputFieldFocusListener passwordFieldListener = new InputFieldFocusListener(passwordPasswordField, "Password...");
        passwordPasswordField.addFocusListener(passwordFieldListener);
    }

    //Login Action
    private void handleLoginAction(){
        // Perform other actions using the inputText variable
        Object userType = userRoleComboBox.getSelectedItem();
        loginService = new LoginService(userNameValue, new String(passWordValue), userType);
    }


    public JPanel createMainPanel(){
        return mainPanel;
    }

    public JTextField getUsernameFormattedTextField() {
        return usernameFormattedTextField;
    }

    public JComboBox getUserRoleComboBox() {
        return userRoleComboBox;
    }

    public JPasswordField getPasswordPasswordField() {
        return passwordPasswordField;
    }
}
