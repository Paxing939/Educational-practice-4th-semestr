package com.barleybreak;

import com.specks.GraphicalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomMouseListener extends MouseAdapter {

    private boolean isOutOfRange(int i, int j) {
        return i >= 0 && j >= 0 && i < BarleyBreak.getBrickRows() && j < BarleyBreak.getBrickColumns();
    }

    private Point whereToMove(int i, int j) {
        Point point = null;

        int indexRow = i - 1;
        int indexColumn = j;
        if (isOutOfRange(indexRow, indexColumn)) {
            if (BarleyBreak.getPanels()[indexRow][indexColumn].getImage() == null) {
                point = new Point(indexRow, indexColumn);
            }
        }
        indexRow = i;
        indexColumn = j + 1;
        if (isOutOfRange(indexRow, indexColumn)) {
            if (BarleyBreak.getPanels()[indexRow][indexColumn].getImage() == null) {
                point = new Point(indexRow, indexColumn);
            }
        }
        indexRow = i + 1;
        indexColumn = j;
        if (isOutOfRange(indexRow, indexColumn)) {
            if (BarleyBreak.getPanels()[indexRow][indexColumn].getImage() == null) {
                point = new Point(indexRow, indexColumn);
            }
        }
        indexRow = i;
        indexColumn = j - 1;
        if (isOutOfRange(indexRow, indexColumn)) {
            if (BarleyBreak.getPanels()[indexRow][indexColumn].getImage() == null) {
                point = new Point(indexRow, indexColumn);
            }
        }
        return point;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GraphicalPanel panel = (GraphicalPanel) e.getSource();
        int row = 0;
        int column = 0;
        boolean found = false;
        for (int i = 0; i < BarleyBreak.getBrickRows(); i++) {
            for (int j = 0; j < BarleyBreak.getBrickColumns(); j++) {
                if (panel.equals(BarleyBreak.getPanels()[i][j])) {
                    row = i;
                    column = j;
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            for (int i = 0; i < BarleyBreak.getBrickRows(); i++) {
                for (int j = 0; j < BarleyBreak.getBrickColumns(); j++) {
                    System.out.println(BarleyBreak.getIndices()[i][j]);
                }
            }
            Point point = whereToMove(row, column);
            if (point != null) {
                BarleyBreak.getPanels()[point.x][point.y].setImage(BarleyBreak.getPanels()[row][column].getImage());
                BarleyBreak.getPanels()[row][column].setImage(null);
                BarleyBreak.getPanels()[row][column].repaint();
                BarleyBreak.getPanels()[point.x][point.y].repaint();

                int temp;
                temp = BarleyBreak.getIndices()[point.x][point.y];
                BarleyBreak.getIndices()[point.x][point.y] = BarleyBreak.getIndices()[row][column];
                BarleyBreak.getIndices()[row][column] = temp;

                int k = 0;
                boolean isOver = true;

                for (int i = 0; i < BarleyBreak.getBrickRows(); i++) {
                    for (int j = 0; j < BarleyBreak.getBrickColumns(); j++) {
                        if (BarleyBreak.getIndices()[i][j] != k) {
                            isOver = false;
                            break;
                        }
                        k++;
                    }
                }
                if (isOver) {
                    JOptionPane.showMessageDialog(null, "Congratulations, you are the WINNER!!!");
                }
            }
        }
    }
}