package grump.command;

import grump.exception.MissingArgException;
import grump.storage.Storage;
import grump.task.TaskList;
import grump.task.ToDo;
import grump.ui.Ui;

public class ToDoCommand extends Command {
    private final String userInput;

    public ToDoCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        String parts[] = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException("Please provide a description for the todo task.");
        }
        tasks.addTask(new ToDo(parts[1].trim()));
        ui.printAddedTask(tasks.getTask(tasks.size() - 1), tasks.size());
        storage.save(tasks);
        return false;
    }

}
