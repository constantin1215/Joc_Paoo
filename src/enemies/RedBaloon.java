package enemies;

import static helperMethods.Constants.Enemies.RED;

public class RedBaloon extends Enemy {
    public RedBaloon(float x, float y, int id) {
        super(x, y, id, RED);
        health = 50;
    }
}
