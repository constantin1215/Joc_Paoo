package enemies;

import java.awt.*;
import static helperMethods.Constants.Direction.*;

public abstract class Enemy {
    private float x,y;
    private Rectangle bounds;
    private int health;
    private int id;
    private int enemyType;
    private int lastDirection;

    public Enemy(float x, float y, int id, int enemyType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enemyType = enemyType;
        bounds = new Rectangle((int) x, (int) y, 32, 32);
        lastDirection = -1;
    }

    public void move(float speed, int direction) {
        lastDirection = direction;
        switch (direction) {
            case LEFT -> this.x -= speed;
            case RIGHT -> this.x += speed;
            case UP -> this.y -= speed;
            case DOWN -> this.y += speed;
        }
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getId() {
        return id;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public int getLastDirection() {
        return lastDirection;
    }
}
