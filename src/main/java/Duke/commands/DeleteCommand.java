package Duke.commands;

import Duke.data.task.Task;

public class DeleteCommand extends Command{
    public static final String COMMAND_WORD = "delete";
    private final int indexToDelete;
    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public void execute() {
        if (indexToDelete > tasks.getNumberOfTasksInList()-1) {
            ui.printIllegalIndexMessage();

        } else {
            Task TaskToDelete = tasks.getTask(indexToDelete);
            tasks.removeTask(indexToDelete);
            ui.printDeleteTaskMessage(TaskToDelete ,tasks.getNumberOfTasksInList());

        }
    }

}
