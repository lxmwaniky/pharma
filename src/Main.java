import com.pages.Login;
import javax.swing.*;
import static com.utils.ImplementLookAndFeel.setThemeAndFont;

public class Main{
    private static final int screenWidth = 700;
    private static final int screenHeight = 600;
    public static void main(String[] args) {
        setThemeAndFont();

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
