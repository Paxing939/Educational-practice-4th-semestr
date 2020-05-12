package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ListPanel extends JPanel {
    public ListPanel(LayoutManager layout, HashMap<String, ImageIcon> map, HashMap<String, String> countries) {
        super(layout);

        var capitalPanel = new JPanel(new BorderLayout());


        var list = new JList<String>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        list.setModel(listModel);
        listModel.addAll(map.keySet());
        list.setCellRenderer(new ListRenderer(map));
        list.setFixedCellHeight(50);
        var label = new JLabel("Capital");
        capitalPanel.add(label);
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                label.setText(list.getSelectedValue() + " - " + countries.get(list.getSelectedValue()));
                label.setIcon(new ImageIcon("flags/" + list.getSelectedValue() + "_16.png"));
                label.setHorizontalTextPosition(JLabel.RIGHT);
            }
        });
        add(BorderLayout.SOUTH, capitalPanel);
        add(BorderLayout.CENTER, list);
    }
}
