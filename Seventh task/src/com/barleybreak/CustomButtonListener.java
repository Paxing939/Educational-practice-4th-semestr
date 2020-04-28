package com.barleybreak;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomButtonListener implements ActionListener {

    private final BarleyBreak parent;

    public CustomButtonListener(BarleyBreak parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(BarleyBreak.getNewGameButton())) {
            parent.initializeMatrices();
            if (parent.hasNoSolution(BarleyBreak.getShuffledIndices()) && BarleyBreak.getBrickColumns() == 4 && BarleyBreak.getBrickRows() == 4) {
                JOptionPane.showMessageDialog(parent, "Ha-Ha, Solution Does Not Exist, But You Can Try =)");
            }
        } else if (e.getSource().equals(BarleyBreak.getChooseImageButton())) {
            JFileChooser fileChooser = new JFileChooser();
            int ret = fileChooser.showDialog(null, "Open File");
            if (ret == JFileChooser.APPROVE_OPTION) {
                BarleyBreak.setFilepath(fileChooser.getSelectedFile().getPath());
                parent.initializeMatrices();
                if (parent.hasNoSolution(BarleyBreak.getShuffledIndices()) && BarleyBreak.getBrickColumns() == 4 && BarleyBreak.getBrickRows() == 4) {
                    JOptionPane.showMessageDialog(parent, "Ha-Ha, Solution Does Not Exist, But You Can Try =)");
                }
            }
        }
    }
}