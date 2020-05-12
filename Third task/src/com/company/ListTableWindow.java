package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ListTableWindow extends JFrame {
    public ListTableWindow() throws HeadlessException, FileNotFoundException {
        super("Third task");

        var countries = readCountries();
        var map = new HashMap<String, ImageIcon>();
        for (var country : countries.entrySet()) {
            map.put(country.getKey(), new ImageIcon("flags/" + country.getKey() + ".png"));
        }

        var mainPanel = new JPanel(new BorderLayout());
        var tabbedPane = new JTabbedPane();
        var listPanel = new ListPanel(new BorderLayout(), map, countries);
        var tablePanel = new TablePanel(new BorderLayout(), map, countries);

        tabbedPane.addTab("List", listPanel);
        tabbedPane.addTab("Table", tablePanel);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

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
}
