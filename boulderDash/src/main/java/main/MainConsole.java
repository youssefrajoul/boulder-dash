package main;

import controller.Controller;
import model.Game;
import view.TextView;

public class MainConsole {
    public static void main(String[] args) {
        Game game = new Game();
        TextView view = new TextView(game);
        Controller controller = new Controller(game, view);
        controller.play();
    }
}
