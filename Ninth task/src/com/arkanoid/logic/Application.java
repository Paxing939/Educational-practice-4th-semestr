package com.arkanoid.logic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Application extends JFrame {

    public static int WIDTH = 750;
    public static int HEIGHT = 600;

    private final JMenuItem difficultyMenuItem;
    private final JMenuItem anotherDifficultyMenuItem;
    private final JDialog difficultyDialog;
    private final JDialog anotherDifficultyDialog;
    private final Image image;
    private final Image difficultyImage;
    private final Font panelFont;
    private final int fontSize = 25;
    private final JButton amateur;
    private final JButton middle;
    private final JButton professional;
    private final JButton psycho;
    private final JButton small;
    private final JButton average;
    private final JButton big;
    private final SoundPlayer soundPlayer;
    private final Gameplay gameplay;

    public JDialog getAnotherDifficultyDialog() {
        return anotherDifficultyDialog;
    }

    public JMenuItem getAnotherDifficultyMenuItem() {
        return anotherDifficultyMenuItem;
    }

    public JButton getSmall() {
        return small;
    }

    public JButton getAverage() {
        return average;
    }

    public JButton getBig() {
        return big;
    }

    Image getDifficultyImage() {
        return difficultyImage;
    }

    Image getImage() {
        return image;
    }

    Font getPanelFont() {
        return panelFont;
    }

    int getFontSize() {
        return fontSize;
    }

    JMenuItem getDifficultyMenuItem() {
        return difficultyMenuItem;
    }

    JDialog getDifficultyDialog() {
        return difficultyDialog;
    }

    JButton getAmateur() {
        return amateur;
    }

    JButton getMiddle() {
        return middle;
    }

    JButton getProfessional() {
        return professional;
    }

    JButton getPsycho() {
        return psycho;
    }

    Gameplay getGameplay() {
        return gameplay;
    }

    SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

    public void centralizeWindow(int width, int height) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;
        this.setBounds(x, y, width, height);
    }

    public Application() throws HeadlessException, IOException {
        centralizeWindow(Application.WIDTH, Application.HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Arkanoid");
        setLayout(new BorderLayout());

        TimerListener listener = new TimerListener(this);
        Timer timer = new Timer(500, listener);
        timer.start();

        JPanel panelsPanel = new JPanel();
        soundPlayer = new SoundPlayer();
        difficultyImage = ImageIO.read(new File("src/com/arkanoid/images/difficultyImage.jpg"));
        image = ImageIO.read(new File("src/com/arkanoid/images/arcanoid.jpg"));
        panelFont = new Font("Bubble Pixel-7 Dark", Font.BOLD, fontSize);

        gameplay = new Gameplay(0, this);
        panelsPanel.setLayout(new BorderLayout());
        panelsPanel.add(gameplay, BorderLayout.CENTER);

        JMenu menu = new JMenu("Settings...");
        JMenuBar menuBar = new JMenuBar();
        difficultyMenuItem = new JMenuItem("Select Difficulty Level");
        anotherDifficultyMenuItem = new JMenuItem("Select Another Difficulty");
        ButtonListener buttonListener = new ButtonListener(this);
        difficultyMenuItem.addActionListener(buttonListener);
        anotherDifficultyMenuItem.addActionListener(buttonListener);
        menu.add(anotherDifficultyMenuItem);
        menu.add(difficultyMenuItem);
        menuBar.add(menu);

        anotherDifficultyDialog = new JDialog();
        anotherDifficultyDialog.setTitle("Chose difficulty");
        anotherDifficultyDialog.setResizable(false);
        anotherDifficultyDialog.setBounds(getBounds());
        anotherDifficultyDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

        AnotherDifficultyDialogPanel anotherDifficultyDialogPanel = new AnotherDifficultyDialogPanel(new BorderLayout(), this);
        anotherDifficultyDialog.add(anotherDifficultyDialogPanel);
        anotherDifficultyDialog.setBounds(getX() + (Application.WIDTH - image.getWidth(null)) / 2, getY(),
                image.getWidth(null), image.getHeight(null));

        small = new JButton(PlayerInfo.difficulty[0]);
        average = new JButton(PlayerInfo.difficulty[1]);
        big = new JButton(PlayerInfo.difficulty[2]);
        anotherDifficultyDialogPanel.setLayout(null);
        small.setFont(panelFont);
        average.setFont(panelFont);
        big.setFont(panelFont);

        int location = getWidth() / 2 - 300;

        small.setBounds(location, 250, 400, 50);
        average.setBounds(location, 350, 400, 50);
        big.setBounds(location, 450, 400, 50);

        small.addActionListener(buttonListener);
        average.addActionListener(buttonListener);
        big.addActionListener(buttonListener);

        anotherDifficultyDialogPanel.add(small);
        anotherDifficultyDialogPanel.add(average);
        anotherDifficultyDialogPanel.add(big);

        difficultyDialog = new JDialog();
        difficultyDialog.setTitle("Chose difficulty");
        difficultyDialog.setResizable(false);
        difficultyDialog.setBounds(getBounds());
        difficultyDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

        DifficultyDialogPanel difficultyDialogPanel = new DifficultyDialogPanel(new BorderLayout(), this);
        difficultyDialog.add(difficultyDialogPanel);
        difficultyDialog.setBounds(getX() + (Application.WIDTH - image.getWidth(null)) / 2, getY(),
                image.getWidth(null), image.getHeight(null));

        amateur = new JButton(PlayerInfo.levels[0]);
        middle = new JButton(PlayerInfo.levels[1]);
        professional = new JButton(PlayerInfo.levels[2]);
        psycho = new JButton(PlayerInfo.levels[3]);
        difficultyDialogPanel.setLayout(null);

        amateur.setFont(panelFont);
        middle.setFont(panelFont);
        professional.setFont(panelFont);
        psycho.setFont(panelFont);

        difficultyDialogPanel.add(amateur);
        difficultyDialogPanel.add(middle);
        difficultyDialogPanel.add(professional);
        difficultyDialogPanel.add(psycho);

        amateur.setBounds(location, 250, 400, 50);
        middle.setBounds(location, 350, 400, 50);
        professional.setBounds(location, 450, 400, 50);
        psycho.setBounds(location, 550, 400, 50);

        amateur.addActionListener(buttonListener);
        middle.addActionListener(buttonListener);
        professional.addActionListener(buttonListener);
        psycho.addActionListener(buttonListener);

        add(panelsPanel, BorderLayout.CENTER);
        setJMenuBar(menuBar);
    }
}
