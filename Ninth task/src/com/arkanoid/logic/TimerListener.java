package com.arkanoid.logic;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TimerListener implements ActionListener {

    private final Application application;

    public TimerListener(Application application) {
        this.application = application;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Gameplay.isGameOver()) {
            application.setVisible(false);
        }
    }
}