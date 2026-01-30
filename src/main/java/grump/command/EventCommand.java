package grump.command;

import java.time.LocalDateTime;
import grump.exception.MissingArgException;
import grump.task.Event;
import grump.task.TaskList;
import grump.parser.Parser;
import grump.storage.Storage;
import grump.ui.Ui;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {
    private final String userInput;

    public EventCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {

        String parts[] = userInput.split(" ", 2);
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
        ui.printAddedTask(tasks.getTask(tasks.size() - 1), tasks.size());
        storage.save(tasks);
        return false;
    }

}
