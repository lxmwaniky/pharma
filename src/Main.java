import com.formdev.flatlaf.FlatIntelliJLaf;
import com.pages.Login;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Main {
    private static final int screenWidth = 700;
    private static final int screenHeight = 600;
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            UIManager.put("defaultFont", new Font("Calibri Body", Font.PLAIN, 14));
        } catch(Exception exception){
            System.out.println("Failed to apply theme");
        }

        JFrame screen = new JFrame();
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Login loginScreen = new Login();

        //Event Listeners
        loginScreen.setFocusListeners();

        //Setting up window
        screen.setContentPane(loginScreen.getMainPanel());
        screen.setTitle("Pharma | Pharmacy POS");
        screen.setSize(screenWidth,screenHeight);
        screen.setVisible(true);
    }
}
