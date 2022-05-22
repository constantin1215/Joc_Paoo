package handlers;

import Other.LoadSave;
import Other.SpriteDirection;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Other.Constants.Tiles.*;

public class TileHandler {

    public Tile GRASS, WATER, ROAD_1, ROAD_2, ROADC_1, ROADC_2, ROADC_3, ROADC_4, WTR_CORNER_1, WTR_CORNER_2, WTR_CORNER_3, WTR_CORNER_4, WTR_B_1, WTR_B_2, WTR_B_3, WTR_B_4, WTR_I_1, WTR_I_2, WTR_I_3, WTR_I_4;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public ArrayList<Tile> roads = new ArrayList<>();
    public ArrayList<Tile> roadsC = new ArrayList<>();
    public ArrayList<Tile> waterC = new ArrayList<>();
    public ArrayList<Tile> beaches = new ArrayList<>();
    public ArrayList<Tile> islands = new ArrayList<>();

    public TileHandler() {
        loadAtlas();
        createTiles();
    }

    private void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(9, 0), id++, GRASS_TILE));
        tiles.add(WATER = new Tile(getAnimatedSprite(0, 0), id++, WATER_TILE));

        roads.add(ROAD_1 = new Tile(getSprite(8, 0), id++, ROAD_TILE));
        roads.add(ROAD_2 = new Tile(SpriteDirection.getRotataedImg(getSprite(8, 0), 90), id++, ROAD_TILE));

        roadsC.add(ROADC_1 = new Tile(getSprite(7, 0), id++, ROAD_TILE));
        roadsC.add(ROADC_2 = new Tile(SpriteDirection.getRotataedImg(getSprite(7, 0), 90), id++, ROAD_TILE));
        roadsC.add(ROADC_3 = new Tile(SpriteDirection.getRotataedImg(getSprite(7, 0), 180), id++, ROAD_TILE));
        roadsC.add(ROADC_4 = new Tile(SpriteDirection.getRotataedImg(getSprite(7, 0), 270), id++, ROAD_TILE));

        waterC.add(WTR_CORNER_1 = new Tile(SpriteDirection.getBuildRotatedImg(getAnimatedSprite(0, 0), getSprite(5, 0), 0), id++, WATER_TILE));
        waterC.add(WTR_CORNER_2 = new Tile(SpriteDirection.getBuildRotatedImg(getAnimatedSprite(0, 0), getSprite(5, 0), 90), id++, WATER_TILE));
        waterC.add(WTR_CORNER_3 = new Tile(SpriteDirection.getBuildRotatedImg(getAnimatedSprite(0, 0), getSprite(5, 0), 180), id++, WATER_TILE));
        waterC.add(WTR_CORNER_4 = new Tile(SpriteDirection.getBuildRotatedImg(getAnimatedSprite(0, 0), getSprite(5, 0), 270), id++, WATER_TILE));

//        waterC.add(WTR_CORNER_1 = new Tile(SpriteDirection.buildImg(getImgs(0,0,5,0)),id++,"Water Corner"));
//        waterC.add(WTR_CORNER_2 = new Tile(SpriteDirection.getBuildRotatedImg(getImgs(0,0,5,0),90,1),id++,"Water Corner 2"));
//        waterC.add(WTR_CORNER_3 = new Tile(SpriteDirection.getBuildRotatedImg(getImgs(0,0,5,0),180,1),id++,"Water Corner 3"));
//        waterC.add(WTR_CORNER_4 = new Tile(SpriteDirection.getBuildRotatedImg(getImgs(0,0,5,0),270,1),id++,"Water Corner 4"));

        beaches.add(WTR_B_1 = new Tile(SpriteDirection.getBuildRotatedImg(getAnimatedSprite(0, 0), getSprite(6, 0), 0), id++, WATER_TILE));
        beaches.add(WTR_B_2 = new Tile(SpriteDirection.getBuildRotatedImg(getAnimatedSprite(0, 0), getSprite(6, 0), 90), id++, WATER_TILE));
        beaches.add(WTR_B_3 = new Tile(SpriteDirection.getBuildRotatedImg(getAnimatedSprite(0, 0), getSprite(6, 0), 180), id++, WATER_TILE));
        beaches.add(WTR_B_4 = new Tile(SpriteDirection.getBuildRotatedImg(getAnimatedSprite(0, 0), getSprite(6, 0), 270), id++, WATER_TILE));

