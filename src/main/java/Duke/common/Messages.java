package Duke.common;

/**
 * Dictionary for Duke's messages
 */
public class Messages {

    public static final String MESSAGE_GOODBYE = "Good bye!";
    public static final String MESSAGE_ADD_NEW_TASK = "Nice, I've added the following task to the list";
    public static final String MESSAGE_REMOVED_TASK = "Noted. I've removed this task: ";
    public static final String MESSAGE_SHOW_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_MARKED_AS_DONE = "Nice! I've marked this task as done: ";
    public static final String MESSAGE_WELCOME =
            " Hello! I'm Duke\n" + " What can I do for you?\n";
    public static final String MESSAGE_INVALID_COMMAND_ERROR =
            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_INDEX_ERROR =
            "☹ OOPS!!! Illegal index, it does not exist in the list";
    public static final String MESSAGE_DATE_ERROR =
            "☹ OOPS!!! Illegal date format!! /at for events and /by for deadlines";
    public static final String MESSAGE_DATE_EMPTY =
            "☹ OOPS!!! Empty date";
    public static final String MESSAGE_DESCRIPTION_ERROR =
            "☹ OOPS!!! Empty description";
    public static final String MESSAGE_UNABLE_TO_WRITE_FILE = "Unable to write file to text file.";
    public static final String MESSAGE_UNABLE_TO_OPEN_FILE = "Unable to open file from memory.";
    public static final String MESSAGE_FILE_CORRUPTED =
            "Text file is corrupted. Some of the pre-existing tasks might not be loaded.";
}
