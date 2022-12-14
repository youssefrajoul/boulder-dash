package command;

import model.Game;

public abstract class BoulderDashCommands implements Command {

    protected Game game;

    public BoulderDashCommands(Game game){
        this.game = game;
    }
}
