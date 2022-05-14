package objects;

public class PathPoint {
    private int x,y;
    public PathPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "PathPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
