package handlers;

import objects.Wave;
import scenes.Playing;

import java.util.ArrayList;
import java.util.Arrays;

import static helperMethods.Constants.Enemies.*;

public class WaveHandler {
    private final Playing playing;
    private final ArrayList<Wave> waves = new ArrayList<>();
    private int enemyTickLimit = 60;
    private int enemyTick = enemyTickLimit;
    private int enemyI, waveI;

    public WaveHandler(Playing playing) {
        this.playing = playing;
        createWaves();
    }

    public void update() {
        if (enemyTick < enemyTickLimit)
            enemyTick++;
    }

    public int spawnNextEnemy() {
        enemyTick = 0;
        return waves.get(waveI).getEnemies().get(enemyI++);
    }

    private void createWaves() {
        waves.add(new Wave(new ArrayList<>(Arrays.asList(RED, RED, RED, RED, RED, RED, BLUE, BLUE, BLUE, BLUE, BLUE, GREEN, GREEN, GREEN, GREEN, GREEN, YELLOW, YELLOW, YELLOW, YELLOW, YELLOW))));
        waves.add(new Wave(new ArrayList<>(Arrays.asList(RED, RED, RED, RED, RED, RED, BLUE, BLUE, BLUE, BLUE, BLUE, GREEN, GREEN, GREEN, GREEN, GREEN, YELLOW, YELLOW, YELLOW, YELLOW, YELLOW))));
        waves.add(new Wave(new ArrayList<>(Arrays.asList(RED, RED, RED, RED, RED, RED, BLUE, BLUE, BLUE, BLUE, BLUE, GREEN, GREEN, GREEN, GREEN, GREEN, YELLOW, YELLOW, YELLOW, YELLOW, YELLOW))));
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public boolean delayPassed() {
        return enemyTick >= enemyTickLimit;
    }

    public boolean waveNotFinished() {
        return enemyI < waves.get(waveI).getEnemies().size();
    }

    public int getWaveI() {
        return waveI;
    }
}
