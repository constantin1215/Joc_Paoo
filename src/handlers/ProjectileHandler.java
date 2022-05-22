package handlers;

import enemies.Enemy;
import Other.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Other.Constants.Projectiles.*;
import static Other.Constants.Towers.*;

public class ProjectileHandler {
    private final Playing playing;
    private final ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage[] projectileImgs;
    private int id = 0;

    public ProjectileHandler(Playing playing) {
        this.playing = playing;
        loadProjectileImgs();
    }

    private void loadProjectileImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        projectileImgs = new BufferedImage[3];

        for (int i = 0; i < 3; i++)
            projectileImgs[i] = atlas.getSubimage((7 + i) * 32, 32, 32, 32);
    }

    public void createProjectile(Tower tower, Enemy enemy) {
        int type = getProjectileType(tower);

        int xDist = (int) (tower.getX() - enemy.getX());
        int yDist = (int) (tower.getY() - enemy.getY());

        int totalDist = Math.abs(xDist) + Math.abs(yDist);

        float xPer = (float) Math.abs(xDist) / totalDist;

        float xSpd = xPer * getSpeed(type);
        float ySpd = getSpeed(type) - xSpd;

        if (tower.getX() > enemy.getX())
            xSpd *= -1;
        if (tower.getY() > enemy.getY())
            ySpd += -1;

        float arcValue = (float) Math.atan(yDist / (float) xDist);
        float rotate = (float) Math.toDegrees(arcValue);

        if (xDist < 0)
            rotate += 180;

        projectiles.add(new Projectile(tower.getX() + 16, tower.getY() + 16, xSpd, ySpd, tower.getDamage(), id++, type, rotate));
    }

    private int getProjectileType(Tower tower) {
        switch (tower.getTowerType()) {
            case MNK_DARTS -> {
                return DART;
            }
            case MNK_BOOMERANG -> {
                return BOOMERANG;
            }
            case MNK_WIZARD -> {
                return FIREBALL;
            }
        }
        return 0;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (Projectile projectile : projectiles)
            if (projectile.projExists()) {
                g2d.translate(projectile.getPos().getX(), projectile.getPos().getY());
                g2d.rotate(Math.toRadians(projectile.getRotation()));
                g2d.drawImage(projectileImgs[projectile.getProjectileType()], -16, -16, null);
                g2d.rotate(-Math.toRadians(projectile.getRotation()));
                g2d.translate(-projectile.getPos().getX(), -projectile.getPos().getY());
            }
    }

    public void update() {
        for (Projectile projectile : projectiles)
            if (projectile.projExists()) {
                projectile.move();
                if (projectileHit(projectile))
                    projectile.setExists(false);
            }
    }

    private boolean projectileHit(Projectile projectile) {
        for (Enemy enemy : playing.getEnemyHandler().getEnemies()) {
            if (enemy.getBounds().contains(projectile.getPos())) {
                enemy.hit(projectile.getDamage());
                if (projectile.getProjectileType() == FIREBALL) {
                    enemy.burn();
                }
                if (!enemy.isAlive() && !enemy.isScoreRecorded()) {
                    playing.addToScore(enemy.getScore());
                    playing.addToCoins(enemy.getMoney());
                    enemy.setScoreRecorded(true);
                    enemy.registerDeath();
                    enemy.resetBounds();
                    playing.notifyObservers();
                }
                return true;
            }
        }
        return false;
    }
}
