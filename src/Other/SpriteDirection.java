package Other;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteDirection {
    public static BufferedImage getRotataedImg(BufferedImage img, int rotation) {
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage newImg = new BufferedImage(width, height, img.getType());
        Graphics2D g2d = newImg.createGraphics();

        g2d.rotate(Math.toRadians(rotation), width / 2, height / 2);
        g2d.drawImage(img, 0, 0, null);

        g2d.dispose();

        return newImg;
    }

    public static BufferedImage buildImg(BufferedImage[] images) {
        int width = images[0].getWidth();
        int height = images[0].getHeight();

        BufferedImage newImg = new BufferedImage(width, height, images[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for (BufferedImage img : images) {
            g2d.drawImage(img, 0, 0, null);
        }

        g2d.dispose();

        return newImg;
    }

    public static BufferedImage getBuildRotatedImg(BufferedImage[] images, int rotation, int imgToRotate) {
        int width = images[0].getWidth();
        int height = images[0].getHeight();

        BufferedImage newImg = new BufferedImage(width, height, images[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for (int i = 0; i < images.length; i++) {
            if (imgToRotate == i)
                g2d.rotate(Math.toRadians(rotation), width / 2, height / 2);
            g2d.drawImage(images[i], 0, 0, null);
            if (imgToRotate == i)
                g2d.rotate(Math.toRadians(-rotation), width / 2, height / 2);
        }

        g2d.dispose();

        return newImg;
    }

    public static BufferedImage[] getBuildRotatedImg(BufferedImage[] images, BufferedImage ground, int rotation) {
        int width = images[0].getWidth();
        int height = images[0].getHeight();

        BufferedImage[] arr = new BufferedImage[images.length];

        for (int i = 0; i < images.length; i++) {
            BufferedImage newImg = new BufferedImage(width, height, images[0].getType());
            Graphics2D g2d = newImg.createGraphics();

            g2d.drawImage(images[i], 0, 0, null);
            g2d.rotate(Math.toRadians(rotation), width / 2, height / 2);
            g2d.drawImage(ground, 0, 0, null);
            g2d.dispose();

            arr[i] = newImg;
        }

        return arr;
    }
}
