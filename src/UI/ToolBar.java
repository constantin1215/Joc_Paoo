package UI;

import helperMethods.LoadSave;
import objects.Tile;
import scenes.Edit;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.company.GameStates.MENU;
import static com.company.GameStates.SetGameState;

public class ToolBar extends Bar {
    private final Edit edit;
    private Bttn bMenu, bSave;
    private Bttn bStart, bFinish;
    private BufferedImage imgStart, imgFinish;
    private Tile selectedTile;

    private final Map<Bttn, ArrayList<Tile>> map = new HashMap<Bttn, ArrayList<Tile>>();

    private Bttn bGrass, bWater, bRoad, bRoadC, bWaterC, bWaterB, bWaterI;
    private Bttn currentBtn;

    private int currentPosition = 0;

    public ToolBar(int x, int y, int width, int height, Edit edit) {
        super(x, y, width, height);
        this.edit = edit;
        initStartFinishImg();
        initButtons();
    }

    private void initStartFinishImg() {
        imgStart = LoadSave.getSpriteAtlas().getSubimage(7 * 32, 2 * 32, 32, 32);
        imgFinish = LoadSave.getSpriteAtlas().getSubimage(8 * 32, 2 * 32, 32, 32);
    }

    private void initButtons() {
        bMenu = new Bttn("Menu", 5, 645, 100, 100 / 3);
        bSave = new Bttn("Save", 5, 685, 100, 100 / 3);

        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int offSet = (int) (w * 1.1f);
        int i = 0;

        bGrass = new Bttn("Grass", xStart, yStart, w, h, i++);
        bWater = new Bttn("Water", xStart + offSet, yStart, w, h, i++);

        bindMapButton(bRoad, edit.getGame().getTileHandler().getRoads(), xStart, yStart, offSet, w, h, i++);
        bindMapButton(bRoadC, edit.getGame().getTileHandler().getRoadsC(), xStart, yStart, offSet, w, h, i++);
        bindMapButton(bWaterC, edit.getGame().getTileHandler().getWaterC(), xStart, yStart, offSet, w, h, i++);
        bindMapButton(bWaterB, edit.getGame().getTileHandler().getBeaches(), xStart, yStart, offSet, w, h, i++);
        bindMapButton(bWaterI, edit.getGame().getTileHandler().getIslands(), xStart, yStart, offSet, w, h, i++);
        bStart = new Bttn("Start", xStart, yStart + offSet, w, h, i++);
        bFinish = new Bttn("Finish", xStart + offSet, yStart + offSet, w, h, i);
    }

    private void bindMapButton(Bttn b, ArrayList<Tile> list, int x, int y, int offSet, int w, int h, int id) {
        b = new Bttn("", x + offSet * id, y, w, h, id);
        map.put(b, list);
    }

    private void saveLvl() {
        edit.saveLvl();
    }

    public void rotateSprite() {
        currentPosition++;
        if (currentPosition >= map.get(currentBtn).size())
            currentPosition = 0;
        selectedTile = map.get(currentBtn).get(currentPosition);
        edit.setSelectedTile(selectedTile);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(156, 81, 39));
        g.fillRect(x, y, width, height);

        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bSave.draw(g);

        drawPathBtn(g, bStart, imgStart);
        drawPathBtn(g, bFinish, imgFinish);

