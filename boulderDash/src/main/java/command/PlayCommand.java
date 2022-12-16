package command;

import model.Game;

public class PlayCommand extends BoulderDashCommands {

    public PlayCommand(Game game) {
        super(game);
    }

    @Override
    public void execute(String direction) {
        game.moveRockford(direction);
    }

    @Override
    public void undo() {
        game.undoCmd();
//        game.addExit();
    }

    @Override
    public void redo() {
        game.redoCmd();
//        game.addExit();
    }
}
