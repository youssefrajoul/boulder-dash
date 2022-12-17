package com.example.boulderdash.javaFxView;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class HomeView extends VBox {
    private Button startBtn;
    private HBox hBox = new HBox();



    public HomeView(){
        startBtn = new Button("Start");
        startBtn.setMaxWidth(Double.MAX_VALUE);
        Image im = new Image("/Home.png");
        ImageView iv = new ImageView(im);
        hBox.getChildren().add(iv);
        this.getChildren().add(hBox);
        this.getChildren().add(startBtn);


    }

    public Button getStartBtn() {
        return startBtn;
    }

    public HBox gethBox() {
        return hBox;
    }

}
