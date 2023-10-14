import com.formdev.flatlaf.FlatIntelliJLaf;
import com.pages.Login;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch(Exception exception){
            System.out.println("Failed to apply theme");
        }

        Login loginScreen = new Login();
        loginScreen.setContentPane(loginScreen.getMainPanel());
        loginScreen.setTitle("Pharma | Pharmacy POS");
        loginScreen.setSize(700,600);
        loginScreen.setVisible(true);
    }
}
