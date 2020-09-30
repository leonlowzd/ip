package Duke.commands;

/**
 * Marks given task as done from the list
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private final int indexToMark;

    /**
     * Sets the index to done in TaskList
     *
     * @param indexToMark Index of task to mark as dome in TaskList
     */
    public DoneCommand(int indexToMark) {
        this.indexToMark = indexToMark;
    }

    /**
     * Runs the DoneCommand
     */
    @Override
    public void run() {
        if (indexToMark > tasks.getNumberOfTasksInList() - 1) {
            ui.printIllegalIndexMessage();
        } else {
            tasks.getTask(indexToMark).markAsDone();
            ui.printTaskAsDoneMessage(tasks.getTask(indexToMark));
        }

    }
}
