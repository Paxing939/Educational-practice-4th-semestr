package com.barleybreak;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.barleybreak.BarleyBreak.*;

public class CustomButtonListener implements ActionListener {

    private BarleyBreak parent;

    public CustomButtonListener(BarleyBreak parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(newGameButton)) {
            parent.fillArrays();
            if (!parent.hasSolution(shuffledIndices) && BRICK_COLUMNS == 4 && BRICK_ROWS == 4) {
                JOptionPane.showMessageDialog(parent, "Ha-Ha, Solution Does Not Exist, But You Can Try =)");
            }
        } else if (e.getSource().equals(chooseImageButton)) {
            JFileChooser fileChooser = new JFileChooser();
            int ret = fileChooser.showDialog(null, "Open File");
            if (ret == JFileChooser.APPROVE_OPTION) {
                filepath = fileChooser.getSelectedFile().getPath();
                parent.fillArrays();
                if (!parent.hasSolution(shuffledIndices) && BRICK_COLUMNS == 4 && BRICK_ROWS == 4) {
                    JOptionPane.showMessageDialog(parent, "Ha-Ha, Solution Does Not Exist, But You Can Try =)");
                }
            }
        }
    }
}