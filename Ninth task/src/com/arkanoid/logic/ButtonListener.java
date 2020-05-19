package com.arkanoid.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonListener implements ActionListener {

    private final Application application;

    ButtonListener(Application application) {
        this.application = application;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(application.getDifficultyMenuItem())) {
            application.getDifficultyDialog().setVisible(true);
        } else if (e.getSource().equals(application.getAnotherDifficultyMenuItem())) {
            application.getAnotherDifficultyDialog().setVisible(true);
        } else {
            int level = 0;
            application.getSoundPlayer().playSound(SoundPlayer.NAVIGATION_SOUND);
            if (e.getSource().equals(application.getAmateur())) {
                level = 0;
            } else if (e.getSource().equals(application.getMiddle())) {
                level = 1;
            } else if (e.getSource().equals(application.getProfessional())) {
                level = 2;
            } else if (e.getSource().equals(application.getPsycho())) {
                level = 3;
            }
            application.getGameplay().setLevel(level);
            application.getDifficultyDialog().setVisible(false);

            int difficulty = 1;
            application.getSoundPlayer().playSound(SoundPlayer.NAVIGATION_SOUND);
            if (e.getSource().equals(application.getSmall())) {
                difficulty = 0;
            } else if (e.getSource().equals(application.getAverage())) {
                difficulty = 1;
            } else if (e.getSource().equals(application.getBig())) {
                difficulty = 2;
            }
            application.getGameplay().setDifficulty(difficulty);
            application.getAnotherDifficultyDialog().setVisible(false);
        }


    }
}
