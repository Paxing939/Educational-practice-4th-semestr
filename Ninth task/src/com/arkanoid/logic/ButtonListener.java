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
        if (e.getSource().equals(application.getMenuItem())) {
            application.getDifficultyDialog().setVisible(true);
        } else {
            int level = 0;
//                soundPlayer.playSound(SoundPlayer.NAVIGATION_SOUND);
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

        }


    }
}
