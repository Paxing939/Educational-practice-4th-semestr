package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class TablePanel extends JPanel {

    public TablePanel(LayoutManager layout, HashMap<String, ImageIcon> map, HashMap<String, String> countries) throws FileNotFoundException {
        super(layout);

        var buttonPanel = new JPanel(new BorderLayout());

        var v = new Vector<>(map.values());
        var tableModel = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        tableModel.setColumnIdentifiers(new Object[]{"Flag", "Description", "Price", "Checked"});
        var descriptions = readDescriptions();
        for (int i = 0; i < map.size(); i++) {
            tableModel.addRow(new Object[]{v.get(i), descriptions.get(i), (i + 1) * 100, false});
        }

        var table = new JTable(tableModel) {
            private static final long serialVersionUID = 1L;

            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return ImageIcon.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Integer.class;
                    case 3:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        table.setRowHeight(45);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        table.getColumnModel().getColumn(1).setMinWidth(120);
        table.getColumnModel().getColumn(2).setMaxWidth(70);
        table.getColumnModel().getColumn(3).setMaxWidth(40);

        var sumButton = new JButton("Final sum");
        var sumLabel = new JLabel("Sum is: ----");
        sumButton.addActionListener(e -> {
            int sum = 0;
            for (var el : tableModel.getDataVector()) {
                if (el.elementAt(3).equals(true)) {
                    sum += Integer.parseInt(el.elementAt(2).toString());
                }
            }
            sumLabel.setText("Sum is: " + sum);
        });

        var addButton = new JButton("Add tour");
        addButton.addActionListener(e -> {
            String iconPath;
            ArrayList<String> description = new ArrayList<>();
            description.add("");
            ArrayList<Integer> price = new ArrayList<>();
            price.add(0);
            JFileChooser jFileChooser = new JFileChooser();
            int ret = jFileChooser.showDialog(null, "Выберите картинку");
            if (ret == JFileChooser.APPROVE_OPTION) {
                iconPath = jFileChooser.getSelectedFile().getPath();
            } else {
                return;
            }

            JDialog dialog = new JDialog();
            dialog.setLayout(new BorderLayout());

            var dialogPanel = new JPanel(new BorderLayout());
            var descriptionPanel = new JPanel(new BorderLayout());
            var pricePanel = new JPanel(new BorderLayout());

            var labelDescription = new JLabel("Введите описание тура:");
            var fieldDescription = new JTextField();
            labelDescription.setLabelFor(fieldDescription);
            descriptionPanel.add(labelDescription, BorderLayout.WEST);
            descriptionPanel.add(fieldDescription, BorderLayout.CENTER);

            var labelPrice = new JLabel("Введите описание тура:");
            var fieldPrice = new JTextField();
            labelPrice.setLabelFor(fieldDescription);
            pricePanel.add(labelPrice, BorderLayout.WEST);
            pricePanel.add(fieldPrice, BorderLayout.CENTER);

            var okButton = new JButton("OK");
            okButton.addActionListener(event3 -> {
                tableModel.addRow(new Object[]{new ImageIcon(iconPath), fieldDescription.getText(),
                        Integer.parseInt(fieldPrice.getText()), false});
                dialog.dispose();
            });

            dialogPanel.add(descriptionPanel, BorderLayout.NORTH);
            pricePanel.add(okButton, BorderLayout.SOUTH);
            dialogPanel.add(pricePanel, BorderLayout.SOUTH);

            dialogPanel.setPreferredSize(new Dimension(250, 70));
            dialog.setContentPane(dialogPanel);
            dialog.setResizable(false);
            dialog.pack();
            dialog.toFront();
            dialog.setVisible(true);
        });

        buttonPanel.add(BorderLayout.WEST, sumButton);
        buttonPanel.add(BorderLayout.EAST, addButton);
        add(BorderLayout.NORTH, buttonPanel);
        add(BorderLayout.CENTER, table);
        add(BorderLayout.SOUTH, sumLabel);
    }

    private ArrayList<String> readDescriptions() throws FileNotFoundException {
        var result = new ArrayList<String>();
        var sc = new Scanner(new File("descriptions.txt"));
        while (sc.hasNext()) {
            result.add(sc.nextLine());
        }
        return result;
    }
}
