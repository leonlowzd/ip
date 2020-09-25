package Duke.data.task;

import Duke.exceptions.IllegalDescription;

import static Duke.common.Messages.EVENT_DISPLAY;

public class Event extends Task {
    protected String date;
    protected final String TASK_TYPE = EVENT_DISPLAY;

    public Event(String description, String date) throws IllegalDescription {
        super(description);
        setDate(date);
    }

    @Override
    public String getTaskType() {
        return this.TASK_TYPE;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String getDate(){
        return this.date;
    }

    private String setPrintDate() {
        return " (at: "+getDate()+")";
    }

    public String toString() {
        return "["+getTaskType()+"]" + super.toString() + setPrintDate();
    }
}
