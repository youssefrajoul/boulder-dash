package Main;

import model.Board;
import model.FilePath;
import model.Level;

public class MainTest {
    public static void main(String[] args) {
        Level level1 = new Level(1, FilePath.LEVEL1);
        Board board1 = new Board(level1);
        //System.out.println(board1.getSquaresAt(2, 38));
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                System.out.print(board1.getSquaresAt(i, j)+ " ");
            }
            System.out.println("");
        }

    }
}
