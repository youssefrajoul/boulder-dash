package model;

import java.io.*;


public class Level {
    private int levelNumber;
    private FilePath filePath;
    private InputStream levelMap;

    public Level(int levelNumber, FilePath filePath){
        this.levelNumber = levelNumber;
        this.filePath = filePath;
        this.levelMap = Level.class.getResourceAsStream(filePath.getLevel());
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public InputStream getLevelMap() {
        return levelMap;
    }

    public static void main(String[] args) {
        FilePath lev = FilePath.LEVEL1;
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
    }
}
