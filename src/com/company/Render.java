package com.company;

import java.awt.*;

public class Render {

    private final Game game;

    public Render(Game game) {
        this.game = game;
    }

    public void render(Graphics g) {
        switch (GameStates.gameStates) {
            case MENU -> game.getMenu().render(g);
            case PLAYING -> game.getPlaying().render(g);
            case EDIT -> game.getEdit().render(g);
            case SETTINGS -> game.getSettings().render(g);
        }
    }
}
