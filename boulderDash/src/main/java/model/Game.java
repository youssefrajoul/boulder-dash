package model;

import java.util.ArrayList;

public class Game {
    private Level level;
    private Board board;
    private Rockford rockford;
    ArrayList<Rock> rockList;
    ArrayList<Diamonds> diamondsList;
    private int score;
    private boolean gameOver;

    public Game() {
        level = new Level(1, FilePath.LEVEL1);
        this.board = new Board(level);
        rockList = new ArrayList<>();
        diamondsList = new ArrayList<>();
        this.score = 0;
        this.gameOver = false;
    }

    public void start() {
        int fileData;
        int i = 0;
        int j = 0;
        try {
            while ((fileData = this.level.getLevelMap().read()) != -1) {
                // 10 and 13 (in ascii-code) are equivalent to \n (new Line)
                if (fileData == 10) {
                    j = 0;
                    i++;
                } else {
                    Position pos = new Position(i, j);
                    switch (fileData) {
                        case 102:
                            this.rockford = new Rockford(pos);
                            board.setItem(rockford, pos);
                            break;
                        case 99:
                            board.setItem(new Clay(pos), pos);
                            break;
                        case 100:
                            board.setItem(new Diamonds(pos), pos);
                            diamondsList.add((Diamonds) board.getItem(pos));
                            break;
                        case 101:
                            board.setItem(new Empty(pos), pos);
                            break;
                        case 114:
                            board.setItem(new Rock(pos), pos);
                            rockList.add((Rock) board.getItem(pos));
                            break;
                        case 119:
                            board.setItem(new Wall(pos), pos);
                            break;
                        case 120:
                            board.setItem(new ExitDoor(pos), pos);
                            break;
                    }
                    j++;
                }
            }
        } catch (Exception e) {
            System.out.println("error resources file level.txt");
        }
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
        if (checkNextPos(nextPos)) {
            if (board.getShape(nextPos) == Shape.d) this.score++;
            Empty e = new Empty(currentPos);
            board.setItem(e, currentPos);
            rockford.setPosition(nextPos);
            board.setItem(rockford, nextPos);
        }
        moveRocks();
        moveRocksDiagonal();
        moveDiamonds();
        moveDiamondsDiagonal();
    }


    private boolean checkNextPos(Position position) {
        return board.getShape(position) != Shape.r && board.getShape(position) != Shape.w;
    }

