package Duke;
import Duke.commands.ByeCommand;
import Duke.commands.Command;
import Duke.data.Storage;
import Duke.data.TaskList;
import Duke.ui.TextUi;


public class Duke {
    //retrieve home directory & set homeDirectory
    private final static String HOME_DIRECTORY = System.getProperty("user.home");
    private final static String STORE_DIRECTORY = HOME_DIRECTORY+"/Documents/log.txt";

    private static TextUi ui = new TextUi();
    private static TaskList taskList = new TaskList();
    private static Storage storage = new Storage(taskList);

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
        ui.showGoodbyeMessage();
        System.exit(0);
    }

}