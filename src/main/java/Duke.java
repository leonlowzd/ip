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
            String testCondition = line.replace(" ", "");
            // Exit Condition, sets exitFlag to true
            if (testCondition.equals("bye")) {
                exitFlag = true;
                printByeMessage();
            } else {
                String echoStatement;
                // Condition to printout the List of tasks
                if (testCondition.equals("list")) {
                    echoStatement = printFullList(list, index);
                }
                // Condition to mark task as completed
                else if (testCondition.contains("done")) {
                    echoStatement = markTaskAsDone(list, line);
                }
                // Echo condition, exitFlag remains the same
                else {
                    if (line.contains("todo")) {
                        line = line.replace("todo", "");
                        ToDo td = new ToDo(line);
                        list[index] = td;
                        echoStatement = printTaskDescription(index, list);
                    } else if (line.contains("deadline")) {
                        String deadlineDescription = line.replace("deadline", "");
                        deadlineDescription = deadlineDescription.substring(0, deadlineDescription.lastIndexOf("/by"));
                        Deadline d = new Deadline(deadlineDescription);
                        d.setDeadline(line.substring(line.lastIndexOf("/")));
                        list[index] = d;
                        echoStatement = printTaskDescription(index, list);

                    } else {
                        String eventDescription = line.replace("event", "");
                        eventDescription = eventDescription.substring(0, eventDescription.lastIndexOf("/at"));
                        Event e = new Event(eventDescription);
                        list[index] = e;
                        echoStatement = printTaskDescription(index, list);
                    }
                    index++;
                }
                System.out.println(echoStatement);

            }

        }
    }

    private static String printTaskDescription(int index,  Task[] list){
        int numberOfTask = index+1;
        String printStatement = "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                +"     " + list[index] + "\n"
                + "Now you have "+numberOfTask+" in the list .\n"
                + "____________________________________________________________\n";
        return printStatement;
    }


    private static String markTaskAsDone(Task[] list, String line) {
        int selectedIndex = Integer.parseInt(line.split(" ")[1])-1;
        list[selectedIndex].markAsDone();
        String printStatement = "Nice! I've marked this task as done: \n"
                + list[selectedIndex].getStatusIcon()+" "+ list[selectedIndex].description + "\n"
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
        String echoStatement;
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
        echoStatement = sb.toString();
        return echoStatement;
    }
}