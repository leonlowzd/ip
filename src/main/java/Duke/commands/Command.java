package Duke.commands;

import Duke.data.TaskList;
import Duke.ui.TextUi;

/**
 * Abstract class for Duke's Commands
 */
abstract public class Command {
    protected TaskList tasks;
    protected static TextUi ui = new TextUi();
    protected Command() {

    }
    /**
     * Sets the entire TaskList to the Command Class
     */
    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }

    abstract public void run();
}
