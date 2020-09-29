package Duke.data;

import Duke.commands.AddTask;
import Duke.data.task.Task;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text file

import static Duke.common.TaskNames.*;


public class Storage {
    private static final String TEXT_DIVIDER = " | ";
    private final TaskList tasks;
    public Storage(TaskList tasks) {
        this.tasks = tasks;
    }

    public void writeFile(String homeDirectory) {
        try {
            File file = new File(homeDirectory);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            // Write in file
            StringBuilder sb = convertListToTextFormat(this.tasks);
            bw.write(sb.toString());
            // Close connection
            bw.close();
        } catch (IOException e) {
            System.out.println("Unable to write File");
        }

    }

    private StringBuilder convertListToTextFormat(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getAllTasks();
        StringBuilder sb = new StringBuilder();
        for (Task task: tasks) {
            String type = task.getTaskType().replace("[","").replace("]","") + TEXT_DIVIDER;
            String doneStatus = task.getStatus() + TEXT_DIVIDER;
            String description = task.getDescription();
            String date = "";
            if (!(type.equals("T | "))) {
                date = TEXT_DIVIDER + task.getDate();
            }
            sb.append(type).append(doneStatus).append(description).append(date).append("\n");
        }
        return sb;
    }

    public void readFile(String homeDirectory) {
        try {
            File file = new File(homeDirectory);
            if (!file.exists()){
                return;
            }
            Scanner myReader = new Scanner(file);
            convertTextToTask(myReader);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    private void convertTextToTask(Scanner myReader) {
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] list = data.split("\\|");
            String type = extractTaskType(list[0]);
            boolean status = extractDoneStatus(list[1].trim());
            String description = list[2].trim();
            String date = "";
            if (!(type.equals(TODO))) date = list[2].trim();

            AddTask add = new AddTask(type,description,date,false);
            add.setData(tasks);
            add.run();
            if (status){
                tasks.getTask(tasks.getNumberOfTasksInList()-1).markAsDone();
            }

        }
    }

    private boolean extractDoneStatus(String status) {
        boolean isFinished;
        isFinished = !status.equals("0");
        return isFinished;
    }

    private String extractTaskType(String type) {
        String taskType;
        if (type.contains("E")) {
            taskType = EVENT;
        } else if(type.contains("T")) {
            taskType = TODO;
        } else {
            taskType = DEADLINE;
        }
        return taskType;
    }

}
