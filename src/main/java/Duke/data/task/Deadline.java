package Duke.data.task;

import Duke.exceptions.IllegalDescription;

import static Duke.common.TaskNames.DEADLINE_DISPLAY;


public class Deadline extends Task {
    protected String date;
    protected final String TASK_TYPE = DEADLINE_DISPLAY;

    public Deadline(String description, String date) throws IllegalDescription {
        super(description);
        setDate(date);

    }

    @Override
    public String getTaskType(){
        return this.TASK_TYPE;
    }

    public void setDate(String date){
        this.date = date;
    }

    @Override
    public String getDate(){
        return this.date;
    }

    private String setPrintDate() {
        return " (by: "+getDate()+")";
    }

    @Override
    public String toString(){
        return "[" + getTaskType() + "]" + super.toString() + setPrintDate();
    }

}
