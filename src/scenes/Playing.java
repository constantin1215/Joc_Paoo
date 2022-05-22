package scenes;

import Observers.Observer;
import Observers.ObserverKill;
import Observers.ObserverScore;
import Observers.Subject;
import Other.Database;
import UI.ActionBar;
import com.company.Game;
import enemies.Enemy;
import handlers.EnemyHandler;
import handlers.ProjectileHandler;
import handlers.TowerHandler;
import handlers.WaveHandler;
import Other.LoadSave;
import objects.PathPoint;
import objects.Tower;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static Other.Constants.Enemies.*;
import static com.company.GameStates.GAME_OVER;
import static com.company.GameStates.SetGameState;
import static Other.Constants.Tiles.GRASS_TILE;


public class Playing extends GameScene implements SceneMethods, Subject {

    private int[][] lvl;

    private final ActionBar actionBar;
    private int mouseX, mouseY;
    private final EnemyHandler enemyHandler;
    private final TowerHandler towerHandler;
    private final ProjectileHandler projectileHandler;
    private final WaveHandler waveHandler;
    private PathPoint start, finish;
    private Tower selectedTower;
    private int lives;
    private int score;
    private int bank;
    private ArrayList<Observer> observers;
    private ObserverKill observerKill;
    private ObserverScore observerScore;

    public Playing(Game game) {
        super(game);
        this.lives = 15;
        this.score = 0;
        this.bank = 100;

        loadDefaultLvl();
        actionBar = new ActionBar(0, 640, 640, 160, this);
        enemyHandler = new EnemyHandler(this, start, finish);
        towerHandler = new TowerHandler(this);
        projectileHandler = new ProjectileHandler(this);
        waveHandler = new WaveHandler(this);
        observers = new ArrayList<>();
        observerKill = new ObserverKill();
        observerScore = new ObserverScore();
        attach(observerKill);
        attach(observerScore);
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
        projectileHandler.draw(g);
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
        projectileHandler.update();
    }

    public void lifeLost(int lifeDamage) {
        this.lives -= lifeDamage;

        if (lives <= 0) {
            SetGameState(GAME_OVER);
            addScoresToDatabase();
        }

    }

    private void addScoresToDatabase() {
        if (score > Database.getInstance().getDatabaseInfo().get(0))
            Database.getInstance().updateEntry("high_score", score);

        Database.getInstance().updateEntry("red_kills", Enemy.getDeaths(RED) + Database.getInstance().getDatabaseInfo().get(1));
        Database.getInstance().updateEntry("blue_kills", Enemy.getDeaths(BLUE) + Database.getInstance().getDatabaseInfo().get(2));
        Database.getInstance().updateEntry("green_kills", Enemy.getDeaths(GREEN) + Database.getInstance().getDatabaseInfo().get(3));
        Database.getInstance().updateEntry("ylw_kills", Enemy.getDeaths(YELLOW) + Database.getInstance().getDatabaseInfo().get(4));
    }

    public void spendMoney(int value) {
        this.bank -= value;
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
//        if (y<640) {
//            new Timer().schedule(new AddEnemyThread(enemyHandler), 0, 1000);
//        }
    }

    private Tower getTowerAt(int x, int y) {
        return towerHandler.getTowerAt(x, y);
    }

    private boolean isTileGrass(int x, int y) {
        int id = lvl[y / 32][x / 32];
        int tileType = getGame().getTileHandler().getTile(id).getType();

        return tileType == GRASS_TILE;
    }

    public void addToScore(int score) {
        this.score += score;
    }

    public void addToCoins(int money) {
        this.bank += money;
    }

    @Override
    public void attach(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void detach(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (Observer obs : observers)
            obs.update();
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

    public void shoot(Tower tower, Enemy enemy) {
        projectileHandler.createProjectile(tower, enemy);
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

    public EnemyHandler getEnemyHandler() {
        return enemyHandler;
    }

    public WaveHandler getWaveHandler() {
        return waveHandler;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public int getMoney() {
        return bank;
    }
}
