import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class Parser {
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

    public static LocalDateTime parseStringToDateTime(String dateTimeStr) {
        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy")
                    .optionalStart().appendPattern(" HH:mm").optionalEnd()
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0).toFormatter();

            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new MissingArgException(
                    "Please provide date and time in the format dd-MM-yyyy HH:mm or dd-MM-yyyy.");
        }
    }

}
