package Duke;
import Duke.exceptions.IllegalDate;
import Duke.exceptions.IllegalIndex;
import Duke.exceptions.IllegalDescription;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();
    private static int numberOfTasks = 0;
    public static void main(String[] args) {

        boolean hasExit = false;

        // Problem will come if there are more than 100 tasks
//        Task[] list = new Task[100];
        printWelcomeMessage();
        while (!hasExit) {
            // Read User input
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            // process user input and decide what operation to use
            String operation = extractOperationType(line);
            String printStatement;

            switch (operation){
                case "bye":
                    hasExit = true;
                    printStatement = printByeMessage();
                    break;

                case "list":
                    printStatement = printFullList(numberOfTasks);
                    break;

                case "done":
                    try {
                        printStatement = markTaskAsDone(line);
                    } catch ( IllegalIndex e) {
                        returnIllegalIndexStatement();
                        continue;
                    }
                    break;
                case "delete":
                    try {
                        printStatement = deleteTask(line);
                    } catch ( IllegalIndex e) {
                        returnIllegalIndexStatement();
                        continue;
                    }
                    break;

                case "todo":
                    try {
                        String toDoDescription = extractDescriptionFromString("todo",line);
                        ToDo t = new ToDo(toDoDescription);
                        list.add(t);
                        printStatement = printNIncrementTask(numberOfTasks);

                    } catch (IllegalDescription e) {
                        printEmptyDescription();
                        continue;

                    } catch (IllegalDate e) {
                        printEmptyDate();
                        continue;
                    }
                    break;

                case "deadline":
                    try {
                        String deadlineDescription = extractDescriptionFromString("deadline", line);
                        String date = extractDateFromString(line);
                        Deadline d = new Deadline(deadlineDescription, date);
                        list.add(d);
                        printStatement = printNIncrementTask(numberOfTasks);

                    } catch (IllegalDescription e) {
                        printEmptyDescription();
                        continue;

                    } catch (IllegalDate e) {
                        printEmptyDate();
                        continue;
                    }

                    break;

                case "event":
                    try {
                        String eventDescription = extractDescriptionFromString("event", line);
                        String eventDate = extractDateFromString(line);
                        Event e = new Event(eventDescription, eventDate);
                        list.add(e);
                        printStatement = printNIncrementTask(numberOfTasks);

                    } catch ( IllegalDescription e) {
                        printEmptyDescription();
                        continue;

                    } catch ( IllegalDate e) {
                        printEmptyDate();
                        continue;
                    }

                    break;

                default:
                    printUnknownMessage();
                    continue;
            }
            System.out.println(printStatement);
        }
    }

    // This function extracts the operation type from the user input's String
    private static String extractOperationType(String userInput) {
        userInput = userInput.trim();
        String [] operation = userInput.split(" ");
        return operation[0];
    }

    // This function removes the task type and extracts the description of the task
    private static String extractDescriptionFromString(String type, String userInput) throws IllegalDescription, IllegalDate {
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

    private static void printUnknownMessage () {
        System.out.println("____________________________________________________________\n"+
                "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"+
                "____________________________________________________________\n");
    }

    private static void returnIllegalIndexStatement () {
        System.out.println("____________________________________________________________\n" +
                "☹ OOPS!!! Illegal index, it does not exist in the list\n" +
                "____________________________________________________________\n");
    }
    //print out empty date function
    private static void printEmptyDate () {
        System.out.println("____________________________________________________________\n" +
                "☹ OOPS!!! Invalid Date input.\n" +
                "____________________________________________________________\n");
    }
    // print out empty disc function
    private static void printEmptyDescription () {
        System.out.println("____________________________________________________________\n" +
                "☹ OOPS!!! The Description cannot be empty.\n" +
                "____________________________________________________________\n");
    }

    // This function constructs the printout of the newly added task
    private static String printNIncrementTask(int index){
        numberOfTasks++;
        return "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + "     " + list.get(index) + "\n"
                + "Now you have "+numberOfTasks+" in the list.\n"
                + "____________________________________________________________\n";
    }
    // This function selects the tagged index from user input and deletes it from the list
    private static String deleteTask (String line) throws IllegalIndex {
        String statement;
        try {
            int selectedIndex = Integer.parseInt(line.split(" ")[1]) - 1;
            numberOfTasks--;
            System.out.println(selectedIndex);
            statement = "____________________________________________________________\n"
                    + "Noted. I've removed this task:  \n"
                    + list.get(selectedIndex) +"\n"
                    + "Now you have "+numberOfTasks+" in the list.\n"
                    + "____________________________________________________________\n";
            list.remove(list.get(selectedIndex));
        } catch (RuntimeException e) {
            numberOfTasks++;
            throw new IllegalIndex();
        }
        return statement;
    }


    // This function marks the tagged index from user input as complete and prints the statement
    private static String markTaskAsDone (String line) throws IllegalIndex  {
        String statement;
        try {
            int selectedIndex = Integer.parseInt(line.split(" ")[1]) - 1;
            list.get(selectedIndex).markAsDone();
            statement = "____________________________________________________________\n"
                    +"Nice! I've marked this task as done: \n"
                    + list.get(selectedIndex) + "\n"
                    + "____________________________________________________________\n";

        } catch (RuntimeException e) {
            throw new IllegalIndex();
        }
        return statement;
    }

    // This function prints the program's welcome message
    private static void printWelcomeMessage(){
        String toPrint = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(toPrint);
    }

    // This function prints the program's goodbye message
    private static String printByeMessage() {
        return "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
    }

    // This function prints the full list of Tasks
    private static String printFullList(int index) {
        String printStatement;
        // This object acts as a buffer to build strings: they are based on mutable character arrays
        // This to reduce the cost of growing the string
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i< index; i++){
            int printOut = i+1;
            sb.append(printOut);
            sb.append(". ");
            sb.append(list.get(i));
            sb.append("\n");
        }
        sb.append("____________________________________________________________\n");
        printStatement = sb.toString();
        return printStatement;
    }
}