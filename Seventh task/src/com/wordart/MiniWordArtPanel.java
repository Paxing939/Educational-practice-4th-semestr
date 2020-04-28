package com.wordart;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

class MiniWordArtPanel extends JPanel {
    private String text;

    private int shadow;

    private int direction;

    private Color color;

    void setText(String text) {
        this.text = text;
    }

    void setShadow(int shadow) {
        this.shadow = shadow;
    }

    void setDirection(int direction) {
        this.direction = direction;
    }

    void setColor(Color color) {
        this.color = color;
    }

    MiniWordArtPanel(int shadow, String text, Color color, int direction) {
        super();
        this.shadow = shadow;
        this.text = text;
        this.color = color;
        this.direction = direction;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        String str = text + " ";
        int symbolsPerString = 7;
        int numberStrings = str.length() / symbolsPerString + 1;
        int startX = -180, startY = -135;

        for (int i = 0; i < numberStrings; ++i) {

            int endSymbol = (i + 1) * symbolsPerString;
            if (endSymbol > str.length()) {
                endSymbol = str.length();
            }
            String tmp = str.substring(i * symbolsPerString, endSymbol);
            if (tmp.isEmpty()) {
                tmp = " ";
            }
            var txt = new TextLayout(tmp, new Font("Bernard MT", Font.BOLD, 150), graphics.getFontRenderContext());
            graphics.translate(startX, startY);

            var transform = new AffineTransform();
            for (int j = 0; j < 50 / 6; ++j) {
                translateShadow(transform, j);
                Shape shape = txt.getOutline(transform);
                graphics.draw(shape);
                graphics.fill(shape);
            }
            for (int j = 0; j < 75 / 6; ++j) {
                translateColor(graphics, j);
                translateDirection(transform, j);

                Shape shape = txt.getOutline(transform);
                graphics.draw(shape);
                graphics.fill(shape);
            }

            startY = 150;
            startX = 0;
        }
    }

    private void translateShadow(AffineTransform transform, int i) {
        switch (shadow) {
            case 0: {
                transform.setToTranslation(200 - i, getWidth() / 3);
                break;
            }
            case 1: {
                transform.setToTranslation(200, getWidth() / 3 + i);
                break;
            }
            case 2: {
                transform.setToTranslation(200 + i, getWidth() / 3);
                break;
            }
            case 3: {
                transform.setToTranslation(200, getWidth() / 3 - i);
                break;
            }
        }
    }

    private void translateColor(Graphics graphics, int i) {
        graphics.setColor(new Color(color.getRed() / 2 + 4 * i, color.getGreen() / 2 + 4 * i, color.getBlue() / 2 + 4 * i));
    }

    private void translateDirection(AffineTransform transform, int i) {
        switch (direction) {
            case 0: {
                transform.setToTranslation(200 + i, getWidth() / 3 - i);
                break;
            }
            case 1: {
                transform.setToTranslation(200 + i, getWidth() / 3 + i);
                break;
            }
            case 2: {
                transform.setToTranslation(200 - i, getWidth() / 3 + i);
                break;
            }
            case 3: {
                transform.setToTranslation(200 - i, getWidth() / 3 - i);
                break;
            }
        }
    }

}