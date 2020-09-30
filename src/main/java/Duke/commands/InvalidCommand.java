package Duke.commands;

/**
 * Invalid command, will print the corresponding error message fed from the error
 */
public class InvalidCommand extends Command {
    public final String printToUser;

    /**
     * Sets the Message to print for InvalidCommand
     *
     * @param printToUser message to print to user
     */
    public InvalidCommand(String printToUser) {
        this.printToUser = printToUser;
    }

    /**
     * Runs the InvalidCommand
     */
    @Override
    public void run() {
        ui.printCustomError(this.printToUser + "\n");
    }
}
