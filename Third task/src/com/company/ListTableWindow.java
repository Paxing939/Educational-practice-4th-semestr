package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;

public class ListTableWindow extends JFrame {

    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private HashMap<String, ImageIcon> map = new HashMap<>();

    public ListTableWindow() throws HeadlessException, FileNotFoundException {
        super("Third task");

        JPanel mainPanel = new JPanel(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel listPanel = new JPanel(new BorderLayout());
        JPanel tablePanel = new JPanel(new BorderLayout());

        tabbedPane.addTab("List", listPanel);
        tabbedPane.addTab("Table", tablePanel);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        HashMap<String, String> countries = getCountriesFromFile();
        for (var country : countries.entrySet()) {
            String str = country.getKey() + ".png";
            System.out.println(str);
            map.put(country.getKey(), new ImageIcon(getClass().getResource(country.getKey() + ".png")));
        }

        JList<String> list = new JList<>();
        list.setModel(listModel);
        listModel.addAll(map.keySet());
        list.setCellRenderer(new CountryListRenderer(map));
        list.setFixedCellHeight(50);
        JLabel label = new JLabel("Capital");
        listPanel.add(BorderLayout.SOUTH, label);
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                label.setText(countries.get(list.getSelectedValue()));
            }
        });
        listPanel.add(BorderLayout.CENTER, list);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(500, 600);
        setVisible(true);
    }

    private HashMap<String, String> getCountriesFromFile() {
        var result =  new HashMap<String, String>();
        result.put("Russia", "Moscow");
        result.put("Belarus", "Minsk");
        result.put("USA", "Washington");
        result.put("Australia", "Canberra");
        result.put("Belgium", "Brussels");
        result.put("Canada", "Ottawa");
        result.put("China", "Beijing");
        result.put("Egypt", "Cairo");
        result.put("United-Kingdom", "London");
        result.put("Brazil", "Brasília");
        return result;
    }

}
