package grump.task;

import java.util.ArrayList;

import grump.enums.TaskType;

/**
 * Represents a To-Do type task.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description Description of the to-do.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a ToDo task with the given description and completion status.
     *
     * @param description Description of the to-do.
     * @param isDone Whether the to-do is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Constructs a ToDo task with the given description, tags, and completion status.
     *
     * @param description Description of the to-do.
     * @param tags List of tags associated with the to-do.
     * @param isDone Whether the to-do is done.
     */
    public ToDo(String description, ArrayList<String> tags, boolean isDone) {
        super(description, tags, isDone);
    }

    /**
     * Returns a CSV-formatted string representation of this to-do task.
     *
     * @return A CSV string representing this task.
     */
    @Override
    public String toCsvString() {
        String doneFlag = isDone ? CSV_DONE_FLAG : CSV_NOT_DONE_FLAG;
        return TaskType.TODO.getTypeIdentifier() + CSV_DELIMITER + description + CSV_DELIMITER
                + doneFlag + CSV_DELIMITER + tags.toString();
    }

    /**
     * Returns a string representation of this to-do task.
     *
     * @return A string in the format "[T][statusIcon] description (Tags: [...])"
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
