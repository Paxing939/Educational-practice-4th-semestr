package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ListTableWindow extends JFrame {

    public ListTableWindow() throws HeadlessException, FileNotFoundException {
        super("Third task");

        JPanel mainPanel = new JPanel(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel listPanel = new JPanel(new BorderLayout());
        JPanel capitalPanel = new JPanel(new BorderLayout());
        JPanel tablePanel = new JPanel(new BorderLayout());

        tabbedPane.addTab("List", listPanel);
        tabbedPane.addTab("Table", tablePanel);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        HashMap<String, String> countries = getCountriesFromFile();
        HashMap<String, ImageIcon> map = new HashMap<>();
        for (var country : countries.entrySet()) {
            map.put(country.getKey(), new ImageIcon("flags/" + country.getKey() + ".png"));
        }

        JList<String> list = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        list.setModel(listModel);
        listModel.addAll(map.keySet());
        list.setCellRenderer(new ListRenderer(map));
        list.setFixedCellHeight(50);
        JLabel label = new JLabel("Capital");
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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(500, 600);
        setVisible(true);
    }

    private HashMap<String, String> getCountriesFromFile() throws FileNotFoundException {
        var result = new HashMap<String, String>();
        Scanner sc = new Scanner(new File("input.txt"));
        String[] slitted;
        while (sc.hasNext()) {
            slitted = sc.nextLine().split(" ");
            result.put(slitted[0], slitted[1]);
        }
        return result;
    }

}
