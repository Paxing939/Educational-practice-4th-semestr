package com.arkanoid;

import com.arkanoid.logic.Application;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Application application;
        try {
            application = new Application();
            application.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
