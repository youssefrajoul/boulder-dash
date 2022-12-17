package com.example.boulderdash;

import com.example.boulderdash.javaFxView.GameView;
import com.example.boulderdash.javaFxView.HomeView;
import com.example.boulderdash.javaFxView.LoseView;
import com.example.boulderdash.javaFxView.WinView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import model.Game;

import java.io.IOException;

public class BoulderDashApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Game game = new Game();
        game.start();
        HomeView homeView = new HomeView();
        WinView winView = new WinView();
        LoseView loseView = new LoseView();
        GameView gameView = new GameView(game);
        Scene gameScene = new Scene(gameView);
        Scene homeScene = new Scene(homeView);
        Scene winScene = new Scene(winView);
        Scene loseScene = new Scene(loseView);
        ScrollPane sp = new ScrollPane();
        sp.setContent(loseView);

        Scene scene = new Scene(sp, 300, 50);

        primaryStage.setTitle("Boulder-Dash");
        primaryStage.setScene(homeScene);
        primaryStage.show();
        homeView.getStartBtn().addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(gameScene);
            }
        });

        gameScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    game.moveRockford("w");
                    break;
                case DOWN:
                    game.moveRockford("s");
                    break;
                case LEFT:
                    game.moveRockford("a");
                    break;
                case RIGHT:
                    game.moveRockford("d");
                    break;
                case U:
                    game.undoCmd();
                    break;
                case R:
                    game.redoCmd();
                    break;
            }
            gameView.getBoardView().refreshBoard();
            game.moveItemsVertical();
            game.moveItemsDiagonal();
            gameView.getBoardView().refreshBoard();
            System.out.println("test2");
            if (game.isGameOver()) {
                primaryStage.setScene(loseScene);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
