package Duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static Duke.common.TaskNames.EVENT_DISPLAY;

/**
 * Class representing the Event Task.
 */
public class Event extends Task {
    protected final String TASK_TYPE = EVENT_DISPLAY;
    protected String date;

    /**
     * Sets the description and date for Event Task
     *
     * @param description Description of the Task in String
     * @param date        Date of the Event in String
     */
    public Event(String description, String date) {
        super(description);
        setDate(date);
    }

    /**
     * Gets the type of the task.
     *
     * @return TASK_TYPE.
     */
    @Override
    public String getTaskType() {
        return this.TASK_TYPE;
    }

    /**
     * Gets the date of the task.
     *
     * @return date of the task.
     */
    @Override
    public String getDate() {
        return this.date;
    }

    /**
     * Sets the date of the task.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets all information of the Event task.
     *
     * @return information of the task in String.
     */
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + setPrintDate();
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
        return " (at: " + tempDate + ")";
    }


}
