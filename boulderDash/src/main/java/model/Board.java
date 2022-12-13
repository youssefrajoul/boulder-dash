package model;

public class Board {
    private Square[][] squares;
    private Level level;

    public Board(Level level) {
        this.squares = new Square[20][39];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                squares[i][j] = new Square(null);
            }
        }
        this.level = level;
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
}
