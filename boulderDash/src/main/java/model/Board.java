package model;

import model.items.Item;
import model.items.Shape;
import model.items.Square;

import java.util.Arrays;
import java.util.Objects;

public class Board {
    private Square[][] squares;

    public Board() {
        this.squares = new Square[20][39];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                squares[i][j] = new Square(null);
            }
        }
    }

    public void setItem(Item item, Position position){
        squares[position.getX()][position.getY()].setItem(item);
    }

    public Shape getShape(Position position) {
        return squares[position.getX()][position.getY()].getItem().getShape();
    }

    public Item getItem(Position position) {
        return squares[position.getX()][position.getY()].getItem();
    }
    public boolean isEmpty(Position position){
        return squares[position.getX()][position.getY()].getItem().getShape() == Shape.e;
    }

    public boolean isExitdoor(Position position){
        return squares[position.getX()][position.getY()].getItem().getShape() == Shape.x;
    }

    public boolean isRock(Position position){
        return squares[position.getX()][position.getY()].getItem().getShape() == Shape.r;
    }

    public boolean isDiamond(Position position){
        return squares[position.getX()][position.getY()].getItem().getShape() == Shape.d;
    }

    public boolean isRockford(Position position){
        return squares[position.getX()][position.getY()].getItem().getShape() == Shape.f;
    }

    public boolean isWall(Position position){
        return squares[position.getX()][position.getY()].getItem().getShape() == Shape.w;
    }

    public boolean isClay(Position position){
        return squares[position.getX()][position.getY()].getItem().getShape() == Shape.c;
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
