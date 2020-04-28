package com.wordart;

import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel {

    private static final int NUM_SHADOW = 4;
    private static final int NUM_DIRECTION = 4;

    private final MiniWordArtPanel wordArtPanel;
    private final JTextField text;
    private final JButton shadowButton;
    private final JButton directionButton;
    private final JButton colorButton;

    private int shadow = 0;
    private int direction = 0;
    private Color color;

    public TextPanel() {
        super(new BorderLayout());

        color = Color.GREEN;

        text = new JTextField();
        shadowButton = new JButton("Change shadow");
        directionButton = new JButton("Change direction");
        colorButton = new JButton("Change color");

        JPanel panel = new JPanel(new GridLayout(2, 3));
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(text);
        panel.add(shadowButton);
        panel.add(directionButton);
        panel.add(colorButton);

        initListeners();

        JPanel northPanel = new JPanel();
        wordArtPanel = new MiniWordArtPanel(shadow, text.getText().trim(), color, direction);

        add(northPanel, BorderLayout.NORTH);
        add(wordArtPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

    }

    private void initListeners() {
        text.addCaretListener(e -> {
            wordArtPanel.setText(text.getText().trim());
            repaint();
        });
        colorButton.addActionListener(e -> {
            color = JColorChooser.showDialog(null, "Choose a color", Color.RED);
            wordArtPanel.setColor(color);
            repaint();
        });
        directionButton.addActionListener(e -> {
            direction = (direction + 1) % NUM_DIRECTION;
            wordArtPanel.setDirection(direction);
            repaint();
        });
        shadowButton.addActionListener(e -> {
            shadow = (shadow + 1) % NUM_SHADOW;
            wordArtPanel.setShadow(shadow);
            repaint();
        });
    }

}