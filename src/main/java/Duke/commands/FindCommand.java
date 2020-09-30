package Duke.commands;

import Duke.data.task.Task;

/**
 * Finds any matched Tasks from the list with a given keyword
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private final String keyword;

    /**
     * Sets the keyword to find in TaskList
     *
     * @param keyword keyword to find in TaskList
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Runs the FindCommand
     */
    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Task task : tasks.getAllTasks()) {
            if (task.getDescription().contains(this.keyword)) {
                sb.append(index + 1);
                sb.append(". ");
                sb.append(task);
                sb.append("\n");
                index++;
            }
        }
        ui.printTaskListView(sb.toString());
    }
}
