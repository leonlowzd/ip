package Duke;

import Duke.exceptions.IllegalDate;
import Duke.exceptions.IllegalDescription;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text file

public class Save extends Duke {
    private static String[] toDiscard = {"____________________________________________________________",
            "Here are the tasks in your list:"};

    public Save() {
    }
    public void writeFile(String homeDirectory, String statement) throws IOException {

        File file = new File(homeDirectory);
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        // Write in file
        bw.write(statement.trim());

        // Close connection
        bw.close();
    }

    public void readFile(String homeDirectory) {
        try {
            File file = new File(homeDirectory);
            if(!file.exists()) return;
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.contains(toDiscard[0])||data.contains(toDiscard[1])) continue;
//                System.out.println(data);
                String taskType = extractTaskType(data);
                boolean status = extractDoneStatus(data);
                String description = extractDescription(data,taskType);
                String date = null;
                if (taskType.equals("event")||taskType.equals("deadline")){
                    date = extractDate(data,taskType);
                }
                Duke.createNewTask(taskType,description,date);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IllegalDescription | IllegalDate illegalDescription) {
            illegalDescription.printStackTrace();
        }
    }


    private static String extractTaskType(String line) {
        String taskType;
        String operation = line.split("]")[0];
        if (operation.contains("T")) taskType = "todo";
        else if (operation.contains("D")) taskType = "deadline";
        else taskType = "event";
        return taskType;
    }

    private static boolean extractDoneStatus(String line) {
        boolean isDone;
        String operation = line.split("]")[2];
        isDone = operation.contains("\u2713");
//        System.out.println(isDone);
        return isDone;
    }

    private static String extractDescription(String line, String type) {
        String statement;
        statement = line.split(" ",3)[2].trim();
        if (type.contains("deadline")||type.contains("event")){
            if (type.contains("deadline")) statement = statement.split("by:")[0];
            else  statement = statement.split("at:")[0];
            statement = statement.replace("(","");
        }
        return statement;
    }
    private static String extractDate(String line, String type) {
        String operation = line.split(":")[1].trim();
        if(type.contains("deadline")) operation = "/by "+operation;
        else operation = "/at "+operation;
        operation = operation.replace(")","").trim();
//        System.out.println(operation);
        return operation;
    }

}
