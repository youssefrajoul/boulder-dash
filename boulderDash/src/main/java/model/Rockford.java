package model;

public class Rockford extends Item{

    private Shape shape;

    public Rockford(Position position) {
        super(position);
        this.shape = Shape.f;
    }

    @Override
    public Shape getShape() {
        return shape;
    }
}
