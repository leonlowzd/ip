package Duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static Duke.common.TaskNames.DEADLINE_DISPLAY;

/**
 * Class representing the Deadline Task.
 */
public class Deadline extends Task {
    protected final String TASK_TYPE = DEADLINE_DISPLAY;
    protected String date;

    /**
     * Sets the description and date for Deadline Task
     *
     * @param description Description of the Task in String
     * @param date        Date of the Event in String
     */
    public Deadline(String description, String date) {
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
     * Gets all information of the Deadline task.
     *
     * @return information of the task in String.
     */
    @Override
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
        return " (by: " + tempDate + ")";
    }
}
