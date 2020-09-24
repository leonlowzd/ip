package Duke;
import Duke.exceptions.IllegalDate;
import Duke.exceptions.IllegalIndex;
import Duke.exceptions.IllegalDescription;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.ToDo;
import Duke.ui.TextUi;

import java.io.IOException;


public class Duke {
    protected static int numberOfTasks = 0;
    //retrieve home directory & set homeDirectory
    private static String home = System.getProperty("user.home");
    protected final static String homeDirectory = home+"/Documents/log.txt";

    private static TextUi ui = new TextUi();
    private static TaskList taskList = new TaskList();

    public static void main(String[] args) throws IOException {
        LogFile logFile = new LogFile();

        ui.showWelcomeMessage("");
        //Read from memory & location to read and write file
        logFile.readFile(homeDirectory);
        boolean hasExit = false;

//        printWelcomeMessage();
        while (!hasExit) {
            String userCommand = ui.getUserCommand();
            // process user input and decide what operation to use
            String operation = extractOperationType(userCommand);

            switch (operation){
                case "bye":
                    hasExit = true;
                    ui.showGoodbyeMessage();
                    break;
                case "list":
                    ui.showTaskListView(taskList.printAllTask());
                    break;

                case "done":
                    try {
                        markTaskAsDone(userCommand);
                    } catch (IllegalIndex e) {
                        ui.showIllegalIndexMessage();
                        continue;
                    }
                    break;
                case "delete":
                    try {
                        deleteTask(userCommand);
                    } catch (IllegalIndex e) {
                        ui.showIllegalIndexMessage();
                        continue;
                    }
                    break;

                case "todo":
                case "deadline":
                case "event":
                    try {
                        String taskDescription = extractDescriptionFromString(operation, userCommand);
                        if (operation.equals("todo")) {
                            createNewTask(operation, taskDescription,null,false,true);
                        }
                        else{

                            String date = extractDateFromString(userCommand);
                            createNewTask(operation, taskDescription ,date ,false,true);

                        }
                    } catch (IllegalDate e) {
                        ui.showIllegalDateMessage();
                        continue;

                    } catch (IllegalDescription e){
                        ui.showIllegalDescriptionMessage();
                        continue;

                    }
                    break;
                default:
                    ui.showIllegalCommandMessage();
                    continue;
            }
            logFile.writeFile(homeDirectory,taskList.printAllTask());
        }
    }
    
    public static void createNewTask(String taskType, String description, String date,
                                     Boolean status, Boolean mode)
            throws IllegalDate, IllegalDescription {
        if(description.isEmpty()) throw new IllegalDescription();
        switch(taskType) {
            case "todo":
                ToDo todo = new ToDo(description);
                taskList.addNewTask(todo);
                numberOfTasks++;
                if (mode) {
                    ui.showCreatedTask(todo,numberOfTasks);
                }
                break;

            case "deadline":
                Deadline deadline = new Deadline(description, date);
                taskList.addNewTask(deadline);
                if (mode) {
                    ui.showCreatedTask(deadline,numberOfTasks);
                }
                break;

            case "event":
                Event event = new Event(description, date);
                taskList.addNewTask(event);
                numberOfTasks++;
                if (mode){
                    ui.showCreatedTask(event,numberOfTasks);
                }
                break;
        }
        if (status) taskList.getTask(numberOfTasks-1).markAsDone();
    }

    // This function extracts the operation type from the user input's String
    private static String extractOperationType(String userInput) {
        userInput = userInput.trim();
        String [] operation = userInput.split(" ");
        return operation[0];
    }

    // This function removes the task type and extracts the description of the task
    private static String extractDescriptionFromString(String type, String userInput)
            throws IllegalDescription, IllegalDate {
        String description;
        try {
            if (type.equals("todo")){
                description = userInput.replace(type, "");
                if (description.contains("/")) throw new IllegalDate();
            } else{
                description = userInput.replace(type, "");
                description = description.substring(0, description.lastIndexOf("/"));
            }
            if (description.replace(" ","").isEmpty()) throw new IllegalDescription();
        } catch ( StringIndexOutOfBoundsException e ) {
            throw new IllegalDate();
        }

        return description;
    }

    // This function extracts the date from the user input
    private static String extractDateFromString(String userInput) throws IllegalDate {
        String date = userInput.substring(userInput.lastIndexOf("/"));
        if (date.isEmpty()) throw new IllegalDate();
        else if (date.contains("/by") == date.contains(("/at"))) {
            throw new IllegalDate();
        }
        return date;

    }

    // This function selects the tagged index from user input and deletes it from the list
    private static void deleteTask (String line) throws IllegalIndex {
        try {
            int selectedIndex = Integer.parseInt(line.split(" ")[1]) - 1;
            if (selectedIndex<0) throw new IllegalIndex();
            numberOfTasks--;
            ui.showDeleteTaskMessage(taskList.getTask(selectedIndex),numberOfTasks);
            taskList.removeTask(selectedIndex);

        } catch (RuntimeException e) {
            throw new IllegalIndex();
        }
    }

    // This function marks the tagged index from user input as complete and prints the statement
    private static void markTaskAsDone (String line) throws IllegalIndex  {
        try {
            int selectedIndex = Integer.parseInt(line.split(" ")[1]) - 1;
            taskList.getTask(selectedIndex).markAsDone();
            ui.showTaskAsDoneMessage(taskList.getTask(selectedIndex));

        } catch (RuntimeException e) {
            throw new IllegalIndex();
        }
    }

}