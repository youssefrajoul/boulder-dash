package model;

/**
 * Represents a position of an item on the board
 */
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the row where that position is on the board
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the row of this position as parameter received
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the column where that position is on the board
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the column of this position as parameter received
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Moves the position
     * @param position
     */
    public void move(Position position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    @Override
    public String toString() {
        return "Position{" +"x=" + x +", y=" + y +'}';
    }
}
