package grump.command;

import grump.storage.Storage;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;

/**
 * Represents a command to exit the application.
 */
public class GoodbyeCommand extends Command {

    @Override
    public CommandResult execute(TaskList tasks, GuiResponseHandler guiResponseHandler,
            Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert guiResponseHandler != null : "GuiResponseHandler cannot be null";
        assert storage != null : "Storage cannot be null";
        String responseString = guiResponseHandler.returnGoodbyeMessage();
        return new CommandResult(true, responseString); // Indicate that the application should exit
    }

}
