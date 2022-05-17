package scenes;

import UI.ToolBar;
import com.company.Game;
import helperMethods.LoadSave;
import objects.PathPoint;
import objects.Tile;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static helperMethods.Constants.Tiles.ROAD_TILE;

public class Edit extends GameScene implements SceneMethods {
    private int[][] lvl;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private int lastTileX, lastTileY, lastTilelId;
    private boolean drawSelect;
    private final ToolBar toolBar;
    public PathPoint start, finish;

    public Edit(Game game) {
        super(game);
        loadDefaultLvl();
        toolBar = new ToolBar(0, 640, 640, 160, this);
    }

    private void loadDefaultLvl() {
        lvl = LoadSave.readLvlData("new_lvl");
        ArrayList<PathPoint> points = LoadSave.getLvlPathPoint("new_lvl");
        start = points.get(0);
        finish = points.get(1);
    }

    @Override
    public void render(Graphics g) {
        drawLvl(g);
        toolBar.draw(g);
        drawSelectedTile(g);
        drawPathPoints(g);
    }

    private void drawPathPoints(Graphics g) {
        if (start != null) {
            g.drawImage(toolBar.getImgStart(), start.getX() * 32, start.getY() * 32, null);
        }

        if (finish != null) {
            g.drawImage(toolBar.getImgFinish(), finish.getX() * 32, finish.getY() * 32, null);
        }
    }

    public void update() {
        updateTick();
    }

    private void drawLvl(Graphics g) {
        for (int i = 0; i < lvl.length; i++) {
            for (int j = 0; j < lvl.length; j++) {
                int id = lvl[i][j];
                if (isAnimated(id)) {
                    g.drawImage(getSprite(id, animationIndex), j * 32, i * 32, null);
                } else
                    g.drawImage(getSprite(id), j * 32, i * 32, null);
            }
        }
    }


    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelect) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    public void saveLvl() {
        LoadSave.saveLvl("new_lvl", lvl, start, finish);
        getGame().getPlaying().setLevel(lvl);
    }

    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelect = true;
    }

    private void changeTile(int x, int y) {

        if (selectedTile != null) {
            int tileX = x / 32;
            int tileY = y / 32;
            if (selectedTile.getId() >= 0) {
                if (lastTileX == tileX && lastTileY == tileY && lastTilelId == selectedTile.getId())
                    return;

                lastTileX = tileX;
                lastTileY = tileY;
                lastTilelId = selectedTile.getId();

                lvl[tileY][tileX] = selectedTile.getId();
            } else {
                int id = lvl[tileY][tileX];
                if (getGame().getTileHandler().getTile(id).getType() == ROAD_TILE) {
                    if (selectedTile.getId() == -1)
                        start = new PathPoint(tileX, tileY);
                    else
                        finish = new PathPoint(tileX, tileY);
                }
            }
        }
    }


    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            toolBar.mouseClicked(x, y);
        } else {
            changeTile(mouseX, mouseY);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            toolBar.mouseMoved(x, y);
            drawSelect = false;
        } else {
            drawSelect = true;
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    @Override
    public void mouseDragged(int x, int y) {
        if (y < 640) {
            changeTile(x, y);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            toolBar.rotateSprite();
        }
    }
}
