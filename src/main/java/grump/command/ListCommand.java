package grump.command;

import grump.storage.Storage;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public CommandResult execute(TaskList tasks, GuiResponseHandler guiResponseHandler,
            Storage storage) {
        String responseString = guiResponseHandler.returnTasks(tasks);
        return new CommandResult(false, responseString);
    }
}
