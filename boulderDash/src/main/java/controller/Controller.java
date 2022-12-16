package controller;

import command.Command;
import command.PlayCommand;
import model.Game;
import view.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Controller {
    private Game game;
    private TextView view;

    public Controller(Game game, TextView view) {
        this.game = game;
        this.view = view;
    }

    public void play(){
        game.start();
        String direction = "";
        PlayCommand play = new PlayCommand(game);

        System.out.println("-----Welcome to Boulder-Dash 1984-----");

        while (!game.isGameOver()) {
            view.display();
            direction = playerInputs();
            if (direction.equals("u") && !game.isUndoStackEmpty()){
                play.undo();
            } else if (direction.equals("r") && !game.isRedoStackEmpty()) {
                play.redo();
            } else if (direction.equals("w") || direction.equals("a") || direction.equals("s") || direction.equals("d")){
                play.execute(direction);
            }
        }
        view.display();
        System.out.println("Game Over");
    }

    private String playerInputs() {
        // w: up / a: left / s: left / d: right/ u: undo / r: redo
        var pattern = Pattern.compile("[wasdur]");
        String input = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("play using w, a, s, d keys -> w to move up, s down, a left, d right");
            input = br.readLine();
            while (!pattern.matcher(input).matches()) {
                System.out.println("wrong input\nplease read & respect these instructions :");
                System.out.println("play using w, a, s, d keys -> w to move up, s down, a left, d right");
                input = br.readLine();
                System.out.println(); // to clean the buffer
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return input;
    }

}
