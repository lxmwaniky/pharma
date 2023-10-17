package com.lomoni.pages.utils;

import org.apache.logging.log4j.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClicked implements ActionListener {
    private final CardLayout cardLayout;
    private final Container container;

    //Logger
    private static final Logger eventsLogger = LogManager.getLogger(ButtonClicked.class.getName());
    public ButtonClicked(CardLayout cardLayout, Container container){
        this.cardLayout = cardLayout;
        this.container = container;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        eventsLogger.info("Data Submitted");
        cardLayout.next(container);
    }
}
