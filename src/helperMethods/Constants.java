package helperMethods;

public class Constants {
    public static class Projectiles {
        public static final int DART = 0;
        public static final int BOOMERANG = 1;
        public static final int FIREBALL = 2;

        public static float getSpeed(int type) {
            switch (type) {
                case DART -> {
                    return 3;
                }
                case BOOMERANG -> {
                    return 2;
                }
                case FIREBALL -> {
                    return 1;
                }
            }
            return 0;
        }
    }

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

        public static int getTowerDamage(int type) {
            switch (type) {
                case MNK_DARTS -> {
                    return 25;
                }
                case MNK_BOOMERANG -> {
                    return 40;
                }
                case MNK_WIZARD -> {
                    return 60;
                }
            }
            return 0;
        }

        public static int getTowerRange(int type) {
            switch (type) {
                case MNK_DARTS -> {
                    return 100;
                }
                case MNK_BOOMERANG -> {
                    return 120;
                }
                case MNK_WIZARD -> {
                    return 80;
                }
            }
            return 0;
        }

        public static int getTowerFireRate(int type) {
            switch (type) {
                case MNK_DARTS -> {
                    return 30;
                }
                case MNK_BOOMERANG -> {
                    return 40;
                }
                case MNK_WIZARD -> {
                    return 60;
                }
            }
            return 0;
        }

        public static int getTowerCost(int type) {
            switch (type) {
                case MNK_DARTS -> {
                    return 25;
                }
                case MNK_BOOMERANG -> {
                    return 50;
                }
                case MNK_WIZARD -> {
                    return 100;
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
            return 0;
        }

        public static int getStartHealth(int enemyType) {
            switch (enemyType) {
                case RED -> {
                    return 100;
                }
                case BLUE -> {
                    return 110;
                }
                case GREEN -> {
                    return 120;
                }
                case YELLOW -> {
                    return 130;
                }
            }

            return 0;
        }

        public static int getLifeDamage(int enemyType) {
            switch (enemyType) {
                case RED -> {
                    return 1;
                }
                case BLUE -> {
                    return 2;
                }
                case GREEN -> {
                    return 3;
                }
                case YELLOW -> {
                    return 4;
                }
            }

            return 0;
        }

        public static int getScoreValue(int enemyType) {
            switch (enemyType) {
                case RED -> {
                    return 10;
                }
                case BLUE -> {
                    return 20;
                }
                case GREEN -> {
                    return 30;
                }
                case YELLOW -> {
                    return 40;
                }
            }

            return 0;
        }

        public static int getCoins(int enemyType) {
            switch (enemyType) {
                case RED -> {
                    return 2;
                }
                case BLUE -> {
                    return 4;
                }
                case GREEN -> {
                    return 6;
                }
                case YELLOW -> {
                    return 8;
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
