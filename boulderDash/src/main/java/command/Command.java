package command;

public interface Command {

    /**
     * Executes the command.
     *
     */
    public void execute();

    /**
     * Undoes the command.
     *
     */
    public void undo();

    /**
     * Redoes the command.
     *
     */
    public void redo();
}
