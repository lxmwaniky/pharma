import com.formdev.flatlaf.FlatIntelliJLaf;
import com.pages.Login;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            UIManager.put("defaultFont", new Font("Calibri Body", Font.PLAIN, 14));
        } catch(Exception exception){
            System.out.println("Failed to apply theme");
        }

        Login loginScreen = new Login();
        loginScreen.setContentPane(loginScreen.getMainPanel());

        //Event Listeners
        loginScreen.getUsernameFormattedTextField().addFocusListener(loginScreen.getUsernameFieldListener());
        loginScreen.getPasswordPasswordField().addFocusListener(loginScreen.getPasswordFieldListener());

        loginScreen.setTitle("Pharma | Pharmacy POS");
        loginScreen.setSize(700,600);
        loginScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginScreen.setVisible(true);
    }
}
