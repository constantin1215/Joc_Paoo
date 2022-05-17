package handlers;

import enemies.*;
import helperMethods.LoadSave;
import objects.PathPoint;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helperMethods.Constants.Direction.*;
import static helperMethods.Constants.Enemies.*;
import static helperMethods.Constants.Tiles.ROAD_TILE;

public class EnemyHandler {
    private final Playing playing;
    private final BufferedImage[] enemyImgs;
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final PathPoint start;
    private final PathPoint finish;
    private int HP_BAR_W = 26;

    public EnemyHandler(Playing playing, PathPoint start, PathPoint finish) {
        this.playing = playing;
        enemyImgs = new BufferedImage[4];
        this.start = start;
        this.finish = finish;
        addEnemy(RED);
        addEnemy(BLUE);
        addEnemy(GREEN);
        addEnemy(YELLOW);
        loadEnemyImg();

    }

    private void loadEnemyImg() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();

        for (int i = 0; i < 4; i++) {
            enemyImgs[i] = atlas.getSubimage(32 * i, 32, 32, 32);
        }
    }

    public void addEnemy(int enemyType) {

        int x = start.getX() * 32;
        int y = start.getY() * 32;

        switch (enemyType) {
            case RED -> enemies.add(new RedBaloon(x, y, 0));
            case BLUE -> enemies.add(new BlueBaloon(x, y, 0));
            case GREEN -> enemies.add(new GreenBaloon(x, y, 0));
            case YELLOW -> enemies.add(new YellowBaloon(x, y, 0));
        }

    }

    public void update() {
        for (Enemy enemy : enemies)
            pathfind(enemy);
    }

    public void pathfind(Enemy enemy) {
        if (enemy.getLastDirection() == -1)
            setNewDirectionAndMove(enemy);

        int newX = (int) (enemy.getX() + getSpeedAndWidth(enemy.getLastDirection(), enemy.getEnemyType()));
        int newY = (int) (enemy.getY() + getSpeedAndHeight(enemy.getLastDirection(), enemy.getEnemyType()));

        if (getTileType(newX, newY) == ROAD_TILE) {
            enemy.move(getEnemySpeed(enemy.getEnemyType()), enemy.getLastDirection());
        } else if (isAtEnd(enemy)) {
            System.out.println("-1 Lives");
        } else {
            setNewDirectionAndMove(enemy);
        }
    }

    private void setNewDirectionAndMove(Enemy enemy) {
        int dir = enemy.getLastDirection();

        int xOffset = (int) (enemy.getX() / 32);
        int yOffset = (int) (enemy.getY() / 32);

        enemyOffset(enemy, dir, xOffset, yOffset);

        if (isAtEnd(enemy))
            return;

        if (dir == LEFT || dir == RIGHT) {
            int newY = (int) (enemy.getY() + getSpeedAndHeight(UP, enemy.getEnemyType()));
            if (getTileType((int) enemy.getX(), newY) == ROAD_TILE)
                enemy.move(getEnemySpeed(enemy.getEnemyType()), UP);
            else
                enemy.move(getEnemySpeed(enemy.getEnemyType()), DOWN);
        } else {
            int newX = (int) (enemy.getX() + getSpeedAndWidth(RIGHT, enemy.getEnemyType()));
            if (getTileType(newX, (int) enemy.getY()) == ROAD_TILE)
                enemy.move(getEnemySpeed(enemy.getEnemyType()), RIGHT);
            else
                enemy.move(getEnemySpeed(enemy.getEnemyType()), LEFT);
        }

    }

    private void enemyOffset(Enemy enemy, int dir, int xOffset, int yOffset) {
        switch (dir) {
            case RIGHT -> {
                if (xOffset < 19)
                    xOffset++;
            }
            case DOWN -> {
                if (yOffset < 19)
                    yOffset++;
            }
        }

        enemy.setPos(xOffset * 32, yOffset * 32);
    }

    private boolean isAtEnd(Enemy enemy) {
        return enemy.getX() == finish.getX() * 32 && enemy.getY() == finish.getY() * 32;
    }

    private int getTileType(int x, int y) {
        return playing.getTileType(x, y);
    }

    private float getSpeedAndWidth(int lastDirection, int enemyType) {
        if (lastDirection == LEFT)
            return -getEnemySpeed(enemyType);
        else if (lastDirection == RIGHT)
            return getEnemySpeed(enemyType) + 32;
        return 0;
    }

    private float getSpeedAndHeight(int lastDirection, int enemyType) {
        if (lastDirection == UP)
            return -getEnemySpeed(enemyType);
        else if (lastDirection == DOWN)
            return getEnemySpeed(enemyType) + 32;
        return 0;
    }

    public void draw(Graphics g) {
        for (Enemy enemy : enemies) {
            drawEnemy(enemy, g);
            drawHealthBar(enemy, g);
        }
    }

    private void drawHealthBar(Enemy enemy, Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)enemy.getX() + 16 - HP_BAR_W/2 , (int)enemy.getY()- 10,HP_BAR_W, 3);
    }

    private int getNewBarWidth(Enemy enemy) {
        return (int)(HP_BAR_W * enemy.getHealthBar());
    }

    private void drawEnemy(Enemy enemy, Graphics g) {
        g.drawImage(enemyImgs[enemy.getEnemyType()], (int) enemy.getX(), (int) enemy.getY(), null);
    }
}
