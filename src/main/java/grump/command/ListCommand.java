package grump.command;

import grump.storage.Storage;
import grump.task.TaskList;
import grump.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks);
        return false; // Indicate that the application should continue running
    }
}
