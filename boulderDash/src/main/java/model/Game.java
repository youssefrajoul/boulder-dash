package model;

import Observer.Subject;
import model.items.*;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Represents the game
 */
public class Game extends Subject {
    private Level level;
    private Board board;
    private Rockford rockford;
    private int numberOfDiamonds;
    private int score;
    private boolean gameOver;
    private Stack<Board> undoBoard;
    private Stack<Board> redoBoard;
    private Stack<Position> undoRockford;
    private Stack<Position> redoRockford;

    public Game() {
        this.board = new Board();
        this.score = 0;
        this.level = new Level(1, FilePath.LEVEL1);
        this.gameOver = false;
        this.undoBoard = new Stack<>();
        this.redoBoard = new Stack<>();
        this.undoRockford = new Stack<>();
        this.redoRockford = new Stack<>();
        this.numberOfDiamonds = 0;
    }

    /**
     * Gets this board
     * @return
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Gets the current score
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Checks if the game is over or not yet
     * @return true if game is over, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Gets the current level
     * @return
     */
    public Level getLevel() {
        return level;
    }

    /**Sets the level
     *
     * @param level
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Gets the item Rockford
     * @return
     */
    public Rockford getRockford() {
        return this.rockford;
    }

    /**
     * Gets the number of diamonds of level
     * @return
     */
    public int getNumberOfDiamonds() {
        return numberOfDiamonds;
    }

    /**
     * Sets the number of diamonds for the current level.
     * @param numberOfDiamonds
     */
    public void setNumberOfDiamonds(int numberOfDiamonds) {
        this.numberOfDiamonds = numberOfDiamonds + 1;
    }

    public void nextLevel() {
        Level level = new Level(2, FilePath.LEVEL2);
        setLevel(level);
    }

    /**
     * Adds the exit door to the current board.
     */
    public void addExit() {
        if (gameWin()){
            Position tempPosition = new Position(16, 34);
            board.setItem(new ExitDoor(tempPosition), tempPosition);
        }
    }

    /**
     * Checks if player won the game or not yet
     * @return true if the player won, false otherwise
     */
    public boolean gameWin(){
        return getScore() == 10;
    }