        drawSimpleBtn(g, bGrass);
        drawSimpleBtn(g, bWater);
        drawSelectedTile(g);
        drawMapButtons(g);
    }

    private void drawPathBtn(Graphics g, Bttn btn, BufferedImage img) {
        g.drawImage(img, btn.x, btn.y, btn.width, btn.height, null);
        btnAnimation(g, btn);
    }

    private void drawSimpleBtn(Graphics g, Bttn btn) {
        g.drawImage(getBtnImg(btn.getId()), btn.x, btn.y, btn.width, btn.height, null);
        btnAnimation(g, btn);
    }

    private void drawMapButtons(Graphics g) {
        for (Map.Entry<Bttn, ArrayList<Tile>> entry : map.entrySet()) {
            Bttn btn = entry.getKey();
            BufferedImage img = entry.getValue().get(0).getSprite();

            g.drawImage(img, btn.x, btn.y, btn.width, btn.height, null);

            btnAnimation(g, btn);
        }
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null) {
            g.drawImage(selectedTile.getSprite(), 550, 650, 50, 50, null);
            g.setColor(Color.green);
            g.drawRect(550, 650, 50, 50);
        }
    }

    public BufferedImage getBtnImg(int id) {
        return edit.getGame().getTileHandler().getSprite(id);
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bSave.setMouseOver(false);
        bGrass.setMouseOver(false);
        bWater.setMouseOver(false);
        bStart.setMouseOver(false);
        bFinish.setMouseOver(false);
        for (Bttn btn : map.keySet())
            btn.setMouseOver(false);

        if (bMenu.getLimits().contains(x, y))
            bMenu.setMouseOver(true);
        else if (bSave.getLimits().contains(x, y))
            bSave.setMouseOver(true);
        else if (bGrass.getLimits().contains(x, y))
            bGrass.setMouseOver(true);
        else if (bWater.getLimits().contains(x, y))
            bWater.setMouseOver(true);
        else if (bStart.getLimits().contains(x, y))
            bStart.setMouseOver(true);
        else if (bFinish.getLimits().contains(x, y))
            bFinish.setMouseOver(true);
        else {
            for (Bttn btn : map.keySet()) {
                if (btn.getLimits().contains(x, y)) {
                    btn.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getLimits().contains(x, y))
            SetGameState(MENU);
        else if (bSave.getLimits().contains(x, y))
            saveLvl();
        else if (bGrass.getLimits().contains(x, y)) {
            selectedTile = edit.getGame().getTileHandler().getTile(bGrass.getId());
            edit.setSelectedTile(selectedTile);
        } else if (bWater.getLimits().contains(x, y)) {
            selectedTile = edit.getGame().getTileHandler().getTile(bWater.getId());
            edit.setSelectedTile(selectedTile);
        } else if (bStart.getLimits().contains(x, y)) {
            selectedTile = new Tile(imgStart, -1, -1);
            edit.setSelectedTile(selectedTile);
        } else if (bFinish.getLimits().contains(x, y)) {
            selectedTile = new Tile(imgFinish, -2, -2);
            edit.setSelectedTile(selectedTile);
        } else {
            for (Bttn btn : map.keySet()) {
                if (btn.getLimits().contains(x, y)) {
                    selectedTile = map.get(btn).get(0);
                    edit.setSelectedTile(selectedTile);
                    currentBtn = btn;
                    currentPosition = 0;
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getLimits().contains(x, y))
            bMenu.setMousePressed(true);
        else if (bSave.getLimits().contains(x, y))
            bSave.setMousePressed(true);
        else if (bGrass.getLimits().contains(x, y))
            bGrass.setMousePressed(true);
        else if (bWater.getLimits().contains(x, y))
            bWater.setMousePressed(true);
        else if (bStart.getLimits().contains(x, y))
            bStart.setMousePressed(true);
        else if (bFinish.getLimits().contains(x, y))
            bFinish.setMousePressed(true);
        else {
            for (Bttn btn : map.keySet())
                if (btn.getLimits().contains(x, y)) {
                    btn.setMousePressed(true);
                    return;
                }
        }
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBool();
        bSave.resetBool();
        bGrass.resetBool();
        bWater.resetBool();
        bStart.resetBool();
        bFinish.resetBool();
        for (Bttn btn : map.keySet())
            btn.resetBool();
    }

    public BufferedImage getImgStart() {
        return imgStart;
    }

    public void setImgStart(BufferedImage imgStart) {
        this.imgStart = imgStart;
    }

    public BufferedImage getImgFinish() {
        return imgFinish;
    }

    public void setImgFinish(BufferedImage imgFinish) {
        this.imgFinish = imgFinish;
    }
}
