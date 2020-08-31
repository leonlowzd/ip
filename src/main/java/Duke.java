import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {

        boolean exitFlag = false;
        int index = 0;
        // Problem will come if there are more than 100 tasks
        Task[] list = new Task[100];
        printWelcomeMessage();
        while (!exitFlag) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            // In case user adds an extra space
            String testCondition = line.replaceAll(" ","");
//            System.out.println("testing: "+testCondition);
            if (testCondition.equals("bye")) {
                exitFlag = true;
                printByeMessage();
            }
            else {
                String printStatement;
                // Condition to printout the List of tasks
                if (testCondition.equals("list")) {
                    printStatement = printFullList(list,index);
                }
                // Condition to mark task as completed
                else if (testCondition.contains("done")) {
                    printStatement = markTaskAsDone(list, line);
                }
                // add task condition, exitFlag remains the same
                else {
                    if (line.contains("todo")) {
                        // remove todo indicator
                        line = line.replace("todo", "");
                        ToDo task = new ToDo(line);
                        list[index] = task;
                    } else if (line.contains("deadline")) {
                        String deadlineDescription = extractDescriptionFromString("deadline",line);
                        String date = extractDateFromString(line);
                        Deadline task = new Deadline(deadlineDescription,date);
                        list[index] = task;
                    } else if (line.contains("event")) {
                        String eventDescription = extractDescriptionFromString("event",line);
                        String date = extractDateFromString(line);
                        Event task = new Event(eventDescription,date);
                        list[index] = task;
                    } else {
                        // when user inputs something unrecognisable
                        continue;
                    }
                    printStatement = printTaskDescription(index, list);
                    index++;
                }
                System.out.println(printStatement);
            }
        }
    }
    // This function removes the task type and extracts the description of the task
    private static String extractDescriptionFromString(String type, String userInput){
        String deadlineDescription = userInput.replace(type, "");
        return deadlineDescription.substring(0, deadlineDescription.lastIndexOf("/"));
    }
    // This function extracts the date from the user input
    private static String extractDateFromString(String userInput){
        return userInput.substring(userInput.lastIndexOf("/"));
    }

    private static String printTaskDescription(int index,  Task[] list){
        int numberOfTask = index+1;
        String printStatement = "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + "     " + list[index] + "\n"
                + "Now you have "+numberOfTask+" in the list.\n"
                + "____________________________________________________________\n";
        return printStatement;
    }


    private static String markTaskAsDone(Task[] list, String line) {
        int selectedIndex = Integer.parseInt(line.split(" ")[1])-1;
        list[selectedIndex].markAsDone();
        String printStatement = "Nice! I've marked this task as done: \n"
                + list[selectedIndex] + "\n"
                + "____________________________________________________________\n";
        return printStatement;
    }
    private static void printWelcomeMessage(){
        String toPrint = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(toPrint);
    }
    private static void printByeMessage() {
        String toPrint;
        toPrint = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(toPrint);
    }

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