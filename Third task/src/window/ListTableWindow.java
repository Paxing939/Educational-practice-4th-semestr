package window;

import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

class ListTableWindow extends JFrame {

    private DefaultListModel<String> organisations = new DefaultListModel<>();
    private Map<String, ImageIcon> map;

    ListTableWindow() throws HeadlessException {
        super("Third task");

        JPanel mainPanel = new JPanel(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel listPanel = new JPanel(new BorderLayout());
        JPanel tablePanel = new JPanel(new BorderLayout());

        tabbedPane.addTab("List", listPanel);
        tabbedPane.addTab("Table", tablePanel);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        search.addActionListener(actionEvent -> {
            if (workers.get(0) instanceof Seller) {
                var max = Collections.max(workers);
                var min = Collections.min(workers);
                double averageSalary = (max.getSalary() + min.getSalary()) / 2;
                for (var el : workers) {
                    if (Math.abs(el.getSalary() - averageSalary) < 2) {
                        JOptionPane.showMessageDialog(this, "Работник со средней зарплатой между max и min:\n" +
                                el.toString());
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "There no any sellers!");
            }
        });
        showMap.addActionListener(actionEvent -> {

            if (workers.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Open .xml file first!");
            } else {

                JDialog dialog = new JDialog();
                dialog.setName("Enter name, please");
                dialog.setSize(100, 150);
                dialog.setLayout(new GridLayout());

                JLabel label = new JLabel("Название организации:");
                JTextField field = new JTextField();
                label.setLabelFor(field);

                dialog.add(label, BorderLayout.CENTER);
                dialog.add(field, BorderLayout.CENTER);

                JButton button = new JButton("Введите");
                button.addActionListener(event2 -> {
                    dialog.dispose();
                    var map = makeMapTea(workers);
                    JOptionPane.showMessageDialog(this,
                            map.get(field.getText()));
                });
                dialog.add(button);
                dialog.pack();
                dialog.toFront();
                dialog.setVisible(true);
            }
        });
        data.add(byPriceName);
        data.add(byAlphabet);
        data.add(search);
        data.add(showMap);
        file.add(open);
        menu.add(file);
        menu.add(data);
        mainPanel.add(menu, BorderLayout.NORTH);

        simpleListPanel.add(new JList<>(byOrganisationList), BorderLayout.CENTER);

        sortedListPanel.add(new JList<>(lazyList), BorderLayout.CENTER);

        alphabetListPanel.add(new JList<>(organisations), BorderLayout.CENTER);

        mapListPanel.add(new JList<>(mapList), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(600, 400);
        setVisible(true);
    }

}