    public void moveRocks(){
        for (int i = 0; i < rockList.size(); i++) {
            Position currentPos = rockList.get(i).getPosition();
            Position nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());
            Position nextPosSUDSUD = new Position(currentPos.getX() + 2, currentPos.getY());
            while (board.getShape(nextPosSUD) == Shape.e){
                Empty empty = new Empty(currentPos);
                if (board.getShape(nextPosSUDSUD) == Shape.f) {
                    gameOver = true;
                    board.setItem(empty, currentPos);
                    rockList.get(i).setPosition(nextPosSUDSUD);
                    board.setItem(rockList.get(i), nextPosSUDSUD);
                    break;
                }
                board.setItem(empty, currentPos);
                rockList.get(i).setPosition(nextPosSUD);
                board.setItem(rockList.get(i), nextPosSUD);
                // Reassign positions
                currentPos = rockList.get(i).getPosition();
                nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());
            }
        }
    }

    public void moveRocksDiagonal(){
        for (int i = 0; i < rockList.size(); i++) {
            Position currentPos = rockList.get(i).getPosition();
            Position nextPosWEST = new Position(currentPos.getX(), currentPos.getY() - 1);
            Position nextPosSUDWEST = new Position(currentPos.getX() + 1, currentPos.getY() - 1);
            Position nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());

            while (board.getShape(nextPosWEST) == Shape.e && board.getShape(nextPosSUDWEST) == Shape.e
                    && ((board.getShape(nextPosSUD)) == Shape.d || (board.getShape(nextPosSUD)) == Shape.r)){
                Empty empty = new Empty(currentPos);
                board.setItem(empty, currentPos);
                rockList.get(i).setPosition(nextPosSUDWEST);
                board.setItem(rockList.get(i), nextPosSUDWEST);
                // Reassign positions
                currentPos = rockList.get(i).getPosition();
                nextPosSUDWEST = new Position(currentPos.getX() + 1, currentPos.getY());
            }
        }
        for (int i = 0; i < rockList.size(); i++) {
            Position currentPos = rockList.get(i).getPosition();
            Position nextPosEST = new Position(currentPos.getX(), currentPos.getY() + 1);
            Position nextPosSUDEST = new Position(currentPos.getX() + 1, currentPos.getY() + 1);
            Position nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());

            while (board.getShape(nextPosEST) == Shape.e && board.getShape(nextPosSUDEST) == Shape.e
                    && ((board.getShape(nextPosSUD)) == Shape.d || (board.getShape(nextPosSUD)) == Shape.r)){
                Empty empty = new Empty(currentPos);
                board.setItem(empty, currentPos);
                rockList.get(i).setPosition(nextPosSUDEST);
                board.setItem(rockList.get(i), nextPosSUDEST);
                // Reassign positions
                currentPos = rockList.get(i).getPosition();
                nextPosSUDEST = new Position(currentPos.getX() + 1, currentPos.getY());
            }
        }
    }

    public void moveDiamonds(){
        for (int i = 0; i < diamondsList.size(); i++) {
            Position currentPos = diamondsList.get(i).getPosition();
            Position nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());
            Position nextPosSUDSUD = new Position(currentPos.getX() + 2, currentPos.getY());
            while (board.getShape(nextPosSUD) == Shape.e){
                Empty empty = new Empty(currentPos);
                if (board.getShape(nextPosSUDSUD) == Shape.f) {
                    gameOver = true;
                    board.setItem(empty, currentPos);
                    rockList.get(i).setPosition(nextPosSUDSUD);
                    board.setItem(rockList.get(i), nextPosSUDSUD);
                    break;
                }
                board.setItem(empty, currentPos);
                diamondsList.get(i).setPosition(nextPosSUD);
                board.setItem(diamondsList.get(i), nextPosSUD);
                // Reassign positions
                currentPos = diamondsList.get(i).getPosition();
                nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());
            }
        }
    }

    public void moveDiamondsDiagonal(){
        for (int i = 0; i < diamondsList.size(); i++) {
            Position currentPos = diamondsList.get(i).getPosition();
            Position nextPosWEST = new Position(currentPos.getX(), currentPos.getY() - 1);
            Position nextPosSUDWEST = new Position(currentPos.getX() + 1, currentPos.getY() - 1);
            Position nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());

            while (board.getShape(nextPosWEST) == Shape.e && board.getShape(nextPosSUDWEST) == Shape.e
                    && ((board.getShape(nextPosSUD)) == Shape.d || (board.getShape(nextPosSUD)) == Shape.r)){
                Empty empty = new Empty(currentPos);
                board.setItem(empty, currentPos);
                diamondsList.get(i).setPosition(nextPosSUDWEST);
                board.setItem(diamondsList.get(i), nextPosSUDWEST);
                // Reassign positions
                currentPos = diamondsList.get(i).getPosition();
                nextPosSUDWEST = new Position(currentPos.getX() + 1, currentPos.getY());
            }
        }
        for (int i = 0; i < diamondsList.size(); i++) {
            Position currentPos = diamondsList.get(i).getPosition();
            Position nextPosEST = new Position(currentPos.getX(), currentPos.getY() + 1);
            Position nextPosSUDEST = new Position(currentPos.getX() + 1, currentPos.getY() + 1);
            Position nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());

            while (board.getShape(nextPosEST) == Shape.e && board.getShape(nextPosSUDEST) == Shape.e
                    && ((board.getShape(nextPosSUD)) == Shape.d || (board.getShape(nextPosSUD)) == Shape.r)){
                Empty empty = new Empty(currentPos);
                board.setItem(empty, currentPos);
                diamondsList.get(i).setPosition(nextPosSUDEST);
                board.setItem(diamondsList.get(i), nextPosSUDEST);
                // Reassign positions
                currentPos = diamondsList.get(i).getPosition();
                nextPosSUDEST = new Position(currentPos.getX() + 1, currentPos.getY());
            }
        }
    }

    public void nextLevel(Level level) {
        this.level = level;
    }

    public Board getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isGameOver(){
        return gameOver;
    }
}
