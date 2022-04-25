package scenes;

import UI.ActionBar;
import com.company.Game;
import helperMethods.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Playing extends GameScene implements SceneMethods{

    private int[][] lvl;

    private ActionBar actionBar;
    private int mouseX, mouseY;

    public Playing(Game game) {
        super(game);

        loadDefaultLvl();
        actionBar = new ActionBar(0,640,640,100, this);
    }

    private void loadDefaultLvl() {
        lvl = LoadSave.readLevelData("new_lvl");
    }

    @Override
    public void render(Graphics g) {
        drawLvl(g);
        actionBar.draw(g);
    }

    private void drawLvl(Graphics g) {
        for (int i = 0; i < lvl.length; i++){
            for (int j = 0; j < lvl.length; j++) {
                int id = lvl[i][j];
                g.drawImage(getSprite(id),j*32,i*32,null);
            }
        }
    }

    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }

    private BufferedImage getSprite(int id) {
        return getGame().getTileHandler().getSprite(id);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            actionBar.mouseClicked(x,y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640)
            actionBar.mouseMoved(x,y);
        else {
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            actionBar.mousePressed(x,y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        actionBar.mouseReleased(x,y);
    }

    @Override
    public void mouseDragged(int x, int y) {
    }
}
