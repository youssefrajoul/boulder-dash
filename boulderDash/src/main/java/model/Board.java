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
    }

    public void constructSquares() {
        int fileData;
        int i = 0;//i
        int j = 0;//j
        try {
            while ((fileData = this.level.getLevelMap().read()) != -1) {
                // 10 and 13 (in ascii-code) are equivalent to \n (new Line)
                if (fileData == 10) {
                    j = 0;
                    i++;
                    continue;
                } else {
                    switch (fileData) {
                        case 102:
                            rockford = new Rockford(new Position(i, j));
                            squares[i][j].setItem(rockford);
                            break;
                        case 99:
                            squares[i][j].setItem(new Clay(new Position(i, j)));
                            break;
                        case 100:
                            squares[i][j].setItem(new Diamonds(new Position(i, j)));
                            break;
                        case 101:
                            squares[i][j].setItem(new Empty(new Position(i, j)));
                            break;
                        case 114:
                            squares[i][j].setItem(new Rock(new Position(i, j)));
                            break;
                        case 119:
                            squares[i][j].setItem(new Wall(new Position(i, j)));
                            break;
                        case 120:
                            squares[i][j].setItem(new ExitDoor(new Position(i, j)));
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


    public void move(String direction) {
        Position currentPos = rockford.getPosition();
        Position nextPos;
        switch (direction) {
            //QWERTY US Keyboard
            case "w":
                nextPos = new Position(currentPos.getX() - 1, currentPos.getY());
                break;
            case "a":
                nextPos = new Position(currentPos.getX(), currentPos.getY() - 1);
                break;
            case "s":
                nextPos = new Position(currentPos.getX() + 1, currentPos.getY());
                break;
            case "d":
                nextPos = new Position(currentPos.getX(), currentPos.getY() + 1);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }

        Empty e = new Empty(currentPos);
        squares[currentPos.getX()][currentPos.getY()].setItem(e);
        rockford.setPosition(nextPos);
        squares[nextPos.getX()][nextPos.getY()].setItem(rockford);
    }

    public Shape getSquaresAt(int x, int y) {
        return squares[x][y].getItem().getShape();
    }
}
