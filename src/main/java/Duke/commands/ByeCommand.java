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
        return command instanceof ByeCommand; // instanceof returns false if it is null
    }

    @Override
    public void run() {

    }
}
