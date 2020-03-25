package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ClockPictureWindow extends JPanel {
    private JSlider slider;
    private int direction = 1;

    ClockPictureWindow() {
        super();
        setLayout(new BorderLayout());

        String[] items = {"По часовой", "Против часовой"};
        JComboBox comboBox = new JComboBox(items);
        comboBox.addActionListener(e -> {
            String item = comboBox.getSelectedItem().toString();
            if ("По часовой".equals(item)) {
                direction = 1;
            } else {
                direction = -1;
            }
        });

        slider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(1);

        JButton openImage = new JButton("open...");

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(450, 50));
        panel.add(slider);
        panel.add(comboBox);
        panel.add(openImage);

        ClockPicturePanel clockPicturePanel = new ClockPicturePanel(this);
        openImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int ret = fileChooser.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                try {
                    clockPicturePanel.setImage(ImageIO.read(fileChooser.getSelectedFile()));

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        add(clockPicturePanel, BorderLayout.CENTER);;
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
        setPreferredSize(new Dimension(450, 450));
    }

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return slider.getValue();
    }

}
