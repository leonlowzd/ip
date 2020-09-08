
public class Deadline extends Task{
    protected String deadline;
    protected final String TASK_TYPE = "[D]";

    public Deadline(String description, String deadline) throws EmptyDate {
        super(description);
        if(deadline.contains("/by")) setDeadline(deadline);
        else throw new EmptyDate();


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
