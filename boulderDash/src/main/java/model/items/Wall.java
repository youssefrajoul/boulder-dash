package model.items;

import model.Position;
import model.items.Item;
import model.items.Shape;

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
