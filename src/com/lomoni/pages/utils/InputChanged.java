package com.lomoni.pages.utils;

import javax.swing.*;
import java.awt.event.*;

public class InputChanged extends FocusAdapter {

    private final JTextField inputField;

    private String inputText;

    public InputChanged(JTextField inputField) {
        this.inputField  = inputField;

    }
    @Override
    public void focusLost(FocusEvent e) {
        inputText = inputField.getText();
    }
    public String getInputText() {
        return inputText;
    }
}

