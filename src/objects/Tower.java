package objects;

import static Other.Constants.Towers.*;

public class Tower {
    private final int x;
    private final int y;
    private final int id;
    private final int towerType;
    private int range, damage;
    private int cooldown;
    private int cooldownTimer;
    private int cost;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        cooldownTimer = 0;
        setDamage();
        setRange();
        setFireRate();
        setTowerCost();
    }

    public void update() {
        cooldownTimer++;
    }

    public boolean notOnCooldown() {
        return cooldownTimer >= cooldown;
    }

    public void resetCooldown() {
        cooldownTimer = 0;
    }

    private void setFireRate() {
        cooldown = getTowerFireRate(towerType);
    }

    private void setRange() {
        range = getTowerRange(towerType);
    }

    private void setDamage() {
        damage = getTowerDamage(towerType);
    }

    private void setTowerCost() {
        cost = getTowerCost(towerType);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public int getTowerType() {
        return towerType;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
    }

    public int getCost() {
        return cost;
    }
}
