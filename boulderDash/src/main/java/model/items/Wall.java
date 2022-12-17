package model.items;

import model.Position;

/**
 * Represents the Walls(surroundings) of the game
 */
public class Wall extends Item {

    private Shape shape;

    public Wall(Position position) {
        super(position);
        this.shape = Shape.w;
    }

    @Override
    public Shape getShape() {
        return shape;
    }
}
