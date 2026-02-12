package grump.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents an Event type task with a start and end datetime.
 */
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an Event task.
     *
     * @param description Description of the event.
     * @param start Start datetime of the event.
     * @param end End datetime of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an Event task with specified completion status.
     *
     * @param description Description of the event.
     * @param isDone Whether the event is done.
     * @param start Start datetime of the event.
     * @param end End datetime of the event.
     */
    public Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an Event task with specified tags and completion status.
     *
     * @param description Description of the event.
     * @param tags List of tags associated with the event.
     * @param isDone Whether the event is done.
     * @param start Start datetime of the event.
     * @param end End datetime of the event.
     */
    public Event(String description, ArrayList<String> tags, boolean isDone, LocalDateTime start,
            LocalDateTime end) {
        super(description, tags, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toCsvString() {
        return "E," + description + "," + (isDone ? "1" : "0") + "," + start + "," + end + ","
                + tags.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + start.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + " to: "
                + end.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }
}
