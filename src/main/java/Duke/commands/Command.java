package Duke.commands;

import Duke.data.TaskList;
import Duke.ui.TextUi;

abstract public class Command {
    protected TaskList tasks;
    protected static TextUi ui = new TextUi();
    protected Command() {

    }

    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }

    abstract public void run();
}
