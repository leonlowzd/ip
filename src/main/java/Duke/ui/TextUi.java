package Duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import Duke.data.task.Task;

import static Duke.common.Messages.*;


/**
 * Text UI of the application.
 */
public class TextUi {

    /**
     * A platform independent line separator.
     */

    private static final String DIVIDER = "____________________________________________________________";


    /**
     * Format of a comment input line. Comment lines are silently consumed when reading user input.
     */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean removeEmptyLines(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    /**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * Echos the command back to the user.
     *
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        String fullInputLine = in.nextLine();
        while (removeEmptyLines(fullInputLine)) {
            fullInputLine = in.nextLine();
        }
        return fullInputLine;
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     *
     */
    public void printWelcomeMessage() {
        printOut(
                DIVIDER,
                MESSAGE_WELCOME,
                DIVIDER);
    }

    public void showGoodbyeMessage() {
        printOut(DIVIDER, MESSAGE_GOODBYE, DIVIDER);
    }


    /**
     * Prints message(s) to the user
     */
    public void printOut(String... text) {
        for (String t : text) {
            System.out.println(t);
        }
    }

    /**
     * Shows a list of persons to the user, formatted as an indexed list.
     */
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


    public void printIllegalDescriptionMessage() {
        printOut(DIVIDER, MESSAGE_DESCRIPTION_ERROR, DIVIDER);
    }

    public void printIllegalIndexMessage() {
        printOut(DIVIDER, MESSAGE_INDEX_ERROR, DIVIDER);
    }

    public void printCustomError(String error) {
        printOut(error);
    }
}
