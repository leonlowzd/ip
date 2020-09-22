package Duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import Duke.task.Task;

import static Duke.common.Messages.*;


/**
 * Text UI of the application.
 */
public class TextUi {

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "____________________________________________________________";

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
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
    private boolean shouldIgnore(String rawInputLine) {
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
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        String fullInputLine = in.nextLine();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        return fullInputLine;
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     * @param version current version of the application.
     */
    public void showWelcomeMessage(String version) {
        showToUser(
                DIVIDER,
                MESSAGE_WELCOME,
                DIVIDER);
    }

    public void showGoodbyeMessage() {
        showToUser(DIVIDER, MESSAGE_GOODBYE, DIVIDER);
    }


    /** Shows message(s) to the user */
    public void showToUser(String... message) {

        for (String m : message) {
            out.println(m.replace("\\n", LS));
        }
    }

    /**
     * Shows a list of persons to the user, formatted as an indexed list.
     */
    public void showTaskListView(ArrayList<Task> Tasks) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Task task : Tasks) {
            sb.append(index+1+". ");
            sb.append(task);
            sb.append("\\n");
            index++;
        }
        showToUser(DIVIDER, MESSAGE_SHOW_LIST,sb.toString(), DIVIDER);
    }

    public void showCreatedTask(Task task, int numberOfTasks){
        String numberOfTaskMessage = "Now you have "+numberOfTasks+" in the list.\n";
        showToUser(DIVIDER, MESSAGE_ADD_NEW_TASK, task.toString(), numberOfTaskMessage, DIVIDER);
    }

    public void showDeleteTaskMessage(Task task, int selectedIndex) {
        String numberOfTaskMessage = "Now you have "+selectedIndex+" in the list.\n";
        showToUser(DIVIDER, MESSAGE_REMOVED_TASK, task.toString(), numberOfTaskMessage, DIVIDER);
    }
    public void showTaskAsDoneMessage(Task task) {
        showToUser(DIVIDER, MESSAGE_MARKED_AS_DONE,task.toString(), DIVIDER);
    }

    public void showIllegalCommandMessage() {
        showToUser(DIVIDER,MESSAGE_INVALID_COMMAND_FORMAT,DIVIDER);
    }

    public void showIllegalDateMessage(){
        showToUser(DIVIDER, MESSAGE_DATE_ERROR, DIVIDER);
    }

    public void showIllegalDescriptionMessage(){
        showToUser(DIVIDER, MESSAGE_DESCRIPTION_ERROR, DIVIDER);
    }

    public void showIllegalIndexMessage(){
        showToUser(DIVIDER, MESSAGE_INDEX_ERROR, DIVIDER);
    }

}
