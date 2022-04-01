package scenes;

import com.company.Game;
import handlers.TileHandler;
import helperMethods.LevelBuilder;

import java.awt.*;

public class Playing extends GameScene implements SceneMethods{

    private int[][] lvl;
    private TileHandler tileHandler;

    public Playing(Game game) {
        super(game);

        lvl = LevelBuilder.getLevelData();
        tileHandler = new TileHandler();
    }

    @Override
    public void render(Graphics g) {
        for (int y = 0; y < lvl.length; y++){
            for (int x = 0; x < lvl.length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileHandler.getSprite(id),x*32,y*32,null);
            }
        }
    }
}
