package helperMethods;

public class Constants {
    public static class Towers {
        public static final int MNK_DARTS = 0;
        public static final int MNK_BOOMERANG = 1;
        public static final int MNK_WIZARD = 2;

        public static String getName(int type) {
            switch (type) {
                case MNK_DARTS -> {
                    return "Darts Monkey";
                }
                case MNK_BOOMERANG -> {
                    return "Boomerang Monkey";
                }
                case MNK_WIZARD -> {
                    return "Wizard Monkey";
                }
            }
            return "";
        }

        public static float getTowerDamage(int type) {
            switch (type) {
                case MNK_DARTS -> {
                    return 1;
                }
                case MNK_BOOMERANG -> {
                    return 2;
                }
                case MNK_WIZARD -> {
                    return 3;
                }
            }
            return 0;
        }

        public static int getTowerRange(int type) {
            switch (type) {
                case MNK_DARTS -> {
                    return 1;
                }
                case MNK_BOOMERANG -> {
                    return 2;
                }
                case MNK_WIZARD -> {
                    return 3;
                }
            }
            return 0;
        }

        public static float getTowerFireRate(int type) {
            switch (type) {
                case MNK_DARTS -> {
                    return 1;
                }
                case MNK_BOOMERANG -> {
                    return 2;
                }
                case MNK_WIZARD -> {
                    return 3;
                }
            }
            return 0;
        }
    }

    public static class Direction {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class Enemies {
        public static final int RED = 0;
        public static final int BLUE = 1;
        public static final int GREEN = 2;
        public static final int YELLOW = 3;

        public static float getEnemySpeed(int enemyType) {
            switch (enemyType) {
                case RED -> {
                    return 0.5f;
                }
                case BLUE -> {
                    return 0.6f;
                }
                case GREEN -> {
                    return 0.75f;
                }
                case YELLOW -> {
                    return 0.9f;
                }
            }
            return 0.1f;
        }

        public static int getStartHealth(int enemyType) {
            switch (enemyType) {
                case RED -> {
                    return 100;
                }
                case BLUE -> {
                    return 100;
                }
                case GREEN -> {
                    return 100;
                }
                case YELLOW -> {
                    return 100;
                }
            }

            return 0;
        }
    }

    public static class Tiles {
        public static final int WATER_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;
    }
}
