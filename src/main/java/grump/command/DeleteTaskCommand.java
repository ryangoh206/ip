package grump.command;

import grump.exception.InvalidArgException;
import grump.exception.MissingArgException;
import grump.storage.Storage;
import grump.task.Task;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;

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
        String responseString = "";
        try {
            String[] parts = userInput.split(" ");
            if (parts.length < 2) {
                throw new MissingArgException("Please provide the task number to delete.");
            }
            int taskNum = Integer.parseInt(parts[1]) - 1;
            Task oldTask = tasks.getTask(taskNum);
            int initialSize = tasks.size();
            tasks.removeTask(taskNum);
            assert tasks.size() == initialSize - 1 : "Task list size should decrease by 1 after deletion";
            responseString = guiResponseHandler.returnDeletedTask(oldTask, tasks.size());
            storage.save(tasks);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidArgException("The task number you provided is invalid.");
        }
        return new CommandResult(false, responseString);
    }
}
