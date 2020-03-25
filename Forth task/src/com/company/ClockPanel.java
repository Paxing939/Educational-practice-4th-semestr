package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClockPanel extends JPanel {
    private int width, height;
    private int tick;
    private int angle;

    ClockPanel() {
        tick = 0;
        angle = 0;
        width = 450;
        height = 450;

        Timer timer = new Timer(10, evt -> {
            repaint();
            if (tick >= 100) {
                tick = 0;
                angle += 6;
            }
            if (angle >= 360) {
                angle = 0;
            }
            tick++;
        });
        timer.start();

        setPreferredSize(new Dimension(width + 10, height + 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, width + 10, height + 10);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        double radAngle = Math.toRadians(angle);
        int x2 = 5 + width / 2 - (int) ((width / 2) * Math.sin(-radAngle));
        int y2 = 5 + width / 2 - (int) ((width / 2) * Math.cos(-radAngle));

        g2.drawLine(5 + width / 2, 5 + height / 2, x2, y2);
        g2.drawOval(5, 5, width, height);
    }
}
