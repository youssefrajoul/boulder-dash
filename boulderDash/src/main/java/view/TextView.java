package view;

import model.Game;
import model.Position;

public class TextView {
    private Game game;

    public TextView(Game game){
        this.game = game;
    }

    public void display(){
        System.out.println("Score: "+game.getScore());
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 39; j++) {
                System.out.print(game.getBoard().getShape(new Position(i, j))+"|");
            }
            System.out.println("");
        }
        System.out.println(game.getRockford().printPosition());
    }
}
