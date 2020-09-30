package Duke;

import Duke.commands.AddTaskCommand;
import Duke.exceptions.IllegalDate;

/**
 * Extracts user arguments to Strings
 */
public class UserInputExtractor extends Parser {
    public UserInputExtractor() {
    }

    protected static String getDescription(String typeOfTask, String arguments) {
        String description;
        if (typeOfTask.equals(AddTaskCommand.COMMAND_WORD_TODO)) description = arguments;
        else {
            description = arguments.substring(0, arguments.lastIndexOf("/")).trim();
        }
        return description;
    }

    protected static String getDate(String arguments, String eventDateIdentifier) throws IllegalDate {
        String date;
        date = arguments.substring(arguments.indexOf(eventDateIdentifier));
        date = date.replace(eventDateIdentifier, "").trim();
        if (date.isEmpty()) throw new IllegalDate();
        return date;
    }

    protected static int getIndexFromString(String arguments) {
        int index;
        try {
            index = Integer.parseInt(arguments.trim()) - 1;

        } catch (RuntimeException e) {
            index = -1;
        }
        return index;
    }

}
