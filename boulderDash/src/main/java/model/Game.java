package model;

import model.items.*;

import java.util.ArrayList;
import java.util.Stack;

public class Game {
    private Level level;
    private Board board;
    private Rockford rockford;
    ArrayList<Rock> rockList;
    ArrayList<Diamonds> diamondsList;
    private int score;
    private boolean gameOver;
    private ArrayList<Board> undoStack;
    private ArrayList<Board> redoStack;

    public Game() {
        //level = new Level(1, FilePath.LEVEL1);
        this.board = new Board();
        rockList = new ArrayList<>();
        diamondsList = new ArrayList<>();
        this.score = 0;
        this.level = new Level(1, FilePath.LEVEL1);
        this.gameOver = false;
        this.undoStack = new ArrayList<>();
        this.redoStack = new ArrayList<>();
    }

    public void saveItems() {
        Board currentBoard = new Board();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                Position tempPos = new Position(i, j);
                if (board.getItem(tempPos) instanceof Clay) currentBoard.setItem(new Clay(tempPos), tempPos);
                if (board.getItem(tempPos) instanceof Diamonds) currentBoard.setItem(new Diamonds(tempPos), tempPos);
                if (board.getItem(tempPos) instanceof Empty) currentBoard.setItem(new Empty(tempPos), tempPos);
                if (board.getItem(tempPos) instanceof ExitDoor) currentBoard.setItem(new ExitDoor(tempPos), tempPos);
                if (board.getItem(tempPos) instanceof Rock) currentBoard.setItem(new Rock(tempPos), tempPos);
                if (board.getItem(tempPos) instanceof Rockford) currentBoard.setItem(new Rockford(tempPos), tempPos);
                if (board.getItem(tempPos) instanceof Wall) currentBoard.setItem(new Wall(tempPos), tempPos);
            }
        }
        //this.undoStack.push(currentBoard);
        this.undoStack.add(currentBoard);
        System.out.println(undoStack.size());
