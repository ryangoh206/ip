package grump.command;

import grump.storage.Storage;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    public abstract CommandResult execute(TaskList tasks, GuiResponseHandler guiResponseHandler,
            Storage storage);

}
