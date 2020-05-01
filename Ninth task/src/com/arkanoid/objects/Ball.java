package com.arkanoid.objects;

import java.awt.*;

public class Ball {
    private Point center;

    public Ball(Point center) {
        this.center = center;
    }

    public Point getCenter() {
        return center;
    }

    public void changeBallPosition(int xPos, int yPos) {
        center = new Point(xPos, yPos);
    }
}
