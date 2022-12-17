package com.example.boulderdash.javaFxView;


import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import model.Game;
import model.Position;
import model.items.*;

public class BoardView extends GridPane {
    private Game game;

    public BoardView(Game game) {
        this.game = game;
        displayBoard();
    }

    public void displayBoard() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                if (game.getBoard().getItem(new Position(i, j)) instanceof Wall) {
                    ImageView wall = new ImageView(new Image("/Wall.png"));
                    this.add(wall, j, i);
                } else if (game.getBoard().getItem(new Position(i, j)) instanceof Rockford) {
                    ImageView rockford = new ImageView(new Image("/Rockford.png"));
                    this.add(rockford, j, i);
                } else if (game.getBoard().getItem(new Position(i, j)) instanceof ExitDoor) {
                    ImageView exit = new ImageView(new Image("/ExitDoor.png"));
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
    }

    public void refreshBoard() {
        System.out.println(game.getBoard().getMovedItems().size());// test
        int stackSize = game.getBoard().getMovedItems().size();
        for (int i = 0; i < stackSize; i++) {
            System.out.println("test for");
            if (game.getBoard().getMovedItems().size() > 0) {
                Item item = game.getBoard().getMovedItems().pop();
                if (item instanceof Wall) {
                    ImageView wall = new ImageView(new Image("/Wall.png"));
                    System.out.println("wall");
                    this.add(wall, item.getPosition().getY(), item.getPosition().getX());
                } else if (item instanceof ExitDoor) {
                    ImageView exit = new ImageView(new Image("/ExitDoor.png"));
                    System.out.println("exit door");
                    this.add(exit, item.getPosition().getY(), item.getPosition().getX());
                } else if (item instanceof Rock) {
                    ImageView rock = new ImageView(new Image("/Rock.png"));
                    System.out.println("rock");
                    this.add(rock, item.getPosition().getY(), item.getPosition().getX());
                } else if (item instanceof Clay) {
                    ImageView clay = new ImageView(new Image("/Clay.png"));
                    System.out.println("clay");
                    this.add(clay, item.getPosition().getY(), item.getPosition().getX());
                } else if (item instanceof Empty) {
                    ImageView empty = new ImageView(new Image("/Empty.png"));
                    System.out.println("empty");
                    this.add(empty, item.getPosition().getY(), item.getPosition().getX());
                } else if (item instanceof Rockford) {
                    ImageView rockford = new ImageView(new Image("/Rockford.png"));
                    System.out.println("rockford");
                    this.add(rockford, item.getPosition().getY(), item.getPosition().getX());
                } else if (item instanceof Diamond) {
                    ImageView wall = new ImageView(new Image("/Diamond.png"));
                    System.out.println("diamond");
                    this.add(wall, item.getPosition().getY(), item.getPosition().getX());
                }
            }
        }
        System.out.println(game.getBoard().getMovedItems().size());// test
    }

}
