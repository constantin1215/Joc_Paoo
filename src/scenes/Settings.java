package scenes;

import UI.Bttn;
import com.company.Game;

import java.awt.*;

import static com.company.GameStates.MENU;
import static com.company.GameStates.SetGameState;

public class Settings extends GameScene implements SceneMethods{
    private Bttn bMenu;
    public Settings(Game game) {
        super(game);
        initButtons();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(0,0,640,640);
        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    private void initButtons() {
        bMenu = new Bttn("Menu", 5,5,100,100/3);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getLimits().contains(x,y)) {
            SetGameState(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if (bMenu.getLimits().contains(x,y)) {
            bMenu.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bMenu.getLimits().contains(x,y)) {
            bMenu.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBool();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
