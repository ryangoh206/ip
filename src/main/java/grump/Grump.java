package grump;

import grump.command.Command;
import grump.command.CommandResult;
import grump.exception.InvalidArgException;
import grump.exception.InvalidCommandException;
import grump.exception.MissingArgException;
import grump.parser.Parser;
import grump.storage.Storage;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;
import grump.ui.Ui;

/**
 * Entry point of the Grump chatbot application. Initializes the application and starts the
 * interaction with the user.
 */
public class Grump {
    /** Ui for printing results to the user and getting input from user */
    private final Ui ui;
    /** GuiResponseHandler for creating and returning the responses to output to the user */
    private final GuiResponseHandler guiResponseHandler;
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
        guiResponseHandler = new GuiResponseHandler();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the main program loop, reading user commands and executing them until exit command is
     * given.
     */
    public void run() {
        ui.printWelcomeMessage();
        CommandResult commandResult;

        do {
            commandResult = processUserInput();
        } while (!commandResult.getIsExit());
    }

    /**
     * Gets the response string for a given user input to be displayed in the GUI.
     *
     * @param userInput The input command from the user.
     * @return The response string to be displayed in the GUI.
     */
    public String getResponseForGui(String userInput) {
        boolean isExit = false;
        CommandResult commandResult = new CommandResult(isExit, "");
        try {
            Command command = Parser.parseCommand(userInput);
            commandResult = command.execute(tasks, guiResponseHandler, storage);
            return commandResult.getResponseString();
        } catch (MissingArgException | InvalidCommandException | InvalidArgException e) {
            return guiResponseHandler.returnErrorMessage(e.getMessage());
        }
    }

    /**
     * Processes user input, executes the corresponding command, and returns the result.
     *
     * @return The result of executing the user's command.
     */
    private CommandResult processUserInput() {
        ui.printLine();
        ui.printNewline();
        String userInput = ui.readCommand();
        ui.printLine();

        try {
            Command command = Parser.parseCommand(userInput);
            CommandResult commandResult = command.execute(tasks, guiResponseHandler, storage);
            ui.printMessage(commandResult.getResponseString());
            return commandResult;
        } catch (MissingArgException | InvalidCommandException | InvalidArgException e) {
            ui.printErrorMessage(e.getMessage());
            return new CommandResult(false, "");
        }
    }

}
