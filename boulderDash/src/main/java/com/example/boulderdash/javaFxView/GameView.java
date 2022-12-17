package com.example.boulderdash.javaFxView;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Game;


public class GameView extends VBox {
    private HBox hBox1 = new HBox(10);
    private HBox hBox2 = new HBox(10);
    private VBox vBox1 = new VBox();
    private Button undo = new Button("UNDO");
    private Button redo = new Button("REDO");
    private BoardView boardView;
    private Game game;

    public GameView(Game game) {
        this.game = game;
        this.boardView = new BoardView(game);
        ImageView imageView = new ImageView(new Image("/Diamond.png"));
        Label diamond = new Label(" "+game.getScore()+" /"+game.getNumberOfDiamonds(),imageView);
        hBox1.getChildren().add(diamond);
        undo.setMaxWidth(Double.MAX_VALUE);
        redo.setMaxWidth(Double.MAX_VALUE);
        hBox2.getChildren().add(undo);
        hBox2.getChildren().add(redo);
        hBox2.setAlignment(Pos.CENTER);

//        ScrollBar scrollBar = new ScrollBar();
//        scrollBar.setOrientation(Orientation.VERTICAL);

        this.getChildren().add(hBox1);
//        this.getChildren().add(hBox2);
        this.getChildren().add(boardView);
    }

    public BoardView getBoardView() {
        return boardView;
    }
}
