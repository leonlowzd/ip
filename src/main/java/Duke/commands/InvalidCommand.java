package Duke.commands;

public class InvalidCommand extends Command {
    public final String feedbackToUser;
    public InvalidCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public void run() {
        ui.printCustomError(this.feedbackToUser + "\n");
    }
}
