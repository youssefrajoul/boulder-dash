package Main;

import model.Board;
import model.Diamonds;
import model.FilePath;
import model.Level;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainTest {
    public static void main(String[] args) {
        Level level1 = new Level(1, FilePath.LEVEL1);
        Board board = new Board(level1);
        board.constructSquares();
        String direction = "";

        Scanner scan = new Scanner(System.in);
        int quit = 0;
        System.out.println("--------------Welcome to Boulder-Dash 1984--------------");

        while (quit == 0) {
            displayBoard(board);
            System.out.println("To Quit enter 1 / To continue enter 0");
            quit = scan.nextInt();
            direction = playerInputs();
            board.move(direction);
        }


    }
    public static void displayBoard(Board board){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                System.out.print(board.getSquaresAt(i, j));
            }
            System.out.println("");
        }
    }

    public static String playerInputs(){
        var pattern = Pattern.compile("[wasd]");
        String input = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("play using wasd keys w to move up, s down, a left, d right");
            input = br.readLine();
            while (!pattern.matcher(input).matches()){
                System.out.println("wrong input\nplease read & respect these instructions :");
                System.out.println("play using wasd keys w to move up, s down, a left, d right");
                input = br.readLine();
                System.out.println(); // to clean the buffer
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return input;
    }
}
