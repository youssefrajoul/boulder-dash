//package command;
//
//import model.Board;
//import model.Game;
//
//public class PlayCommand extends BoulderDashCommands {
//
//    private char direction;
//    private Board oldBoard, newBoard;
//
//    public PlayCommand(Game game, char dir) {
//        super(game);
//        this.direction = dir;
//    }
//
//    @Override
//    public void execute() {
//        this.oldBoard = game.getBoard();
//        game.playShot(dir);
//        this.newBoard = new Board(game.getBoard());
//    }
//
//    @Override
//    public void undo() {
//        game.setBoard(oldBoard);
//        game.displayExit();
//    }
//
//    @Override
//    public void redo() {
//        game.setBoard(newBoard);
//        game.displayExit();
//    }
//}
