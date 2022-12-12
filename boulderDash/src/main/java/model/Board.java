package model;

public class Board {
    private Square[][] squares;
    private Level level;
    private Rockford rockford;

    public Board(Level level) {
        this.squares = new Square[20][39];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                squares[i][j] = new Square(null);
            }
        }
        this.level = level;
        int fileData;
        int i = 0;//i
        int j = 0;//j
        try {
            while ((fileData = this.level.getLevelMap().read()) != -1) {
                // 10 and 13 (in ascii-code) are equivalent to \n
                if (fileData == 10) {
                    j = 0;
                    i++;
                    continue;
                } else {
                    switch (fileData) {
                        case 112:
                            rockford = new Rockford(Shape.p, new Position(i, j));
                            squares[i][j].setItem(rockford);
                            break;
                        case 99:
                            squares[i][j].setItem(new Clay(Shape.c, new Position(i, j)));
                            break;
                        case 100:
                            squares[i][j].setItem(new Diamonds(Shape.d, new Position(i, j)));
                            break;
                        case 101:
                            squares[i][j].setItem(new Empty(Shape.e, new Position(i, j)));
                            break;
                        case 114:
                            squares[i][j].setItem(new Rock(Shape.r, new Position(i, j)));
                            break;
                        case 119:
                            squares[i][j].setItem(new Wall(Shape.w, new Position(i, j)));
                            break;
                        case 120:
                            squares[i][j].setItem(new ExitDoor(Shape.x, new Position(i, j)));
                            break;
                    }
                    j++;
                }
            }
        } catch (Exception e) {
            System.out.println("error resources file level.txt");
        }
    }

    public Square[][] getSquares() {
        return squares;
    }

    public void move(int x, int y){
        Position currentPos = rockford.getPosition();
        Position nextPos = new Position(x, y);
        Empty e = new Empty(Shape.e, currentPos);
        squares[currentPos.getX()][currentPos.getY()].setItem(e);
        rockford.setPosition(nextPos);
        squares[x][y].setItem(rockford);
    }

    public Shape getSquaresAt(int x, int y) {
        return squares[x][y].getItem().getShape();
    }
}
