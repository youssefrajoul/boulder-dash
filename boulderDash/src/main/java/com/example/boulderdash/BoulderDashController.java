package com.example.boulderdash;

import com.example.boulderdash.javaFxView.BoardView;
import model.Game;

public class BoulderDashController {
    private BoardView view;
    private Game game;

    public BoulderDashController(Game game, BoardView boardView){
        this.game = game;
        this.view = boardView;
    }



}