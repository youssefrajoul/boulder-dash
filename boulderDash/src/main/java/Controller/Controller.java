package Controller;

import model.Board;
import model.Game;
import model.Level;
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

        Scanner scan = new Scanner(System.in);
        int quit = 0;
        System.out.println("-----Welcome to Boulder-Dash 1984-----");

        while (!game.isGameOver()) {
            view.display();
            direction = playerInputs();
            game.move(direction);
        }
        view.display();
        System.out.println("Game Over");
    }

    private String playerInputs() {
        var pattern = Pattern.compile("[wasd]");
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
