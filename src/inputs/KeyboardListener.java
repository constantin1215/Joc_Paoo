package inputs;

import com.company.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.company.GameStates.*;

public class KeyboardListener implements KeyListener {
    private final Game game;

    public KeyboardListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameStates == EDIT) {
            game.getEdit().keyPressed(e);
        } else if (gameStates == PLAYING) {
            game.getPlaying().keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
