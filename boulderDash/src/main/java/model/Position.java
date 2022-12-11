package model;

public abstract class Position {
    private int x;
    private int y;
    private char shape;

    public Position(int x, int y, char shape){
        this.x = x;
        this.y = y;
        this.shape = shape;
    }

    public char getShape() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(int x, int y){
        this.x += x;
        this.y += y;
    }
}
