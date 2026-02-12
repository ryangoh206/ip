package grump.command;

import grump.exception.InvalidArgException;
import grump.exception.MissingArgException;
import grump.storage.Storage;
import grump.task.Task;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;

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
        String responseString = "";
        try {
            String[] parts = userInput.split(" ", 2);
            if (parts.length < 2) {
                throw new MissingArgException("Please provide the task number to unmark as done.");
            }
            int taskNum = Integer.parseInt(parts[1]) - 1;
            Task task = tasks.getTask(taskNum);
            task.markAsNotDone();
            assert !task.isDone() : "Task should be marked as not done";
            responseString = guiResponseHandler.returnUnmarkTaskMessage(task);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidArgException("The task number you provided is invalid.");
        }
        return new CommandResult(false, responseString);
    }

}
