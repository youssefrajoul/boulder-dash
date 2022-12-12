package Main;

import model.Board;
import model.Diamonds;
import model.FilePath;
import model.Level;

public class MainTest {
    public static void main(String[] args) {
        Level level1 = new Level(1, FilePath.LEVEL1);
        Board board = new Board(level1);
        //System.out.println(board.getSquaresAt(2, 38));
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                System.out.print(board.getSquaresAt(i, j)+ " ");
            }
            System.out.println("");
        }
        board.move(2, 4);
        System.out.println("*****************************************************************************");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                System.out.print(board.getSquaresAt(i, j)+ " ");
            }
            System.out.println("");
        }
        board.move(2, 5);
        System.out.println("*****************************************************************************");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                System.out.print(board.getSquaresAt(i, j)+ " ");
            }
            System.out.println("");
        }
        board.move(2, 6);
        System.out.println("*****************************************************************************");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                System.out.print(board.getSquaresAt(i, j)+ " ");
            }
            System.out.println("");
        }
        board.move(3, 6);
        System.out.println("*****************************************************************************");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                System.out.print(board.getSquaresAt(i, j)+ " ");
            }
            System.out.println("");
        }
        board.move(3, 7);
        System.out.println("*****************************************************************************");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                System.out.print(board.getSquaresAt(i, j)+ " ");
            }
            System.out.println("");
        }
    }
}
