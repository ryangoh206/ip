package grump.command;

import grump.exception.InvalidArgException;
import grump.exception.MissingArgException;
import grump.storage.Storage;
import grump.task.Task;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;

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
        String responseString = "";
        try {
            String[] parts = this.userInput.split(" ");
            if (parts.length < 2) {
                throw new MissingArgException("Please provide the task number to mark as done.");
            }
            int taskNum = Integer.parseInt(parts[1]) - 1;
            Task task = tasks.getTask(taskNum);
            task.markAsDone();
            assert task.isDone() : "Task should be marked as done";
            responseString = guiResponseHandler.returnMarkTaskMessage(task);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidArgException("The task number you provided is invalid.");
        }
        return new CommandResult(false, responseString);
    }

}
