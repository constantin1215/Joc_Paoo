package UI;

import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.company.GameStates.MENU;
import static com.company.GameStates.SetGameState;
import static helperMethods.Constants.Towers;

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
        String[] prices = {String.valueOf(Towers.getTowerCost(Towers.MNK_DARTS)), String.valueOf(Towers.getTowerCost(Towers.MNK_BOOMERANG)), String.valueOf(Towers.getTowerCost(Towers.MNK_WIZARD))};
        int i = 0;
        bMenu.draw(g);

        for (Bttn bttn : towerBttn) {
            g.setColor(Color.green);
            g.fillRect(bttn.x, bttn.y, bttn.width, bttn.height);
            g.drawImage(playing.getTowerHandler().getTowerImg()[bttn.getId()], bttn.x, bttn.y, bttn.width, bttn.height, null);
            btnAnimation(g, bttn);
            g.setColor(Color.black);
            g.setFont(new Font("TimesRoman", Font.BOLD, 15));
            int txtW = g.getFontMetrics().stringWidth(prices[i]);
            g.drawString(prices[i], bttn.x + (bttn.width - txtW) / 2, (int) (bttn.y + 1.3 * bttn.height));
            i++;
        }

    }

    public BufferedImage getBtnImg(int id) {
        return playing.getGame().getTileHandler().getSprite(id);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(156, 81, 39));
        g.fillRect(x, y, width, height);

        drawButtons(g);
        drawInfo(g);

        drawDisplayedTower(g);
    }

    private void drawInfo(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.drawString("Lives: " + playing.getLives(), 5, 700);
        g.drawString("Coins: " + playing.getMoney(), 5, 725);
        g.drawString("Wave: " + (playing.getWaveHandler().getWaveI() + 1), 5, 750);
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
            g.drawString(Towers.getName(displayedTower.getTowerType()), 490, 660);
            g.drawString("ID: " + displayedTower.getId(), 490, 675);
            g.drawString("Cost: " + displayedTower.getCost(), 420, 720);

            drawHightlightOnTower(g);
            drawRange(g);
        }
    }

    private void drawRange(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawOval(displayedTower.getX() + 16 - displayedTower.getRange(), displayedTower.getY() + 16 - displayedTower.getRange(), displayedTower.getRange() * 2, displayedTower.getRange() * 2);
    }

    private void drawHightlightOnTower(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
        g.setColor(Color.RED);
        g.drawRect(displayedTower.getX() - 3, displayedTower.getY() - 3, 38, 38);
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
