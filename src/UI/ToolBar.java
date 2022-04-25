package UI;

import objects.Tile;
import scenes.Edit;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.company.GameStates.MENU;
import static com.company.GameStates.SetGameState;

public class ToolBar extends Bar{
    private Edit edit;
    private Bttn bMenu, bSave;
    private Tile selectedTile;

    private Map<Bttn, ArrayList<Tile>> map = new HashMap<Bttn,ArrayList<Tile>>();

    private Bttn bGrass, bWater, bRoad, bRoadC, bWaterC, bWaterB, bWaterI;
    private Bttn currentBtn;

    private int currentPosition = 0;

    public ToolBar(int x, int y, int width, int height, Edit edit) {
        super(x, y, width, height);
        this.edit = edit;

        initButtons();
    }

    private void initButtons() {
        bMenu = new Bttn("Menu", 5,645,100,100/3);
        bSave = new Bttn("Save", 5,685,100,100/3);

        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int offSet = (int)(w*1.1f);
        int i = 0;

        bGrass = new Bttn("Grass", xStart, yStart,w,h,i++);
        bWater = new Bttn("Water", xStart + offSet, yStart,w,h,i++);

        bindMapButton(bRoad, edit.getGame().getTileHandler().getRoads(),xStart, yStart,offSet,w,h,i++);
        bindMapButton(bRoadC, edit.getGame().getTileHandler().getRoadsC(),xStart, yStart,offSet,w,h,i++);
        bindMapButton(bWaterC, edit.getGame().getTileHandler().getWaterC(),xStart, yStart,offSet,w,h,i++);
        bindMapButton(bWaterB, edit.getGame().getTileHandler().getBeaches(),xStart, yStart,offSet,w,h,i++);
        bindMapButton(bWaterI, edit.getGame().getTileHandler().getIslands(),xStart, yStart,offSet,w,h,i++);
    }

    private void bindMapButton(Bttn b,ArrayList<Tile> list, int x, int y, int offSet, int w, int h, int id) {
        b = new Bttn("",x + offSet * id, y, w, h, id);
        map.put(b,list);
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
        g.setColor(new Color(156,81,39));
        g.fillRect(x,y,width,height);

        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bSave.draw(g);
        
        drawSimpleBtn(g, bGrass);
        drawSimpleBtn(g, bWater);
        drawSelectedTile(g);
        drawMapButtons(g);
    }

    private void drawSimpleBtn(Graphics g, Bttn btn) {
        g.drawImage(getBtnImg(btn.getId()),btn.x,btn.y,btn.width,btn.height,null);
        btnAnimation(g,btn);
    }

    private void drawMapButtons(Graphics g) {
        for (Map.Entry<Bttn, ArrayList<Tile>> entry : map.entrySet()) {
            Bttn btn = entry.getKey();
            BufferedImage img = entry.getValue().get(0).getSprite();

            g.drawImage(img, btn.x, btn.y, btn.width, btn.height, null);

            btnAnimation(g,btn);
        }
    }

    private void btnAnimation(Graphics g, Bttn btn) {
        if(btn.isMouseOver())
            g.setColor(Color.white);
        else
            g.setColor(Color.blue);

        g.drawRect(btn.x, btn.y, btn.width, btn.height);

        if (btn.isMousePressed()) {
            g.drawRect(btn.x + 1, btn.y + 1, btn.width-2, btn.height-2);
            g.drawRect(btn.x + 2, btn.y + 2, btn.width-4, btn.height-4);
        }
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null) {
            g.drawImage(selectedTile.getSprite(), 550, 650 , 50 ,50,null);
            g.setColor(Color.green);
            g.drawRect(550,650,50,50);
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
        for (Bttn btn : map.keySet())
            btn.setMouseOver(false);

        if (bMenu.getLimits().contains(x,y))
            bMenu.setMouseOver(true);
        else if(bSave.getLimits().contains(x,y))
            bSave.setMouseOver(true);
        else if(bGrass.getLimits().contains(x,y))
            bGrass.setMouseOver(true);
        else if(bWater.getLimits().contains(x,y))
            bWater.setMouseOver(true);
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
        if (bMenu.getLimits().contains(x,y))
            SetGameState(MENU);
        else if(bSave.getLimits().contains(x,y))
            saveLvl();
        else if(bGrass.getLimits().contains(x,y))
        {
            selectedTile = edit.getGame().getTileHandler().getTile(bGrass.getId());
            edit.setSelectedTile(selectedTile);
        }
        else if(bWater.getLimits().contains(x,y)) {
            selectedTile = edit.getGame().getTileHandler().getTile(bWater.getId());
            edit.setSelectedTile(selectedTile);
        }
        else {
            for (Bttn btn : map.keySet()) {
                if (btn.getLimits().contains(x,y)) {
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
        if (bMenu.getLimits().contains(x,y))
            bMenu.setMousePressed(true);
        else if(bSave.getLimits().contains(x,y))
            bSave.setMousePressed(true);
        else if(bGrass.getLimits().contains(x,y))
            bGrass.setMousePressed(true);
        else if(bWater.getLimits().contains(x,y))
            bWater.setMousePressed(true);
        else {
            for (Bttn btn : map.keySet())
                if (btn.getLimits().contains(x,y)) {
                    btn.setMousePressed(true);
                    return;
                }
        }
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBool();
        bSave.resetBool();
        bGrass.resetBool();
        bGrass.resetBool();
        for (Bttn btn : map.keySet())
            btn.resetBool();
    }
}
