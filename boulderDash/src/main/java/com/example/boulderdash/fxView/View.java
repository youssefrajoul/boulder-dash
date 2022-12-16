package com.example.boulderdash.fxView;


import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import model.Game;
import model.Position;
import model.items.*;

public class View extends GridPane {
    private Game game;

    public View(Game game) {
        this.game = game;
        this.setHgap(0);
        this.setVgap(0);
        refreshScene();
    }

    public void refreshScene(){
        System.out.println("test3");
        this.getChildren().clear();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                if (game.getBoard().getItem(new Position(i, j)) instanceof Wall) {
                    ImageView wall = new ImageView(new Image("/Wall.png"));
                    this.add(wall, j, i);
                } else if (game.getBoard().getItem(new Position(i, j)) instanceof Rockford) {
                    ImageView rockford = new ImageView(new Image("/Rockford.png"));
                    this.add(rockford, j,i);
                } else if (game.getBoard().getItem(new Position(i, j)) instanceof ExitDoor) {
                    ImageView exit = new ImageView(new Image("/Wall.png"));
                    this.add(exit, j, i);
                } else if (game.getBoard().getItem(new Position(i, j)) instanceof Rock) {
                    ImageView rock = new ImageView(new Image("/Rock.png"));
                    this.add(rock, j, i);
                } else if (game.getBoard().getItem(new Position(i, j)) instanceof Clay) {
                    ImageView wall = new ImageView(new Image("/Clay.png"));
                    this.add(wall, j, i);
                } else if (game.getBoard().getItem(new Position(i, j)) instanceof Empty) {
                    ImageView wall = new ImageView(new Image("/Empty.png"));
                    this.add(wall, j, i);
                } else if (game.getBoard().getItem(new Position(i, j)) instanceof Diamond) {
                    ImageView wall = new ImageView(new Image("/Diamond.png"));
                    this.add(wall, j, i);
                }
            }
        }
        System.out.println("test4");
    }

}
