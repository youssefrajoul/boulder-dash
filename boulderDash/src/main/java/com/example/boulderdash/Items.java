package com.example.boulderdash;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import model.Board;
import model.Game;
import model.Position;
import model.items.Item;
import model.items.Wall;

import java.util.ArrayList;

public class Items {
    private FlowPane flowPane;
    private Game game;

    public Items(){
        flowPane = new FlowPane();
        game = new Game();
    }

    public FlowPane getFlowPane() {
        return flowPane;
    }

    public void sethBoxList(FlowPane flowPane) {
        this.flowPane = flowPane;
    }

    public void fillItems(){
        game.start();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                if (game.getBoard().getItem(new Position(i, j)) instanceof Wall){
                    ImageView imageView = new ImageView("/wall.png");
                    flowPane.getChildren().add(imageView);
                }
            }
        }
    }
}
