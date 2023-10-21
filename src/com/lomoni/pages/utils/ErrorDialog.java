package com.lomoni.pages.utils;

import javax.swing.*;
import java.awt.*;

import static com.lomoni.pages.utils.ImplementLookAndFeel.setThemeAndFont;

public class ErrorDialog extends JDialog {

    private JLabel errorMessageLabel;
    private static final int screenWidth = 200;
    private static final int screenHeight = 300;
    public ErrorDialog(String errorMessage) {
        setThemeAndFont();

        JFrame screen = new JFrame();
        screen.setLayout(new FlowLayout(FlowLayout.CENTER));
//        add(screen);

        errorMessageLabel = new JLabel(errorMessage);
        screen.add(errorMessageLabel);

        pack();
        screen.setTitle("Error | Pharma");
        screen.setSize(screenWidth,screenHeight);
        screen.setVisible(true);
    }

    public void display() {
        setVisible(true);
    }
}

