package grump.command;

import java.time.LocalDateTime;

import grump.constants.CommandMessages;
import grump.enums.CommandType;
import grump.parser.Parser;
import grump.storage.Storage;
import grump.task.Deadline;
import grump.task.TaskList;
import grump.ui.GuiResponseHandler;
import grump.util.CommandValidator;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private static final String BY_SEPARATOR = "/by";

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

        String arguments = CommandValidator.validateHasArguments(userInput);
        String[] deadlineParts = arguments.split(BY_SEPARATOR, 2);
        CommandValidator.validateCommandParts(deadlineParts, 2, 0,
                CommandMessages.MISSING_DESCRIPTION);
        CommandValidator.validateCommandParts(deadlineParts, 2, 1, CommandMessages.MISSING_BY_DATE);

        String description = deadlineParts[0].trim();
        LocalDateTime by = Parser.parseStringToDateTime(deadlineParts[1].trim());
        tasks.addTask(new Deadline(description, by));
        String responseString =
                guiResponseHandler.returnAddedTask(tasks.getTask(tasks.size() - 1), tasks.size());
        storage.save(tasks);
        return new CommandResult(false, responseString, CommandType.DEADLINE);
    }

}
