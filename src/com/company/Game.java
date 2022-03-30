package com.company;

import inputs.KeyboardListener;
import inputs.NewMouseListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Game extends JFrame implements Runnable {

    private GameScreen gameScreen;
    private BufferedImage img;
    private Thread gameThread;

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    private NewMouseListener mouseListen;
    private KeyboardListener keybdListen;

    public Game() {

        try{
            importImg();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameScreen = new GameScreen(img);
        add(gameScreen);

        pack();
        setVisible(true);
    }

    private void initInputs() {
        mouseListen = new NewMouseListener();
        keybdListen = new KeyboardListener();

        addMouseListener(mouseListen);
        addMouseMotionListener(mouseListen);
        addKeyListener(keybdListen);

        requestFocus();
    }

    private void importImg() throws FileNotFoundException {
        InputStream is = new BufferedInputStream(new FileInputStream("resources/spriteatlas.png"));

        try {
            img = ImageIO.read(is);
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void start() {
        gameThread = new Thread(this) {};
        gameThread.start();
    }

    private void update() {

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.initInputs();
        game.start();
    }

    @Override
    public void run() {
        int frames = 0;
        int updates = 0;

        double timePerUpdate = 1000000000.0 / UPS_SET;
        double timePerFrame = 1000000000.0 / FPS_SET;;
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();

        long now;

        while (true) {
            now = System.nanoTime();
            //Render
            if (now - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = now;
                frames++;
            }
            //Update
            if (now - lastUpdate >= timePerUpdate) {
                update();
                lastUpdate = now;
                updates++;
            }

            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }


    }
}
