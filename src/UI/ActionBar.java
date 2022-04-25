package UI;

import objects.Tile;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static com.company.GameStates.MENU;
import static com.company.GameStates.SetGameState;

public class ActionBar extends Bar{
    private Bttn bMenu;
    private Playing playing;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x,y,width,height);
        this.playing = playing;

        initButtons();
    }

    private void initButtons() {
        bMenu = new Bttn("Menu", 5,645,100,100/3);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    public BufferedImage getBtnImg(int id) {
        return playing.getGame().getTileHandler().getSprite(id);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(156,81,39));
        g.fillRect(x,y,width,height);

        drawButtons(g);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getLimits().contains(x,y))
            SetGameState(MENU);
    }
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if (bMenu.getLimits().contains(x,y))
            bMenu.setMouseOver(true);
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getLimits().contains(x,y))
            bMenu.setMousePressed(true);
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBool();
    }
}
