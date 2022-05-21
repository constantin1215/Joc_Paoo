package enemies;

import static helperMethods.Constants.Enemies.RED;

public class RedBaloon extends Enemy {
    private static final int deathCount = 0;

    public RedBaloon(float x, float y, int id) {
        super(x, y, id, RED);
    }

}
