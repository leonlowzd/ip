import java.util.Scanner;

public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        }

        public void markAsDone(){
            this.isDone = true;
        }
    }

    public static void main(String[] args) {

        boolean exitFlag = false;
        int index = 0;
        // Problem will come if there are more than 100 tasks
        Task[] list = new Task[100];
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
                        sb.append("[");
                        sb.append(list[i].getStatusIcon());
                        sb.append("] ");
                        sb.append(list[i].description);
                        sb.append("\n");
                    }
                }
                echoStatement = sb.toString();
            }
            // Condition to mark task as completed
            else if(testCondition.contains("done")){
                int selectedIndex = Integer.parseInt(line.split(" ")[1])-1;
                list[selectedIndex].markAsDone();
                echoStatement = "Nice! I've marked this task as done: \n"
                        +"["+ list[selectedIndex].getStatusIcon() +"] "+ list[selectedIndex].description + "\n"
                        + "____________________________________________________________\n";
            }
            // Echo condition, exitFlag remains the same
            else{
                Task t = new Task(line);
                list[index] = t;

                echoStatement = "____________________________________________________________\n"
                        + "added: "+ list[index].description + "\n"
                        + "____________________________________________________________\n";
                index++;
            }
            System.out.println(echoStatement);
        }

    }
}