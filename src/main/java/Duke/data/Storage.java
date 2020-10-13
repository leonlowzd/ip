package Duke.data;

import Duke.commands.AddTaskCommand;
import Duke.data.task.Task;
import Duke.exceptions.FileCorrupted;
import Duke.ui.TextUi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static Duke.common.Messages.*;
import static Duke.common.TaskNames.*;

/**
 * Class handling the reading and writing of existing task into/out to a text file.
 *
 */
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
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // Write in file
            StringBuilder stringBuilder = convertListToTextFormat(this.tasks);
            bufferedWriter.write(stringBuilder.toString());
            // Close connection
            bufferedWriter.close();
        } catch (IOException e) {
            ui.printCustomError(MESSAGE_UNABLE_TO_WRITE_FILE);
        }

    }

    private StringBuilder convertListToTextFormat(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getAllTasks();
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            String type = task.getTaskType().replace("[", "").replace("]", "") + TEXT_DIVIDER;
            String doneStatus = task.getStatus() + TEXT_DIVIDER;
            String description = task.getDescription();
            String date = "";
            if (!(type.equals("T | "))) {
                date = TEXT_DIVIDER + task.getDate();
            }
            stringBuilder.append(type).append(doneStatus).append(description).append(date).append("\n");
        }
        return stringBuilder;
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
            ui.printCustomError(MESSAGE_UNABLE_TO_OPEN_FILE);
        } catch (FileCorrupted e) {
            ui.printCustomError(MESSAGE_FILE_CORRUPTED);
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
        if (type.contains(EVENT_DISPLAY)) {
            taskType = EVENT;
        } else if (type.contains(TODO_DISPLAY)) {
            taskType = TODO;
        } else if (type.contains(DEADLINE_DISPLAY)){
            taskType = DEADLINE;
        } else {
            throw new FileCorrupted();
        }
        return taskType;
    }

}
