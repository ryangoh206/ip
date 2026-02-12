package grump.command;

import grump.exception.MissingArgException;
import grump.storage.Storage;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;

/**
 * Represents a command to find tasks.
 */
public class FindCommand extends Command {
    private final String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(TaskList tasks, GuiResponseHandler guiResponseHandler,
            Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert guiResponseHandler != null : "GuiResponseHandler cannot be null";
        assert storage != null : "Storage cannot be null";
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException("Please provide a description to find tasks.");
        }
        String responseString =
                guiResponseHandler.returnTasks(new TaskList(tasks.findTasks(parts[1].trim())));
        return new CommandResult(false, responseString);
    }

}
