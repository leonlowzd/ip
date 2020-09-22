package Duke.task;

import Duke.Duke;
import Duke.exceptions.IllegalDate;
import Duke.task.Task;


public class Deadline extends Task {
    protected String date;
    protected final String TASK_TYPE = "[D]";

    public Deadline(String description, String date) throws IllegalDate {
        super(description);
        if(date.contains("/by")) {
            if (date.replace("/by","").trim().isEmpty()) {
                throw new IllegalDate();
            }
            setDate(date);
        }
        else {
            throw new IllegalDate();
        }

    }
    public String getTaskType(){
        return this.TASK_TYPE;
    }

    public void setDate(String date){
        date = date.replace("/by"," (by:");
        date += ")";
        this.date = date;

    }
    @Override
    public String toString(){

        return getTaskType() + super.toString() + getDate();
    }
    public String getDate(){
        return this.date;
    }
}
