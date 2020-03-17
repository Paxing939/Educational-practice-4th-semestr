package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

class ListRenderer extends DefaultListCellRenderer {

    private HashMap<String, ImageIcon> imageMap;

    ListRenderer(HashMap<String, ImageIcon> imageMap) {
        this.imageMap = imageMap;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        label.setIcon(imageMap.get((String) value));
        label.setHorizontalTextPosition(JLabel.RIGHT);
        return label;
    }
}