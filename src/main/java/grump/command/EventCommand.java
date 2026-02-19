package grump.command;

import java.time.LocalDateTime;

import grump.constants.CommandMessages;
import grump.parser.Parser;
import grump.storage.Storage;
import grump.task.Event;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;
import grump.util.CommandValidator;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {
    private static final String FROM_SEPARATOR = "/from";
    private static final String TO_SEPARATOR = "/to";

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

        String arguments = CommandValidator.validateHasArguments(userInput);
        String[] eventParts = arguments.split(FROM_SEPARATOR, 2);
        CommandValidator.validateCommandParts(eventParts, 2, 0,
                CommandMessages.MISSING_DESCRIPTION);
        CommandValidator.validateCommandParts(eventParts, 2, 1, CommandMessages.MISSING_FROM_TO);

        String description = eventParts[0].trim();
        String[] timeParts = eventParts[1].split(TO_SEPARATOR, 2);
        CommandValidator.validateCommandParts(timeParts, 2, 0, CommandMessages.MISSING_FROM_DATE);
        CommandValidator.validateCommandParts(timeParts, 2, 1, CommandMessages.MISSING_TO_DATE);

        LocalDateTime start = Parser.parseStringToDateTime(timeParts[0].trim());
        LocalDateTime end = Parser.parseStringToDateTime(timeParts[1].trim());

        tasks.addTask(new Event(description, start, end));
        String responseString =
                guiResponseHandler.returnAddedTask(tasks.getTask(tasks.size() - 1), tasks.size());
        storage.save(tasks);
        return new CommandResult(false, responseString);
    }

}
