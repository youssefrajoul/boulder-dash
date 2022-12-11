package model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private Position[][] squares;
    private Level level;

    public Board(Level level) {
        this.squares = new Position[20][39];
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
//                } else if (fileData == 13) {
//                    continue;
                } else {
                    switch (fileData) {
                        case 112:
                            squares[i][j] = new Player(i, j);
                            break;
                        case 99:
                            squares[i][j] = new Clay(i, j);
                            break;
                        case 100:
                            squares[i][j] = new Diamonds(i, j);
                            break;
                        case 101:
                            squares[i][j] = new Empty(i, j);
                            break;
                        case 114:
                            squares[i][j] = new Rock(i, j);
                            break;
                        case 119:
                            squares[i][j] = new Wall(i, j);
                            break;
                        case 120:
                            squares[i][j] = new ExitDoor(i, j);
                            break;
                    }
                    j++;
                }
            }
        } catch (Exception e) {
            System.out.println("error resources file level.txt");
        }
    }

    public Position[][] getSquares() {
        return squares;
    }

    public char getSquaresAt(int x, int y) {
        return squares[x][y].getShape();
    }
}
