package com.company;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    Window() {
        super("Clock");
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        ClockPanel clockPanel = new ClockPanel();
        tabbedPane.add("Clock", clockPanel);

        ClockPictureWindow clockPicturePanel = new ClockPictureWindow();
        tabbedPane.add("Clock with picture", clockPicturePanel);

        MainWindow mainWindow = new MainWindow();
        tabbedPane.add("3d", mainWindow);

        add(tabbedPane, BorderLayout.CENTER);

        pack();
        setPreferredSize(new Dimension(380, 400));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

}
