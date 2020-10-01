package Duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static Duke.common.TaskNames.DEADLINE_DISPLAY;


public class Deadline extends Task {
    protected String date;
    protected final String TASK_TYPE = DEADLINE_DISPLAY;

    public Deadline(String description, String date) {
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
        String tempDate = getDate();
        try {
            LocalDate localDate = LocalDate.parse(date);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            tempDate = localDate.format(formatter);
        } catch (DateTimeParseException e) {
            this.date = tempDate;
        }
        return " (by: "+tempDate+")";
    }

    @Override
    public String toString(){
        return "[" + getTaskType() + "]" + super.toString() + setPrintDate();
    }

}
