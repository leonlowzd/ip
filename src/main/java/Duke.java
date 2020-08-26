import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean exitFlag = false;
        String toPrint = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(toPrint);
        while(!exitFlag){
            String echoStatement;
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            String testExit = line.replace(" ","");
            // Exit Condition, sets exitFlag to true
            if(testExit.equals("bye")){
                exitFlag = true;
                echoStatement = "____________________________________________________________\n"
                        + " Bye. Hope to see you again soon!\n"
                        + "____________________________________________________________\n";
            }
            // Echo condition, exitFlag remains the same
            else{
                echoStatement = "____________________________________________________________\n"
                        + line + "\n"
                        + "____________________________________________________________\n";
            }
            System.out.println(echoStatement);
        }

    }
}