package com.barleybreak;

import com.specks.GraphicalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.barleybreak.BarleyBreak.panels;
import static com.barleybreak.BarleyBreak.indices;

public class CustomMouseListener extends MouseAdapter {

    private boolean isOutOfRange(int i, int j) {
        return i >= 0 && j >= 0 && i < BarleyBreak.BRICK_ROWS && j < BarleyBreak.BRICK_COLUMNS;
    }

    private Point whereToMove(int i, int j) {
        Point point = null;

        int indexRow = i - 1;
        int indexColumn = j;
        if (isOutOfRange(indexRow, indexColumn)) {
            if (panels[indexRow][indexColumn].getImage() == null) {
                point = new Point(indexRow, indexColumn);
            }
        }
        indexRow = i;
        indexColumn = j + 1;
        if (isOutOfRange(indexRow, indexColumn)) {
            if (panels[indexRow][indexColumn].getImage() == null) {
                point = new Point(indexRow, indexColumn);
            }
        }
        indexRow = i + 1;
        indexColumn = j;
        if (isOutOfRange(indexRow, indexColumn)) {
            if (panels[indexRow][indexColumn].getImage() == null) {
                point = new Point(indexRow, indexColumn);
            }
        }
        indexRow = i;
        indexColumn = j - 1;
        if (isOutOfRange(indexRow, indexColumn)) {
            if (panels[indexRow][indexColumn].getImage() == null) {
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
        for (int i = 0; i < BarleyBreak.BRICK_ROWS; i++) {
            for (int j = 0; j < BarleyBreak.BRICK_COLUMNS; j++) {
                if (panel.equals(panels[i][j])) {
                    row = i;
                    column = j;
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            for (int i = 0; i < BarleyBreak.BRICK_ROWS; i++) {
                for (int j = 0; j < BarleyBreak.BRICK_COLUMNS; j++) {
                    System.out.println(indices[i][j]);
                }
            }
            Point point = whereToMove(row, column);
            if (point != null) {
                panels[point.x][point.y].setImage(panels[row][column].getImage());
                panels[row][column].setImage(null);
                panels[row][column].repaint();
                panels[point.x][point.y].repaint();

                int temp;
                temp = indices[point.x][point.y];
                indices[point.x][point.y] = indices[row][column];
                indices[row][column] = temp;

                int k = 0;
                boolean isOver = true;

                for (int i = 0; i < BarleyBreak.BRICK_ROWS; i++) {
                    for (int j = 0; j < BarleyBreak.BRICK_COLUMNS; j++) {
                        if (indices[i][j] != k) {
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