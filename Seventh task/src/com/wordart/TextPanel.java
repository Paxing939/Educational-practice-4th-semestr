package com.wordart;

import com.barleybreak.BarleyBreak;

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

        color = Color.RED;

        text = new JTextField();
        text.setColumns(30);
        shadowButton = new JButton("Change shadow");
        directionButton = new JButton("Change direction");
        colorButton = new JButton("Change color");

        JPanel operationPanel = new JPanel(new BorderLayout());
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.add(text, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(shadowButton);
        buttonPanel.add(directionButton);
        buttonPanel.add(colorButton);
        operationPanel.add(textPanel, BorderLayout.NORTH);
        operationPanel.add(buttonPanel, BorderLayout.SOUTH);

        initListeners();

        JPanel northPanel = new JPanel();
        wordArtPanel = new MiniWordArtPanel(shadow, text.getText().trim(), color, direction);

        add(northPanel, BorderLayout.NORTH);
        add(wordArtPanel, BorderLayout.CENTER);
        add(operationPanel, BorderLayout.SOUTH);
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