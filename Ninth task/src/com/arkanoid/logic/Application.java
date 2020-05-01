package com.arkanoid.logic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Application extends JFrame {

    public static int WIDTH = 750;
    public static int HEIGHT = 600;

    private final JMenuItem menuItem;
    private final JDialog difficultyDialog;
    private final Image image;
    private final Font panelFont;
    private final int fontSize = 25;
    private final JButton amateur;
    private final JButton middle;
    private final JButton professional;
    private final JButton psycho;
    private SoundPlayer soundPlayer;
    private final Gameplay gameplay;

    Image getImage() {
        return image;
    }

    Font getPanelFont() {
        return panelFont;
    }

    int getFontSize() {
        return fontSize;
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }

    public JDialog getDifficultyDialog() {
        return difficultyDialog;
    }

    public JButton getAmateur() {
        return amateur;
    }

    public JButton getMiddle() {
        return middle;
    }

    public JButton getProfessional() {
        return professional;
    }

    public JButton getPsycho() {
        return psycho;
    }

    public Gameplay getGameplay() {
        return gameplay;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Arkanoid");
        setLayout(new BorderLayout());

        TimerListener listener = new TimerListener(this);
        Timer timer = new Timer(500, listener);
        timer.start();

        JPanel panelsPanel = new JPanel();
//        soundPlayer = new SoundPlayer();
        image = ImageIO.read(new File("src/com/arkanoid/images/arcanoid.jpg"));
        panelFont = new Font("Bubble Pixel-7 Dark", Font.BOLD, fontSize);

        gameplay = new Gameplay(0, this);
        panelsPanel.setLayout(new BorderLayout());
        panelsPanel.add(gameplay, BorderLayout.CENTER);

        JMenu menu = new JMenu("Settings...");
        JMenuBar menuBar = new JMenuBar();
        menuItem = new JMenuItem("Select Difficulty Level");
        ButtonListener buttonListener = new ButtonListener(this);
        menuItem.addActionListener(buttonListener);
        menu.add(menuItem);
        menuBar.add(menu);

        difficultyDialog = new JDialog();
        difficultyDialog.setTitle("Chose difficulty");
        difficultyDialog.setResizable(false);
        difficultyDialog.setBounds(getBounds());
        difficultyDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

        DialogPanel dialogPanel = new DialogPanel(new BorderLayout(), true, this);
        difficultyDialog.add(dialogPanel);
        difficultyDialog.setBounds(getX() + (Application.WIDTH - image.getWidth(null)) / 2, getY(),
                image.getWidth(null), image.getHeight(null));

        amateur = new JButton(PlayerInfo.levels[0]);
        middle = new JButton(PlayerInfo.levels[1]);
        professional = new JButton(PlayerInfo.levels[2]);
        psycho = new JButton(PlayerInfo.levels[3]);
        dialogPanel.setLayout(null);

        amateur.setFont(panelFont);
        middle.setFont(panelFont);
        professional.setFont(panelFont);
        psycho.setFont(panelFont);

        dialogPanel.add(amateur);
        dialogPanel.add(middle);
        dialogPanel.add(professional);
        dialogPanel.add(psycho);

        int location = getWidth() / 2 - 300;

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
