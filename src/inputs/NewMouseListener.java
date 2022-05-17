package inputs;

import com.company.Game;
import com.company.GameStates;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class NewMouseListener implements MouseListener, MouseMotionListener {

    private final Game game;

    public NewMouseListener(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            switch (GameStates.gameStates) {
                case MENU -> game.getMenu().mouseClicked(e.getX(), e.getY());
                case PLAYING -> game.getPlaying().mouseClicked(e.getX(), e.getY());
                case EDIT -> game.getEdit().mouseClicked(e.getX(), e.getY());
                case SETTINGS -> game.getSettings().mouseClicked(e.getX(), e.getY());
                default -> {
                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameStates.gameStates) {
            case MENU -> game.getMenu().mousePressed(e.getX(), e.getY());
            case PLAYING -> game.getPlaying().mousePressed(e.getX(), e.getY());
            case EDIT -> game.getEdit().mousePressed(e.getX(), e.getY());
            case SETTINGS -> game.getSettings().mousePressed(e.getX(), e.getY());
            default -> {
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameStates.gameStates) {
            case MENU -> game.getMenu().mouseReleased(e.getX(), e.getY());
            case PLAYING -> game.getPlaying().mouseReleased(e.getX(), e.getY());
            case EDIT -> game.getEdit().mouseReleased(e.getX(), e.getY());
            case SETTINGS -> game.getSettings().mouseReleased(e.getX(), e.getY());
            default -> {
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (GameStates.gameStates) {
            case MENU -> game.getMenu().mouseDragged(e.getX(), e.getY());
            case PLAYING -> game.getPlaying().mouseDragged(e.getX(), e.getY());
            case EDIT -> game.getEdit().mouseDragged(e.getX(), e.getY());
            case SETTINGS -> game.getSettings().mouseDragged(e.getX(), e.getY());
            default -> {
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameStates.gameStates) {
            case MENU -> game.getMenu().mouseMoved(e.getX(), e.getY());
            case PLAYING -> game.getPlaying().mouseMoved(e.getX(), e.getY());
            case EDIT -> game.getEdit().mouseMoved(e.getX(), e.getY());
            case SETTINGS -> game.getSettings().mouseMoved(e.getX(), e.getY());
            default -> {
            }
        }
    }
}
