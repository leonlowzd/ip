package Duke.data;

import Duke.data.task.Task;

import java.util.ArrayList;

/**
 * Represents the task in a list.
 */
public class TaskList {

    ArrayList<Task> taskList;

    /**
     * Constructs empty Task List.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Add new Task into the list
     */
    public void addNewTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Remove Task from the list
     *
     * @param index index to remove from
     */
    public void removeTask(int index) {
        taskList.remove(index);
    }

    /**
     * Returns Task from the list
     *
     * @param index index to retrieve
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the number of tasks in the list
     */
    public int getNumberOfTasksInList() {
        return taskList.size();
    }

    /**
     * Returns the entire Task List
     */
    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

}
