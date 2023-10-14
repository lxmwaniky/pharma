package com.pages;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import javax.swing.*;
public class Login extends JFrame{
    private JFormattedTextField usernameFormattedTextField;
    private JButton loginButton;
    private JComboBox userRoleComboBox;
    private JPasswordField passwordPasswordField;
    private JPanel mainPanel;

    public JFormattedTextField getUsernameFormattedTextField() {
        return usernameFormattedTextField;
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
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        Login loginScreen = new Login();
        loginScreen.setContentPane(loginScreen.mainPanel);
        loginScreen.setTitle("Pharma | Pharmacy POS");
        loginScreen.setSize(300,400);
        loginScreen.setVisible(true);
    }
}
