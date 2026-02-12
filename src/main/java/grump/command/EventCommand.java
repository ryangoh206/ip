package grump.command;

import java.time.LocalDateTime;

import grump.exception.MissingArgException;
import grump.parser.Parser;
import grump.storage.Storage;
import grump.task.Event;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {
    private final String userInput;

    public EventCommand(String userInput) {
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
            throw new MissingArgException("Please provide valid arguments for the event task.");
        }
        parts = parts[1].split("/from", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException(
                    "Please provide the start and end date/time for the event task.");
        } else if (parts[0].trim().isEmpty()) {
            throw new MissingArgException("Please provide a description for the event task.");
        }
        String description = parts[0].trim();
        parts = parts[1].split("/to", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException("Please provide the end date/time for the event task.");
        } else if (parts[0].trim().isEmpty()) {
            throw new MissingArgException("Please provide the start date/time for the event task.");
        }
        LocalDateTime start = Parser.parseStringToDateTime(parts[0].trim());
        LocalDateTime end = Parser.parseStringToDateTime(parts[1].trim());

        tasks.addTask(new Event(description, start, end));
        String responseString =
                guiResponseHandler.returnAddedTask(tasks.getTask(tasks.size() - 1), tasks.size());
        storage.save(tasks);
        return new CommandResult(false, responseString);
    }

}
