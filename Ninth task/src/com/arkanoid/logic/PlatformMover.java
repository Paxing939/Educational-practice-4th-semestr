package com.arkanoid.logic;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class PlatformMover extends KeyAdapter {

    private final Gameplay gameplay;

    PlatformMover(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (!gameplay.isGameStarted()) {
                gameplay.setLeftPressed(true);
                gameplay.setRightPressed(false);
            }
            gameplay.setPlatformX((int) gameplay.getPlatform().getCenter().getX() - gameplay.getPLATFORM_WIDTH() / 2 - gameplay.getBORDER_SIZE());
            if (gameplay.getPlatformX() > 0) {
                gameplay.setPlatformX((int) gameplay.getPlatform().getCenter().getX() - gameplay.getPlatformSpeed());
            } else {
                gameplay.setPlatformX(gameplay.getWidth() - gameplay.getBORDER_SIZE() - gameplay.getPLATFORM_WIDTH() / 2);
            }
            gameplay.setPlatformY((int) gameplay.getPlatform().getCenter().getY());
            gameplay.getPlatform().changePlatformPosition(gameplay.getPlatformX(), gameplay.getPlatformY());
            gameplay.repaint();

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (!gameplay.isGameStarted()) {
                gameplay.setLeftPressed(false);
                gameplay.setRightPressed(true);
            }
            gameplay.setPlatformX((int) gameplay.getPlatform().getCenter().getX() + gameplay.getPLATFORM_WIDTH() / 2 + gameplay.getBORDER_SIZE());
            if (gameplay.getPlatformX() < gameplay.getBounds().width) {
                gameplay.setPlatformX((int) gameplay.getPlatform().getCenter().getX() + gameplay.getPlatformSpeed());
            } else {
                gameplay.setPlatformX(gameplay.getBORDER_SIZE() + gameplay.getPLATFORM_WIDTH() / 2);
            }
            gameplay.setPlatformY((int) gameplay.getPlatform().getCenter().getY());
            gameplay.getPlatform().changePlatformPosition(gameplay.getPlatformX(), gameplay.getPlatformY());
            gameplay.repaint();

        } else if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
                gameplay.setGameStarted(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && !gameplay.isGameStarted()) {
            gameplay.setLeftPressed(false);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !gameplay.isGameStarted()) {
            gameplay.setRightPressed(false);
        }
    }
}
