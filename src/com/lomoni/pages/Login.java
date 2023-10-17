package com.lomoni.pages;
import com.lomoni.pages.utils.ButtonClicked;
import com.lomoni.pages.utils.InputChanged;
import com.lomoni.pages.utils.InputFieldFocusListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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

    //Constructor
    public Login(CardLayout cardLayout, Container container){
        //Can be run privately
        setFocusListeners();


        //Inputs Action Listener
        //USERNAME
        InputChanged userNameInputListener = new InputChanged(usernameFormattedTextField);
        usernameFormattedTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Nothing to do when focus is gained
            }

            @Override
            public void focusLost(FocusEvent e) {
                userNameValue = usernameFormattedTextField.getText(); // Update the input text when focus is lost
            }
        });

        //PASSWORD
        InputChanged passWordInputListener = new InputChanged(passwordPasswordField);
        passwordPasswordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //Nothing to do when focus is gained
            }

            @Override
            public void focusLost(FocusEvent e) {
                passWordValue = passwordPasswordField.getPassword();
            }
        });

        //USER TYPE
//        InputChanged userRoleListener = new InputChanged(userRoleComboBox);


        //Button Action Listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Access the updated input text here
                System.out.println("Username: " + userNameValue);
                System.out.println("Password: " + passWordValue);
                System.out.println("Input text on button click: " + userRoleComboBox.getAction());

                // Perform other actions using the inputText variable
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

    //Set cardLayout and Container


    public JPanel createMainPanel(){
        return mainPanel;
    }

    public JTextField getUsernameFormattedTextField() {
        return usernameFormattedTextField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JComboBox getUserRoleComboBox() {
        return userRoleComboBox;
    }

    public JPasswordField getPasswordPasswordField() {
        return passwordPasswordField;
    }
}
