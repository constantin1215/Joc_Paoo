package scenes;

import UI.Bttn;
import com.company.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static com.company.GameStates.*;

public class Menu extends GameScene implements SceneMethods{
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private Random random;

    private Bttn bPlay, bSettings, bQuit;

    public Menu(Game game) {
        super(game);
        random = new Random();
        try{
            importImg();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        loadSprites();
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w/3;
        int x = 640/2 - w/2;
        int y = 150;
        int offSet = 100;

        bPlay = new Bttn("Play", x,y,w,h);
        bSettings = new Bttn("Settings",x,y+offSet,w,h);
        bQuit = new Bttn("Quit",x,y+2*offSet,w,h);
    }

    @Override
    public void render(Graphics g) {
        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        bPlay.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
    }

    private void importImg() throws FileNotFoundException {
        InputStream is = new BufferedInputStream(new FileInputStream("resources/spriteatlas.png"));

        try {
            img = ImageIO.read(is);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSprites() {
        for (int i = 0;i < 10; i++) {
            for (int j = 0;j < 10; j++) {
                sprites.add(img.getSubimage(i*32,j*32,32,32));
            }
        }
    }

    private int getRndInt() {
        return random.nextInt(100);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlay.getLimits().contains(x,y)) {
            SetGameState(PLAYING);
        } else if (bSettings.getLimits().contains(x,y)) {
            SetGameState(SETTINGS);
        } else if (bQuit.getLimits().contains(x,y))
            System.exit(0);
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlay.setMouseOver(false);
        bSettings.setMouseOver(false);
        bQuit.setMouseOver(false);
        if (bPlay.getLimits().contains(x,y)) {
            bPlay.setMouseOver(true);
        } else if (bSettings.getLimits().contains(x,y)) {
            bSettings.setMouseOver(true);
        } else if (bQuit.getLimits().contains(x,y)) {
            bQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bPlay.getLimits().contains(x,y)) {
            bPlay.setMousePressed(true);
        } else if (bSettings.getLimits().contains(x,y)) {
            bSettings.setMousePressed(true);
        } else if (bQuit.getLimits().contains(x,y)) {
            bQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {
        bPlay.resetBool();
        bSettings.resetBool();
        bQuit.resetBool();
    }
}
