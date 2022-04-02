package UI;

import objects.Tile;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static com.company.GameStates.MENU;
import static com.company.GameStates.SetGameState;

public class BottomBar {
    int x, y, width, height;
    private Bttn bMenu;
    private Playing playing;

    private ArrayList<Bttn> tileButtons = new ArrayList<>();

    public BottomBar(int x, int y, int width, int height, Playing playing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;

        initButtons();
    }

    private void initButtons() {
        bMenu = new Bttn("Menu", 5,645,100,100/3);

        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int offSet = (int)(w*1.1f);
        int i = 0;

        for (Tile tile : playing.getTileHandler().tiles) {
            tileButtons.add(new Bttn(tile.getName(),xStart + offSet*i,yStart,w,h,i));
            i++;
        }
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);

        drawTileButtons(g);
    }

    private void drawTileButtons(Graphics g) {
        for (Bttn btn : tileButtons) {
            g.drawImage(getBtnImg(btn.getId()),btn.x,btn.y,btn.width,btn.height,null);
        }
    }

    public BufferedImage getBtnImg(int id) {
        return playing.getTileHandler().getSprite(id);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(156,81,39));
        g.fillRect(x,y,width,height);

        drawButtons(g);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getLimits().contains(x,y)) {
            SetGameState(MENU);
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if (bMenu.getLimits().contains(x,y)) {
            bMenu.setMouseOver(true);
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getLimits().contains(x,y)) {
            bMenu.setMousePressed(true);
        }
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBool();
    }
}
