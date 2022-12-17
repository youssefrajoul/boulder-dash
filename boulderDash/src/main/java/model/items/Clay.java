package model.items;

import model.Position;

/**
 * Represents the clay or dirt in the game
 */
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