//        for (int i = 0; i < undoStack.size(); i++) {
//            System.out.println(undoStack.peek());
//        }
    }

    public boolean isUndoStackEmpty(){
        return this.undoStack.isEmpty();
    }

    public boolean isRedoStackEmpty(){
        return this.redoStack.isEmpty();
    }

    public void undoCmd() {
//        // Condition to prevent undoing (or display) the current board twice.
//        if (this.board.equals(undoStack.peek())) {
//            redoStack.push(undoStack.pop());
//            //System.out.println("teset1");
//        }
//        //System.out.println("teset2");
//        if (isRedoStackEmpty()) redoStack.push(undoStack.pop());

        //Board tempBoard = this.undoStack.pop();
        Board tempBoard = this.undoStack.get(undoStack.size() - 1);
        undoStack.remove(undoStack.size()-1);
        this.redoStack.add(tempBoard);
        this.board = tempBoard;
        //System.out.println(undoStack.size());
    }

    public void redoCmd(){
//        // Condition to prevent redoing (or display) the current board twice.
//        if (this.board.equals(redoStack.peek())) {
//            undoStack.push(redoStack.pop());
//            //System.out.println("test3");
//        }
//        //if (isUndoStackEmpty()) undoStack.push(redoStack.pop());
        Board tempBoard = this.redoStack.get(redoStack.size() - 1);
        redoStack.remove(redoStack.size()-1);
        this.undoStack.add(tempBoard);
        this.board = tempBoard;
        System.out.println(redoStack.size());
    }

    public void start() {
        int fileData;
        int i = 0;
        int j = 0;
        try {
            while ((fileData = this.getLevel().getLevelMap().read()) != -1) {
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

    public void moveRockford(String direction) {
        //saveItems();
        Position currentPos = rockford.getPosition();
        Position nextPos;
        switch (direction) {
            // Warnings!!! QWERTY US Keyboard
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
        if (!board.isRock(nextPos) && !board.isWall(nextPos)) {
            if (board.isDiamond(nextPos)) {
                this.score++;
                Diamonds d = (Diamonds) board.getItem(nextPos);
                d.setClaimed();
            }
            board.setItem(new Empty(currentPos), currentPos);
            rockford.setPosition(nextPos);
            board.setItem(rockford, nextPos);
        }
        moveItemsVertical();
        moveItemsDiagonal();
        moveItemsVertical();
        moveItemsDiagonal();
    }

    public void moveItemsVertical() {
        for (int i = 0; i < rockList.size(); i++) {
            verticalMovement(rockList.get(i));
        }
        for (int i = 0; i < diamondsList.size(); i++) {
            if (!diamondsList.get(i).isClaimed())
                verticalMovement(diamondsList.get(i));
        }
    }

    private void verticalMovement(Item item) {
        Position currentPos = item.getPosition();
        Position nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());
        Position nextPosSUDSUD = new Position(currentPos.getX() + 2, currentPos.getY());
        while (board.isEmpty(nextPosSUD)) {
            Empty empty = new Empty(currentPos);
            if (board.isRockford(nextPosSUDSUD)) {
                gameOver = true;
                board.setItem(empty, currentPos);
                item.setPosition(nextPosSUDSUD);
                board.setItem(item, nextPosSUDSUD);
                break;
            }
            board.setItem(empty, currentPos);
            item.setPosition(nextPosSUD);
            board.setItem(item, nextPosSUD);
            // Reassign current & next positions
            currentPos = item.getPosition();
            nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());
        }
    }

    public void moveItemsDiagonal() {
        for (int i = 0; i < rockList.size(); i++) {
            leftDiagonalMovement(rockList.get(i));
            rightDiagonalMovement(rockList.get(i));
        }
        for (int i = 0; i < diamondsList.size(); i++) {
            if (!diamondsList.get(i).isClaimed()) {
                leftDiagonalMovement(diamondsList.get(i));
                rightDiagonalMovement(diamondsList.get(i));
            }
        }
    }

    public void leftDiagonalMovement(Item item) {
        Position currentPos = item.getPosition();
        Position nextPosWEST = new Position(currentPos.getX(), currentPos.getY() - 1);
        Position nextPosSUDWEST = new Position(currentPos.getX() + 1, currentPos.getY() - 1);
        Position nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());

        while (board.isEmpty(nextPosWEST) && board.isEmpty(nextPosSUDWEST)
                && (board.isDiamond(nextPosSUD) || (board.isRock(nextPosSUD)))) {
            Empty empty = new Empty(currentPos);
            board.setItem(empty, currentPos);
            item.setPosition(nextPosSUDWEST);
            board.setItem(item, nextPosSUDWEST);
            // Reassign current & next positions
            currentPos = item.getPosition();
            nextPosSUDWEST = new Position(currentPos.getX() + 1, currentPos.getY());
        }
    }

    private void rightDiagonalMovement(Item item) {
        Position currentPos = item.getPosition();
        Position nextPosEST = new Position(currentPos.getX(), currentPos.getY() + 1);
        Position nextPosSUDEST = new Position(currentPos.getX() + 1, currentPos.getY() + 1);
        Position nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());

        while (board.isEmpty(nextPosEST) && board.isEmpty(nextPosSUDEST)
                && (board.isDiamond(nextPosSUD) || (board.isRock(nextPosSUD)))) {
            Empty empty = new Empty(currentPos);
            board.setItem(empty, currentPos);
            item.setPosition(nextPosSUDEST);
            board.setItem(item, nextPosSUDEST);
            // Reassign current & next positions
            currentPos = item.getPosition();
            nextPosSUDEST = new Position(currentPos.getX() + 1, currentPos.getY());
        }
    }

//    private int calculDiamonds(){
//        for (int i = 0; i < 20; i++) {
//            for (int j = 0; j < 39; j++) {
//                Diamonds d = (Diamonds) board.getItem(new Position(i,j));
//                if (d.isClaimed()){
//
//                }
//            }
//        }
//    }

    public void nextLevel() {
        Level level = new Level(2, FilePath.LEVEL2);
        setLevel(level);
    }

    public Board getBoard() {
        return this.board;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Rockford getRockford() {
        return rockford;
    }
}