//        beaches.add(WTR_B_1 = new Tile(SpriteDirection.buildImg(getImgs(0,0,6,0)),id++,"Water Beach 1"));
//        beaches.add(WTR_B_2 = new Tile(SpriteDirection.getBuildRotatedImg(getImgs(0,0,6,0),90,1),id++,"Water Beach 2"));
//        beaches.add(WTR_B_3 = new Tile(SpriteDirection.getBuildRotatedImg(getImgs(0,0,6,0),180,1),id++,"Water Beach 3"));
//        beaches.add(WTR_B_4 = new Tile(SpriteDirection.getBuildRotatedImg(getImgs(0,0,6,0),270,1),id++,"Water Beach 4"));

        islands.add(WTR_I_1 = new Tile(SpriteDirection.getBuildRotatedImg(getAnimatedSprite(0, 0), getSprite(4, 0), 0), id++, WATER_TILE));
        islands.add(WTR_I_2 = new Tile(SpriteDirection.getBuildRotatedImg(getAnimatedSprite(0, 0), getSprite(4, 0), 90), id++, WATER_TILE));
        islands.add(WTR_I_3 = new Tile(SpriteDirection.getBuildRotatedImg(getAnimatedSprite(0, 0), getSprite(4, 0), 180), id++, WATER_TILE));
        islands.add(WTR_I_4 = new Tile(SpriteDirection.getBuildRotatedImg(getAnimatedSprite(0, 0), getSprite(4, 0), 270), id, WATER_TILE));

//        islands.add(WTR_I_1 = new Tile(SpriteDirection.buildImg(getImgs(0,0,4,0)),id++,"Water Island 1"));
//        islands.add(WTR_I_2 = new Tile(SpriteDirection.getBuildRotatedImg(getImgs(0,0,4,0),90,1),id++,"Water Island 2"));
//        islands.add(WTR_I_3 = new Tile(SpriteDirection.getBuildRotatedImg(getImgs(0,0,4,0),180,1),id++,"Water Island 3"));
//        islands.add(WTR_I_4 = new Tile(SpriteDirection.getBuildRotatedImg(getImgs(0,0,4,0),270,1),id,"Water Island 4"));

        tiles.addAll(roads);
        tiles.addAll(roadsC);
        tiles.addAll(waterC);
        tiles.addAll(beaches);
        tiles.addAll(islands);
    }

    private BufferedImage[] getImgs(int coordX1, int coordY1, int coordX2, int coordY2) {
        return new BufferedImage[]{getSprite(coordX1, coordY1), getSprite(coordX2, coordY2)};
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }

    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    private BufferedImage getSprite(int x, int y) {
        return atlas.getSubimage(x * 32, y * 32, 32, 32);
    }

    public BufferedImage getAnimatedSpriteAtIndex(int id, int animationIndex) {
        return tiles.get(id).getSprite(animationIndex);
    }

    private BufferedImage[] getAnimatedSprite(int x, int y) {
        BufferedImage[] animation = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            animation[i] = getSprite(x + i, y);
        }

        return animation;
    }

    public boolean isAnimated(int id) {
        return tiles.get(id).isAnimation();
    }

    public ArrayList<Tile> getRoads() {
        return roads;
    }

    public ArrayList<Tile> getRoadsC() {
        return roadsC;
    }

    public ArrayList<Tile> getWaterC() {
        return waterC;
    }

    public ArrayList<Tile> getBeaches() {
        return beaches;
    }

    public ArrayList<Tile> getIslands() {
        return islands;
    }
}
