package grump.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import grump.enums.TaskType;

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
        String doneFlag = isDone ? CSV_DONE_FLAG : CSV_NOT_DONE_FLAG;
        return TaskType.EVENT.getTypeIdentifier() + CSV_DELIMITER + description + CSV_DELIMITER
                + doneFlag + CSV_DELIMITER + start + CSV_DELIMITER + end + CSV_DELIMITER
                + tags.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(DISPLAY_DATE_FORMAT) + " to: "
                + end.format(DISPLAY_DATE_FORMAT) + ")";
    }
}
