package helperMethods;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class LoadSave {
    public static BufferedImage getSpriteAtlas() throws FileNotFoundException {

        BufferedImage img = null;
        InputStream is = new BufferedInputStream(new FileInputStream("resources/spriteatlas.png"));

        try {
            img = ImageIO.read(is);
        }catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }
}
