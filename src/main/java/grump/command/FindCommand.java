package grump.command;

import grump.enums.CommandType;
import grump.storage.Storage;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;
import grump.util.CommandValidator;

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
        String keyword = CommandValidator.validateHasArguments(userInput);
        String responseString =
                guiResponseHandler.returnTasks(new TaskList(tasks.findTasks(keyword)));
        return new CommandResult(false, responseString, CommandType.FIND);
    }

}
