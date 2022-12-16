package com.example.boulderdash;

import com.example.boulderdash.fxView.View;
import model.Game;

public class BoulderDashController {
    private View view;
    private Game game;

    public BoulderDashController(Game game, View view){
        this.game = game;
        this.view = view;
    }



}