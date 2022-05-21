package scenes;

import UI.Bttn;
import com.company.Game;

import java.awt.*;

import static com.company.GameStates.*;

public class Menu extends GameScene implements SceneMethods {
    private Bttn bPlay, bEdit, bSettings, bQuit;

    public Menu(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 150;
        int offSet = 100;

        bPlay = new Bttn("Play", x, y, w, h);
        bEdit = new Bttn("Edit", x, y + offSet, w, h);
        bSettings = new Bttn("Stats", x, y + 2 * offSet, w, h);
        bQuit = new Bttn("Quit", x, y + 3 * offSet, w, h);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 640, 800);
        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        bPlay.draw(g);
        bEdit.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlay.getLimits().contains(x, y))
            SetGameState(PLAYING);
        else if (bEdit.getLimits().contains(x, y))
            SetGameState(EDIT);
        else if (bSettings.getLimits().contains(x, y))
            SetGameState(SETTINGS);
        else if (bQuit.getLimits().contains(x, y))
            System.exit(0);
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlay.setMouseOver(false);
        bEdit.setMouseOver(false);
        bSettings.setMouseOver(false);
        bQuit.setMouseOver(false);
        if (bPlay.getLimits().contains(x, y))
            bPlay.setMouseOver(true);
        else if (bEdit.getLimits().contains(x, y))
            bEdit.setMouseOver(true);
        else if (bSettings.getLimits().contains(x, y))
            bSettings.setMouseOver(true);
        else if (bQuit.getLimits().contains(x, y))
            bQuit.setMouseOver(true);
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bPlay.getLimits().contains(x, y))
            bPlay.setMousePressed(true);
        else if (bEdit.getLimits().contains(x, y))
            bEdit.setMousePressed(true);
        else if (bSettings.getLimits().contains(x, y))
            bSettings.setMousePressed(true);
        else if (bQuit.getLimits().contains(x, y))
            bQuit.setMousePressed(true);
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void resetButtons() {
        bPlay.resetBool();
        bEdit.resetBool();
        bSettings.resetBool();
        bQuit.resetBool();
    }
}
