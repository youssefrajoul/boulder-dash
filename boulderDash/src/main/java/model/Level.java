package model;

import java.io.*;
import java.util.*;


public class Level {


    public static void main(String[] args) {
        LevelFile lev = LevelFile.LEVEL1;
        char[][] squares = new char[20][39];
        try (var in = Level.class.getResourceAsStream(lev.getLevel())) {
            int i;
            int j = 0;
            int k = 0;
            while ((i = in.read()) != -1) {
                // 10 and 13 (in ascii table) are equivalent to \n
                if (i == 10) {
                    k=0;
                    j++;
                    continue;
                } else if (i == 13) {
                    continue;
                } else {
                    squares[j][k] = (char) i;
                    k++;
                }
            }
        } catch (IOException e) {
            System.out.println("error resources file level.txt");
        }
//        for (int k = 0; k < squares.length; k++) {
//            for (int l = 0; l < squares[k].length; l++) {
//                System.out.print(squares[k][l]);
//            }
//            System.out.println("");
//        }
    }
}
