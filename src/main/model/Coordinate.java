package model;

public class Coordinate {

    // fields
    private int coordinateX;
    private int coordinateY;

    public Coordinate(int x, int y) {
        coordinateX = x;
        coordinateY = y;
    }

    public int getX() {
        return coordinateX;
    }

    public int getY() {
        return coordinateY;
    }

    public boolean equals(Coordinate c) {
        if (this.getX() == c.getX() && this.getY() == c.getY()) {
            return true;
        }
        return false;
    }
}
