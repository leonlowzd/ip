package Duke.commands;

import Duke.task.Task;

abstract public class Command {
    protected Task task;
    private int numberOfTask = -1;

    public Command(int targetIndex) {
        this.setNumberOfTask(targetIndex);
    }

    public Command() {

    }


    public int getNumberOfTask() {
        return numberOfTask;
    }

    public void setNumberOfTask(int numberOfTask) {
        this.numberOfTask = numberOfTask;
    }
}