    /**
     * Creates items and set them on the board (each item is inside a square inside the board)
     */
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
                            board.createItem(rockford, pos);
                            break;
                        case 99:
                            board.createItem(new Clay(pos), pos);
                            break;
                        case 100:
                            board.createItem(new Diamond(pos), pos);
                            setNumberOfDiamonds(getNumberOfDiamonds());
                            break;
                        case 101:
                            board.createItem(new Empty(pos), pos);
                            break;
                        case 114:
                            board.createItem(new Rock(pos), pos);
                            break;
                        case 119:
                            board.createItem(new Wall(pos), pos);
                            break;
                        case 120:
                            board.createItem(new ExitDoor(pos), pos);
                            break;
                    }
                    j++;
                }
            }
        } catch (Exception e) {
            System.out.println("error resources file level.txt");
        }
    }

    /**
     * Moves the rockford in the direction received as parameter on board
     * Updates the current position and the next position after them movement
     * @param direction direction of the movement
     */
    public void moveRockford(String direction) {
        redoRockford.clear();
        saveItems();
        Position currentPos = new Position(rockford.getPosition().getX(), rockford.getPosition().getY());
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
        if (!(board.getItem(nextPos) instanceof Rock) && !(board.getItem(nextPos) instanceof Wall)) {
            Empty empty = new Empty(currentPos);
            board.setItem(empty, currentPos);
            rockford.setPosition(nextPos);
            board.setItem(rockford, nextPos);
        }
        setScore(calculScore());
        addExit();
    }

    /**
     * Moves the items except (rockford and clay) in vertical if under that item is empty
     */
    public void moveItemsVertical() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                Position tempPosition = new Position(i, j);
                if (board.getItem(tempPosition) instanceof Rock) {
                    verticalMovement(board.getItem(tempPosition));
                }
                if (board.getItem(tempPosition) instanceof Diamond) {
                    verticalMovement(board.getItem(tempPosition));
                }
            }
        }
    }

    /**
     * Vertical movement algorithm
     * updates the current and next position of the moved item each time under of that item is empty
     */
    private void verticalMovement(Item item) {
        Position currentPos = new Position(item.getPosition().getX(), item.getPosition().getY());
        Position nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());
        Position nextPosSUDSUD = new Position(currentPos.getX() + 2, currentPos.getY());
        while (board.getItem(nextPosSUD) instanceof Empty) {
            Empty empty = new Empty(currentPos);
            if (board.getItem(nextPosSUDSUD) instanceof Rockford) {
                gameOver = true;
                board.setItem(empty, currentPos);
                item.setPosition(nextPosSUDSUD);
                board.setItem(item, nextPosSUDSUD);
                break;
            }
            board.setItem(empty, currentPos);
            item.setPosition(nextPosSUD);
            board.setItem(item, nextPosSUD);
            moveItemsVertical();// Recursive method to let all rocks fall until there is no rock that has to fall
            // Reassign current & next positions
            currentPos = new Position(item.getPosition().getX(), item.getPosition().getY());
            nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());
        }
    }
    /**
     * Moves the items except (rockford and clay) in diagonal if  (Right or Left) and under them is empty
     */
    public void moveItemsDiagonal() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                Position tempPosition = new Position(i, j);
                if (board.getItem(tempPosition) instanceof Rock) {
                    leftDiagonalMovement(board.getItem(tempPosition));
                    rightDiagonalMovement(board.getItem(tempPosition));
                }
                if (board.getItem(tempPosition) instanceof Diamond) {
                    leftDiagonalMovement(board.getItem(tempPosition));
                    rightDiagonalMovement(board.getItem(tempPosition));
                }
            }
        }
    }

    /**
     * Left Diagonal movement algorithm
     * updates the current and next position of the moved item each time LeftUnder of that item is empty
     */
    private void leftDiagonalMovement(Item item) {
        Position currentPos = new Position(item.getPosition().getX(), item.getPosition().getY());
        Position nextPosWEST = new Position(currentPos.getX(), currentPos.getY() - 1);
        Position nextPosSUDWEST = new Position(currentPos.getX() + 1, currentPos.getY() - 1);
        Position nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());

        while (board.getItem(nextPosWEST) instanceof Empty && board.getItem(nextPosSUDWEST) instanceof Empty
                && ((board.getItem(nextPosSUD) instanceof Diamond || (board.getItem(nextPosSUD) instanceof Diamond)))) {
            Empty empty = new Empty(currentPos);
            board.setItem(empty, currentPos);
            item.setPosition(nextPosSUDWEST);
            board.setItem(item, nextPosSUDWEST);
            // Reassign current & next positions
            currentPos = new Position(item.getPosition().getX(), item.getPosition().getY());
            nextPosSUDWEST = new Position(currentPos.getX() + 1, currentPos.getY());
        }
    }

    /**
     * Left Diagonal movement algorithm
     * updates the current and next position of the moved item each time RightUnder of that item is empty
     */
    private void rightDiagonalMovement(Item item) {
        Position currentPos = new Position(item.getPosition().getX(), item.getPosition().getY());
        Position nextPosEST = new Position(currentPos.getX(), currentPos.getY() + 1);
        Position nextPosSUDEST = new Position(currentPos.getX() + 1, currentPos.getY() + 1);
        Position nextPosSUD = new Position(currentPos.getX() + 1, currentPos.getY());

        while (board.getItem(nextPosEST) instanceof Empty && board.getItem(nextPosSUDEST) instanceof Empty
                && (board.getItem(nextPosSUD) instanceof Diamond || (board.getItem(nextPosSUD) instanceof Rock))) {
            Empty empty = new Empty(currentPos);
            board.setItem(empty, currentPos);
            item.setPosition(nextPosSUDEST);
            board.setItem(item, nextPosSUDEST);
            // Reassign current & next positions
            currentPos = new Position(item.getPosition().getX(), item.getPosition().getY());
            nextPosSUDEST = new Position(currentPos.getX() + 1, currentPos.getY());
        }
    }

    /**
     * Saves the old board and old rockford position after each movement in the stack to use them for undo redo
     */
    public void saveItems() {
        Board currentBoard = new Board();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                Position tempPos = new Position(i, j);
                if (this.board.getItem(tempPos) instanceof Clay) currentBoard.createItem(new Clay(tempPos), tempPos);
                if (this.board.getItem(tempPos) instanceof Diamond) currentBoard.createItem(new Diamond(tempPos), tempPos);
                if (this.board.getItem(tempPos) instanceof Empty) currentBoard.createItem(new Empty(tempPos), tempPos);
                if (this.board.getItem(tempPos) instanceof ExitDoor) currentBoard.createItem(new ExitDoor(tempPos), tempPos);
                if (this.board.getItem(tempPos) instanceof Rock) currentBoard.createItem(new Rock(tempPos), tempPos);
                if (this.board.getItem(tempPos) instanceof Rockford) currentBoard.createItem(new Rockford(tempPos), tempPos);
                if (this.board.getItem(tempPos) instanceof Wall) currentBoard.createItem(new Wall(tempPos), tempPos);
            }
        }
        this.undoBoard.push(currentBoard);
        this.undoRockford.push(new Position(this.rockford.getPosition().getX(), this.rockford.getPosition().getY()));
    }

    /**
     * modifies the current board to the boards saved in the stack by order LIFO(last in first out)
     */
    public void undoCmd() {
        if (!undoRockford.isEmpty() && !undoBoard.isEmpty()) {
            redoBoard.push(getBoard());
            redoRockford.push(new Position(getRockford().getPosition().getX(), getRockford().getPosition().getY()));
            this.board = this.undoBoard.pop();
            this.rockford.setPosition(new Position(undoRockford.peek().getX(), undoRockford.pop().getY()));
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                Position tempPosition = new Position(i, j);
                board.setItem(board.getItem(tempPosition), tempPosition);
            }
        }

        setScore(calculScore());
    }

    /**
     * modifies the current board to the boards saved in the stack by order LIFO(last in first out)
     */
    public void redoCmd() {
        if (!redoRockford.isEmpty() && !redoBoard.isEmpty()) {
            undoBoard.push(getBoard());
            undoRockford.push(new Position(getRockford().getPosition().getX(), getRockford().getPosition().getY()));
            this.board = this.redoBoard.pop();
            this.rockford.setPosition(new Position(redoRockford.peek().getX(), redoRockford.pop().getY()));
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                Position tempPosition = new Position(i, j);
                board.setItem(board.getItem(tempPosition), tempPosition);
            }
        }
        setScore(calculScore());
    }

    public boolean isUndoStackEmpty() {
        return this.undoBoard.isEmpty();
    }

    public boolean isRedoStackEmpty() {
        return this.redoBoard.isEmpty();
    }

    /**
     * Calcul the score = number of diamonds claimed
     * @return
     */
    private int calculScore() {
        int nbOnBoard = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                Position tempPos = new Position(i, j);
                if (board.getItem(tempPos) instanceof Diamond) nbOnBoard++;
            }
        }
        return getNumberOfDiamonds() - nbOnBoard;
    }
}
