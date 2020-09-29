package Duke.commands;

import Duke.data.task.Task;

public class FindCommand extends Command{

    public static final String COMMAND_WORD = "find";
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for(Task task: tasks.getAllTasks()) {
            if (task.getDescription().contains(this.keyword)) {
                sb.append(index+1);
                sb.append(". ");
                sb.append(task);
                sb.append("\n");
                index++;
            }
        }
        ui.printTaskListView(sb.toString());
    }
}
