package scenes;

import UI.ActionBar;
import com.company.Game;
import handlers.EnemyHandler;
import handlers.TowerHandler;
import helperMethods.LoadSave;
import objects.PathPoint;
import objects.Tower;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static helperMethods.Constants.Tiles.GRASS_TILE;


public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;

    private final ActionBar actionBar;
    private int mouseX, mouseY;
    private final EnemyHandler enemyHandler;
    private final TowerHandler towerHandler;
    private PathPoint start, finish;
    private Tower selectedTower;

    public Playing(Game game) {
        super(game);

        loadDefaultLvl();
        actionBar = new ActionBar(0, 640, 640, 160, this);
        enemyHandler = new EnemyHandler(this, start, finish);
        towerHandler = new TowerHandler(this);
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
        actionBar.draw(g);
        enemyHandler.draw(g);
        towerHandler.draw(g);
        drawSelectedTower(g);
        drawHighlighter(g);
    }

    private void drawHighlighter(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect(mouseX, mouseY, 32, 32);
    }

    private void drawSelectedTower(Graphics g) {
        if (selectedTower != null)
            g.drawImage(towerHandler.getTowerImg()[selectedTower.getTowerType()], mouseX, mouseY, null);
    }

    private void drawLvl(Graphics g) {
        for (int i = 0; i < lvl.length; i++) {
            for (int j = 0; j < lvl[i].length; j++) {
                int id = lvl[i][j];
                if (isAnimated(id)) {
                    g.drawImage(getSprite(id, animationIndex), j * 32, i * 32, null);
                } else
                    g.drawImage(getSprite(id), j * 32, i * 32, null);
            }
        }
    }

    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }

    public void update() {
        updateTick();
        enemyHandler.update();
        towerHandler.update();
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640)
            actionBar.mouseClicked(x, y);
        else {
            if (selectedTower != null) {
                if (isTileGrass(mouseX, mouseY)) {
                    if (getTowerAt(mouseX, mouseY) == null) {
                        towerHandler.addTower(selectedTower, mouseX, mouseY);
                        selectedTower = null;
                    }
                }
            } else {
                Tower tower = getTowerAt(mouseX, mouseY);
                actionBar.displayTower(tower);
            }
        }
//        else
//            enemyHandler.addEnemy(RED);
    }

    private Tower getTowerAt(int x, int y) {
        return towerHandler.getTowerAt(x, y);
    }

    private boolean isTileGrass(int x, int y) {
        int id = lvl[y / 32][x / 32];
        int tileType = getGame().getTileHandler().getTile(id).getType();

        return tileType == GRASS_TILE;
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640)
            actionBar.mouseMoved(x, y);
        else {
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            actionBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        actionBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    public int getTileType(int x, int y) {
        int X = x / 32;
        int Y = y / 32;

        if (X < 0 || X > 19)
            return 0;

        if (Y < 0 || Y > 19)
            return 0;

        int id = lvl[Y][X];
        return getGame().getTileHandler().getTile(id).getType();
    }

    public TowerHandler getTowerHandler() {
        return towerHandler;
    }

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            selectedTower = null;
    }
}
