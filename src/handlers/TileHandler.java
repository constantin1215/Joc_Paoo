package handlers;

import helperMethods.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TileHandler {

    public Tile GRASS, WATER, ROAD;
    public BufferedImage atlas;
    public ArrayList<Tile> tile = new ArrayList<>();

    public TileHandler() {
        loadAtlas();
        createTiles();
    }

    private void createTiles() {
        tile.add(GRASS = new Tile(getSprite(8,1)));
        tile.add(WATER = new Tile(getSprite(0,6)));
        tile.add(ROAD = new Tile(getSprite(9,0)));
    }

    private void loadAtlas() {
        try {
            atlas = LoadSave.getSpriteAtlas();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getSprite(int id) {
        return tile.get(id).getSprite();
    }

    private BufferedImage getSprite(int x, int y) {
        return atlas.getSubimage(x*32,y*32,32,32);
    }
}
