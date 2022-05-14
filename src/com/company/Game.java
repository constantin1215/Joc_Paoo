package com.company;

import handlers.TileHandler;
import helperMethods.LoadSave;
import scenes.Edit;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

import javax.swing.*;
import java.util.Arrays;

public class Game extends JFrame implements Runnable {

    private GameScreen gameScreen;
    private Thread gameThread;

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    private Render render;
    private Menu menu;
    private Playing playing;
    private Edit edit;
    private Settings settings;

    private TileHandler tileHandler;
    public Game() {
        initClasses();
        createDefaultLvl();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        add(gameScreen);
        pack();
        setVisible(true);
    }

    private void initClasses() {
        tileHandler = new TileHandler();
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        edit = new Edit(this);
        playing = new Playing(this);
        settings = new Settings(this);
    }

    private void createDefaultLvl() {
        int[] arr = new int[400];
        Arrays.fill(arr, 1);

        LoadSave.createDefaultLvl("new_lvl",arr);
    }

    private void start() {
        gameThread = new Thread(this) {};
        gameThread.start();
    }

    private void update() {
        switch (GameStates.gameStates) {
            case PLAYING -> playing.update();
            case EDIT -> edit.update();
            case MENU, SETTINGS -> {
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.gameScreen.initInputs();
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
            if (now - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = now;
                frames++;
            }
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

    public Render getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Settings getSettings() {
        return settings;
    }

    public Edit getEdit() {
        return edit;
    }

    public TileHandler getTileHandler() {
        return tileHandler;
    }
}
