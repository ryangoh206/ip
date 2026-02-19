package grump.task;

import java.util.ArrayList;

import grump.enums.TaskType;

/**
 * Represents a To-Do type task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public ToDo(String description, ArrayList<String> tags, boolean isDone) {
        super(description, tags, isDone);
    }

    @Override
    public String toCsvString() {
        String doneFlag = isDone ? CSV_DONE_FLAG : CSV_NOT_DONE_FLAG;
        return TaskType.TODO.getTypeIdentifier() + CSV_DELIMITER + description + CSV_DELIMITER
                + doneFlag + CSV_DELIMITER + tags.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
