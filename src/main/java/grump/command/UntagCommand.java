package grump.command;

import grump.exception.InvalidArgException;
import grump.exception.MissingArgException;
import grump.storage.Storage;
import grump.task.Task;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;

/**
 * Represents a command to untag a task.
 */
public class UntagCommand extends Command {
    private final String userInput;

    public UntagCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(TaskList tasks, GuiResponseHandler guiResponseHandler,
            Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert guiResponseHandler != null : "GuiResponseHandler cannot be null";
        assert storage != null : "Storage cannot be null";

        try {
            String[] parts = this.userInput.split(" ", 2);
            if (parts.length < 2) {
                throw new MissingArgException("Please provide the task number to tag.");
            }
            parts = parts[1].split(" ", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new MissingArgException("Please provide the tag name for the task.");
            }

            int taskNum = Integer.parseInt(parts[0]) - 1;
            Task task = tasks.getTask(taskNum);
            String tag = parts[1].trim();
            boolean isUntagged = task.removeTag(tag);

            String responseString =
                    guiResponseHandler.returnUntaggedTaskMessage(task, tag, isUntagged);
            storage.save(tasks);
            return new CommandResult(false, responseString);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidArgException("The task number you provided is invalid.");
        }
    }

}
