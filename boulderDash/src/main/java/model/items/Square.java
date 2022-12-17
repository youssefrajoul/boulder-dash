package model.items;

import model.items.Item;

import java.util.Objects;

/**
 * Represents the squares inside the board (20 squares * 39 squares)
 * & each square has an item inside
 */
public class Square {
    private Item item;

    public Square(Item item) {
        this.item = item;
    }

    /**
     * Gets the item that exists inside this square
     * @return
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets an item inside this square
     * @param item
     */
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
