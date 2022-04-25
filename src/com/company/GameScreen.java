package com.company;

import inputs.KeyboardListener;
import inputs.NewMouseListener;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {

    private Dimension size;
    private Game game;

    private NewMouseListener mouseListen;
    private KeyboardListener keybdListen;

    public GameScreen(Game game) {
        this.game = game;

        setPanelSize();
    }

    public void initInputs() {
        mouseListen = new NewMouseListener(game);
        keybdListen = new KeyboardListener(game);

        addMouseListener(mouseListen);
        addMouseMotionListener(mouseListen);
        addKeyListener(keybdListen);

        requestFocus();
    }

    private void setPanelSize() {
        size = new Dimension(640,740);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.getRender().render(g);
    }
}
