package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainWindow extends JFrame {

    MainWindow() throws IOException {
        super("Clock");
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        ClockPanel clockPanel = new ClockPanel();
        tabbedPane.add("Clock", clockPanel);

        ClockPictureWindow clockPicturePanel = new ClockPictureWindow();
        tabbedPane.add("Clock with picture", clockPicturePanel);

        Diagram diagram = new Diagram();
        tabbedPane.add("Diagram", diagram);

        add(tabbedPane, BorderLayout.CENTER);

        pack();
        setMaximumSize(new Dimension(500, 500));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

}
