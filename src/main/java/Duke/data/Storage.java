package Duke.data;

import Duke.commands.AddTaskCommand;
import Duke.data.task.Task;
import Duke.exceptions.FileCorrupted;
import Duke.exceptions.IllegalDate;
import Duke.exceptions.IllegalDescription;
import Duke.exceptions.IllegalType;
import Duke.ui.TextUi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static Duke.common.TaskNames.TODO;
import static Duke.common.TaskNames.EVENT;
import static Duke.common.TaskNames.DEADLINE;


public class Storage {
    private static final String TEXT_DIVIDER = " | ";
    private static TextUi ui = new TextUi();
    private final TaskList tasks;

    public Storage(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Write current TaskList into text file
     *
     * @param homeDirectory Directory to store the text file
     */
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
            ui.printCustomError("Unable to write file to text file.");
        }

    }

    private StringBuilder convertListToTextFormat(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getAllTasks();
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            String type = task.getTaskType().replace("[", "").replace("]", "") + TEXT_DIVIDER;
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

    /**
     * Reads text file and creates Tasks based in reference from the text file.
     *
     * @param homeDirectory Directory to store the text file
     */
    public void readFile(String homeDirectory) {
        try {
            File file = new File(homeDirectory);
            if (!file.exists()) {
                return;
            }
            Scanner myReader = new Scanner(file);
            convertTextToTask(myReader);
            myReader.close();
        } catch (FileNotFoundException e) {
            ui.printCustomError("Unable to open file from memory.");
        } catch (FileCorrupted e) {
            ui.printCustomError("Text file is corrupted. Some of the pre-existing tasks might not be loaded.");
        }

    }

    private void convertTextToTask(Scanner myReader) throws FileCorrupted {
        try {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] list = data.split("\\|");
                String type = extractTaskType(list[0]);
                boolean status = extractDoneStatus(list[1].trim());
                String description = list[2].trim();
                String date = "";
                if (!(type.equals(TODO))) {
                    date = list[3].trim();
                }
                if (description.isEmpty() || date.isEmpty()&&(!type.equals(TODO))) {
                    throw new FileCorrupted();
                }
                AddTaskCommand add = new AddTaskCommand(type, description, date, false);
                add.setData(tasks);
                add.run();
                if (status) {
                    tasks.getTask(tasks.getNumberOfTasksInList() - 1).markAsDone();
                }
            }
        } catch (FileCorrupted fileCorrupted) {
            throw new FileCorrupted();
        }

    }

    private boolean extractDoneStatus(String status) {
        boolean isFinished;
        isFinished = !status.equals("0");
        return isFinished;
    }

    private String extractTaskType(String type) throws FileCorrupted {
        String taskType;
        if (type.contains("E")) {
            taskType = EVENT;
        } else if (type.contains("T")) {
            taskType = TODO;
        } else if (type.contains("D")){
            taskType = DEADLINE;
        } else {
            throw new FileCorrupted();
        }
        return taskType;
    }

}
