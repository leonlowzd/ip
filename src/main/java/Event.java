public class Event extends Task {
    protected String date;
    protected final String TASK_TYPE = "[E]";

    public Event(String description, String date) throws IllegalDate {
        super(description);
        if(date.contains("/at")) setDate(date);
        else throw new IllegalDate();
    }

    public String getTaskType() {
        return this.TASK_TYPE;
    }

    public void setDate(String date) {
        date = date.replace("/at", " (at:");
        date += ")";
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
    public String toString() {
        return getTaskType() + super.toString() + getDate();
    }
}
