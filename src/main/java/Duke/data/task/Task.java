package Duke.data.task;

abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String getTaskType();

    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public String getDescription() {
        return this.description;
    }

    public String toString(){
        return getStatusIcon() +" "+ this.description.trim();
    }

    public abstract String getDate();
}
