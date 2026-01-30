package grump.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import grump.command.Command;
import grump.command.DeadlineCommand;
import grump.command.DeleteTaskCommand;
import grump.command.EventCommand;
import grump.command.GoodbyeCommand;
import grump.command.ListCommand;
import grump.command.MarkCommand;
import grump.command.ToDoCommand;
import grump.command.UnmarkCommand;
import grump.exception.InvalidCommandException;

/**
 * Parses user input into Commands for the Grump application.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param userInput The input string from the user.
     * @return The Command object corresponding to the user input.
     * @throws InvalidCommandException If the command is not recognized.
     */
    public static Command parseCommand(String userInput) {
        String[] parts = userInput.split(" ", 2);

        switch (parts[0].toUpperCase()) {
        case "BYE":
            return new GoodbyeCommand();
        case "LIST":
            return new ListCommand();
        case "MARK":
            return new MarkCommand(userInput);
        case "UNMARK":
            return new UnmarkCommand(userInput);
        case "DELETE":
            return new DeleteTaskCommand(userInput);
        case "TODO":
            return new ToDoCommand(userInput);
        case "DEADLINE":
            return new DeadlineCommand(userInput);
        case "EVENT":
            return new EventCommand(userInput);
        default:
            throw new InvalidCommandException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param dateTimeStr The date-time string to parse. E.g. dd-MM-yyyy HH:mm
     * @return The corresponding LocalDateTime object.
     * @throws InvalidCommandException If the date-time string is in an invalid format.
     */
    public static LocalDateTime parseStringToDateTime(String dateTimeStr) {
        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy")
                    .optionalStart().appendPattern(" HH:mm").optionalEnd()
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0).toFormatter();

            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException(
                    "Please provide date and time in the format dd-MM-yyyy HH:mm or dd-MM-yyyy.");
        }
    }

}
