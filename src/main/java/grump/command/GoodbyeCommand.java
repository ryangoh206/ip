package grump.command;

import grump.task.TaskList;
import grump.storage.Storage;
import grump.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class GoodbyeCommand extends Command {

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbyeMessage();
        return true; // Indicate that the application should exit
    }

}
