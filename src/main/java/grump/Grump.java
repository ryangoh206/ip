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

/**
 * Entry point of the Grump chatbot application. Initializes the application and starts the
 * interaction with the user.
 */
public class Grump {
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
        guiResponseHandler = new GuiResponseHandler();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

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

}
