package model;

// Models and returns the size of a coordinate in the snake game.
public class Coordinate {
    // size of cell in screen coordinates
    public static final int CELL_PIXELS = 30;

    private int coordinateX;
    private int coordinateY;

    // EFFECTS: Instantiates new coordinate.
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

    // EFFECTS: Checks if a given coordinate is equal in value to this coordinate.
    public boolean equals(Coordinate c) {
        if (this.getX() == c.getX() && this.getY() == c.getY()) {
            return true;
        }
        return false;
    }
}
