package model.items;

import model.Position;

/**
 * Represents Exit door to the next level
 */
public class ExitDoor extends Item {
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
