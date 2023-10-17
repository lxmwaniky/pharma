package com.lomoni.pages;
import com.lomoni.pages.utils.ButtonClicked;
import com.lomoni.pages.utils.InputFieldFocusListener;

import javax.swing.*;
import java.awt.*;

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
    public Login(CardLayout cardLayout, Container container){
        //Can be run privately
        setFocusListeners();


        //Button Action Listener
        ButtonClicked buttonClickedListener = new ButtonClicked(cardLayout, container);
        loginButton.addActionListener(buttonClickedListener);
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
