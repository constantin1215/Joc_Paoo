package objects;

import static helperMethods.Constants.Towers.*;

public class Tower {
    private final int x;
    private final int y;
    private final int id;
    private final int towerType;
    private int range;
    private float fireRate, damage;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        setDamage();
        setRange();
        setFireRate();
    }

    private void setFireRate() {
        fireRate = getTowerFireRate(towerType);
    }

    private void setRange() {
        range = getTowerRange(towerType);
    }

    private void setDamage() {
        damage = getTowerDamage(towerType);
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

    public float getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public float getFireRate() {
        return fireRate;
    }
}
