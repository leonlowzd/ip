package Duke.commands;

/**
 * Exits the program
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Sets the isExit flag to true if there an instance of ByeCommand
     */
    public static boolean isExit(Command command) {
        // instanceof returns false if it is null
        return command instanceof ByeCommand;
    }

    /**
     * Runs the ByeCommand
     */
    @Override
    public void run() {

    }
}
