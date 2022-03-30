package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel {

    private Random random;
    private BufferedImage img;

    private Dimension size;

    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    public GameScreen(BufferedImage img) {
        this.img = img;

        setPanelSize();
        loadSprites();

        random = new Random();
    }

    private void setPanelSize() {
        size = new Dimension(640,640);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    private void loadSprites() {
        for (int i = 0;i < 10; i++) {
            for (int j = 0;j < 10; j++) {
                sprites.add(img.getSubimage(i*32,j*32,32,32));
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //g.drawImage(sprites.get(0), 0, 0,null);

        //BufferedImage orc = img.getSubimage(32*9,32,32,32);
        //g.drawImage(orc,0,0,null);

        for (int i = 0;i < 20;i++) {
            for (int j = 0;j < 20;j++) {
                g.drawImage(sprites.get(getRndInt()),i * 32,j * 32,null);
           }
        }

    }

    private int getRndInt() {
        return random.nextInt(100);
    }

    private Color gerRndColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        return new Color(r,g,b);
    }
}
