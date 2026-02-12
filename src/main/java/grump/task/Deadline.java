package grump.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
        return "D," + description + "," + (isDone ? "1" : "0") + "," + by + "," + tags.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }
}
