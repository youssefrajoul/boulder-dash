package model.items;

import model.items.Item;

import java.util.Objects;

public class Square {
    private Item item;

    public Square(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Objects.equals(item, square.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }
}
