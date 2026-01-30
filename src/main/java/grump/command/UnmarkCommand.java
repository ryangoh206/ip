package grump.command;

import grump.exception.InvalidArgException;
import grump.exception.MissingArgException;
import grump.storage.Storage;
import grump.task.Task;
import grump.task.TaskList;
import grump.ui.Ui;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private final String userInput;

    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] parts = userInput.split(" ", 2);
            if (parts.length < 2) {
                throw new MissingArgException("Please provide the task number to unmark as done.");
            }
            int taskNum = Integer.parseInt(parts[1]) - 1;
            Task task = tasks.getTask(taskNum);
            task.markAsNotDone();
            ui.printUnmarkTaskMessage(task);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidArgException("The task number you provided is invalid.");
        }
        return false;
    }

}
