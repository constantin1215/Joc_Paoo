package scenes;

import UI.Bttn;
import com.company.Game;
import enemies.Enemy;

import java.awt.*;

public class GameOver extends GameScene implements SceneMethods {
    private Bttn bExit;
    private final int w = 150;
    private final int x = 640 / 2 - w / 2;
    private final int y = 150;
    private final int offSet = 80;

    public GameOver(Game game) {
        super(game);
        initButton();
    }

    private void drawButtons(Graphics g) {
        bExit.draw(g);
    }

    private void initButton() {
        int h = w / 3;
        bExit = new Bttn("Exit", x, y + 6 * offSet, w, h);
    }

    private void drawMessages(Graphics g) {

        int width = 640;

        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));

        String text = "GAME OVER";
        int txtWidth = g.getFontMetrics().stringWidth(text);
        g.drawString(text, width / 2 - txtWidth / 2, y);


        String[] texte = {"Score:" + getGame().getPlaying().getScore(), "Red Baloons killed:" + Enemy.getDeaths(0), "Green Baloons killed:" + Enemy.getDeaths(1),
                "Blue Baloons killed:" + Enemy.getDeaths(2), "Yellow Baloons killed:" + Enemy.getDeaths(3)};

        g.setColor(Color.black);

        for (int i = 0; i < texte.length; i++) {
            txtWidth = g.getFontMetrics().stringWidth(texte[i]);
            g.drawString(texte[i], width / 2 - txtWidth / 2, y + (i + 1) * offSet);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(0, 0, 640, 800);
        drawButtons(g);
        drawMessages(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bExit.getLimits().contains(x, y))
            System.exit(0);
    }

    @Override
    public void mouseMoved(int x, int y) {
        bExit.setMouseOver(false);
        if (bExit.getLimits().contains(x, y))
            bExit.setMouseOver(true);
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bExit.getLimits().contains(x, y))
            bExit.setMousePressed(true);
    }

    @Override
    public void mouseReleased(int x, int y) {
        bExit.resetBool();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
