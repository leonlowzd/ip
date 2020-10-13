package Duke;

import Duke.commands.ByeCommand;
import Duke.commands.Command;
import Duke.data.Storage;
import Duke.data.TaskList;
import Duke.ui.TextUi;

/**
 * Duke is a Personal Assistant Chat bot that keep tracks of schedule.
 *
 * @author Low Zhen Dong Leon
 * @version 1.0
 * @since 2020-09-30
 */
public class Duke {
    //retrieve home directory & set homeDirectory
    private final static String HOME_DIRECTORY = System.getProperty("user.home");
    private final static String STORE_DIRECTORY = HOME_DIRECTORY + "/Documents/log.txt";

    private static TextUi ui = new TextUi();
    private static TaskList taskList = new TaskList();
    private static Storage storage = new Storage(taskList);

    /**
     * Runs program until exit command
     */
    public static void main(String[] args) {
        Duke.run();
    }

    private static void run() {
        start();
        runCommandLoop();
        exit();
    }

    private static void start() {
        ui.printWelcomeMessage();
        storage.readFile(STORE_DIRECTORY);
    }

    private static void runCommandLoop() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = Parser.parseCommand(userCommandText);
            command.setData(taskList);
            command.run();
            storage.writeFile(STORE_DIRECTORY);
        } while (!ByeCommand.isExit(command));
    }

    private static void exit() {
        ui.printGoodbyeMessage();
        System.exit(0);
    }

}