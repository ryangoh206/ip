package grump.command;

import java.time.LocalDateTime;

import grump.exception.MissingArgException;
import grump.parser.Parser;
import grump.storage.Storage;
import grump.task.Deadline;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private final String userInput;

    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(TaskList tasks, GuiResponseHandler guiResponseHandler,
            Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert guiResponseHandler != null : "GuiResponseHandler cannot be null";
        assert storage != null : "Storage cannot be null";
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException("Please provide valid arguments for the deadline task.");
        }
        parts = parts[1].split("/by", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException(
                    "Please provide a valid by date/time for the deadline task.");
        } else if (parts[0].trim().isEmpty()) {
            throw new MissingArgException("Please provide a description for the deadline task.");
        }
        String description = parts[0].trim();
        LocalDateTime by = Parser.parseStringToDateTime(parts[1].trim());
        tasks.addTask(new Deadline(description, by));
        String responseString =
                guiResponseHandler.returnAddedTask(tasks.getTask(tasks.size() - 1), tasks.size());
        storage.save(tasks);
        return new CommandResult(false, responseString);
    }

}
