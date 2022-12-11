package model;

public enum FilePath {
    LEVEL1("/level1.txt"), LEVEL2("/level2.txt"), LEVEL3("/level3.txt"), LEVEL4("/level4.txt"), LEVEL5("/level5.txt"), LEVEL6("/level6.txt");

    private final String level;

    private FilePath(String level){
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
