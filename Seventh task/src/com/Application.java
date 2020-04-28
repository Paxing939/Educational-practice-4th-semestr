package com;

import com.barleybreak.BarleyBreak;
import com.wordart.TextPanel;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 900;

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public void setScreenBounds() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension getScreenSize = toolkit.getScreenSize();
        int x = getScreenSize.width / 2 - WIDTH / 2;
        int y = getScreenSize.height / 2 - HEIGHT / 2;
        setBounds(x, y, WIDTH, HEIGHT);
    }

    public Application() {
        setTitle("Barley - Break and Mini WordArt");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setScreenBounds();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Mini WordArt", new TextPanel());
        tabbedPane.addTab("Barley-break", new BarleyBreak());
        add(tabbedPane);
    }

}
