package handlers;

import helperMethods.LoadSave;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TowerHandler {

    private final Playing playing;
    private BufferedImage[] towerImg;
    private final ArrayList<Tower> towers = new ArrayList<>();
    private int towersCount = 0;

    public TowerHandler(Playing playing) {
        this.playing = playing;

        loadTowerImg();
    }

    public void addTower(Tower selectedTower, int x, int y) {
        towers.add(new Tower(x, y, towersCount++, selectedTower.getTowerType()));
    }

    private void loadTowerImg() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImg = new BufferedImage[3];

        for (int i = 0; i < 3; i++)
            towerImg[i] = atlas.getSubimage(32 * (4 + i), 32, 32, 32);
    }

    public void draw(Graphics g) {

        for (Tower tower : towers) {
            g.drawImage(towerImg[tower.getTowerType()], tower.getX(), tower.getY(), null);
        }

    }

    public void update() {

    }

    public Tower getTowerAt(int x, int y) {
        for (Tower tower : towers)
            if (tower.getX() == x && tower.getY() == y)
                return tower;
        return null;
    }

    public BufferedImage[] getTowerImg() {
        return towerImg;
    }
}
