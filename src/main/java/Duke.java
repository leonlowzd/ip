import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        boolean exitFlag = false;
        int index = 0;
        // Problem will come if there are more than 100 tasks
        Task[] list = new Task[100];
        printWelcomeMessage();
        while (!exitFlag) {
            // Read User input
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            // process user input and decide what operation to use
            String operation = extractOperationType(line);
            String printStatement = null;

            switch (operation){
                case "bye":
                    exitFlag = true;
                    printStatement = printByeMessage();
                    break;

                case "list":
                    printStatement = printFullList(list, index);
                    index--;
                    break;

                case "done":
                    markTaskAsDone(list,line);
                    index--;
                    break;

                case "todo":
                    String toDoDescription = extractDescriptionFromString("todo",line);
                    ToDo t = new ToDo(toDoDescription);
                    list[index] = t;
                    printStatement = printTaskDescription(index, list);
                    break;

                case "deadline":
                    String deadlineDescription = extractDescriptionFromString("deadline", line);
                    String date = extractDateFromString(line);
                    Deadline d = new Deadline(deadlineDescription, date);
                    list[index] = d;
                    printStatement = printTaskDescription(index, list);
                    break;

                case "event":
                    String eventDescription = extractDescriptionFromString("event", line);
                    String eventDate = extractDateFromString(line);
                    Event e = new Event(eventDescription, eventDate);
                    list[index] = e;
                    printStatement = printTaskDescription(index, list);
                    break;

                default:
                    index--;
                    break;
            }
            System.out.println(printStatement);
            index++;
        }
    }

    // This function extracts the operation type from the user input's String
    private static String extractOperationType(String userInput){
        userInput = userInput.trim();
        String [] operation = userInput.split(" ");
        return operation[0];

    }

    // This function removes the task type and extracts the description of the task
    private static String extractDescriptionFromString(String type, String userInput){
        String description;
        if (type.equals("todo")){
            description = userInput.replace(type, "");
        } else{
            description = userInput.replace(type, "");
            description = description.substring(0, description.lastIndexOf("/"));
        }
        return description;
    }

    // This function extracts the date from the user input
    private static String extractDateFromString(String userInput){
        return userInput.substring(userInput.lastIndexOf("/"));
    }

    // This function constructs the printout of the newly added task
    private static String printTaskDescription(int index,  Task[] list){
        int numberOfTask = index+1;
        String printStatement = "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + "     " + list[index] + "\n"
                + "Now you have "+numberOfTask+" in the list.\n"
                + "____________________________________________________________\n";
        return printStatement;
    }

    // This function marks the tagged index from user input as complete and prints the statement
    private static String markTaskAsDone(Task[] list, String line) {
        int selectedIndex = Integer.parseInt(line.split(" ")[1])-1;
        list[selectedIndex].markAsDone();
        return "Nice! I've marked this task as done: \n"
                + list[selectedIndex] + "\n"
                + "____________________________________________________________\n";
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
    private static String printFullList(Task[] list, int index) {
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
            sb.append(list[i]);
            sb.append("\n");
        }
        sb.append("____________________________________________________________\n");
        printStatement = sb.toString();
        return printStatement;
    }
}