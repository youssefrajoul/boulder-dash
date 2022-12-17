package com.example.boulderdash;

import com.example.boulderdash.javaFxView.GameView;
import com.example.boulderdash.javaFxView.HomeView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Game;

import java.io.IOException;

public class BoulderDashApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Game game = new Game();
        game.start();
        HomeView homeView = new HomeView();
        //BoardView boardView = new BoardView(game);
        //Scene gameScene = new Scene(boardView);
        GameView gameView = new GameView(game);
        Scene gameScene = new Scene(gameView);
        Scene homeScene = new Scene(homeView);

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
                    game.undoCmdFx();
                    break;
                case R:
                    game.redoCmd();
                    break;
            }
            gameView.getBoardView().refreshBoard();
//            boardView.refreshBoard();
            game.moveItemsVertical();
            game.moveItemsDiagonal();
            gameView.getBoardView().refreshBoard();
//            boardView.refreshBoard();
            System.out.println("test2");
            
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
