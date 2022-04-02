package scenes;

import UI.BottomBar;
import UI.Bttn;
import com.company.Game;
import handlers.TileHandler;
import helperMethods.LevelBuilder;

import java.awt.*;

import static com.company.GameStates.*;

public class Playing extends GameScene implements SceneMethods{

    private int[][] lvl;
    private TileHandler tileHandler;


    private BottomBar bottomBar;

    public Playing(Game game) {
        super(game);

        lvl = LevelBuilder.getLevelData();
        tileHandler = new TileHandler();
        bottomBar = new BottomBar(0,640,640,100, this);
    }

    public TileHandler getTileHandler() {
        return tileHandler;
    }

    @Override
    public void render(Graphics g) {
        for (int y = 0; y < lvl.length; y++){
            for (int x = 0; x < lvl.length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileHandler.getSprite(id),x*32,y*32,null);
            }
        }
        bottomBar.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            bottomBar.mouseClicked(x,y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            bottomBar.mouseMoved(x,y);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            bottomBar.mousePressed(x,y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bottomBar.mouseReleased(x,y);
    }
}
