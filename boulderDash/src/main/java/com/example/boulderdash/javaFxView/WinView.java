package com.example.boulderdash.javaFxView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WinView extends VBox {
    private HBox hBox = new HBox();

    public WinView() {
        Image im = new Image("/Win.png");
        ImageView iv = new ImageView(im);
        hBox.getChildren().add(iv);
        this.getChildren().add(hBox);


    }

    public HBox gethBox() {
        return hBox;
    }
}
