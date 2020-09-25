package Duke.commands;

import Duke.exceptions.IllegalDate;
import Duke.exceptions.IllegalDescription;

public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public static boolean isExit(Command command) {
        return command instanceof ByeCommand; // instanceof returns false if it is null
    }

    @Override
    public void execute() {

    }
}
