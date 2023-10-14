package com.pages;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.utils.InputFieldFocusListener;

import javax.swing.UIManager;
import javax.swing.*;
public class Login{
    private JTextField usernameFormattedTextField;
    private JButton loginButton;
    private JComboBox userRoleComboBox;
    private JPasswordField passwordPasswordField;
    private JPanel mainPanel;
    private JLabel lbl_error_login;


    //Listeners
    private final InputFieldFocusListener usernameFieldListener = new InputFieldFocusListener(usernameFormattedTextField, "Username...");



    private final InputFieldFocusListener passwordFieldListener = new InputFieldFocusListener(passwordPasswordField, "Password...");

    //Getter and Setter Functions
    public InputFieldFocusListener getUsernameFieldListener() {
        return usernameFieldListener;
    }
    public InputFieldFocusListener getPasswordFieldListener() {
        return passwordFieldListener;
    }
    public JTextField getUsernameFormattedTextField() {
        return usernameFormattedTextField;
    }

    //Set focus listeners
    public void setFocusListeners(){
        //Username
        usernameFormattedTextField.addFocusListener(usernameFieldListener);
        passwordPasswordField.addFocusListener(passwordFieldListener);
    }
    public void setUsernameFormattedTextField(JFormattedTextField usernameFormattedTextField) {
        this.usernameFormattedTextField = usernameFormattedTextField;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public void setUserRoleComboBox(JComboBox userRoleComboBox) {
        this.userRoleComboBox = userRoleComboBox;
    }

    public void setPasswordPasswordField(JPasswordField passwordPasswordField) {
        this.passwordPasswordField = passwordPasswordField;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
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

    public JPanel getMainPanel(){
        return mainPanel;
    }
}
