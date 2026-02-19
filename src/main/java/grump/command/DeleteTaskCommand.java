package grump.command;

import grump.storage.Storage;
import grump.task.Task;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;
import grump.util.CommandValidator;

/**
 * Represents a command to delete a task.
 */
public class DeleteTaskCommand extends Command {
    private final String userInput;

    public DeleteTaskCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(TaskList tasks, GuiResponseHandler guiResponseHandler,
            Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert guiResponseHandler != null : "GuiResponseHandler cannot be null";
        assert storage != null : "Storage cannot be null";

        int taskIndex = CommandValidator.validateTaskIndex(userInput, tasks.size());
        Task oldTask = tasks.getTask(taskIndex);
        int initialSize = tasks.size();
        tasks.removeTask(taskIndex);
        assert tasks.size() == initialSize
                - 1 : "Task list size should decrease by 1 after deletion";

        String responseString = guiResponseHandler.returnDeletedTask(oldTask, tasks.size());
        storage.save(tasks);
        return new CommandResult(false, responseString);
    }
}
