package Duke;

import Duke.commands.*;
import Duke.exceptions.IllegalDate;
import Duke.exceptions.IllegalDescription;
import Duke.exceptions.IllegalIndex;

import static Duke.common.Messages.*;
import static Duke.common.TaskNames.DEADLINE_DATE_IDENTIFIER;
import static Duke.common.TaskNames.EVENT_DATE_IDENTIFIER;

public class Parser {
    public Parser() {
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
            return new InvalidCommand(MESSAGE_INVALID_COMMAND_ERROR);
        }
    }

    private static String getDescription(String typeOfTask, String arguments) {
        String description;
        if (typeOfTask.equals(AddTask.COMMAND_WORD_TODO)) description = arguments;
        else description = arguments.substring(0, arguments.lastIndexOf("/")).trim();
        return description;
    }

    private static String getDate(String arguments, String eventDateIdentifier) throws IllegalDate {
        String date;
        System.out.println(EVENT_DATE_IDENTIFIER);
        date = arguments.substring(arguments.indexOf(eventDateIdentifier));
        date = date.replace(eventDateIdentifier, "").trim();
        if (date.isEmpty()) throw new IllegalDate();
        return date;
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
            if (selectedIndex < 0) throw new IllegalIndex();
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
                date = getDate(arguments, DEADLINE_DATE_IDENTIFIER);

            } else if (commandWord.equals(AddTask.COMMAND_WORD_EVENT)) {
                date = getDate(arguments, EVENT_DATE_IDENTIFIER);

            } else {
                date = null;
            }
            description = getDescription(commandWord, arguments);
            if (description.isEmpty()) throw new IllegalDescription();
            return new AddTask(commandWord, description, date, true);

        } catch (IllegalDate | StringIndexOutOfBoundsException e) {
            return new InvalidCommand(MESSAGE_DATE_ERROR);

        } catch (IllegalDescription e) {
            return new InvalidCommand(MESSAGE_DESCRIPTION_ERROR);

        }
    }

}