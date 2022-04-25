package scenes;

import UI.ToolBar;
import com.company.Game;
import helperMethods.LoadSave;
import objects.Tile;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Edit extends GameScene implements SceneMethods{
    private int[][] lvl;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private int lastTileX, lastTileY, lastTilelId;
    private boolean drawSelect;
    private ToolBar toolBar;

    public Edit(Game game) {
        super(game);
        loadDefaultLvl();
        toolBar = new ToolBar(0,640,640,100, this);
    }

    private void loadDefaultLvl() {
        lvl = LoadSave.readLevelData("new_lvl");
    }

    @Override
    public void render(Graphics g) {
        drawLvl(g);
        toolBar.draw(g);
        drawSelectedTile(g);
    }

    private void drawLvl(Graphics g) {
        for (int i = 0; i < lvl.length; i++){
            for (int j = 0; j < lvl.length; j++) {
                int id = lvl[i][j];
                g.drawImage(getSprite(id),j*32,i*32,null);
            }
        }
    }

    private BufferedImage getSprite(int id) {
        return getGame().getTileHandler().getSprite(id);
    }


    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelect) {
            g.drawImage(selectedTile.getSprite(),mouseX,mouseY,32,32,null);
        }
    }

    public void saveLvl() {
        LoadSave.saveLvl("new_lvl",lvl);
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

            if (lastTileX == tileX && lastTileY == tileY && lastTilelId == selectedTile.getId())
                return;

            lastTileX = tileX;
            lastTileY = tileY;
            lastTilelId = selectedTile.getId();

            lvl[tileY][tileX] = selectedTile.getId();
        }
    }


    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            toolBar.mouseClicked(x,y);
        } else {
            changeTile(mouseX,mouseY);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            toolBar.mouseMoved(x,y);
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
            changeTile(x,y);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            toolBar.rotateSprite();
        }
    }
}
