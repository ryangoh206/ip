package grump;

import grump.command.Command;
import grump.exception.InvalidArgException;
import grump.exception.InvalidCommandException;
import grump.exception.MissingArgException;
import grump.parser.Parser;
import grump.storage.Storage;
import grump.task.TaskList;
import grump.ui.Ui;

/**
 * Entry point of the Grump chatbot application. Initializes the application and starts the
 * interaction with the user.
 */
public class Grump {
    /** Ui for printing results to the user and getting input from user */
    private final Ui ui;
    /** Storage for saving and loading tasks from a specified filepath */
    private final Storage storage;
    /** TaskList for storing tasks in memory during runtime */
    private final TaskList tasks;

    /**
     * Constructs a Grump application with the specified file path for task storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Grump(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the main program loop, reading user commands and executing them until exit command is
     * given.
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            ui.printLine();
            ui.printNewline();
            String userInput = ui.readCommand();
            ui.printLine();

            try {
                Command command = Parser.parseCommand(userInput);
                isExit = command.execute(tasks, ui, storage);
            } catch (MissingArgException | InvalidCommandException | InvalidArgException e) {
                ui.printErrorMessage(e.getMessage());
                continue;
            }
        }

    }

    public static void main(String[] args) {
        new Grump("data/tasks.csv").run();
    }

}
