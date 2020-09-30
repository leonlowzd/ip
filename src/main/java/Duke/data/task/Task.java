package Duke.data.task;

/**
 * Abstract Class for all of the different types of tasks
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Sets the description of the task
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the Task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the type of the Task in String
     */
    public abstract String getTaskType();

    /**
     * Returns the Status of the Task in String
     */
    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    /**
     * Returns the Status of the Task in Symbol Format
     */
    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    /**
     * Returns the Description of the Task in String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the entire setup of the Task in String
     */
    public String toString() {
        return getStatusIcon() + " " + this.description.trim();
    }

    /**
     * Returns the date of the Task if applicable
     */
    public abstract String getDate();
}
