package Duke.commands;

import Duke.data.task.Task;

/**
 * Deletes task from list from a given index
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final int indexToDelete;

    /**
     * Sets the index to delete in TaskList
     *
     * @param indexToDelete Index of task to delete in TaskList
     */
    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    /**
     * Runs DeleteCommand
     */
    @Override
    public void run() {
        if (indexToDelete > tasks.getNumberOfTasksInList() - 1) {
            ui.printIllegalIndexMessage();

        } else {
            Task TaskToDelete = tasks.getTask(indexToDelete);
            tasks.removeTask(indexToDelete);
            ui.printDeleteTaskMessage(TaskToDelete, tasks.getNumberOfTasksInList());

        }
    }

}
