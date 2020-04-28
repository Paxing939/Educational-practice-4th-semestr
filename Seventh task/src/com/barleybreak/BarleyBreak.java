package com.barleybreak;

import com.Application;
import com.specks.GraphicalPanel;
import com.specks.ImageResize;
import com.specks.Shuffler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BarleyBreak extends JPanel {

    private JPanel gamePanel;
    private GraphicalPanel fullImagePanel;

    private static String filepath = "src/lena.jpg";

    private static JButton newGameButton;
    private static JButton chooseImageButton;
    private static final int BRICK_ROWS = 3;
    private static final int BRICK_COLUMNS = 3;

    private static GraphicalPanel[][] panels;
    private BufferedImage[][] images;
    private static int[] shuffledIndices;
    private static int[][] indices;

    public static void setFilepath(String filepath) {
        BarleyBreak.filepath = filepath;
    }

    static JButton getNewGameButton() {
        return newGameButton;
    }

    static JButton getChooseImageButton() {
        return chooseImageButton;
    }

    static int getBrickRows() {
        return BRICK_ROWS;
    }

    static int getBrickColumns() {
        return BRICK_COLUMNS;
    }

    static GraphicalPanel[][] getPanels() {
        return panels;
    }

    static int[] getShuffledIndices() {
        return shuffledIndices;
    }

    static int[][] getIndices() {
        return indices;
    }

    boolean hasNoSolution(int[] array) {
        int inversions = 0;
        int emptyIndex = BRICK_COLUMNS * BRICK_ROWS - 1;
        for (int i = 1; i < BRICK_ROWS * BRICK_COLUMNS; i++) {
            if (array[i - 1] > array[i]) {
                inversions++;
            }
        }
        return (inversions + emptyIndex) % 2 != 0;
    }

    public void initializeMatrices() {
        gamePanel.removeAll();
        gamePanel.repaint();

        try {
            BufferedImage originalImage = ImageIO.read(new File(filepath));

            int imageHeight = 200, imageWidth = 200;
            fullImagePanel.setPreferredSize(new Dimension(imageWidth, imageHeight));
            gamePanel.setPreferredSize(new Dimension(Application.getWIDTH(), Application.getHEIGHT() - imageHeight));
            BufferedImage fullImage = ImageResize.resize(originalImage, imageWidth, imageHeight);
            BufferedImage breakingImage = ImageResize.resize(originalImage, Application.getWIDTH(), Application.getHEIGHT() - imageHeight);
            fullImagePanel.setImage(fullImage);
            fullImagePanel.repaint();

            for (int i = 0; i < BRICK_ROWS * BRICK_COLUMNS; i++) {
                shuffledIndices[i] = i;
            }
            Shuffler.shuffle(shuffledIndices);
            int rowSize = breakingImage.getWidth() / BRICK_ROWS;
            int columnSize = breakingImage.getHeight() / BRICK_COLUMNS;
            int k = 0;
            for (int i = 0; i < BRICK_ROWS; i++) {
                for (int j = 0; j < BRICK_COLUMNS; j++) {
                    images[i][j] = breakingImage.getSubimage(i * rowSize, j * columnSize, rowSize, columnSize);
                    indices[i][j] = shuffledIndices[k];
                    k++;
                }
            }
            for (int i = 0; i < BRICK_ROWS; i++) {
                for (int j = 0; j < BRICK_COLUMNS; j++) {
                    int indexOne = (indices[i][j]) % BRICK_COLUMNS;
                    int indexTwo = (indices[i][j]) / BRICK_COLUMNS;
                    if (indices[i][j] != BRICK_COLUMNS * BRICK_ROWS - 1) {
                        panels[i][j].setPreferredSize(new Dimension(rowSize, columnSize));
                        panels[i][j].setImage(images[indexOne][indexTwo]);
                    } else {
                        panels[i][j].setImage(null);
                    }
                    gamePanel.add(panels[i][j]);
                }
            }

            CustomMouseListener mouseListener = new CustomMouseListener();
            for (int i = 0; i < BRICK_ROWS; i++) {
                for (int j = 0; j < BRICK_COLUMNS; j++) {
                    panels[i][j].addMouseListener(mouseListener);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Image not found!");
        }

    }

    public BarleyBreak() {
        JPanel infoPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(BRICK_ROWS, BRICK_COLUMNS));
        fullImagePanel = new GraphicalPanel();
        buttonPanel.setLayout(new BorderLayout());
        infoPanel.setLayout(new BorderLayout());

        newGameButton = new JButton("New Game");
        chooseImageButton = new JButton("Choose Image");
        CustomButtonListener buttonListener = new CustomButtonListener(this);
        newGameButton.addActionListener(buttonListener);
        chooseImageButton.addActionListener(buttonListener);

        buttonPanel.add(newGameButton, BorderLayout.NORTH);
        buttonPanel.add(chooseImageButton, BorderLayout.SOUTH);
        infoPanel.add(fullImagePanel, BorderLayout.WEST);
        infoPanel.add(buttonPanel, BorderLayout.EAST);

        panels = new GraphicalPanel[BRICK_ROWS][BRICK_COLUMNS];
        images = new BufferedImage[BRICK_ROWS][BRICK_COLUMNS];
        indices = new int[BRICK_ROWS][BRICK_COLUMNS];
        shuffledIndices = new int[BRICK_ROWS * BRICK_COLUMNS];
        for (int i = 0; i < BRICK_ROWS; i++) {
            for (int j = 0; j < BRICK_COLUMNS; j++) {
                panels[i][j] = new GraphicalPanel();
            }
        }

        initializeMatrices();

        add(infoPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
    }
}
