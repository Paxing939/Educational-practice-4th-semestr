package com.wordart;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

class MiniWordArtPanel extends JPanel {
    private String text;

    private int shadow;

    private int direction;

    private int color;

    void setText(String text) {
        this.text = text;
    }

    void setShadow(int shadow) {
        this.shadow = shadow;
    }

    void setDirection(int direction) {
        this.direction = direction;
    }

    void setColor(int color) {
        this.color = color;
    }

    MiniWordArtPanel(int shadow, String text, int color, int direction) {
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

        if (str.length() < 20) {
            TextLayout txt = new TextLayout(str,
                    new Font("Bernard MT", Font.BOLD, getHeight() / (str.length() + 1)),
                    graphics.getFontRenderContext());
            AffineTransform transform = new AffineTransform();
            Shape shape;
            for (int i = 0; i < 50 / str.length(); ++i) {
                translateShadow(transform, i);
                shape = txt.getOutline(transform);
                graphics.draw(shape);
                graphics.fill(shape);
            }
            for (int i = 0; i < 75 / str.length(); ++i) {
                translateColor(graphics, i, str);
                translateDirection(transform, i);

                shape = txt.getOutline(transform);
                graphics.draw(shape);
                graphics.fill(shape);
            }
        } else {
            TextLayout txt = new TextLayout(str,
                    new Font("Bernard MT", Font.BOLD, getHeight() / (str.length() + 1)),
                    graphics.getFontRenderContext());
            AffineTransform transform = new AffineTransform();
            Shape shape;
            for (int i = 0; i < 50 / str.length(); ++i) {
                translateShadow(transform, i);
                shape = txt.getOutline(transform);
                graphics.draw(shape);
                graphics.fill(shape);
            }
            for (int i = 0; i < 75 / str.length(); ++i) {
                translateColor(graphics, i, str);
                translateDirection(transform, i);

                shape = txt.getOutline(transform);
                graphics.draw(shape);
                graphics.fill(shape);
            }
        }
    }

    private void translateShadow(AffineTransform transform, int i) {
        if (shadow == 0) {
            transform.setToTranslation(200 - i, getWidth() / 3);
        } else if (shadow == 1) {
            transform.setToTranslation(200, getWidth() / 3 + i);
        } else if (shadow == 2) {
            transform.setToTranslation(200 + i, getWidth() / 3);
        } else if (shadow == 3) {
            transform.setToTranslation(200, getWidth() / 3 - i);
        }
    }

    private void translateColor(Graphics graphics, int i, String str) {
        if (color == 0) {
            graphics.setColor(new Color(0, 50 + i * 3 / 2 * str.length(), 0));
        } else if (color == 1) {
            graphics.setColor(new Color(0, 0, 50 + i * 3 / 2 * str.length()));
        } else if (color == 2) {
            graphics.setColor(new Color(50 + i * 3 / 2 * str.length(), 0, 0));
        }
    }

    private void translateDirection(AffineTransform transform, int i) {
        if (direction == 0) {
            transform.setToTranslation(200 + i, getWidth() / 3 - i);
        } else if (direction == 1) {
            transform.setToTranslation(200 + i, getWidth() / 3 + i);
        } else if (direction == 2) {
            transform.setToTranslation(200 - i, getWidth() / 3 + i);
        } else if (direction == 3) {
            transform.setToTranslation(200 - i, getWidth() / 3 - i);
        }
    }
}