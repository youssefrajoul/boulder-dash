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

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public InputStream getLevelMap() {
        return levelMap;
    }
}
