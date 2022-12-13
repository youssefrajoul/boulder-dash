package model;

public class Item {
    private Position position;
    private Shape shape;

    public Item(Position position) {
        this.position = position;
    }

    public Shape getShape() {
        return shape;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position.move(position);
    }
}
