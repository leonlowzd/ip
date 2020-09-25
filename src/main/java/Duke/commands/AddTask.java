package Duke.commands;

import Duke.exceptions.IllegalDescription;
import Duke.data.task.Deadline;
import Duke.data.task.Event;
import Duke.data.task.ToDo;

public class AddTask extends Command{

    public static final String COMMAND_WORD_TODO = "todo";
    public static final String COMMAND_WORD_DEADLINE = "deadline";
    public static final String COMMAND_WORD_EVENT = "event";

    public static final String TODO_ADD = "todo";
    public static final String DEADLINE_ADD = "deadline";
    public static final String EVENT_ADD = "event";

    private final String description;
    private final String date;
    private final String type;
    private final Boolean mode;

    public AddTask(String type, String description, String date, Boolean mode) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.mode = mode;
    }

    @Override
    public void execute() {
        try {
            switch (type) {
            case TODO_ADD:
                ToDo todo = new ToDo(description);
                tasks.addNewTask(todo);
                if (mode) {
                    ui.printCreatedTask(todo,tasks.getNumberOfTasksInList());
                }
                break;

            case DEADLINE_ADD:
                Deadline deadline = new Deadline(description, date);
                tasks.addNewTask(deadline);
                if (mode) {
                    ui.printCreatedTask(deadline,tasks.getNumberOfTasksInList());
                }
                break;

            case EVENT_ADD:
                Event event = new Event(description, date);
                tasks.addNewTask(event);
                if (mode) {
                    ui.printCreatedTask(event,tasks.getNumberOfTasksInList());
                }
                break;

            }

        } catch (IllegalDescription e) {
            ui.printIllegalDescriptionMessage();

        }

    }
}
