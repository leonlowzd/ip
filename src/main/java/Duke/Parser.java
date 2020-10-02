package Duke;

import Duke.commands.AddTaskCommand;
import Duke.commands.DoneCommand;
import Duke.commands.DeleteCommand;
import Duke.commands.ByeCommand;
import Duke.commands.ListCommand;
import Duke.commands.FindCommand;
import Duke.commands.InvalidCommand;
import Duke.commands.Command;
import Duke.exceptions.IllegalDate;
import Duke.exceptions.IllegalDescription;
import Duke.exceptions.IllegalIndex;

import static Duke.UserInputExtractor.getDate;
import static Duke.UserInputExtractor.getDescription;
import static Duke.UserInputExtractor.getIndexFromString;

import static Duke.common.Messages.MESSAGE_DATE_ERROR;
import static Duke.common.Messages.MESSAGE_DATE_EMPTY;
import static Duke.common.Messages.MESSAGE_DESCRIPTION_ERROR;
import static Duke.common.Messages.MESSAGE_INVALID_COMMAND_ERROR;
import static Duke.common.Messages.MESSAGE_INDEX_ERROR;
import static Duke.common.TaskNames.DEADLINE_DATE_IDENTIFIER;
import static Duke.common.TaskNames.EVENT_DATE_IDENTIFIER;

/**
 * Parses User Input into command
 */
public class Parser {
    public Parser() {
    }

    /**
     * Parses user input into taskCategory for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public static Command parseCommand(String userInput) {
        String[] words = userInput.trim().split(" ", 2);
        final String commandWord = words[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord) {
        case AddTaskCommand.COMMAND_WORD_TODO:
        case AddTaskCommand.COMMAND_WORD_DEADLINE:
        case AddTaskCommand.COMMAND_WORD_EVENT:
            return prepareAddCommand(arguments, commandWord);
        case DoneCommand.COMMAND_WORD:
            return prepareDoneCommand(arguments);
        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteCommand(arguments);
        case ByeCommand.COMMAND_WORD:
            return prepareByeCommand();
        case ListCommand.COMMAND_WORD:
            return prepareListCommand();
        case FindCommand.COMMAND_WORD:
            return prepareFindCommand(arguments);
        default:
            return new InvalidCommand(MESSAGE_INVALID_COMMAND_ERROR);
        }
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
            if (commandWord.equals(AddTaskCommand.COMMAND_WORD_DEADLINE)) {
                date = getDate(arguments, DEADLINE_DATE_IDENTIFIER);

            } else if (commandWord.equals(AddTaskCommand.COMMAND_WORD_EVENT)) {
                date = getDate(arguments, EVENT_DATE_IDENTIFIER);

            } else {
                date = null;
            }
            description = getDescription(commandWord, arguments);
            if (description.isEmpty()) throw new IllegalDescription();
            return new AddTaskCommand(commandWord, description, date, true);

        } catch (IllegalDate e) {
            return new InvalidCommand(MESSAGE_DATE_EMPTY);

        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(MESSAGE_DATE_ERROR);

        } catch (IllegalDescription e) {
            return new InvalidCommand(MESSAGE_DESCRIPTION_ERROR);

        }
    }

    private static Command prepareFindCommand(String arguments) {
        return new FindCommand(arguments);
    }

}
