package com.example.boulderdash.javaFxView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoseView extends VBox {
    private HBox hBox = new HBox();

    public LoseView() {
        Image im = new Image("/Lose.png");
        ImageView iv = new ImageView(im);
        hBox.getChildren().add(iv);
        this.getChildren().add(hBox);


    }

    public HBox gethBox() {
        return hBox;
    }
}
