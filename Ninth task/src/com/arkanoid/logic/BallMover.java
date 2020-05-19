package com.arkanoid.logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BallMover implements ActionListener {

    private final Gameplay gameplay;

    BallMover(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameplay.isGameStarted()) {
            gameplay.setBallX((int) gameplay.getBall().getCenter().getX() - gameplay.getBALL_RADIUS());
            gameplay.setBallY((int) gameplay.getBall().getCenter().getY() - gameplay.getBALL_RADIUS());

            Rectangle ballRect = new Rectangle(gameplay.getBallX(), gameplay.getBallY(), gameplay.getBALL_RADIUS() * 2, gameplay.getBALL_RADIUS() * 2);

            if (new Rectangle(0, 0, gameplay.getBORDER_SIZE(), gameplay.getRanges().height).intersects(ballRect)) {
                gameplay.setDeltaX(-gameplay.getDeltaX());
                gameplay.getSoundPlayer().playSound(SoundPlayer.WALL_HIT_SOUND);
            } else if (new Rectangle(0, 0, gameplay.getRanges().width, gameplay.getBORDER_SIZE()).intersects(ballRect)) {
                gameplay.setDeltaY(-gameplay.getDeltaY());
                gameplay.getSoundPlayer().playSound(SoundPlayer.WALL_HIT_SOUND);
            } else if (new Rectangle(gameplay.getRanges().width - gameplay.getBORDER_SIZE(), 0, gameplay.getBORDER_SIZE(), gameplay.getRanges().height).intersects(ballRect)) {
                gameplay.setDeltaX(-gameplay.getDeltaX());
                gameplay.getSoundPlayer().playSound(SoundPlayer.WALL_HIT_SOUND);
            } else if (new Rectangle(0, gameplay.getBounds().height - gameplay.getBORDER_SIZE(), gameplay.getRanges().width, gameplay.getBORDER_SIZE()).intersects(ballRect)) {
                gameplay.setGameStarted(false);
                gameplay.setLeftPressed(false);
                gameplay.setRightPressed(false);
                gameplay.setDeltaY(gameplay.getDELTA_Y());
                gameplay.setDeltaX(gameplay.getDELTA_X());
                if (gameplay.getHitPoints() != 0) {
                    gameplay.setHitPoints(gameplay.getHitPoints() - 1);
                    gameplay.getHitPointImages().set(gameplay.getHitPoints(), gameplay.getHitPointEmptyImage());
                    gameplay.getSoundPlayer().playSound(SoundPlayer.MINUS_LIFE_SOUND);
                }

                if (gameplay.getHitPoints() == 0) {
                    gameplay.getSoundPlayer().playSound(SoundPlayer.LOSE_SOUND);
                    JOptionPane.showMessageDialog(null, "GAME OVER!");
                    Gameplay.setGameOver(true);
                    gameplay.setVisible(false);
                    gameplay.getApplication().dispose();
                    System.exit(0);
                }
            } else if (new Rectangle((int) gameplay.getPlatform().getCenter().getX() - gameplay.getPLATFORM_WIDTH() / 2,
                    (int) gameplay.getPlatform().getCenter().getY() - gameplay.getPLATFORM_HEIGHT() / 2,
                    gameplay.getPLATFORM_WIDTH(), gameplay.getPLATFORM_HEIGHT()).intersects(ballRect) && gameplay.getDeltaY() < 0) {
                gameplay.setDeltaY(-gameplay.getDeltaY());
                gameplay.getSoundPlayer().playSound(SoundPlayer.PLATFORM_HIT_SOUND);
            } else {
                for (int i = 0; i < gameplay.getROWS() * gameplay.getCOLUMNS(); i++) {
                    int x = (int) gameplay.getBlockList().get(i).getCenter().getX() - gameplay.getBlockWidth() / 2;
                    int y = (int) gameplay.getBlockList().get(i).getCenter().getY() - gameplay.getBlockHeight() / 2;
                    Rectangle rectangle = new Rectangle(x, y, gameplay.getBlockWidth(), gameplay.getBlockHeight());
                    if (rectangle.intersects(ballRect) && gameplay.getBlockList().get(i).isBlockEnabled()) {
                        gameplay.setDeltaY(-gameplay.getDeltaY());
                        gameplay.getBlockList().get(i).setEnabled(false);
                        gameplay.getSoundPlayer().playSound(SoundPlayer.BRICK_HIT_SOUND);
                        gameplay.setActiveBlocks(gameplay.getActiveBlocks() - 1);
                        if (gameplay.getActiveBlocks() == 0) {
                            gameplay.getSoundPlayer().playSound(SoundPlayer.WIN_SOUND);
                            JOptionPane.showMessageDialog(null, "Here is the Winner!!!");
                            Gameplay.setGameOver(true);
                            gameplay.setVisible(false);
                            gameplay.getApplication().dispose();
                            System.exit(0);
                        }
                    }
                }
            }
        }

        if (gameplay.isLeftPressed()) {
            gameplay.setBallX((int) gameplay.getBall().getCenter().getX() - gameplay.getDeltaX());
            gameplay.setBallY((int) gameplay.getBall().getCenter().getY() - gameplay.getDeltaY());
            gameplay.getBall().changeBallPosition(gameplay.getBallX(), gameplay.getBallY());
        } else if (gameplay.isRightPressed()) {
            gameplay.setBallX((int) gameplay.getBall().getCenter().getX() + gameplay.getDeltaX());
            gameplay.setBallY((int) gameplay.getBall().getCenter().getY() - gameplay.getDeltaY());
            gameplay.getBall().changeBallPosition(gameplay.getBallX(), gameplay.getBallY());
        } else if (gameplay.isGameStarted()) {
            gameplay.setBallX((int) gameplay.getBall().getCenter().getX() + gameplay.getDeltaX());
            gameplay.setBallY((int) gameplay.getBall().getCenter().getY() - gameplay.getDeltaY());
            gameplay.getBall().changeBallPosition(gameplay.getBallX(), gameplay.getBallY());
        }
        gameplay.repaint();
    }
}
