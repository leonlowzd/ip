package Duke.data.task;

import static Duke.common.TaskNames.TODO_DISPLAY;

/**
 * Class representing the ToDo Task.
 */
public class ToDo extends Task {
    private final String TASK_TYPE = TODO_DISPLAY;
    protected boolean isDone;

    /**
     * Sets the description for ToDo Task
     *
     * @param description Description of the Task in String
     */
    public ToDo(String description) {
        super(description);
        isDone = false;
    }

    /**
     * Gets the date of the task, in this case, null.
     *
     * @return null
     */
    @Override
    public String getDate() {
        return null;
    }

    /**
     * Gets the type of the task.
     *
     * @return TASK_TYPE.
     */
    @Override
    public String getTaskType() {
        return this.TASK_TYPE;
    }

    /**
     * Gets all information of the Event task.
     *
     * @return information of the task in String.
     */
    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString();
    }
}
