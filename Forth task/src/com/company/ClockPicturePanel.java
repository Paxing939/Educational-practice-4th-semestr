package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ClockPicturePanel extends JPanel {
    private int width, height;
    private int tick;
    private int angle;
    private int direction;
    private int speed;
    private BufferedImage image;

    ClockPicturePanel(ClockPictureWindow parent) {
        super();
        tick = 0;
        angle = 0;
        width = 400;
        height = 400;
        setPreferredSize(new Dimension(width, height));
        speed = 1;
        int delay = 1;
        direction = 1;
        Timer timer = new Timer(delay, evt -> {
            width = getWidth();
            height = getHeight();
            speed = parent.getSpeed();
            direction = parent.getDirection();
            if (tick > 30 / speed) {
                tick = 0;
                angle += direction;
            }
            if (angle >= 360) {
                angle = angle - 360;
            }
            tick++;
            repaint();
        });
        timer.start();
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, width * 2, height * 2);


        double radAngle = Math.toRadians(angle);
        if (image != null) {
            int size = Math.min(width, height);
            int xImage = size / 2 - image.getWidth() / 2 - (int) ((size / 2 - image.getWidth() / 2) * Math.sin(-radAngle));
            int yImage = size / 2 - image.getHeight() / 2 - (int) ((size / 2 - image.getHeight() / 2) * Math.cos(-radAngle));
            g.drawImage(image, xImage, yImage, null);
        }
    }
}
