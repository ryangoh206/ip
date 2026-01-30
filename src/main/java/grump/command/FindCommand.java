package grump.command;

import grump.exception.MissingArgException;
import grump.storage.Storage;
import grump.task.TaskList;
import grump.ui.Ui;

/*
 * Represents a command to find tasks.
 */
public class FindCommand extends Command {
    private final String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        String parts[] = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException("Please provide a description to find tasks.");
        }
        ui.printTasks(new TaskList(tasks.findTasks(parts[1].trim())));
        return false;
    }

}
