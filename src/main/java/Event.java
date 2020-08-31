public class Event extends Task {
    protected String eventTime;
    protected final String TASK_TYPE = "[E]";

    public Event(String description) {
        super(description);
    }

    public String getTaskType() {
        return this.TASK_TYPE;
    }

    public void setDeadline(String eventTime) {
        eventTime = eventTime.replace("/at", " (at:");
        eventTime += ")";
        this.eventTime = eventTime;
    }
    public String getDate(){
        return this.eventTime;
    }
    public String toString() {
        return getTaskType() + super.toString() + getDate();
    }
}
