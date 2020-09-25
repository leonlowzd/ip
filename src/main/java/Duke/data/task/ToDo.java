package Duke.data.task;

import static Duke.common.TaskNames.TODO_DISPLAY;

public class ToDo extends Task {
    protected boolean isDone;
    private final String TASK_TYPE = TODO_DISPLAY;

    public ToDo(String description) {
        super(description);
        isDone = false;
    }

    @Override
    public String getDate() {
        return null;
    }

    @Override
    public String getTaskType() {
        return this.TASK_TYPE;
    }

    @Override
    public String toString(){
        return "["+getTaskType()+"]" + super.toString();
    }
}
