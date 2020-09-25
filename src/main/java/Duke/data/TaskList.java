package Duke.data;

import Duke.data.task.Task;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addNewTask(Task newTask) {
        taskList.add(newTask);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getNumberOfTasksInList() {
        return taskList.size();
    }

    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

}
