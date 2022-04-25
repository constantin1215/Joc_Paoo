package inputs;

import com.company.Game;
import com.company.GameStates;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.company.GameStates.*;

public class KeyboardListener implements KeyListener {
    private Game game;
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
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
