package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class View extends JFrame {
    final static char A = 'A';
    final static int NUM = 26;

    View() {
        DefaultTableModel tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                } else {
                    return super.isCellEditable(row, column);
                }
            }

            public Class getColumnClass(int column) {
                return MyCell.class;
            }
        };

        JTable table = new JTable(tableModel);
        JScrollPane mainPane = new JScrollPane(table);
        mainPane.setVisible(true);
        table.setRowHeight(25);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Vector v = new Vector();
        for (int i = 1; i < NUM; ++i) {
            v.add(i);
        }
        table.getTableHeader().setReorderingAllowed(false);
        tableModel.addColumn("", v);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        for (int i = 0; i < NUM; i++) {
            tableModel.addColumn((char) (A + i));
            table.getColumnModel().getColumn(i).setPreferredWidth(300);
        }
        Controller editor = new Controller();
        table.setDefaultEditor(MyCell.class, editor);

        setContentPane(mainPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1500, 666));
        setVisible(true);
        setResizable(false);
        pack();
    }

    public static void main(String[] args) {
        new View();
    }
}
