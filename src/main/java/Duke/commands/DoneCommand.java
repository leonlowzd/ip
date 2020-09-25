package Duke.commands;

import Duke.exceptions.IllegalIndex;
import Duke.ui.TextUi;

public class DoneCommand extends Command{
    public static final String COMMAND_WORD = "done";
    private final int indexToMark;

    public DoneCommand(int indexToMark) {
        this.indexToMark = indexToMark;
    }
    @Override
    public void execute() {
        if (indexToMark > tasks.getNumberOfTasksInList()-1){
            ui.showIllegalIndexMessage();
        } else {
            tasks.getTask(indexToMark).markAsDone();
            ui.showTaskAsDoneMessage(tasks.getTask(indexToMark));
        }

    }
}
