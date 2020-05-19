package com.arkanoid.logic;

import javax.swing.*;
import java.awt.*;

class AnotherDifficultyDialogPanel extends JPanel {

    private final Application application;

    public AnotherDifficultyDialogPanel(LayoutManager layoutManager, Application application) {
        super(layoutManager);
        this.application = application;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(application.getDifficultyImage(), 0, 0, null);
        g.setFont(application.getPanelFont());
        g.setColor(Color.WHITE);
        g.drawString("Select Difficulty:",
                getWidth() / 2 - "Select Difficulty:".length() * application.getFontSize() / 2,
                200);
    }
}