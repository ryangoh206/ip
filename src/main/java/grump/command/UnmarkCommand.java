package grump.command;

import grump.storage.Storage;
import grump.task.Task;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;
import grump.util.CommandValidator;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private final String userInput;

    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(TaskList tasks, GuiResponseHandler guiResponseHandler,
            Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert guiResponseHandler != null : "GuiResponseHandler cannot be null";
        assert storage != null : "Storage cannot be null";

        int taskIndex = CommandValidator.validateTaskIndex(userInput, tasks.size());
        Task task = tasks.getTask(taskIndex);
        task.markAsNotDone();
        assert !task.isDone() : "Task should be marked as not done";

        String responseString = guiResponseHandler.returnUnmarkTaskMessage(task);
        storage.save(tasks);
        return new CommandResult(false, responseString);
    }

}
