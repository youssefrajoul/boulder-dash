package model;

import model.items.Item;
import model.items.Shape;
import model.items.Square;

import java.util.Arrays;
import java.util.Stack;

/**
 * Represents the board of the game with squares and items inside
 */
public class Board {
    private Square[][] squares;
    private Stack<Item> movedItems;
    private Stack<Item> undoFx;
    private Stack<Item> redoFx;

    public Board() {
        this.squares = new Square[20][39];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                squares[i][j] = new Square(null);
            }
        }
        this.movedItems = new Stack<>();
        this.undoFx = new Stack<>();
        this.redoFx = new Stack<>();
    }

    /**
     * Puts Items on the board at the beginning of the game
     * @param item
     * @param position
     */
    public void createItem(Item item, Position position){
        squares[position.getX()][position.getY()].setItem(item);
    }

    /**
     * Puts Items on the board during the game
     * @param item
     * @param position
     */
    public void setItem(Item item, Position position){
        squares[position.getX()][position.getY()].setItem(item);
        //item.setPosition(new Position(position.getX(), position.getY()));
        saveMovedItems(item, position);
    }

    /**
     * Saves the item that changed position in a stack data structure
     * @param item
     * @param position
     */
    public void saveMovedItems(Item item, Position position){
        item.setPosition(new Position(position.getX(), position.getY()));
        movedItems.push(item);
        undoFx.push(item);
    }

    /**
     * Gets the stack movedItems that saves last moved items
     * @return
     */
    public Stack<Item> getMovedItems() {
        return movedItems;
    }

    /**
     * Gets the shape of an item in a specific position received as parameter on board
     * @param position
     * @return
     */
    public Shape getShape(Position position) {
        return squares[position.getX()][position.getY()].getItem().getShape();
    }

    /**
     * Gets the item that exits in specific position
     * @param position
     * @return
     */
    public Item getItem(Position position) {
        return squares[position.getX()][position.getY()].getItem();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Arrays.equals(squares, board.squares);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(squares);
    }
}
