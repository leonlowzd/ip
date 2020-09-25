package Duke;

import Duke.commands.*;
import Duke.data.TaskList;
import Duke.exceptions.IllegalDate;
import Duke.exceptions.IllegalDescription;
import Duke.exceptions.IllegalIndex;

import static Duke.common.Messages.*;

public class Parser {

    public Parser(TaskList tasks, String userInput) {
    }

    public static Command parseCommand(String userInput) {
        String[] words = userInput.trim().split(" ", 2);
        final String commandWord = words[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord) {
        case AddTask.COMMAND_WORD_TODO:
        case AddTask.COMMAND_WORD_DEADLINE:
        case AddTask.COMMAND_WORD_EVENT:
            return prepareAddCommand(arguments, commandWord);
        case DoneCommand.COMMAND_WORD:
            return prepareDoneCommand(arguments);
        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteCommand(arguments);
        case ByeCommand.COMMAND_WORD:
            return prepareByeCommand();
        case ListCommand.COMMAND_WORD:
            return prepareListCommand();
        default:
            return new InvalidCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
    private static int getIndexFromString(String arguments) {
        int index;
        try {
            index = Integer.parseInt(arguments.trim()) - 1;
        } catch (RuntimeException e) {
            index = -1;
        }
        return index;
    }

    private static Command prepareDeleteCommand(String arguments) {
        try {
            int selectedIndex = getIndexFromString(arguments);
            if (selectedIndex < 0 ) throw new IllegalIndex();
            return new DeleteCommand(selectedIndex);

        } catch (IllegalIndex e) {
            return new InvalidCommand(MESSAGE_INDEX_ERROR);

        }
    }

    private static Command prepareDoneCommand(String arguments) {
        try {
            int selectedIndex = getIndexFromString(arguments);
            if (selectedIndex < 0) throw new IllegalIndex();
            return new DoneCommand(selectedIndex);

        } catch (IllegalIndex e) {
            return new InvalidCommand(MESSAGE_INDEX_ERROR);
        }
    }

    private static Command prepareListCommand() {
        return new ListCommand();
    }

    private static Command prepareByeCommand() {
        return new ByeCommand();
    }

    private static Command prepareAddCommand(String arguments, String commandWord) {
        String description;
        String date;
        try {

            if (commandWord.equals(AddTask.COMMAND_WORD_DEADLINE)) {
                date = arguments.substring(arguments.indexOf("/by"));
                date = date.replace("/by","").trim();
                description = arguments.substring(0, arguments.lastIndexOf("/")).trim();
                if (date.isEmpty()) throw new IllegalDate();

            } else if (commandWord.equals(AddTask.COMMAND_WORD_EVENT)) {
                date = arguments.substring(arguments.indexOf("/at"));
                date = date.replace("/at","").trim();
                description = arguments.substring(0, arguments.lastIndexOf("/")).trim();
                if (date.isEmpty()) throw new IllegalDate();

            } else {
                date = null;
                description = arguments;
            }
            if (description.isEmpty()) throw new IllegalDescription();
            return new AddTask(commandWord, description, date,true);

        } catch (IllegalDate | StringIndexOutOfBoundsException e) {
            return new InvalidCommand(MESSAGE_DATE_ERROR);

        } catch (IllegalDescription e) {
            return new InvalidCommand(MESSAGE_DESCRIPTION_ERROR);

        }
    }

}
