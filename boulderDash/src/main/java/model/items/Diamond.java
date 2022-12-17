package model.items;

import model.Position;

/**
 * Represents Diamond in the game
 */
public class Diamond extends Item {
    private Shape shape;
    private boolean claimed;

    public Diamond(Position position) {
        super(position);
        this.shape = Shape.d;
        this.claimed = false;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

}
