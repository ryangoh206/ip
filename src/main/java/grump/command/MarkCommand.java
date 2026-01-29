package grump.command;

import grump.exception.InvalidArgException;
import grump.exception.MissingArgException;
import grump.task.Task;
import grump.task.TaskList;
import grump.storage.Storage;
import grump.ui.Ui;

public class MarkCommand extends Command {
    private final String userInput;

    public MarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String parts[] = this.userInput.split(" ");
            if (parts.length < 2) {
                throw new MissingArgException("Please provide the task number to mark as done.");
            }
            int taskNum = Integer.parseInt(parts[1]) - 1;
            Task task = tasks.getTask(taskNum);
            task.markAsDone();
            ui.printMarkTaskMessage(task);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidArgException("The task number you provided is invalid.");
        }
        return false;
    }

}
