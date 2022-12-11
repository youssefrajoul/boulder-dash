package model;

public class Item {
    private Shape shape;
    private Position position;

    public Item(Shape shape, Position position){
        this.shape = shape;
        this.position = position;
    }

    public Shape getShape() {
        return shape;
    }

    public Position getPosition() {
        return position;
    }
}
