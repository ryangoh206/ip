package grump.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import grump.enums.TaskType;

/**
 * Represents a Deadline type task with a due datetime.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a Deadline task.
     *
     * @param description Description of the deadline.
     * @param by Due datetime of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with specified completion status.
     *
     * @param description Description of the deadline.
     * @param isDone Whether the deadline is done.
     * @param by Due datetime of the deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with specified tags and completion status.
     *
     * @param description Description of the deadline.
     * @param tags List of tags associated with the deadline.
     * @param isDone Whether the deadline is done.
     * @param by Due datetime of the deadline.
     */
    public Deadline(String description, ArrayList<String> tags, boolean isDone, LocalDateTime by) {
        super(description, tags, isDone);
        this.by = by;
    }

    @Override
    public String toCsvString() {
        String doneFlag = isDone ? CSV_DONE_FLAG : CSV_NOT_DONE_FLAG;
        return TaskType.DEADLINE.getTypeIdentifier() + CSV_DELIMITER + description + CSV_DELIMITER
                + doneFlag + CSV_DELIMITER + by + CSV_DELIMITER + tags.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_DATE_FORMAT) + ")";
    }
}
