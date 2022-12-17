package model.items;

import model.Position;

/**
 * Represents the background(an empty square) in the game
 */
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
