import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        boolean exitFlag = false;
        int iterator = 0;
        // Problem will come if there are more than 100 tasks
        String[] list = new String[100];
        String toPrint = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(toPrint);
        while(!exitFlag){
            String echoStatement;
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            // In case user adds an extra space
            String testCondition = line.replace(" ","");
            // Exit Condition, sets exitFlag to true
            if(testCondition.equals("bye")){
                exitFlag = true;
                echoStatement = "____________________________________________________________\n"
                        + " Bye. Hope to see you again soon!\n"
                        + "____________________________________________________________\n";
            }
            // Condition to printout the List of tasks
            else if (testCondition.equals("list")){
                // This object acts as a buffer to build strings: they are based on mutable character arrays
                // This to reduce the cost of growing the string
                StringBuilder sb = new StringBuilder();
                for ( int i =0;i<list.length;i++){
                    //Suppose if list is null, break and exit generating the printout
                    if (list[i] == null) break;
                        // No concatenation
                    else{
                        int printOut = i+1;
                        sb.append(printOut);
                        sb.append(". ");
                        sb.append(list[i]);
                        sb.append("\n");
                    }
                }
                echoStatement = sb.toString();
            }
            // Echo condition, exitFlag remains the same
            else{

                list[iterator] = line;

                echoStatement = "____________________________________________________________\n"
                        + "added: "+ list[iterator] + "\n"
                        + "____________________________________________________________\n";
                iterator++;
            }
            System.out.println(echoStatement);
        }

    }
}