package scenes;

import com.company.Game;

import java.awt.image.BufferedImage;

public class GameScene {

    private final Game game;

    protected int animationIndex;
    protected int tick;
    protected int ANIMATION_SPEED = 60;

    public GameScene(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    protected void updateTick() {
        tick++;
        if (tick >= ANIMATION_SPEED) {
            tick = 0;
            animationIndex++;
            if (animationIndex >= 4)
                animationIndex = 0;
        }
    }

    protected boolean isAnimated(int id) {
        return getGame().getTileHandler().isAnimated(id);
    }

    protected BufferedImage getSprite(int id) {
        return getGame().getTileHandler().getSprite(id);
    }

    protected BufferedImage getSprite(int id, int animationIndex) {
        return getGame().getTileHandler().getAnimatedSpriteAtIndex(id, animationIndex);
    }
}
