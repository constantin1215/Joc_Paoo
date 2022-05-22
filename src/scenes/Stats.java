package scenes;

import Other.Database;
import UI.Bttn;
import com.company.Game;

import java.awt.*;
import java.util.ArrayList;

import static com.company.GameStates.MENU;
import static com.company.GameStates.SetGameState;

public class Stats extends GameScene implements SceneMethods {
    private Bttn bMenu;
    private final int y = 150;
    private final int offSet = 100;
    private ArrayList<Integer> scores;

    public Stats(Game game) {
        super(game);
        initButtons();
        scores = Database.getInstance().getDatabaseInfo();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(0, 0, 640, 800);
        drawButtons(g);
        drawStats(g);
    }

    private void drawStats(Graphics g) {
        int width = 640;
        String[] texte = {"" + scores.get(0), "Total red baloons poped: " + scores.get(1), "Total green baloons poped: " + scores.get(2), "Total blue baloons poped: " + scores.get(3), "Total yellow baloons poped: " + scores.get(4)};

        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));

        String text = "High Score: ";
        int txtWidth = g.getFontMetrics().stringWidth(text);
        g.drawString(text, (width - txtWidth) / 2, y);

        g.setColor(Color.black);
        for (int i = 0; i < texte.length; i++) {
            txtWidth = g.getFontMetrics().stringWidth(texte[i]);
            g.drawString(texte[i], (width - txtWidth) / 2, y + (i + 1) * offSet);
        }
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    private void initButtons() {
        bMenu = new Bttn("Menu", 5, 5, 100, 100 / 3);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getLimits().contains(x, y)) {
            SetGameState(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if (bMenu.getLimits().contains(x, y)) {
            bMenu.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bMenu.getLimits().contains(x, y)) {
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
