package grump.command;

import grump.storage.Storage;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    /**
     * Executes this command using the provided task list, GUI response handler, and storage.
     *
     * @param tasks The current task list.
     * @param guiResponseHandler The handler used to generate response strings.
     * @param storage The storage used to persist task data.
     * @return A CommandResult containing the response string and exit status.
     */
    public abstract CommandResult execute(TaskList tasks, GuiResponseHandler guiResponseHandler,
            Storage storage);

}
