package Duke.commands;

import Duke.data.task.Task;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public ListCommand() {

    }

    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Task task :tasks.getAllTasks()) {
            sb.append(index+1);
            sb.append(". ");
            sb.append(task);
            sb.append("\n");
            index++;
        }
        ui.printTaskListView(sb.toString());
    }
}
