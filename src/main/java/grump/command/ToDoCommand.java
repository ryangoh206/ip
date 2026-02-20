package grump.command;

import grump.enums.CommandType;
import grump.storage.Storage;
import grump.task.TaskList;
import grump.task.ToDo;
import grump.ui.GuiResponseHandler;
import grump.util.CommandValidator;

/**
 * Represents a command to add a todo task.
 */
public class ToDoCommand extends Command {
    private final String userInput;

    public ToDoCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(TaskList tasks, GuiResponseHandler guiResponseHandler,
            Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert guiResponseHandler != null : "GuiResponseHandler cannot be null";
        assert storage != null : "Storage cannot be null";
        String description = CommandValidator.validateHasArguments(userInput);
        tasks.addTask(new ToDo(description));
        String responseString =
                guiResponseHandler.returnAddedTask(tasks.getTask(tasks.size() - 1), tasks.size());
        storage.save(tasks);
        return new CommandResult(false, responseString, CommandType.TODO);
    }

}
