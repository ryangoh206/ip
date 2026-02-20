package grump.command;

import grump.enums.CommandType;
import grump.storage.Storage;
import grump.task.Task;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;
import grump.util.CommandValidator;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final String userInput;

    public MarkCommand(String userInput) {
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
        task.markAsDone();
        assert task.isDone() : "Task should be marked as done";

        String responseString = guiResponseHandler.returnMarkTaskMessage(task);
        storage.save(tasks);
        return new CommandResult(false, responseString, CommandType.MARK);
    }

}
