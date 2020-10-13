package Duke.commands;

import Duke.data.task.Task;

/**
 * Lists all Tasks in the list
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Sets the ListCommand
     */
    public ListCommand() {
    }

    /**
     * Runs ListCommand
     */
    @Override
    public void run() {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        for (Task task : tasks.getAllTasks()) {
            stringBuilder.append(index + 1);
            stringBuilder.append(". ");
            stringBuilder.append(task);
            stringBuilder.append("\n");
            index++;
        }
        ui.printTaskListView(stringBuilder.toString());
    }
}
