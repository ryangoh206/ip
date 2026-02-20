package grump.command;

import grump.enums.CommandType;
import grump.storage.Storage;
import grump.task.Task;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;
import grump.util.CommandValidator;
import javafx.util.Pair;

/**
 * Represents a command to tag a task.
 */
public class TagCommand extends Command {
    private final String userInput;

    public TagCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(TaskList tasks, GuiResponseHandler guiResponseHandler,
            Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert guiResponseHandler != null : "GuiResponseHandler cannot be null";
        assert storage != null : "Storage cannot be null";

        Pair<Integer, String> taskIndexAndTag =
                CommandValidator.validateTaskIndexAndTag(userInput, tasks.size());
        int taskIndex = taskIndexAndTag.getKey();
        String tag = taskIndexAndTag.getValue();

        Task task = tasks.getTask(taskIndex);
        task.addTag(tag);

        String responseString = guiResponseHandler.returnTaggedTaskMessage(task, tag);
        storage.save(tasks);
        return new CommandResult(false, responseString, CommandType.TAG);
    }

}
