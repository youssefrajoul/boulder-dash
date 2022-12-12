package model;

public class Empty extends Item {
    private Shape shape;

    public Empty(Position position) {
        super(position);
        this.shape = Shape.e;
    }

    @Override
    public Shape getShape() {
        return shape;
    }
}
