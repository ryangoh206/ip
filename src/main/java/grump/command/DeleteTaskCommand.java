package grump.command;

import grump.exception.InvalidArgException;
import grump.exception.MissingArgException;
import grump.task.Task;
import grump.task.TaskList;
import grump.storage.Storage;
import grump.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteTaskCommand extends Command {
    private final String userInput;

    public DeleteTaskCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String parts[] = userInput.split(" ");
            if (parts.length < 2) {
                throw new MissingArgException("Please provide the task number to delete.");
            }
            int taskNum = Integer.parseInt(parts[1]) - 1;
            Task oldTask = tasks.getTask(taskNum);
            tasks.removeTask(taskNum);
            ui.printDeletedTask(oldTask, tasks.size());
            storage.save(tasks);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidArgException("The task number you provided is invalid.");
        }
        return false;
    }
}
