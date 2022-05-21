package enemies;

import java.awt.*;

import static helperMethods.Constants.Direction.*;
import static helperMethods.Constants.Enemies.*;

public abstract class Enemy {
    protected float x, y;
    protected final Rectangle bounds;
    protected int health;
    protected int maxHealth;
    protected final int id;
    protected final int enemyType;
    protected int lastDirection;
    protected boolean alive = true;
    protected int fireTickLimit = 180;
    protected int fireTick = fireTickLimit;
    protected int lifeDamage;
    protected int scoreValue;
    protected int coins;
    protected boolean scoreRecorded = false;
    protected static int redDeaths = 0;
    protected static int greenDeaths = 0;
    protected static int blueDeaths = 0;
    protected static int yellowDeaths = 0;

    public Enemy(float x, float y, int id, int enemyType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enemyType = enemyType;
        bounds = new Rectangle((int) x, (int) y, 32, 32);
        lastDirection = -1;
        setStartHealth();
        setLifeDamage();
        setScoreValue();
        setCoins();
    }

    private void setCoins() {
        coins = getCoins(enemyType);
    }

    private void setScoreValue() {
        scoreValue = getScoreValue(enemyType);
    }

    public float getHealthBar() {
        return health / (float) maxHealth;
    }

    private void setStartHealth() {
        health = getStartHealth(enemyType);
        maxHealth = health;
    }

    private void setLifeDamage() {
        lifeDamage = getLifeDamage(enemyType);
    }

    public void move(float speed, int direction) {
        lastDirection = direction;

        if (fireTick < fireTickLimit) {
            fireTick++;
            hit(1);
            speed *= 0.75;
        }
        switch (direction) {
            case LEFT -> this.x -= speed;
            case RIGHT -> this.x += speed;
            case UP -> this.y -= speed;
            case DOWN -> this.y += speed;
        }

        updateBounds();
    }

    private void updateBounds() {
        bounds.x = (int) x;
        bounds.y = (int) y;
    }

    public void resetBounds() {
        bounds.x = -1;
        bounds.y = -1;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void hit(int damage) {
        this.health -= damage;

        if (health <= 0) {
            this.alive = false;
        }
    }

    public static int getDeaths(int enemyType) {
        switch (enemyType) {
            case RED -> {
                return redDeaths;
            }
            case GREEN -> {
                return greenDeaths;
            }
            case BLUE -> {
                return blueDeaths;
            }
            case YELLOW -> {
                return yellowDeaths;
            }
        }
        return 0;
    }

    public void registerDeath() {
        switch (enemyType) {
            case RED -> {
                redDeaths++;
            }
            case GREEN -> {
                greenDeaths++;
            }
            case BLUE -> {
                blueDeaths++;
            }
            case YELLOW -> {
                yellowDeaths++;
            }
        }
    }

    public void burn() {
        fireTick = 0;
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

    public boolean isAlive() {
        return alive;
    }

    public int getHealth() {
        return health;
    }

    public boolean isOnFire() {
        return fireTick < fireTickLimit;
    }

    public int getDamage() {
        return lifeDamage;
    }

    public int getScore() {
        return scoreValue;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getMoney() {
        return coins;
    }

    public boolean isScoreRecorded() {
        return scoreRecorded;
    }

    public void setScoreRecorded(boolean recorded) {
        this.scoreRecorded = recorded;
    }
}
