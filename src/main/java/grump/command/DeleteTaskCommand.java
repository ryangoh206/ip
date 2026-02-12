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
    public static final String MESSAGE_INVALID_TASK_NUMBER =
            "The task number you provided is invalid.";
    public static final String MESSAGE_MISSING_TASK_NUMBER =
            "Please provide the task number to delete.";

    private final String userInput;

    public DeleteTaskCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(TaskList tasks, GuiResponseHandler guiResponseHandler,
            Storage storage) {
        try {
            String[] parts = userInput.split(" ");
            if (parts.length < 2) {
                throw new MissingArgException(MESSAGE_MISSING_TASK_NUMBER);
            }
            int taskNum = Integer.parseInt(parts[1]) - 1;
            Task oldTask = tasks.getTask(taskNum);
            tasks.removeTask(taskNum);
            String responseString = guiResponseHandler.returnDeletedTask(oldTask, tasks.size());
            storage.save(tasks);
            return new CommandResult(false, responseString);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidArgException(MESSAGE_INVALID_TASK_NUMBER);
        }
    }
}
