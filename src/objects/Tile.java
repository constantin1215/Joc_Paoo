package objects;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage[] sprite;
    private int id;
    private int type;

    public Tile(BufferedImage sprite, int id, int type) {
        this.sprite = new BufferedImage[1];
        this.sprite[0] = sprite;
        this.id = id;
        this.type = type;
    }

    public Tile(BufferedImage[] sprite, int id, int type) {
        this.sprite = sprite;
        this.id = id;
        this.type = type;
    }

    public BufferedImage getSprite(int index) {
        return sprite[index];
    }

    public BufferedImage getSprite() {
        return sprite[0];
    }

    public boolean isAnimation() {
        return sprite.length > 1;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }
}
