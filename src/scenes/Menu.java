package scenes;

import UI.Bttn;
import com.company.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

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
        bPlay = new Bttn("Play", 100,100,100,30);
    }

    @Override
    public void render(Graphics g) {
        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        bPlay.draw(g);
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
}
