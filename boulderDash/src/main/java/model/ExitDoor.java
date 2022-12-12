package model;

public class ExitDoor extends Item{
    private Shape shape;

    public ExitDoor(Position position) {
        super(position);
        this.shape = Shape.x;
    }

    @Override
    public Shape getShape() {
        return shape;
    }
}
