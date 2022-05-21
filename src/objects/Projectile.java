package objects;

import java.awt.geom.Point2D;

public class Projectile {
    private final Point2D.Float pos;
    private final int id;
    private final int projectileType;
    private final int damage;
    private final float xSpd;
    private final float ySpd;
    private final float rotation;
    private boolean exists = true;

    public Projectile(float x, float y, float xSpd, float ySpd, int damage, int id, int projectileType, float rotation) {
        pos = new Point2D.Float(x, y);
        this.id = id;
        this.xSpd = xSpd;
        this.ySpd = ySpd;
        this.damage = damage;
        this.rotation = rotation;
        this.projectileType = projectileType;
    }

    public void move() {
        pos.x += xSpd;
        pos.y += ySpd;
    }

    public Point2D.Float getPos() {
        return pos;
    }

    public int getId() {
        return id;
    }

    public int getDamage() {
        return damage;
    }

    public int getProjectileType() {
        return projectileType;
    }

    public boolean projExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public float getRotation() {
        return rotation;
    }
}
