package UI;

import helperMethods.Constants;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;

import static helperMethods.Constants.*;

import static com.company.GameStates.MENU;
import static com.company.GameStates.SetGameState;

public class ActionBar extends Bar {
    private Bttn bMenu;
    private Bttn[] towerBttn;
    private final Playing playing;
    private Tower selectedTower;
    private Tower displayedTower;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;

        initButtons();
    }

    private void initButtons() {
        bMenu = new Bttn("Menu", 5, 645, 100, 100 / 3);

        towerBttn = new Bttn[3];

        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int offSet = (int) (w * 1.1f);

        for (int i = 0; i < towerBttn.length; i++) {
            towerBttn[i] = new Bttn("", xStart + offSet * i, yStart, w, h, i);
        }
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);

        for (Bttn bttn : towerBttn) {
            g.setColor(Color.green);
            g.fillRect(bttn.x, bttn.y, bttn.width, bttn.height);
            g.drawImage(playing.getTowerHandler().getTowerImg()[bttn.getId()], bttn.x, bttn.y, bttn.width, bttn.height, null);
            btnAnimation(g, bttn);
        }

    }

    public BufferedImage getBtnImg(int id) {
        return playing.getGame().getTileHandler().getSprite(id);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(156, 81, 39));
        g.fillRect(x, y, width, height);

        drawButtons(g);

        drawDisplayedTower(g);
    }

    private void drawDisplayedTower(Graphics g) {
        if (displayedTower != null) {
            g.setColor(Color.green);
            g.fillRect(410, 645, 220, 85);
            g.setColor(Color.black);
            g.drawRect(410, 645, 220, 85);
            g.drawRect(420, 650, 50, 50);
            g.drawImage(playing.getTowerHandler().getTowerImg()[displayedTower.getTowerType()], 420, 650, 50, 50, null);
            g.setFont(new Font("TimesRoman", Font.BOLD, 15));
            g.drawString(Towers.getName(displayedTower.getTowerType()), 490,660);
            g.drawString("ID: " + displayedTower.getId(), 490,675);
            
            drawHightlightOnTower(g);
        }
    }

    private void drawHightlightOnTower(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
        g.setColor(Color.RED);
        g.drawRect(displayedTower.getX() - 3, displayedTower.getY() -3, 38, 38);
    }

    public void displayTower(Tower tower) {
        displayedTower = tower;
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getLimits().contains(x, y))
            SetGameState(MENU);
        else {
            for (Bttn bttn : towerBttn)
                if (bttn.getLimits().contains(x, y)) {
                    selectedTower = new Tower(0, 0, -1, bttn.getId());
                    playing.setSelectedTower(selectedTower);
                    return;
                }

        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        for (Bttn bttn : towerBttn)
            bttn.setMouseOver(false);

        if (bMenu.getLimits().contains(x, y))
            bMenu.setMouseOver(true);
        else {
            for (Bttn bttn : towerBttn)
                if (bttn.getLimits().contains(x, y)) {
                    bttn.setMouseOver(true);
                    return;
                }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getLimits().contains(x, y))
            bMenu.setMousePressed(true);
        else {
            for (Bttn bttn : towerBttn)
                if (bttn.getLimits().contains(x, y)) {
                    bttn.setMousePressed(true);
                    return;
                }

        }
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBool();
        for (Bttn bttn : towerBttn)
            bttn.resetBool();
    }
}
