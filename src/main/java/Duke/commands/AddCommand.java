package Duke.commands;

import Duke.exceptions.IllegalDate;
import Duke.exceptions.IllegalDescription;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.ToDo;

public class AddCommand extends Command{
    private final Task task;
    private final String TO_DO = "todo";
    private final String DEADLINE = "deadline";
    private final String EVENT = "event";
    public AddCommand(String type, String description, String date)
            throws IllegalDate, IllegalDescription {
        switch(type) {
            case TO_DO:
                this.task = new ToDo(description);
                break;
            case DEADLINE:
                this.task = new Deadline(description,date);
                break;
            case EVENT:
                this.task = new Event(description,date);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
