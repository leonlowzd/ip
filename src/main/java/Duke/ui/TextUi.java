package Duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import Duke.data.task.Task;
import static Duke.common.Messages.*;

public class TextUi {

    private static final String DIVIDER = "____________________________________________________________";

    private static final String COMMENT_LINE_FORMAT_REGEX = "#.\\*";
    private static final String COMMENT_LINE_ESCAPE_CHAR = "\\\\";
    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    private boolean removeEmptyLines(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine) || isEscapeKey(rawInputLine);
    }

    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    private boolean isEscapeKey(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_ESCAPE_CHAR);
    }

    public String getUserCommand() {
        String fullInputLine = in.nextLine();
        while (removeEmptyLines(fullInputLine)) {
            fullInputLine = in.nextLine();
        }
        return fullInputLine;
    }

    public void printWelcomeMessage() {
        printOut(
                DIVIDER,
                MESSAGE_WELCOME,
                DIVIDER);
    }

    public void showGoodbyeMessage() {
        printOut(DIVIDER, MESSAGE_GOODBYE, DIVIDER);
    }


    public void printOut(String... text) {
        for (String t : text) {
            System.out.println(t);
        }
    }

    public void printTaskListView(String toPrint) {
        printOut(DIVIDER, MESSAGE_SHOW_LIST, toPrint, DIVIDER);
    }

    public void printCreatedTask(Task task, int numberOfTasks) {
        String numberOfTaskMessage = "Now you have " + numberOfTasks + " in the list.\n";
        printOut(DIVIDER, MESSAGE_ADD_NEW_TASK, task.toString(), numberOfTaskMessage, DIVIDER);
    }

    public void printDeleteTaskMessage(Task task, int selectedIndex) {
        String numberOfTaskMessage = "Now you have " + selectedIndex + " in the list.\n";
        printOut(DIVIDER, MESSAGE_REMOVED_TASK, task.toString(), numberOfTaskMessage, DIVIDER);
    }

    public void printTaskAsDoneMessage(Task task) {
        printOut(DIVIDER, MESSAGE_MARKED_AS_DONE, task.toString(), DIVIDER);
    }

    public void printIllegalIndexMessage() {
        printOut(DIVIDER, MESSAGE_INDEX_ERROR, DIVIDER);
    }

    public void printCustomError(String error) {
        printOut(DIVIDER, error, DIVIDER);
    }
}
