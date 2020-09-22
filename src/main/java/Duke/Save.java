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
    private final String textDivider = " | ";
    private static final String[] toDiscard = {"____________________________________________________________",
            "Here are the tasks in your list:"};

    public Save() {
    }
    public void writeFile(String homeDirectory, String statement) throws IOException {

        File file = new File(homeDirectory);
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        // Write in file
        StringBuilder sb = convertListToTextFormat(statement);
        bw.write(sb.toString());
        // Close connection
        bw.close();
    }

    private StringBuilder convertListToTextFormat(String statement) {
        String[] lines = statement.split("\\r?\\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines){
            if (!(line.contains(toDiscard[0])||line.contains(toDiscard[1]))) {
                extractListToText(sb, line);
            }
        }
        return sb;
    }

    private void extractListToText(StringBuilder sb, String line) {
        String extractedType= line.split("]")[0].replace("[","") +textDivider;
        String extractedDoneStatus = convertDoneStatusToText(line);
        String extractedDescription = convertDescriptionToText(line, extractedType);
        String extractedDate = convertDateToText(extractedType, line);
        sb.append(extractedType).append(extractedDoneStatus).append(extractedDescription).append(extractedDate).append("\n");
    }

    private String convertDescriptionToText(String line, String extractedType) {
        String extractedDescription = line.split(" ",3)[2].trim();
        if (extractedType.contains("E")) {
            extractedDescription = extractedDescription.split("\\(at:")[0].trim();
        }
        else if (extractedType.contains("D")) {
            extractedDescription = extractedDescription.split("\\(by:")[0].trim();
        }
        return extractedDescription;
    }

    private String convertDateToText(String extractedType, String line) {
        line = line.split(" ",3)[2].trim();
        String extractedDate = "";
        if(extractedType.contains("E")) {
            extractedDate = textDivider+ line.split("\\(at:")[1].trim().replace(")","").trim();
        } else if (extractedType.contains("D")) {
            extractedDate = textDivider+ line.split("\\(by:")[1].replace(")","").trim();
        }
        return extractedDate;
    }

    private String convertDoneStatusToText(String line) {
        String extractedDoneStatus = line.split("]")[1].replace("[","");
        if (extractedDoneStatus.equals("\u2713")) {
            extractedDoneStatus = "1"+textDivider;
        }
        else {
            extractedDoneStatus = "0"+textDivider;
        }
        return extractedDoneStatus;
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
        } catch (IllegalDescription | IllegalDate illegalDescription) {
            illegalDescription.printStackTrace();
        }
    }

    private void convertTextToTask(Scanner myReader) throws IllegalDate, IllegalDescription {
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String taskType = extractTaskType(data.split("\\|")[0].trim());
            boolean status = extractDoneStatus(data.split("\\|")[1].trim());
            String description = extractDescription(data.split("\\|")[2].trim(),taskType);
            String date = extractDate(data,taskType);
            // Create New Task from Duke Main function
            Duke.createNewTask(taskType,description,date,status,false);
        }
    }

    private static String extractTaskType(String type) {
        String taskType;
        if (type.contains("E")) {
            taskType = "event";
        }
        else if(type.contains("T")) {
            taskType = "todo";
        }
        else {
            taskType = "deadline";
        }
        return taskType;
    }

    private static boolean extractDoneStatus(String status) {
        return status.contains("1");
    }

    private static String extractDescription(String description, String type) {
        if (type.contains("deadline")){
            description = description.split("by:")[0];
        }
        else {
            description = description.split("at:")[0];
        }
        description = description.replace("(","");
        return description;
    }

    private static String extractDate(String line, String type) {
        String date;
        if (type.contains("deadline")) {
            date = "/by "+line.split("\\|")[3].trim();
        }
        else if (type.contains("event")) {
            date = "/at "+line.split("\\|")[3].trim();
        }
        else {
            date = "";
        }
        return date;
    }

}
