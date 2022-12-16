package com.example.boulderdash;

import com.example.boulderdash.fxView.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Game;

import java.io.IOException;

public class BoulderDashApplication extends Application {

    private Game game = new Game();
    @Override
    public void start(Stage primaryStage) throws IOException {
        game.start();
        View view = new View(game);
        Scene scene = new Scene(view);
        primaryStage.setTitle("Boulder-Dash");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(e -> {
            System.out.println("test1");
            switch (e.getCode()) {
                case UP:
                    game.moveRockford("w");
                    view.refreshScene();
                    break;
                case DOWN:
                    game.moveRockford("s");
                    view.refreshScene();
                    break;
                case LEFT:
                    game.moveRockford("a");
                    view.refreshScene();
                    break;
                case RIGHT:
                    game.moveRockford("d");
                    view.refreshScene();
                    break;
            }
            System.out.println("test2");
        });
    }

    public static void main(String[] args) {
        launch();
    }
}