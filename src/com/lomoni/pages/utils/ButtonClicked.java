package com.lomoni.pages.utils;


import org.apache.logging.log4j.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClicked implements ActionListener {
    private final CardLayout cardLayout;
    private final Container container;
    private String userName;
    private char[] passWord;
    private String userType;

    //Logger
    private static final Logger eventsLogger = LogManager.getLogger(ButtonClicked.class.getName());
    public ButtonClicked(CardLayout cardLayout, Container container, String userName) {
        this.cardLayout = cardLayout;
        this.container = container;
        this.userName = userName;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(userName);
        System.out.println(passWord);
        System.out.println(userType);
        eventsLogger.info("Data Submitted");
//        cardLayout.next(container);
    }
}
