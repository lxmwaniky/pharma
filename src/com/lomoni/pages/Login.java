package com.lomoni.pages;

import com.lomoni.pages.utils.InputFieldFocusListener;
import com.lomoni.services.LoginService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;

import static com.lomoni.pages.utils.LogManagerImplementation.Log;
import static com.lomoni.pages.utils.PharmaDialog.showMessage;

/*
 * Author : Briane Lomoni 168864 14/10/2023
 * Functionality :
 * - Login form with input fields for Username, Password, User role
 * - Set Focus Listeners on Username and Password fields
 * - Handle focus events to clear and set placeholder text in input fields
 * - Handle login action when login button is clicked
 *
 */
public class  Login {
    private JTextField usernameFormattedTextField;
    private String userNameValue;
    private JButton loginButton;
    private JComboBox userRoleComboBox;
    private JPasswordField passwordPasswordField;
    private char[] passWordValue;
    private JPanel mainPanel;
    private JLabel lbl_error_login;

    private Container container;
    private CardLayout cardLayout;


    //Constructor
    public Login(Container container, CardLayout cardLayout) {
        //Set the layout and parent component
        this.container = container;
        this.cardLayout = cardLayout;

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
        loginButton.addActionListener(e -> {
            handleLoginAction();
            Log("INFO","Login Button Clicked",null,Login.class.getName());
            showMessage(container,"Sign In Successful!","Logged In",1);
        });
    }

    //Focus Listeners on Button
    private void setFocusListeners() {
        try{
            final InputFieldFocusListener usernameFieldListener = new InputFieldFocusListener(usernameFormattedTextField, "Username...");
            usernameFormattedTextField.addFocusListener(usernameFieldListener);

            final InputFieldFocusListener passwordFieldListener = new InputFieldFocusListener(passwordPasswordField, "Password...");
            passwordPasswordField.addFocusListener(passwordFieldListener);
            Log("INFO","Input fields focus set",null,Login.class.getName());
        }catch(Exception e){
            Log("FATAL","Exception occurred while setting the input fields focus listeners : "+e.getMessage(),e,Inventory.class.getName());
        }

    }

    //Login Action
    private void handleLoginAction() {
        try{
            Object userType = userRoleComboBox.getSelectedItem();
            LoginService loginService = new LoginService(userNameValue, new String(passWordValue), userType);
            Log("INFO","Login Data Passed to Service",null,Login.class.getName());
            cardLayout.next(container);
            Log("TRACE","Screen switched to Inventory",null,Login.class.getName());
        }catch(Exception e){
            //Call method that will create an error on the users end
            if(userNameValue.isEmpty() || new String(passWordValue).isEmpty()){
                showMessage(container, "Fill all the inputs","Empty inputs!",0);
            }

            Log("FATAL","Exception occurred while carrying out the login action : "+e.getMessage(),e,Login.class.getName());
        }
    }
    public JPanel createMainPanel() {
        return mainPanel;
    }
}
