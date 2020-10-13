package Duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import Duke.data.task.Task;

import static Duke.common.Messages.MESSAGE_WELCOME;
import static Duke.common.Messages.MESSAGE_GOODBYE;
import static Duke.common.Messages.MESSAGE_SHOW_LIST;
import static Duke.common.Messages.MESSAGE_ADD_NEW_TASK;
import static Duke.common.Messages.MESSAGE_REMOVED_TASK;
import static Duke.common.Messages.MESSAGE_MARKED_AS_DONE;
import static Duke.common.Messages.MESSAGE_INDEX_ERROR;

/**
 * Class representing the UI of Duke.
 */
public class TextUi {

    private static final String DIVIDER = "____________________________________________________________";

    private static final String COMMENT_LINE_ESCAPE_CHAR = "\\\\";
    private static final String COMMENT_LINE_ASTERISK = "\\*";
    private final Scanner in;
    private final PrintStream out;

    /**
     * Handles the input and output of the program.
     */
    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    private boolean removeEmptyLines(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isSpecialCharacters(rawInputLine);
    }

    private boolean isSpecialCharacters(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_ESCAPE_CHAR)
                || rawInputLine.trim().matches(COMMENT_LINE_ASTERISK);
    }

    /**
     * Retrieve User Input, incorrect command will be rejected.
     */
    public String getUserCommand() {
        String fullInputLine = in.nextLine();
        while (removeEmptyLines(fullInputLine)) {
            fullInputLine = in.nextLine();
        }
        return fullInputLine;
    }

    /**
     * Prints Welcome Message.
     */
    public void printWelcomeMessage() {
        printOut(
                DIVIDER,
                MESSAGE_WELCOME,
                DIVIDER);
    }

    /**
     * Prints Exit Message.
     */
    public void printGoodbyeMessage() {
        printOut(DIVIDER, MESSAGE_GOODBYE, DIVIDER);
    }

    /**
     * Prints the list of all of the tasks.
     */
    public void printTaskListView(String toPrint) {
        printOut(DIVIDER, MESSAGE_SHOW_LIST, toPrint, DIVIDER);
    }

    /**
     * Prints created Task.
     *
     * @param task          newly created task.
     * @param numberOfTasks total number of tasks in the list.
     */
    public void printCreatedTask(Task task, int numberOfTasks) {
        String numberOfTaskMessage = "Now you have " + numberOfTasks + " in the list.\n";
        printOut(DIVIDER, MESSAGE_ADD_NEW_TASK, task.toString(), numberOfTaskMessage, DIVIDER);
    }

    /**
     * Prints Deleted task.
     *
     * @param task          task to be deleted.
     * @param numberOfTasks total number of tasks in the list.
     */
    public void printDeleteTaskMessage(Task task, int numberOfTasks) {
        String numberOfTaskMessage = "Now you have " + numberOfTasks + " in the list.\n";
        printOut(DIVIDER, MESSAGE_REMOVED_TASK, task.toString(), numberOfTaskMessage, DIVIDER);
    }

    /**
     * Prints Completed task.
     *
     * @param task task marked as completed.
     */
    public void printTaskAsDoneMessage(Task task) {
        printOut(DIVIDER, MESSAGE_MARKED_AS_DONE, task.toString(), DIVIDER);
    }

    /**
     * Prints Illegal Index Message.
     */
    public void printIllegalIndexMessage() {
        printOut(DIVIDER, MESSAGE_INDEX_ERROR, DIVIDER);
    }

    /**
     * Prints Custom Error Message.
     *
     * @param error Error message to print.
     */
    public void printCustomError(String error) {
        printOut(DIVIDER, error, DIVIDER);
    }

    private void printOut(String... text) {
        for (String t : text) {
            System.out.println(t);
        }
    }
}
