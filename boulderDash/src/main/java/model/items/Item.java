package model.items;

import model.Position;

import java.util.Objects;

public class Item {
    private Position position;
    private Shape shape;

    public Item(Position position) {
        this.position = position;
    }

    public Shape getShape() {
        return shape;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position.move(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(position, item.position) && shape == item.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, shape);
    }
}
