
public class Deadline extends Task{
    protected String deadline;
    protected final String TASK_TYPE = "[D]";

    public Deadline(String description) {
        super(description);
    }
    public String getTaskType(){
        return this.TASK_TYPE;
    }

    public void setDeadline(String deadline){
        deadline = deadline.replace("/by"," (by:");
        deadline += ")";
        this.deadline = deadline;
    }
    @Override
    public String toString(){
        return getTaskType() + super.toString() + getDate();
    }
    public String getDate(){
        return this.deadline;
    }
}
