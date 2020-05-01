package com.arkanoid.logic;


import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

class MotionListener extends MouseMotionAdapter {

    private final Gameplay gameplay;

    public MotionListener(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = gameplay.getPlatform().getCenter().y;
        if (x < gameplay.getBORDER_SIZE() + gameplay.getPLATFORM_WIDTH() / 2) {
            x = gameplay.getBORDER_SIZE() + gameplay.getPLATFORM_WIDTH() / 2;
        }
        if (x > gameplay.getWidth() - gameplay.getBORDER_SIZE() - gameplay.getPLATFORM_WIDTH() / 2) {
            x = gameplay.getWidth() - gameplay.getBORDER_SIZE() - gameplay.getPLATFORM_WIDTH() / 2;
        }
        gameplay.getPlatform().changePlatformPosition(x, y);
    }
}