package Duke;

import Duke.task.Task;

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

    public String printAllTask() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Task task :taskList) {
            sb.append(index+1);
            sb.append(". ");
            sb.append(task);
            sb.append("\\n");
            index++;
        }
        return sb.toString();
    }

}
