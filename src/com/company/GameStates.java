package com.company;

public enum GameStates {
    PLAYING,
    EDIT,
    MENU,
    SETTINGS,
    GAME_OVER;

    public static GameStates gameStates = MENU;

    public static void SetGameState(GameStates state) {
        gameStates = state;
    }
}
