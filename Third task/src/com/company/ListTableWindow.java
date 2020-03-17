package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class ListTableWindow extends JFrame {
    public ListTableWindow() throws HeadlessException, FileNotFoundException {
        super("Third task");

        var mainPanel = new JPanel(new BorderLayout());
        var tabbedPane = new JTabbedPane();
        var listPanel = new JPanel(new BorderLayout());
        var capitalPanel = new JPanel(new BorderLayout());
        var tablePanel = new JPanel(new BorderLayout());
        var buttonPanel = new JPanel(new BorderLayout());

        tabbedPane.addTab("List", listPanel);
        tabbedPane.addTab("Table", tablePanel);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        var countries = readCountries();
        var map = new HashMap<String, ImageIcon>();
        for (var country : countries.entrySet()) {
            map.put(country.getKey(), new ImageIcon("flags/" + country.getKey() + ".png"));
        }

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
        listPanel.add(BorderLayout.SOUTH, capitalPanel);
        listPanel.add(BorderLayout.CENTER, list);

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
        tablePanel.add(BorderLayout.NORTH, buttonPanel);
        tablePanel.add(BorderLayout.CENTER, table);
        tablePanel.add(BorderLayout.SOUTH, sumLabel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(500, 600);
        setVisible(true);
    }

    private HashMap<String, String> readCountries() throws FileNotFoundException {
        var result = new HashMap<String, String>();
        var sc = new Scanner(new File("input.txt"));
        String[] slitted;
        while (sc.hasNext()) {
            slitted = sc.nextLine().split(" ");
            result.put(slitted[0], slitted[1]);
        }
        return result;
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
