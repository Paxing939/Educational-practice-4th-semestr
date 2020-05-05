package com.arkanoid.objects;

import java.awt.*;

public class Block {
    private final Color color;
    private final Point center;
    private boolean isEnabled;

    public Block(Color color, Point center, boolean isEnabled) {
        this.color = color;
        this.center = center;
        this.isEnabled = isEnabled;
    }

    public Color getColor() {
        return color;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isBlockEnabled() {
        return isEnabled;
    }

    public Point getCenter() {
        return center;
    }
}
