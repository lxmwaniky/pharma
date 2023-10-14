package com.pages;

import javax.swing.*;
public class Login extends JFrame{
    private JFormattedTextField usernameFormattedTextField;
    private JButton button1;
    private JComboBox comboBox1;
    private JPasswordField passwordPasswordField;
    private JPanel mainPanel;

    public static void main(String[] args) {
        Login loginScreen = new Login();
        loginScreen.setContentPane(loginScreen.mainPanel);
        loginScreen.setTitle("Pharma | Pharmacy POS");
        loginScreen.setSize(300,400);
        loginScreen.setVisible(true);
    }
}
