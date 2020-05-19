package com.arkanoid.logic;

import javax.swing.*;
import java.awt.*;

class DifficultyDialogPanel extends JPanel {

    private final Application application;

    public DifficultyDialogPanel(LayoutManager layoutManager, Application application) {
        super(layoutManager);
        this.application = application;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(application.getImage(), 0, 0, null);
        g.setFont(application.getPanelFont());
        g.setColor(Color.WHITE);
        g.drawString("Select Level:",
                getWidth() / 2 - ("Select Level:".length() * application.getFontSize()) / 2,
                200);
    }
}
