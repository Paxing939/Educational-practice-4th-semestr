package com.specks;

import com.barleybreak.BarleyBreak;
import com.wordart.TextPanel;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 900;
    public static final String CAPTION = "Barley - Break & Mini WordArt";

    public void setScreenBounds() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension getScreenSize = toolkit.getScreenSize();
        int x = getScreenSize.width / 2 - WIDTH / 2;
        int y = getScreenSize.height / 2 - HEIGHT / 2;
        setBounds(x, y, WIDTH, HEIGHT);
    }

    public Application() {
        setTitle(CAPTION);
        setScreenBounds();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Barley-break", new BarleyBreak());
        tabbedPane.addTab("Mini WordArt", new TextPanel());

        add(tabbedPane);
    }

}
