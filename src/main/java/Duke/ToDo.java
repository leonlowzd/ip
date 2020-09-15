package Duke;

public class ToDo extends Task {
    protected boolean isDone;
    private String TASK_TYPE = "[T]";

    public ToDo(String description){
        super(description);
        isDone = false;
    }
    @Override
    public String toString(){
        return getTaskType() + super.toString();
    }
    public String getTaskType() {
        return this.TASK_TYPE;
    }
}
