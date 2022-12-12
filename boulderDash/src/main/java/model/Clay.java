package model;

public class Clay extends Item {

    private Shape shape;

    public Clay(Position position) {
        super(position);
        this.shape = Shape.c;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

}

