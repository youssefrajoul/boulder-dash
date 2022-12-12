package model;

public class Rock extends Item{
    private Shape shape;

    public Rock(Position position) {
        super(position);
        this.shape = Shape.r;
    }

    @Override
    public Shape getShape() {
        return shape;
    }
}
