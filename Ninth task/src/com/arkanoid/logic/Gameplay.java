package com.arkanoid.logic;

import com.arkanoid.objects.Ball;
import com.arkanoid.objects.Block;
import com.arkanoid.objects.Platform;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Gameplay extends JPanel {
    enum BlockColors {MAGENTA, RED, YELLOW, BLUE, GREEN, ORANGE, CYAN, GRAY, DARK_GRAY, LIGHT_GRAY, PINK, WHITE}

    private int platformSpeed;
    private int ballX;
    private int ballY;
    private int deltaX;
    private int deltaY;
    private int delay;
    private int platformX;
    private int platformY;
    private int blockWidth = 0;
    private int blockHeight = 0;
    private int activeBlocks;
    private int level = 0;
    private int hitPoints;
    private final int hitPointImageSize = 50;
    private final int COLUMNS = 10;
    private final int ROWS = 20;
    private final int PLATFORM_WIDTH = 100;
    private final int PLATFORM_HEIGHT = 15;
    private final int BALL_RADIUS = 10;
    private final int PLATFORM_GAP = 50;
    private final int BORDER_SIZE = 15;
    private final int DELTA_X = 5;
    private final int DELTA_Y = 5;
    private final int All_HIT_POINTS = 3;
    private final int BRICKS_AMOUNT = 50;

    private boolean isGameStarted = false;
    private boolean isFirstTime = true;
    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;
    private boolean isEnterPressed = false;
    private static boolean gameOver = false;

    private final Ball ball;
    private final Color backgroundColor = new Color(149, 68, 100);
    private final Color platformColor = Color.GREEN;
    private final Color ballColor = Color.RED;
    private final Color borderColor = new Color(92, 43, 63);
    private final Platform platform;
    private final ArrayList<Block> blockList;
    private final ArrayList<Color> colorList;
    private final ArrayList<Image> hitPointImages;
    private final BallMover ballMover = new BallMover(this);
    private final Font font;
    private final Rectangle blocksArea;
    private final Application application;
    private Timer timer;
    private Rectangle ranges;
    private Image hitPointImage;
    private SoundPlayer soundPlayer;
    private Image hitPointEmptyImage;

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setPlatformSpeed(int speed) {
        this.platformSpeed = speed;
    }

    public void setLevel(int level) {
        this.level = level;
        setPlatformSpeed(PlayerInfo.platformSpeeds[level]);
        setDelay(PlayerInfo.delays[level]);

        if (timer == null) {
            timer = new Timer(delay, ballMover);
            timer.start();
        } else {
            timer.setDelay(delay);
            timer.restart();
        }
    }

    public Gameplay(int lev, Application application) {
        this.application = application;
        activeBlocks = BRICKS_AMOUNT;
        soundPlayer = new SoundPlayer();
        font = new Font("Bubble Pixel-7 Dark", Font.BOLD, hitPointImageSize / 2);
        hitPoints = All_HIT_POINTS;
        try {
            hitPointImage = ImageIO.read(new File("src/com/arkanoid/images/heart.png"));
            hitPointEmptyImage = ImageIO.read(new File("src/com/arkanoid/images/empty heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        hitPointImages = new ArrayList<>();
        for (int i = 0; i < All_HIT_POINTS; i++) {
            hitPointImages.add(hitPointImage);
        }
        setLevel(lev);

        ranges = new Rectangle();
        blocksArea = new Rectangle();
        blockList = new ArrayList<>();
        colorList = new ArrayList<>();
        Random random = new Random();
        int ballColorNumber = random.nextInt(12);
        for (int i = 0; i < ROWS * COLUMNS; i++) {
            if (ballColorNumber == BlockColors.BLUE.ordinal()) {
                colorList.add(Color.BLUE);
            } else if (ballColorNumber == BlockColors.CYAN.ordinal()) {
                colorList.add(Color.CYAN);
            } else if (ballColorNumber == BlockColors.DARK_GRAY.ordinal()) {
                colorList.add(Color.DARK_GRAY);
            } else if (ballColorNumber == BlockColors.GRAY.ordinal()) {
                colorList.add(Color.GRAY);
            } else if (ballColorNumber == BlockColors.LIGHT_GRAY.ordinal()) {
                colorList.add(Color.LIGHT_GRAY);
            } else if (ballColorNumber == BlockColors.GREEN.ordinal()) {
                colorList.add(Color.GREEN);
            } else if (ballColorNumber == BlockColors.MAGENTA.ordinal()) {
                colorList.add(Color.MAGENTA);
            } else if (ballColorNumber == BlockColors.ORANGE.ordinal()) {
                colorList.add(Color.ORANGE);
            } else if (ballColorNumber == BlockColors.RED.ordinal()) {
                colorList.add(Color.RED);
            } else if (ballColorNumber == BlockColors.YELLOW.ordinal()) {
                colorList.add(Color.YELLOW);
            } else if (ballColorNumber == BlockColors.PINK.ordinal()) {
                colorList.add(Color.PINK);
            } else {
                colorList.add(Color.WHITE);
            }
            ballColorNumber = random.nextInt(12);
        }

        deltaX = DELTA_X;
        deltaY = DELTA_Y;
        platformX = Application.WIDTH / 2;
        platformY = Application.HEIGHT - PLATFORM_GAP - BORDER_SIZE - PLATFORM_HEIGHT / 2;
        platform = new Platform(platformColor, PLATFORM_WIDTH, PLATFORM_HEIGHT, new Point(platformX, platformY));
        ballX = platformX;
        ballY = platformY - PLATFORM_HEIGHT / 2 - BALL_RADIUS;
        ball = new Ball(new Point(ballX, ballY));
        PlatformMover platformMover = new PlatformMover(this);

        setVisible(true);
        setFocusable(true);
        addKeyListener(platformMover);
        setBackground(backgroundColor);
        addMouseMotionListener(new MotionListener(this));
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(backgroundColor);
        g.setColor(platformColor);
        ranges = getBounds();

        if (isFirstTime) {
            platformX = ranges.width / 2;
            platformY = ranges.height - BORDER_SIZE - PLATFORM_GAP - PLATFORM_HEIGHT / 2;
            platform.changePlatformPosition(platformX, platformY);

            blocksArea.setBounds(BORDER_SIZE, BORDER_SIZE, ranges.width - 2 * BORDER_SIZE, platformY - PLATFORM_HEIGHT / 2 - 2 * BALL_RADIUS - BORDER_SIZE);
            int BLOCKS_GAP = 5;
            blockHeight = (blocksArea.height - (ROWS - 1) * BLOCKS_GAP) / ROWS;
            blockWidth = (blocksArea.width - (COLUMNS - 1) * BLOCKS_GAP) / COLUMNS;

            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    Color color = colorList.get(i * COLUMNS + j);
                    Point point = new Point(
                            BORDER_SIZE + BLOCKS_GAP * (j + 1) + blockWidth * j + blockWidth / 2,
                            BORDER_SIZE + BLOCKS_GAP * (i + 1) + blockHeight * i + blockHeight / 2);
                    Block block;
                    if (i * COLUMNS + j < BRICKS_AMOUNT) {
                        block = new Block(color, point, true);
                    } else {
                        block = new Block(color, point, false);
                    }
                    blockList.add(block);
                }
            }
            isFirstTime = false;
        }
        platformX = (int) platform.getCenter().getX() - PLATFORM_WIDTH / 2;
        platformY = (int) platform.getCenter().getY() - PLATFORM_HEIGHT / 2;

        g.fillRect(platformX, platformY, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        g.setColor(ballColor);

        if (!isGameStarted) {
            ballX = (int) platform.getCenter().getX();
            ballY = (int) platform.getCenter().getY() - PLATFORM_HEIGHT / 2 - BALL_RADIUS;
            ball.changeBallPosition(ballX, ballY);
        }
        ballX = (int) ball.getCenter().getX() - BALL_RADIUS;
        ballY = (int) ball.getCenter().getY() - BALL_RADIUS;

        g.fillOval(ballX, ballY, BALL_RADIUS * 2, BALL_RADIUS * 2);
        g.setColor(borderColor);
        g.fillRect(0, 0, BORDER_SIZE, ranges.height);
        g.fillRect(0, 0, ranges.width, BORDER_SIZE);
        g.fillRect(ranges.width - BORDER_SIZE, 0, BORDER_SIZE, ranges.height);
        g.fillRect(0, ranges.height - BORDER_SIZE, ranges.width, BORDER_SIZE);
        g.setColor(Color.white);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Block block = blockList.get(i * COLUMNS + j);
                if (block.isBlockEnabled()) {
                    g.setColor(block.getColor());
                    g.fillRect((int) block.getCenter().getX() - blockWidth / 2,
                            (int) block.getCenter().getY() - blockHeight / 2,
                            blockWidth, blockHeight);
                }
            }
        }

        for (int i = 0; i < All_HIT_POINTS; i++) {
            g.drawImage(hitPointImages.get(i), BORDER_SIZE * 2 + hitPointImageSize * i, Application.HEIGHT - 105 - BORDER_SIZE, hitPointImageSize, hitPointImageSize, null);
        }
        g.setFont(font);
        g.setColor(Color.cyan);
    }

    SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

    int getPLATFORM_WIDTH() {
        return PLATFORM_WIDTH;
    }

    int getPLATFORM_HEIGHT() {
        return PLATFORM_HEIGHT;
    }

    Platform getPlatform() {
        return platform;
    }

    boolean isGameStarted() {
        return isGameStarted;
    }

    void setGameStarted(boolean gameStarted) {
        isGameStarted = gameStarted;
    }

    boolean isLeftPressed() {
        return isLeftPressed;
    }

    void setLeftPressed(boolean leftPressed) {
        isLeftPressed = leftPressed;
    }

    boolean isRightPressed() {
        return isRightPressed;
    }

    void setRightPressed(boolean rightPressed) {
        isRightPressed = rightPressed;
    }

    static boolean isGameOver() {
        return gameOver;
    }

    static void setGameOver(boolean gameOver) {
        Gameplay.gameOver = gameOver;
    }

    int getBallX() {
        return ballX;
    }

    void setBallX(int ballX) {
        this.ballX = ballX;
    }

    int getBallY() {
        return ballY;
    }

    void setBallY(int ballY) {
        this.ballY = ballY;
    }

    int getDeltaX() {
        return deltaX;
    }

    void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    int getDeltaY() {
        return deltaY;
    }

    void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    int getBlockWidth() {
        return blockWidth;
    }

    int getBlockHeight() {
        return blockHeight;
    }

    int getActiveBlocks() {
        return activeBlocks;
    }

    void setActiveBlocks(int activeBlocks) {
        this.activeBlocks = activeBlocks;
    }

    int getHitPoints() {
        return hitPoints;
    }

    void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    int getCOLUMNS() {
        return COLUMNS;
    }

    int getROWS() {
        return ROWS;
    }

    int getBORDER_SIZE() {
        return BORDER_SIZE;
    }

    int getDELTA_X() {
        return DELTA_X;
    }

    int getDELTA_Y() {
        return DELTA_Y;
    }

    int getBALL_RADIUS() {
        return BALL_RADIUS;
    }

    Image getHitPointEmptyImage() {
        return hitPointEmptyImage;
    }

    ArrayList<Block> getBlockList() {
        return blockList;
    }

    ArrayList<Image> getHitPointImages() {
        return hitPointImages;
    }

    Rectangle getRanges() {
        return ranges;
    }

    int getPlatformSpeed() {
        return platformSpeed;
    }

    int getPlatformX() {
        return platformX;
    }

    void setPlatformX(int platformX) {
        this.platformX = platformX;
    }

    int getPlatformY() {
        return platformY;
    }

    void setPlatformY(int platformY) {
        this.platformY = platformY;
    }

    Ball getBall() {
        return ball;
    }

    public Application getApplication() {
        return application;
    }
}
